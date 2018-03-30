package io.yufeiz.adindex;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

public class AdsServer {
    private static final Logger logger = Logger.getLogger(AdsServer.class.getName());
    private Server server;
    private int port;
    private String mMemcachedServer;
    private int mMemcachedPortal;
    private int mTFMemcachedPortal;
    private int mDFMemcachedPortal;
    private int mClickFeaturePortal;

    private String mysql_host;
    private String mysql_db;
    private String mysql_user;
    private String mysql_pass;
    private String m_logistic_reg_model_file;
    private String m_gbdt_model_path;
    public AdsServer(int _port, String _memcachedServer,
                     int _memcachedPortal,
                     int _tfMemcachedPortal,
                     int _dfMemcachedPortal,
                     int _clickMemcachedPortal,
                     String _logistic_reg_model_file,
                     String _gbdt_model_path,
                     String _mysql_host,
                     String _mysql_db, String _mysql_user,
                     String _mysql_pass) {
        port = _port;
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

    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new AdsIndexServerImpl(mMemcachedServer, mMemcachedPortal,
                        mTFMemcachedPortal, mDFMemcachedPortal, mClickFeaturePortal, m_logistic_reg_model_file, m_gbdt_model_path,
                        mysql_host, mysql_db,
                        mysql_user, mysql_pass))
                .build()
                .start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.print("*** shutting down gRPC server since JVM is shutting down");
                AdsServer.this.stop();
                System.err.print("ads index server shut down");
        }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
    private void blockUnitlShutdown() throws IOException, InterruptedException {
        if(server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String portStr = args[0];
        int port = Integer.parseInt(portStr);
        String memcachedServer = args[1];
        int tfmemcachedPortal = Integer.parseInt(args[2]);
        int dfmemcachedPortal = Integer.parseInt(args[3]);
        int memcachedPortal = Integer.parseInt(args[4]);
        String mysql_host = args[5];
        String mysql_db = args[6];
        String mysql_user = args[7];
        String mysql_pass = args[8];
        String logistic_reg_model_file = "";
        String gbdt_model_path = "";
        int clickMemcachedPortal = 0;
        final AdsServer server = new AdsServer(port, memcachedServer,
                memcachedPortal, tfmemcachedPortal, dfmemcachedPortal, clickMemcachedPortal, logistic_reg_model_file, gbdt_model_path, mysql_host, mysql_db, mysql_user, mysql_pass);
        server.start();
        server.blockUnitlShutdown();
    }
}