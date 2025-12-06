package io.github.real_septicake.hexxyplanes

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey

object HexxyplanesDimension {
    val WORLD_KEY = ResourceKey.create(
        Registries.DIMENSION,
        Hexxyplanes.id("demiplane")
    )
}