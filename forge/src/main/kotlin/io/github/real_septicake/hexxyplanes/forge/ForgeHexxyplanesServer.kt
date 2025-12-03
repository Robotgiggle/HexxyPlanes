package io.github.real_septicake.hexxyplanes.forge

import io.github.real_septicake.hexxyplanes.Hexxyplanes
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent

object ForgeHexxyplanesServer {
    @Suppress("UNUSED_PARAMETER")
    fun init(event: FMLDedicatedServerSetupEvent) {
        Hexxyplanes.initServer()
    }
}
