package io.github.real_septicake.hexxyplanes.networking.handler

import dev.architectury.networking.NetworkManager.PacketContext
import io.github.real_septicake.hexxyplanes.config.HexxyplanesServerConfig
import io.github.real_septicake.hexxyplanes.networking.msg.*

fun HexxyplanesMessageS2C.applyOnClient(ctx: PacketContext) = ctx.queue {
    when (this) {
        is MsgSyncConfigS2C -> {
            HexxyplanesServerConfig.onSyncConfig(serverConfig)
        }

        // add more client-side message handlers here
    }
}
