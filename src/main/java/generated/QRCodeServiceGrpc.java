package generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: QRCodeService.proto")
public final class QRCodeServiceGrpc {

  private QRCodeServiceGrpc() {}

  public static final String SERVICE_NAME = "qrCode.QRCodeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generated.DecodeRequest,
      generated.QRCodeContent> getQRCodeDecodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "QRCodeDecode",
      requestType = generated.DecodeRequest.class,
      responseType = generated.QRCodeContent.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generated.DecodeRequest,
      generated.QRCodeContent> getQRCodeDecodeMethod() {
    io.grpc.MethodDescriptor<generated.DecodeRequest, generated.QRCodeContent> getQRCodeDecodeMethod;
    if ((getQRCodeDecodeMethod = QRCodeServiceGrpc.getQRCodeDecodeMethod) == null) {
      synchronized (QRCodeServiceGrpc.class) {
        if ((getQRCodeDecodeMethod = QRCodeServiceGrpc.getQRCodeDecodeMethod) == null) {
          QRCodeServiceGrpc.getQRCodeDecodeMethod = getQRCodeDecodeMethod = 
              io.grpc.MethodDescriptor.<generated.DecodeRequest, generated.QRCodeContent>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "qrCode.QRCodeService", "QRCodeDecode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.DecodeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generated.QRCodeContent.getDefaultInstance()))
                  .setSchemaDescriptor(new QRCodeServiceMethodDescriptorSupplier("QRCodeDecode"))
                  .build();
          }
        }
     }
     return getQRCodeDecodeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static QRCodeServiceStub newStub(io.grpc.Channel channel) {
    return new QRCodeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static QRCodeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new QRCodeServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static QRCodeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new QRCodeServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class QRCodeServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void qRCodeDecode(generated.DecodeRequest request,
        io.grpc.stub.StreamObserver<generated.QRCodeContent> responseObserver) {
      asyncUnimplementedUnaryCall(getQRCodeDecodeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQRCodeDecodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                generated.DecodeRequest,
                generated.QRCodeContent>(
                  this, METHODID_QRCODE_DECODE)))
          .build();
    }
  }

  /**
   */
  public static final class QRCodeServiceStub extends io.grpc.stub.AbstractStub<QRCodeServiceStub> {
    private QRCodeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QRCodeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QRCodeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QRCodeServiceStub(channel, callOptions);
    }

    /**
     */
    public void qRCodeDecode(generated.DecodeRequest request,
        io.grpc.stub.StreamObserver<generated.QRCodeContent> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQRCodeDecodeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class QRCodeServiceBlockingStub extends io.grpc.stub.AbstractStub<QRCodeServiceBlockingStub> {
    private QRCodeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QRCodeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QRCodeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QRCodeServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generated.QRCodeContent qRCodeDecode(generated.DecodeRequest request) {
      return blockingUnaryCall(
          getChannel(), getQRCodeDecodeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class QRCodeServiceFutureStub extends io.grpc.stub.AbstractStub<QRCodeServiceFutureStub> {
    private QRCodeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private QRCodeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected QRCodeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new QRCodeServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generated.QRCodeContent> qRCodeDecode(
        generated.DecodeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getQRCodeDecodeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QRCODE_DECODE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final QRCodeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(QRCodeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QRCODE_DECODE:
          serviceImpl.qRCodeDecode((generated.DecodeRequest) request,
              (io.grpc.stub.StreamObserver<generated.QRCodeContent>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class QRCodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    QRCodeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generated.QRCodeProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("QRCodeService");
    }
  }

  private static final class QRCodeServiceFileDescriptorSupplier
      extends QRCodeServiceBaseDescriptorSupplier {
    QRCodeServiceFileDescriptorSupplier() {}
  }

  private static final class QRCodeServiceMethodDescriptorSupplier
      extends QRCodeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    QRCodeServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (QRCodeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new QRCodeServiceFileDescriptorSupplier())
              .addMethod(getQRCodeDecodeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
