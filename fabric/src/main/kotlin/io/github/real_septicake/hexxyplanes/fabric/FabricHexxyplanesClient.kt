package io.github.real_septicake.hexxyplanes.fabric

import io.github.real_septicake.hexxyplanes.HexxyplanesClient
import net.fabricmc.api.ClientModInitializer

object FabricHexxyplanesClient : ClientModInitializer {
    override fun onInitializeClient() {
        HexxyplanesClient.init()
    }
}
