package io.yufeiz.adindex;

import io.grpc.stub.StreamObserver;

import java.util.List;

public class AdsIndexServerImpl extends AdsIndexGrpc.AdsIndexImplBase {
    private String mMemcachedServer;
    private int mMemcachedPortal;
    private int mTFMemcachedPortal;
    private int mDFMemcachedPortal;
    private int mClickFeaturePortal;
    private String m_logistic_reg_model_file;
    private String m_gbdt_model_path;
    private String mysql_host;
    private String mysql_db;
    private String mysql_user;
    private String mysql_pass;

    public AdsIndexServerImpl(String _memcachedServer,
                     int _memcachedPortal,
                     int _tfMemcachedPortal,
                     int _dfMemcachedPortal,
                     int _clickMemcachedPortal,
                     String _logistic_reg_model_file,
                     String _gbdt_model_path,
                     String _mysql_host,
                     String _mysql_db, String _mysql_user,
                     String _mysql_pass) {
        mMemcachedServer = _memcachedServer;
        mMemcachedPortal = _memcachedPortal;
        mTFMemcachedPortal = _tfMemcachedPortal;
        mDFMemcachedPortal = _dfMemcachedPortal;
        mClickFeaturePortal = _clickMemcachedPortal;
        m_logistic_reg_model_file = _logistic_reg_model_file;
        m_gbdt_model_path = _gbdt_model_path;
        mysql_host = _mysql_host;
        mysql_db = _mysql_db;
        mysql_pass = _mysql_pass;
        mysql_user = _mysql_user;
    }
    @Override
    public void getAds(AdsRequest request, StreamObserver<AdsReply> responseObserver) {
        System.out.println("received requests number of query:" + request.getQueryCount());
        for(int i = 0; i < request.getQueryCount(); i++) {
            Query query = request.getQuery(i);
            List<Ad> adsCandidates = AdsSelector.getInstance(mMemcachedServer, mMemcachedPortal, mTFMemcachedPortal, mDFMemcachedPortal, mClickFeaturePortal, m_logistic_reg_model_file, m_gbdt_model_path, mysql_host, mysql_db, mysql_user, mysql_pass).
                    selectAds(query, request.getDeviceId(), request.getDeviceIp());
            AdsReply.Builder replyBuilder = AdsReply.newBuilder();
            for(Ad ad : adsCandidates) {
                if (ad.getRelevanceScore() > 0.07 && ad.getPClick() >= 0.0) {
                    replyBuilder.addAd(ad);
                }
            }
            AdsReply reply = replyBuilder.build();
            responseObserver.onNext(reply);
        }
        responseObserver.onCompleted();
    }
}
