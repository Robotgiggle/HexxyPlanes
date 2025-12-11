package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import io.github.real_septicake.hexxyplanes.getExit
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer

object OpExitReveal : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        if(env.castingEntity !is ServerPlayer)
            throw MishapBadCaster()
        val exit = getExit(env.castingEntity as ServerPlayer)
        if(exit === null)
            env.printMessage(Component.literal("Current exit is: Spawn"))
        else
            env.printMessage(Component.literal("Current exit is:\n" +
                "- Dimension: ${exit.dimension.location()}\n" +
                "- Position: ${exit.position.toShortString()}"))
        return listOf()
    }
}