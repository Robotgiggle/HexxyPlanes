package io.github.real_septicake.hexxyplanes

import com.mojang.brigadier.CommandDispatcher
import io.github.real_septicake.hexxyplanes.commands.*
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands

object HexxyplanesCommands {
    fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        val source = Commands.literal("hexxyplanes")

        GetChunkCommand.add(source)
        CreatePlaneCommand.add(source)
        EnterPlaneCommand.add(source)
        ExitPlaneCommand.add(source)
        PlaneExitCommand.add(source)
        GetPlaneExitCommand.add(source)
        SendToPlaneCommand.add(source)
        BanishCommand.add(source)

        source.requires{ it.hasPermission(Commands.LEVEL_ADMINS) }

        dispatcher.register(source)
    }
}