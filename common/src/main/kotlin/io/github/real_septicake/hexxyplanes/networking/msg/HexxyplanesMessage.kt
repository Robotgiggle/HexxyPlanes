package io.github.real_septicake.hexxyplanes.networking.msg

import dev.architectury.networking.NetworkChannel
import dev.architectury.networking.NetworkManager.PacketContext
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.networking.HexxyplanesNetworking
import io.github.real_septicake.hexxyplanes.networking.handler.applyOnClient
import io.github.real_septicake.hexxyplanes.networking.handler.applyOnServer
import net.fabricmc.api.EnvType
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.server.level.ServerPlayer
import java.util.function.Supplier

sealed interface HexxyplanesMessage

sealed interface HexxyplanesMessageC2S : HexxyplanesMessage {
    fun sendToServer() {
        HexxyplanesNetworking.CHANNEL.sendToServer(this)
    }
}

sealed interface HexxyplanesMessageS2C : HexxyplanesMessage {
    fun sendToPlayer(player: ServerPlayer) {
        HexxyplanesNetworking.CHANNEL.sendToPlayer(player, this)
    }

    fun sendToPlayers(players: Iterable<ServerPlayer>) {
        HexxyplanesNetworking.CHANNEL.sendToPlayers(players, this)
    }
}

sealed interface HexxyplanesMessageCompanion<T : HexxyplanesMessage> {
    val type: Class<T>

    fun decode(buf: FriendlyByteBuf): T

    fun T.encode(buf: FriendlyByteBuf)

    fun apply(msg: T, supplier: Supplier<PacketContext>) {
        val ctx = supplier.get()
        when (ctx.env) {
            EnvType.SERVER, null -> {
                Hexxyplanes.LOGGER.debug("Server received packet from {}: {}", ctx.player.name.string, this)
                when (msg) {
                    is HexxyplanesMessageC2S -> msg.applyOnServer(ctx)
                    else -> Hexxyplanes.LOGGER.warn("Message not handled on server: {}", msg::class)
                }
            }
            EnvType.CLIENT -> {
                Hexxyplanes.LOGGER.debug("Client received packet: {}", this)
                when (msg) {
                    is HexxyplanesMessageS2C -> msg.applyOnClient(ctx)
                    else -> Hexxyplanes.LOGGER.warn("Message not handled on client: {}", msg::class)
                }
            }
        }
    }

    fun register(channel: NetworkChannel) {
        channel.register(type, { msg, buf -> msg.encode(buf) }, ::decode, ::apply)
    }
}
