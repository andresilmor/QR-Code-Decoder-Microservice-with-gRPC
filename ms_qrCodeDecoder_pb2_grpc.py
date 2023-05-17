# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import ms_qrCodeDecoder_pb2 as ms__qrCodeDecoder__pb2


class QRCodeDecoderServiceStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.QRCodeDecoder = channel.unary_unary(
                '/qrCodeDecoder.QRCodeDecoderService/QRCodeDecoder',
                request_serializer=ms__qrCodeDecoder__pb2.DecodeRequest.SerializeToString,
                response_deserializer=ms__qrCodeDecoder__pb2.QRCodeContent.FromString,
                )


class QRCodeDecoderServiceServicer(object):
    """Missing associated documentation comment in .proto file."""

    def QRCodeDecoder(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_QRCodeDecoderServiceServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'QRCodeDecoder': grpc.unary_unary_rpc_method_handler(
                    servicer.QRCodeDecoder,
                    request_deserializer=ms__qrCodeDecoder__pb2.DecodeRequest.FromString,
                    response_serializer=ms__qrCodeDecoder__pb2.QRCodeContent.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'qrCodeDecoder.QRCodeDecoderService', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class QRCodeDecoderService(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def QRCodeDecoder(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/qrCodeDecoder.QRCodeDecoderService/QRCodeDecoder',
            ms__qrCodeDecoder__pb2.DecodeRequest.SerializeToString,
            ms__qrCodeDecoder__pb2.QRCodeContent.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)