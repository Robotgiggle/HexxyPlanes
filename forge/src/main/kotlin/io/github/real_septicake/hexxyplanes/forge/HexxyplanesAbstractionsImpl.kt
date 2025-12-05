@file:JvmName("HexxyplanesAbstractionsImpl")

package io.github.real_septicake.hexxyplanes.forge

import commoble.infiniverse.api.InfiniverseAPI
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesRegistrar
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.dimension.LevelStem
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

val planeCache = mutableMapOf<String, ServerLevel>()

fun <T : Any> initRegistry(registrar: HexxyplanesRegistrar<T>) {
    MOD_BUS.addListener { event: RegisterEvent ->
        event.register(registrar.registryKey) { helper ->
            registrar.init(helper::register)
        }
    }
}

fun getOrMakeDim(server: MinecraftServer, id: ResourceLocation) {
    planeCache.computeIfAbsent(id.path) {
        Hexxyplanes.planes += id.path
        val demiplane = server.registryAccess().lookupOrThrow(Registries.DIMENSION_TYPE)
            .getOrThrow(ResourceKey.create(Registries.DIMENSION_TYPE, Hexxyplanes.DEMIPLANE_RL))
        InfiniverseAPI.get().getOrCreateLevel(server, ResourceKey.create(Registries.DIMENSION, id)) {
            LevelStem(demiplane, VoidChunkGenerator(server))
        }
    }
}
