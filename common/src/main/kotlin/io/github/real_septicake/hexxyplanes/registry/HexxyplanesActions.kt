package io.github.real_septicake.hexxyplanes.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.HexRegistries
import at.petrak.hexcasting.common.lib.hex.HexActions
import io.github.real_septicake.hexxyplanes.casting.actions.OpGetPlane
import io.github.real_septicake.hexxyplanes.casting.actions.spells.OpEnterPlane
import io.github.real_septicake.hexxyplanes.casting.actions.spells.OpExitPlane

object HexxyplanesActions : HexxyplanesRegistrar<ActionRegistryEntry>(
    HexRegistries.ACTION,
    { HexActions.REGISTRY },
) {
    val GET_PLANE = make("get_plane", HexDir.NORTH_EAST, "dwawd") { OpGetPlane }
    val ENTER_PLANE = make("enter_plane", HexDir.NORTH_EAST, "dwawdeewedwwqqqwwqq") { OpEnterPlane }
    val EXIT_PLANE = make("exit_plane", HexDir.NORTH_EAST, "dwawddww") { OpExitPlane }

    private fun make(name: String, startDir: HexDir, signature: String, getAction: () -> Action) = register(name) {
        ActionRegistryEntry(HexPattern.fromAngles(signature, startDir), getAction())
    }
}
