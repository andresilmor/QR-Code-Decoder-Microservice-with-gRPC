import asyncio
from io import BytesIO

import grpc
from PIL import Image
import cv2
from ms_qrCodeDecoder_pb2 import DecodeRequest, QRCodeContent
from ms_qrCodeDecoder_pb2_grpc import QRCodeDecoderServiceStub
import logging
from pprint import pformat


async def main():
    im = cv2.imread('frame2.png')
   

    async with grpc.aio.insecure_channel("[::]:50060") as channel:
        stub = QRCodeDecoderServiceStub(channel)

        img = Image.open("download.png")
        img_byte_arr = BytesIO()
        img.save(img_byte_arr, format='PNG', subsampling=0, quality=100)
        img_byte_arr = img_byte_arr.getvalue()

        res: QRCodeContent = await stub.QRCodeDecoder(
            DecodeRequest(image=img_byte_arr)
        )

        print(res)

if __name__ == "__main__":
    asyncio.run(main())