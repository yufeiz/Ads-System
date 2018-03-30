package io.yufeiz.adindex;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;

public class AdsIndexClient {
	private static final Logger logger = Logger.getLogger(AdsIndexClient.class.getName());
	private final ManagedChannel channel;
	private final AdsIndexGrpc.AdsIndexBlockingStub blockingstub;
	public AdsIndexClient(String host, int port) {
		this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
	}
	AdsIndexClient(ManagedChannelBuilder<?> channelBuilder) {
		channel = channelBuilder.build();
		blockingstub = AdsIndexGrpc.newBlockingStub(channel);
	}
	
	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}
	
	public List<Ad> GetAds(List<Query> queryList, String device_id, String device_ip) {
		AdsRequest.Builder request = AdsRequest.newBuilder();
		System.out.println("queryList.size() " + queryList.size());
		for(int i = 0; i < queryList.size(); i++) {
			Query q = queryList.get(i);
			System.out.println("q.getTermCount() : " + q.getTermCount());
			for(int index = 0; index < q.getTermCount(); index++) {
				System.out.println("preparing request term : " + q.getTerm(index));
			}
			request.addQuery(q);
			request.setDeviceId(device_id);
			request.setDeviceIp(device_ip);
		}
		AdsReply reply;
		try {
			System.out.println("sending request...");
			reply = blockingstub.getAds(request.build());
			List<Ad> adList = new ArrayList<Ad>();
			adList = reply.getAdList();
			return adList;
		} catch (StatusRuntimeException e) {
			logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
		}
		return null;
	}
}
