package io.github.real_septicake.hexxyplanes.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.getOrMakeDim
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument

object GenDemiplaneCommand {
    fun add(cmd: LiteralArgumentBuilder<CommandSourceStack>) {
        cmd.then(Commands.literal("genPlane")
            .then(Commands.argument("for", EntityArgument.player())
                .executes {
                    val p = EntityArgument.getPlayer(it, "for")
                    getOrMakeDim(it.source.server, Hexxyplanes.rlFromUuid(p.uuid))
                    return@executes 1
                })
        )
    }
}