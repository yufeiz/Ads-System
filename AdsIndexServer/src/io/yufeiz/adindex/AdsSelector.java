package io.yufeiz.adindex;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;
import net.spy.memcached.MemcachedClient;

public class AdsSelector {
	private static AdsSelector instance = null;
	//private int EXP = 7200;
	private int numDocs = 10040;
	private String mMemcachedServer;
	private int mMemcachedPortal;
	private int mTFMemcachedPortal;
	private int mDFMemcachedPortal;
	private int mClickFeaturePortal;
	private String  mysql_host;
	private String mysql_db;
	private String mysql_user;
	private String mysql_pass;
	private String m_logistic_reg_model_file;
	private String m_gbdt_model_path;
	private Boolean enableTFIDF;
	MemcachedClient cache;
	MemcachedClient tfCacheClient;
	MemcachedClient dfCacheClient;
	MemcachedClient clickFeatureCacheClient;
	private AdsSelector(String memcachedServer,int memcachedPortal,int tfMemcachedPortal, int dfMemcachedPortal, int clickMemcachedPortal, String logistic_reg_model_file, String gdbt_model_path, String mysqlHost,String mysqlDb,String user,String pass)
	{
		mMemcachedServer = memcachedServer;
		mMemcachedPortal = memcachedPortal;
		mTFMemcachedPortal = tfMemcachedPortal;
		mDFMemcachedPortal = dfMemcachedPortal;
		mClickFeaturePortal = clickMemcachedPortal;
		m_logistic_reg_model_file = logistic_reg_model_file;
		m_gbdt_model_path = gdbt_model_path;
		mysql_host = mysqlHost;
		mysql_db = mysqlDb;	
		mysql_user = user;
		mysql_pass = pass;
		String address = mMemcachedServer + ":" + mMemcachedPortal;
		try {
			cache = new MemcachedClient(new ConnectionFactoryBuilder().setDaemon(true).setFailureMode(FailureMode.Retry).build(), AddrUtil.getAddresses(address));
		} catch (IOException e) {
			e.printStackTrace();
		}
		enableTFIDF = true;
		String tf_address = mMemcachedServer + ":" + mTFMemcachedPortal;
		String df_address = mMemcachedServer + ":" + mDFMemcachedPortal;
		String click_address = mMemcachedServer + ":" + mClickFeaturePortal;
	}
	public static AdsSelector getInstance(String memcachedServer,int memcachedPortal,int tfMemcachedPortal, int dfMemcachedPortal, int clickMemcachedPortal, String logistic_reg_model_file, String gdbt_model_path, String mysqlHost,String mysqlDb,String user,String pass) {
	      if(instance == null) {
	         instance = new AdsSelector(memcachedServer, memcachedPortal, tfMemcachedPortal, dfMemcachedPortal, clickMemcachedPortal, logistic_reg_model_file, gdbt_model_path,  mysqlHost,mysqlDb,user,pass);
	      }
	      return instance;
    }
	public List<Ad> selectAds(Query query, String deviceId, String deviceIp) {
		List<Ad> adList = new ArrayList<>();
		HashMap<Long, Integer> matchedAds = new HashMap<Long, Integer>();
		try {
			for(String queryTerm : query.getTermList()) {
				System.out.println("selectAds queryTerm = " + queryTerm);
				@SuppressWarnings("unchecked")
				Set<Long>  adIdList = (Set<Long>)cache.get(queryTerm);
				if(adIdList != null && adIdList.size() > 0) {
					for(Object adId : adIdList) {
						Long key = (Long) adId;
						if(matchedAds.containsKey(key)) {
							int count = matchedAds.get(key) + 1;
							matchedAds.put(key, count);
						}
						else {
							matchedAds.put(key, 1);
						}
					}
				}
			}
			for(Long adId : matchedAds.keySet()) {
				System.out.print("selectAds adId = " + adId);
				MySQLAccess mysql = new MySQLAccess(mysql_host, mysql_db, mysql_user, mysql_pass);
				Ad.Builder ad = mysql.getAdData(adId);
				double relevanceScore = (double) (matchedAds.get(adId) * 1.0 / ad.getKeyWordsList().size());
				ad.setRelevanceScore(relevanceScore);
				System.out.println("selectAds pClick = " + ad.getPClick());
				System.out.println("selectAds relevanceScore = " + ad.getRelevanceScore());
				adList.add(ad.build());
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adList;
	}
}
