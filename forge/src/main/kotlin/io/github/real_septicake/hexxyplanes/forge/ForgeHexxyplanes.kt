package io.github.real_septicake.hexxyplanes.forge

import dev.architectury.platform.forge.EventBuses
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.HexxyplanesCommands
import io.github.real_septicake.hexxyplanes.forge.datagen.ForgeHexxyplanesDatagen
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.storage.LevelResource
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.event.server.ServerStartingEvent
import net.minecraftforge.event.server.ServerStoppedEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import java.nio.charset.StandardCharsets
import java.nio.file.Files

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
            addListener { evt: ServerStartingEvent ->
                val file = evt.server!!.getWorldPath(LevelResource("genned_planes"))
                if(Files.exists(file))
                    Hexxyplanes.planes.addAll(
                        Files.readAllLines(file, StandardCharsets.UTF_8).toMutableSet().filter { line -> line.isNotBlank() }
                    )
                for(plane in Hexxyplanes.planes)
                    getOrMakeDim(evt.server, Hexxyplanes.id(plane))
            }
            addListener { evt: ServerStoppedEvent ->
                val file = evt.server!!.getWorldPath(LevelResource("genned_planes"))
                Files.write(file, Hexxyplanes.planes)
            }
        }
        Hexxyplanes.init()
    }
}
