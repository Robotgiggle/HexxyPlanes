package io.github.real_septicake.hexxyplanes.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument

object SendToPlaneCommand : HexxyplanesCommand() {
    override fun add(cmd: LiteralArgumentBuilder<CommandSourceStack>) {
        cmd.then(Commands.literal("kidnap")
            .then(Commands.argument("target", EntityArgument.entity())
                .then(Commands.argument("to", EntityArgument.player())
                    .executes {
                        val target = EntityArgument.getEntity(it, "target")
                        val player = EntityArgument.getPlayer(it, "to")
                        HexxyplanesDimension.sendToPlane(it.source.level, target, player.uuid)
                        1
                    }
                )
            )
        )
    }
}