import io.grpc.Server;
import io.grpc.ServerBuilder;
import services.QRCodeService;

import java.io.IOException;
import java.util.logging.Logger;

public class ApplicationServer {
    private static final Logger logger = Logger.getLogger(ApplicationServer.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(50060).addService(new QRCodeService()).build();

        server.start();

        logger.info("Server started on " + server.getPort());

        server.awaitTermination();

    }

}
