package io.github.real_septicake.hexxyplanes.commands

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesBlocks
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.commands.arguments.EntityArgument
import net.minecraft.core.BlockPos

object CreatePlaneCommand : HexxyplanesCommand() {
    override fun add(cmd: LiteralArgumentBuilder<CommandSourceStack>) {
        cmd.then(Commands.literal("genPlane")
            .then(Commands.argument("for", EntityArgument.player())
                .executes {
                    val p = EntityArgument.getPlayer(it, "for")
                    val world = it.source.server.getLevel(HexxyplanesDimension.WORLD_KEY) ?: return@executes 0
                    val chunkPos = Hexxyplanes.chunkFromUUID(world, p.uuid).pos
                    for(x in chunkPos.minBlockX..(chunkPos.minBlockX + 11)) {
                        for(y in 0..11) {
                            for(z in chunkPos.minBlockZ..(chunkPos.minBlockZ + 11)) {
                                if(x == chunkPos.minBlockX || x == chunkPos.minBlockX + 11 ||
                                    y == 0 || y == 11 ||
                                    z == chunkPos.minBlockZ || z == chunkPos.minBlockZ + 11) {
                                    world.setBlock(BlockPos(x, y, z), HexxyplanesBlocks.BARRIER.block.defaultBlockState(), 3)
                                }
                            }
                        }
                    }
                    1
                }
            )
        )
    }
}