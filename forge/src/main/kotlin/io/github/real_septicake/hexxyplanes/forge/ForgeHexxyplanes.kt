package io.github.real_septicake.hexxyplanes.forge

import dev.architectury.platform.forge.EventBuses
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.HexxyplanesCommands
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.VoidChunkGenerator
import io.github.real_septicake.hexxyplanes.forge.datagen.ForgeHexxyplanesDatagen
import net.minecraft.core.registries.Registries
import net.minecraft.server.level.ServerPlayer
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent
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
            addListener { evt: PlayerLoggedInEvent ->
                if(HexxyplanesDimension.WORLD_KEY == evt.entity.level().dimension()) {
                    HexxyplanesDimension.goToPlane(evt.entity.server!!.getLevel(HexxyplanesDimension.WORLD_KEY)!!, evt.entity!! as ServerPlayer, evt.entity.uuid)
                }
            }
        }
        Hexxyplanes.init()
    }
}
