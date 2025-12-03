@file:JvmName("HexxyplanesAbstractions")

package io.github.real_septicake.hexxyplanes

import dev.architectury.injectables.annotations.ExpectPlatform
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesRegistrar

fun initRegistries(vararg registries: HexxyplanesRegistrar<*>) {
    for (registry in registries) {
        initRegistry(registry)
    }
}

@ExpectPlatform
fun <T : Any> initRegistry(registrar: HexxyplanesRegistrar<T>) {
    throw AssertionError()
}
