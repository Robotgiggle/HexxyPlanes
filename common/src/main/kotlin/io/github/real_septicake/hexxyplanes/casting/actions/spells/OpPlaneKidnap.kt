package io.github.real_septicake.hexxyplanes.casting.actions.spells

import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import at.petrak.hexcasting.api.misc.MediaConstants
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player

object OpPlaneKidnap : SpellAction {
    override val argc = 1

    override fun execute(args: List<Iota>, env: CastingEnvironment): SpellAction.Result {
        if(env.castingEntity !is ServerPlayer)
            throw MishapBadCaster()

        val p = args.getEntity(0, argc)
        env.assertEntityInRange(p)

        return SpellAction.Result(
            Spell(p, env.castingEntity as ServerPlayer),
            if(p is Player) MediaConstants.CRYSTAL_UNIT * 10 else MediaConstants.SHARD_UNIT * 2,
            listOf()
        )
    }

    private data class Spell(val target: Entity, val by: ServerPlayer) : RenderedSpell {
        override fun cast(env: CastingEnvironment) {
            HexxyplanesDimension.sendToPlane(env.world, target, by.uuid)
        }
    }
}