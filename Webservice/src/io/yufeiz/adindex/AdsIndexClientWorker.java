package io.yufeiz.adindex;

import java.util.List;
import java.util.logging.Logger;

public class AdsIndexClientWorker extends Thread {
	private static final Logger logger = Logger.getLogger(AdsIndexClientWorker.class.getName());
	protected AdsSelectionResult result = null;
	private String adsIndexServer;
	private String deviceId;
	private String deviceIp;
	private int adsIndexServerPortal;
	private List<Query> queryList;
	public AdsIndexClientWorker(List<Query> queryList, String adsIndexServer, int adsIndexServerPortal, String deviceId, String deviceIp, AdsSelectionResult result) {
		this.result = result;
		this.queryList = queryList;
		this.deviceId = deviceId;
		this.deviceIp = deviceIp;
		this.adsIndexServer = adsIndexServer;
		this.adsIndexServerPortal = adsIndexServerPortal;
	}
	public void start() {
		AdsIndexClient adsIndexClient = new AdsIndexClient(adsIndexServer, adsIndexServerPortal);
		List<Ad> adsList = adsIndexClient.GetAds(queryList, deviceId, deviceIp);
		result.add(adsList);
	}
}
