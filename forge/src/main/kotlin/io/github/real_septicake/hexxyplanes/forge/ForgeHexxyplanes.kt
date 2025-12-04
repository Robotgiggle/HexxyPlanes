package io.github.real_septicake.hexxyplanes.forge

import dev.architectury.platform.forge.EventBuses
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.HexxyplanesCommands
import io.github.real_septicake.hexxyplanes.forge.datagen.ForgeHexxyplanesDatagen
import net.minecraft.core.registries.Registries
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Hexxyplanes.MODID)
class ForgeHexxyplanes {
    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(Hexxyplanes.MODID, this)
            addListener(ForgeHexxyplanesClient::init)
            addListener(ForgeHexxyplanesDatagen::init)
            addListener(ForgeHexxyplanesServer::init)
            addListener { evt: RegisterEvent -> evt.register(Registries.CHUNK_GENERATOR) {
                it.register(Hexxyplanes.id("void"), VoidChunkGenerator.CODEC)
            } }
        }
        MinecraftForge.EVENT_BUS.apply {
            addListener { evt: RegisterCommandsEvent -> HexxyplanesCommands.register(evt.dispatcher) }
        }
        Hexxyplanes.init()
    }
}
