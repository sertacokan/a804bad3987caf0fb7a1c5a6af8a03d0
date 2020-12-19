package com.example.spacedeliveryman.protodata

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.example.spacedeliveryman.DeliveryRemainingData
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object DeliveryRemainingSerializer : Serializer<DeliveryRemainingData> {

    override fun readFrom(input: InputStream): DeliveryRemainingData {
        return try {
            DeliveryRemainingData.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override fun writeTo(t: DeliveryRemainingData, output: OutputStream) {
        t.writeTo(output)
    }
}