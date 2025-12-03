package io.github.real_septicake.hexxyplanes.forge

import dev.architectury.platform.forge.EventBuses
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.forge.datagen.ForgeHexxyplanesDatagen
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Hexxyplanes.MODID)
class ForgeHexxyplanes {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(Hexxyplanes.MODID, this)
            addListener(ForgeHexxyplanesClient::init)
            addListener(ForgeHexxyplanesDatagen::init)
            addListener(ForgeHexxyplanesServer::init)
        }
        Hexxyplanes.init()
    }
}
