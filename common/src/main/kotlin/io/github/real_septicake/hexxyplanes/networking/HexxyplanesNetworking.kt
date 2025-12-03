package io.github.real_septicake.hexxyplanes.networking

import dev.architectury.networking.NetworkChannel
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.networking.msg.HexxyplanesMessageCompanion

object HexxyplanesNetworking {
    val CHANNEL: NetworkChannel = NetworkChannel.create(Hexxyplanes.id("networking_channel"))

    fun init() {
        for (subclass in HexxyplanesMessageCompanion::class.sealedSubclasses) {
            subclass.objectInstance?.register(CHANNEL)
        }
    }
}
