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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class QRCodeService extends QRCodeServiceGrpc.QRCodeServiceImplBase {

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void qRCodeDecode(DecodeRequest request, StreamObserver<QRCodeContent> responseObserver) {

        ByteString byteStringImage = request.getImage();

        byte[] byteArrayImage = byteStringImage.toByteArray();

        InputStream is = new ByteArrayInputStream(byteArrayImage);
        //QRCodeContent.Builder response = QRCodeContent.newBuilder();
        QRCodeContent response;

        try {
            BufferedImage bufferedImage = ImageIO.read(is);
            GrayU8 grayImage = ConvertBufferedImage.convertFrom(bufferedImage, (GrayU8)null);
            ConfigQrCode config = new ConfigQrCode();
//		config.considerTransposed = false; // by default, it will consider incorrectly encoded markers. Faster if false
            QrCodeDetector<GrayU8> detector = FactoryFiducial.qrcode(config, GrayU8.class);

            byte tentatives = 0;
            List<QrCode> detections;
            do {
                detector.process(grayImage);
                detections = detector.getDetections();

                if (tentatives++ >= 10)
                    break;

            } while (detections.size() <= 0);


            String qrCodesDetected = "{ \"detections\": [ ";

            for (int index = 0; index < detections.size(); index += 1) {
                QrCode detection = detections.get(index);

                if (isJSONValid(detection.message))
                    qrCodesDetected += " { \"content\" : " + detection.message.replace("\n", "").replace("\r", "") + ", ";
                else
                    qrCodesDetected += " { \"content\" : \"" + detection.message.replace("\n", "").replace("\r", "") + "\", ";

                // detections.get(index).bounds
                qrCodesDetected += "\"bounds\" : { ";

                qrCodesDetected += "\"TL\" : { \"x\" : " + detection.bounds.get(0).x + ", \"y\" : " + detection.bounds.get(0).y + " },";
                qrCodesDetected += "\"TR\" : { \"x\" : " + detection.bounds.get(1).x + ", \"y\" : " + detection.bounds.get(1).y + " },";
                qrCodesDetected += "\"BL\" : { \"x\" : " + detection.bounds.get(3).x + ", \"y\" : " + detection.bounds.get(3).y + " },";
                qrCodesDetected += "\"BR\" : { \"x\" : " + detection.bounds.get(2).x + ", \"y\" : " + detection.bounds.get(2).y + " }";

                qrCodesDetected += " }, ";

                qrCodesDetected += "\"size\" : { \"width\" : "+ (detection.bounds.get(1).x -  detection.bounds.get(0).x) + ", \"height\" : " + (detection.bounds.get(2).y -  detection.bounds.get(0).y) + " } } ";

                if (index + 1 < detections.size())
                    qrCodesDetected += ", ";

            }

            qrCodesDetected += " ] }";

            //System.out.println(qrCodesDetected.trim());


            response = QRCodeContent.newBuilder().setContent(qrCodesDetected.trim()).build();


            responseObserver.onNext(response);
            responseObserver.onCompleted();


        } catch (IOException e) {
            response = QRCodeContent.newBuilder().setContent("{ \"message\" : \"Image.IO.read() error\" }").build();
            System.out.println("mennn");

            responseObserver.onNext(response);
            responseObserver.onCompleted();

            throw new RuntimeException(e);
        }


    }

}
