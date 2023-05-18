package services;

import boofcv.abst.fiducial.QrCodeDetector;
import boofcv.alg.fiducial.qrcode.QrCode;
import boofcv.factory.fiducial.ConfigQrCode;
import boofcv.factory.fiducial.FactoryFiducial;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.struct.image.GrayU8;
import com.google.protobuf.ByteString;
import generated.DecodeRequest;
import generated.QRCodeContent;
import generated.QRCodeServiceGrpc;
import io.grpc.stub.StreamObserver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QRCodeService extends QRCodeServiceGrpc.QRCodeServiceImplBase {

    @Override
    public void qRCodeDecoder(DecodeRequest request, StreamObserver<QRCodeContent> responseObserver) {

        ByteString byteStringImage = request.getImage();

        byte[] byteArrayImage = byteStringImage.toByteArray();

        InputStream is = new ByteArrayInputStream(byteArrayImage);
        QRCodeContent.Builder response = QRCodeContent.newBuilder();

        try {
            BufferedImage bufferedImage = ImageIO.read(is);
            GrayU8 grayImage = ConvertBufferedImage.convertFrom(bufferedImage, (GrayU8)null);
            ConfigQrCode config = new ConfigQrCode();
//		config.considerTransposed = false; // by default, it will consider incorrectly encoded markers. Faster if false
            QrCodeDetector<GrayU8> detector = FactoryFiducial.qrcode(config, GrayU8.class);

            detector.process(grayImage);
            List<QrCode> detections = detector.getDetections();

            String qrCodesDetected = "{ \"detections\": [ ";

            for (int index = 0; index < detections.size(); index += 1) {
                qrCodesDetected += " { \"content\" : \"" + detections.get(index).message + "\" } ";
                if (index + 1 < detections.size())
                    qrCodesDetected += ", ";

            }

            qrCodesDetected += " ] }";

            response.setContent(qrCodesDetected);

        } catch (IOException e) {
            response.setContent("{ \"message\" : \"Image.IO.read() error\" }");

            throw new RuntimeException(e);
        }

    }

}
