@file:JvmName("HexxyplanesAbstractionsImpl")

package io.github.real_septicake.hexxyplanes.fabric

import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesRegistrar
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.biome.Biomes
import xyz.nucleoid.fantasy.Fantasy
import xyz.nucleoid.fantasy.RuntimeWorldConfig
import xyz.nucleoid.fantasy.util.VoidChunkGenerator

val planeCache = mutableMapOf<String, ServerLevel>()

fun <T : Any> initRegistry(registrar: HexxyplanesRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}

fun getOrMakeDim(server: MinecraftServer, id: ResourceLocation) {
    planeCache.computeIfAbsent(id.path) {
        Hexxyplanes.planes += id.path
        val demiplane = server.registryAccess().lookupOrThrow(Registries.DIMENSION_TYPE)
            .getOrThrow(ResourceKey.create(Registries.DIMENSION_TYPE, Hexxyplanes.DEMIPLANE_RL))
        val config = RuntimeWorldConfig()
            .setDimensionType(demiplane)
            .setGenerator(VoidChunkGenerator(server.registryAccess().lookupOrThrow(Registries.BIOME)
                .getOrThrow(Biomes.THE_VOID)))
        Fantasy.get(server).getOrOpenPersistentWorld(id, config).asWorld()
    }
}