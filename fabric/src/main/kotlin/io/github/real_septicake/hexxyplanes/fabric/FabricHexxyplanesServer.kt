package io.github.real_septicake.hexxyplanes.fabric

import io.github.real_septicake.hexxyplanes.Hexxyplanes
import net.fabricmc.api.DedicatedServerModInitializer

object FabricHexxyplanesServer : DedicatedServerModInitializer {
    override fun onInitializeServer() {
        Hexxyplanes.initServer()
    }
}
