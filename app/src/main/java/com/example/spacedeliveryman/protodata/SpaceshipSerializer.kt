package com.example.spacedeliveryman.protodata

import androidx.datastore.CorruptionException
import androidx.datastore.Serializer
import com.example.spacedeliveryman.Spaceship
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object SpaceshipSerializer : Serializer<Spaceship> {


    override fun readFrom(input: InputStream): Spaceship {
        return try {
            Spaceship.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", e)
        }
    }

    override fun writeTo(t: Spaceship, output: OutputStream) {
        t.writeTo(output)
    }

}