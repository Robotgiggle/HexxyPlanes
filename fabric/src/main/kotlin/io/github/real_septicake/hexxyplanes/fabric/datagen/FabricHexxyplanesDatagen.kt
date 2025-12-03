package io.github.real_septicake.hexxyplanes.fabric.datagen

import io.github.real_septicake.hexxyplanes.datagen.HexxyplanesActionTags
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object FabricHexxyplanesDatagen : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()

        pack.addProvider(::HexxyplanesActionTags)
    }
}
