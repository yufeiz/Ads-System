package io.yufeiz.adindex;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.3.0)",
    comments = "Source: SelectAds.proto")
public final class AdsIndexGrpc {

  private AdsIndexGrpc() {}

  public static final String SERVICE_NAME = "adindex.AdsIndex";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<io.yufeiz.adindex.AdsRequest,
      io.yufeiz.adindex.AdsReply> METHOD_GET_ADS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "adindex.AdsIndex", "GetAds"),
          io.grpc.protobuf.ProtoUtils.marshaller(io.yufeiz.adindex.AdsRequest.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(io.yufeiz.adindex.AdsReply.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdsIndexStub newStub(io.grpc.Channel channel) {
    return new AdsIndexStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdsIndexBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AdsIndexBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static AdsIndexFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AdsIndexFutureStub(channel);
  }

  /**
   */
  public static abstract class AdsIndexImplBase implements io.grpc.BindableService {

    /**
     */
    public void getAds(io.yufeiz.adindex.AdsRequest request,
        io.grpc.stub.StreamObserver<io.yufeiz.adindex.AdsReply> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_ADS, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_ADS,
            asyncUnaryCall(
              new MethodHandlers<
                io.yufeiz.adindex.AdsRequest,
                io.yufeiz.adindex.AdsReply>(
                  this, METHODID_GET_ADS)))
          .build();
    }
  }

  /**
   */
  public static final class AdsIndexStub extends io.grpc.stub.AbstractStub<AdsIndexStub> {
    private AdsIndexStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdsIndexStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AdsIndexStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdsIndexStub(channel, callOptions);
    }

    /**
     */
    public void getAds(io.yufeiz.adindex.AdsRequest request,
        io.grpc.stub.StreamObserver<io.yufeiz.adindex.AdsReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_ADS, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class AdsIndexBlockingStub extends io.grpc.stub.AbstractStub<AdsIndexBlockingStub> {
    private AdsIndexBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdsIndexBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AdsIndexBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdsIndexBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.yufeiz.adindex.AdsReply getAds(io.yufeiz.adindex.AdsRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_ADS, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class AdsIndexFutureStub extends io.grpc.stub.AbstractStub<AdsIndexFutureStub> {
    private AdsIndexFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdsIndexFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected AdsIndexFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdsIndexFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.yufeiz.adindex.AdsReply> getAds(
        io.yufeiz.adindex.AdsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_ADS, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ADS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdsIndexImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AdsIndexImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ADS:
          serviceImpl.getAds((io.yufeiz.adindex.AdsRequest) request,
              (io.grpc.stub.StreamObserver<io.yufeiz.adindex.AdsReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class AdsIndexDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.yufeiz.adindex.searchAds.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AdsIndexGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AdsIndexDescriptorSupplier())
              .addMethod(METHOD_GET_ADS)
              .build();
        }
      }
    }
    return result;
  }
}
