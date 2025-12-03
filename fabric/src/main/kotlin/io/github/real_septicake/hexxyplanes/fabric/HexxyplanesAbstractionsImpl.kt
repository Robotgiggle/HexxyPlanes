@file:JvmName("HexxyplanesAbstractionsImpl")

package io.github.real_septicake.hexxyplanes.fabric

import io.github.real_septicake.hexxyplanes.registry.HexxyplanesRegistrar
import net.minecraft.core.Registry

fun <T : Any> initRegistry(registrar: HexxyplanesRegistrar<T>) {
    val registry = registrar.registry
    registrar.init { id, value -> Registry.register(registry, id, value) }
}
