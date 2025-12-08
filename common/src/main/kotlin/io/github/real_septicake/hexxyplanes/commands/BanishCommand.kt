package io.github.real_septicake.hexxyplanes.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument

object BanishCommand : HexxyplanesCommand() {
    override fun add(cmd: LiteralArgumentBuilder<CommandSourceStack>) {
        cmd.then(Commands.literal("banish")
            .then(Commands.argument("target", EntityArgument.entity())
                .executes {
                    val target = EntityArgument.getEntity(it, "target")
                    HexxyplanesDimension.banishFromPlane(it.source.level, it.source.playerOrException, target)
                    1
                }
            )
        )
    }
}