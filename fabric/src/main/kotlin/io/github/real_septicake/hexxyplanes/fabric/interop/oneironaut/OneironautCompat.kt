package io.github.real_septicake.hexxyplanes.fabric.interop.oneironaut

import at.petrak.hexcasting.api.casting.math.HexDir
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.fabric.interop.oneironaut.casting.actions.OpExitBreakdown
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesActions
import net.beholderface.oneironaut.casting.iotatypes.DimIota

object OneironautCompat {
    fun load() {
        HexxyplanesActions.make("exit_deconstruct", HexDir.NORTH_EAST, "dwawdaawwwa") { OpExitBreakdown }
        DimIota.registerTransformer(DimIota.TextTransformer.colorizer(HexxyplanesDimension.WORLD_KEY.location().toString(), 0xda92e5))
    }
}