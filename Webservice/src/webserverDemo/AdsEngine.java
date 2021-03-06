package webserverDemo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import io.yufeiz.adindex.AdsIndexClientWorker;
import io.yufeiz.adindex.AdsSelectionResult;
import io.yufeiz.adindex.Query;

public class AdsEngine {
	private String mAdsDataFilePath1;
	private String mAdsDataFilePath2;
	private String mBudgetFilePath;
	private IndexBuilder indexBuilder1;
	private IndexBuilder indexBuilder2;
	private String mMemcachedServer;
	private int mMemcachedPortal1;
	private int mMemcachedPortal2;
	private String mysql_host;
	private String mysql_db;
	private String mysql_user;
	private String mysql_pass;
	private int indexServerTimeout;
	
	public AdsEngine(String adsDataFilePath1, String adsDataFilePath2, String budgetDataFilePath,String memcachedServer,int memcachedPortal1,int memcachedPortal2,String mysqlHost,String mysqlDb,String user,String pass) {
		mAdsDataFilePath1 = adsDataFilePath1;
		mAdsDataFilePath2 = adsDataFilePath2;
		mBudgetFilePath = budgetDataFilePath;
		mMemcachedServer = memcachedServer;
		mMemcachedPortal1 = memcachedPortal1;
		mMemcachedPortal2 = memcachedPortal2;
		mysql_host = mysqlHost;
		mysql_db = mysqlDb;	
		mysql_user = user;
		mysql_pass = pass;	
		indexServerTimeout = 50;
		indexBuilder1 = new IndexBuilder(mMemcachedServer,mMemcachedPortal1,mysql_host,mysql_db,mysql_user,mysql_pass);
		indexBuilder2 = new IndexBuilder(mMemcachedServer,mMemcachedPortal2,mysql_host,mysql_db,mysql_user,mysql_pass);
	}
	
	public boolean writeIntoMemcached(IndexBuilder builder, String filePathName) {
		try(BufferedReader brAd = new BufferedReader(new FileReader(filePathName))) {
			String line;
			while((line = brAd.readLine()) != null) {
				JSONObject adJson = new JSONObject(line);
				Ad ad = new Ad();
				if(adJson.isNull("adId") || adJson.isNull("campaignId")) {
					continue;
				}
				ad.adId = adJson.getLong("adId");
				ad.campaignId = adJson.getLong("campaignId");
				ad.brand = adJson.isNull("brand") ? "" : adJson.getString("brand");
				ad.price = adJson.isNull("price") ? 100.00 : adJson.getDouble("price");
				ad.thumbnail = adJson.isNull("thumbnail") ? "" : adJson.getString("thumbnail");
				ad.title = adJson.isNull("title") ? "" : adJson.getString("title");
				ad.detail_url = adJson.isNull("detail_url") ? "" : adJson.getString("detail_url");
				ad.bidPrice = adJson.isNull("bidPrice") ? 1.0 : adJson.getDouble("bidPrice");
				ad.pClick = adJson.isNull("pClick") ? 0.0 : adJson.getDouble("pClick");
				ad.category = adJson.isNull("category") ? "" : adJson.getString("category");
				ad.description = adJson.isNull("description") ? "" : adJson.getString("description");
				ad.keyWords = new ArrayList<String>();
				JSONArray keyWords = adJson.isNull("keyWords") ? null : adJson.getJSONArray("keyWords");
				for(int j = 0; j < keyWords.length(); j++) {
					ad.keyWords.add(keyWords.getString(j));
				}
				builder.buildInvertIndex(ad);
//				indexBuilder.buildForwardIndex(ad);
//				if(!indexBuilder.buildInvertIndex(ad) || !indexBuilder.buildForwardIndex(ad)) {
//					
//				}
				
			} 
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try(BufferedReader brBudget = new BufferedReader(new FileReader(mBudgetFilePath))) {
			String line;
			while((line = brBudget.readLine()) != null) {
				JSONObject campaignJson = new JSONObject(line);
				Long campaignId = campaignJson.getLong("campaignId");
				double budget = campaignJson.getDouble("budget");
				Campaign camp = new Campaign();
				camp.campaignId = campaignId;
				camp.budget = budget;
				if(!builder.updateBudget(camp)) {
					
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		builder.Close();
		return true;
	}
	
	public Boolean init() {
//		return true;
		return writeIntoMemcached(indexBuilder1, mAdsDataFilePath1) && writeIntoMemcached(indexBuilder2, mAdsDataFilePath2);
	}
	private AdsSelectionResult getAdsFromIndexServer(List<String> queryTerms) {
		AdsSelectionResult adsResult = new AdsSelectionResult();
		io.yufeiz.adindex.Query.Builder _query = io.yufeiz.adindex.Query.newBuilder();
		for(int i = 0; i < queryTerms.size(); i++) {
			_query.addTerm(queryTerms.get(i));
		}
		System.out.println("term count= " + _query.getTermCount());
		List<Query> queryList = new ArrayList<Query>();
		queryList.add(_query.build());
		AdsIndexClientWorker adsIndexClient1 = new AdsIndexClientWorker(queryList, "127.0.0.1", 50051, "", "", adsResult);
		AdsIndexClientWorker adsIndexClient2 = new AdsIndexClientWorker(queryList, "127.0.0.1", 50052, "", "", adsResult);
		adsIndexClient1.start();
		adsIndexClient2.start();
		try {
			adsIndexClient1.join(indexServerTimeout);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		try {
			adsIndexClient2.join(indexServerTimeout);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		return adsResult;
	}
	public List<Ad> selectAds(String query, String device_id, String device_ip) {
		List<Ad> adsCondidates = new ArrayList<Ad>();
		List<String> queryTerms = QueryParser.getInstance().QueryUnderstand(query);
		Set<Long> uniqueAds = new HashSet<Long>();
		AdsSelectionResult adsResult = getAdsFromIndexServer(queryTerms);
		for(io.yufeiz.adindex.Ad _ad : adsResult.getAdsList()) {
			System.out.println("relevance score = " + _ad.getRelevanceScore());
			if(!uniqueAds.contains(_ad.getAdId())) {
				Ad ad = new Ad();
				ad.CloneAd(_ad);
				System.out.println("relevance score = " + ad.relevanceScore);
				adsCondidates.add(ad);
			}
		}
//		List<Ad> adsCandidates = AdsSelector.getInstance(mMemcachedServer, mMemcachedPortal,mysql_host, mysql_db,mysql_user, mysql_pass).selectAds(queryTerms);
//		List<Ad> L0unfilteredAds = AdsFilter.getInstance().LevelZeroFilterAds(adsCandidates);
//		int k = 20;
//		List<Ad> unfilteredAds = AdsFilter.getInstance().LevelOneFilterAds(L0unfilteredAds, k);
//		List<Ad> dedupedAds = AdsCampaignManager.getInstance(mysql_host, mysql_db,mysql_user, mysql_pass).DedupeByCampaignId(unfilteredAds);
//		List<Ad> ads = AdsCampaignManager.getInstance(mysql_host, mysql_db,mysql_user, mysql_pass).ApplyBudget(dedupedAds);
		return adsCondidates;
	}
}
