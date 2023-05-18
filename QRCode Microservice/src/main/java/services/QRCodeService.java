package services;

import generated.DecodeRequest;
import generated.QRCodeContent;
import generated.QRCodeServiceGrpc;
import io.grpc.stub.StreamObserver;

public class QRCodeService extends QRCodeServiceGrpc.QRCodeServiceImplBase {

    @Override
    public void qRCodeDecoder(DecodeRequest request, StreamObserver<QRCodeContent> responseObserver) {


    }

}
