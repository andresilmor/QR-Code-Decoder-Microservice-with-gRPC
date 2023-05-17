import asyncio
import grpc

from ms_qrCodeDecoder_pb2_grpc import QRCodeDecoderServiceStub, QRCodeDecoderService, add_QRCodeDecoderServiceServicer_to_server
from ms_qrCodeDecoder_pb2 import QRCodeContent, DecodeRequest 

import numpy as np
import pyboof as pb

import logging

"""
# Detects all the QR Codes in the image and prints their message and location

"""


class QRCodeDecoderService(QRCodeDecoderService):

    async def QRCodeDecoder(self, request: DecodeRequest, context) -> QRCodeContent:
        
        data_path = "download.png"

        detector = pb.FactoryFiducial(np.uint8).qrcode()

        image = pb.load_single_band(data_path, np.uint8)

        detector.detect(image)

        print("Detected a total of {} QR Codes".format(len(detector.detections)))

        for qr in detector.detections:
            print("Message: " + qr.message)
            print("     at: " + str(qr.bounds))

        detections = QRCodeContent(content="yo")

        return detections


async def serve():
    server = grpc.aio.server()
    add_QRCodeDecoderServiceServicer_to_server(QRCodeDecoderService(), server)
    # using ip v6
    adddress = "[::]:50060"
    server.add_insecure_port(adddress)
    logging.info(f"[📡] Starting server on {adddress}")
    print("[📡] Starting server on " + adddress)
    await server.start()
    await server.wait_for_termination()

if __name__ == "__main__":
    asyncio.run(serve())