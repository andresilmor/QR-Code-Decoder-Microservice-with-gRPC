import boofcv.abst.fiducial.QrCodeDetector;
import boofcv.alg.fiducial.qrcode.QrCode;
import boofcv.factory.fiducial.ConfigQrCode;
import boofcv.factory.fiducial.FactoryFiducial;
import boofcv.io.UtilIO;
import boofcv.io.image.ConvertBufferedImage;
import boofcv.io.image.UtilImageIO;
import boofcv.struct.image.GrayU8;
import com.google.protobuf.ByteString;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import services.QRCodeService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class ApplicationServer {
    private static final Logger logger = Logger.getLogger(ApplicationServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {

        /*
        BufferedImage input = UtilImageIO.loadImageNotNull(UtilIO.pathExample("img_1.png "));

        // Testing
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(input, "png", baos);
        byte[] bytes = baos.toByteArray();
        ByteString test = ByteString.copyFrom(bytes);
        //

        byte[] bytesFromString = test.toByteArray();


        InputStream is = new ByteArrayInputStream(bytesFromString);
        input = ImageIO.read(is);


        GrayU8 gray = ConvertBufferedImage.convertFrom(input, (GrayU8)null);

        ConfigQrCode config = new ConfigQrCode();
//		config.considerTransposed = false; // by default, it will consider incorrectly encoded markers. Faster if false
        QrCodeDetector<GrayU8> detector = FactoryFiducial.qrcode(config, GrayU8.class);

        detector.process(gray);

        // Gets a list of all the qr codes it could successfully detect and decode
        List<QrCode> detections = detector.getDetections();

        Graphics2D g2 = input.createGraphics();
        int strokeWidth = Math.max(4, input.getWidth()/200); // in large images the line can be too thin
        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(strokeWidth));

        String qrCodesDedtected = "{ \"detections\": [ ";

        for (int index = 0; index < detections.size(); index += 1) {
            qrCodesDedtected += " { \"content\" : \"" + detections.get(index).message + "\" } ";
            if (index + 1 < detections.size())
                qrCodesDedtected += ", ";

        }

        qrCodesDedtected += " ] }";

        // List of objects it thinks might be a QR Code but failed for various reasons
        List<QrCode> failures = detector.getFailures();
        g2.setColor(Color.RED);
        for (QrCode qr : failures) {
            // If the 'cause' is ERROR_CORRECTION or higher, then there's a decent chance it's a real marker
            if (qr.failureCause.ordinal() < QrCode.Failure.ERROR_CORRECTION.ordinal())
                continue;

        }

        */

        Server server = ServerBuilder.forPort(50060).addService(new QRCodeService()).build();

        server.start();

        logger.info("Server started on " + server.getPort());

        server.awaitTermination();

    }

}
