package io.github.real_septicake.hexxyplanes

import io.github.real_septicake.hexxyplanes.registry.HexxyplanesBlocks
import net.minecraft.ChatFormatting
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.level.Level
import java.util.*

object HexxyplanesDimension {
    val WORLD_KEY = ResourceKey.create(
        Registries.DIMENSION,
        Hexxyplanes.id("demiplane")
    )

    fun repairPlane(world: Level, uuid: UUID) {
        val chunkPos = Hexxyplanes.chunkFromUUID(world, uuid).pos
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
        world.setBlock(BlockPos(chunkPos.minBlockX + 1, 0, chunkPos.minBlockZ + 1), HexxyplanesBlocks.BARRIER_SPAWN.block.defaultBlockState(), 3)
        world.destroyBlock(BlockPos(chunkPos.minBlockX + 1, 1, chunkPos.minBlockZ + 1), true)
        world.destroyBlock(BlockPos(chunkPos.minBlockX + 1, 2, chunkPos.minBlockZ + 1), true)
    }

    fun goToPlane(world: ServerLevel, player: ServerPlayer, uuid: UUID) {
        repairPlane(world, uuid)
        val chunkPos = Hexxyplanes.chunkFromUUID(world, uuid).pos
        player.teleportTo(world, (chunkPos.minBlockX + 1.5), 1.0, (chunkPos.minBlockZ + 1.5), -45f, 0f)
    }

    fun exitPlane(world: ServerLevel, player: ServerPlayer) {
        var dest = player.respawnPosition ?: world.sharedSpawnPos
        var dim = player.respawnDimension
        if(dim == WORLD_KEY) {
            dim = Level.OVERWORLD
            dest = world.sharedSpawnPos
            player.sendSystemMessage(
                Component.translatable("hexxyplanes.error.bad_respawn")
                .withStyle(ChatFormatting.BOLD))
        }
        player.teleportTo(world.server.getLevel(dim)!!,
            dest.x.toDouble(), dest.y.toDouble(), dest.z.toDouble(), 0f, 0f)
    }
}