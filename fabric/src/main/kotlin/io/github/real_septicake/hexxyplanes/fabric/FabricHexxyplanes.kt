package io.github.real_septicake.hexxyplanes.fabric

import io.github.real_septicake.hexxyplanes.Hexxyplanes
import net.fabricmc.api.ModInitializer

object FabricHexxyplanes : ModInitializer {
    override fun onInitialize() {
        Hexxyplanes.init()
    }
}
