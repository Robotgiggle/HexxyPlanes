package io.github.real_septicake.hexxyplanes.fabric

import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.HexxyplanesCommands
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.world.level.storage.LevelResource
import java.nio.charset.StandardCharsets
import java.nio.file.Files

object FabricHexxyplanes : ModInitializer {
    override fun onInitialize() {
        Hexxyplanes.init()
        CommandRegistrationCallback.EVENT.register { dp, _, _ -> HexxyplanesCommands.register(dp) }
        ServerLifecycleEvents.SERVER_STARTED.register {
            val file = it!!.getWorldPath(LevelResource("genned_planes"))
            if(Files.exists(file))
                Hexxyplanes.planes.addAll(
                    Files.readAllLines(file, StandardCharsets.UTF_8).toMutableSet().filter { line -> line.isNotBlank() }
                )
            for(plane in Hexxyplanes.planes)
                getOrMakeDim(it, Hexxyplanes.id(plane))
        }
        ServerLifecycleEvents.SERVER_STOPPED.register {
            val file = it!!.getWorldPath(LevelResource("genned_planes"))
            Files.write(file, Hexxyplanes.planes)
        }
    }
}
