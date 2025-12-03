package io.github.real_septicake.hexxyplanes.networking.msg

import io.github.real_septicake.hexxyplanes.config.HexxyplanesServerConfig
import net.minecraft.network.FriendlyByteBuf

data class MsgSyncConfigS2C(val serverConfig: HexxyplanesServerConfig.ServerConfig) : HexxyplanesMessageS2C {
    companion object : HexxyplanesMessageCompanion<MsgSyncConfigS2C> {
        override val type = MsgSyncConfigS2C::class.java

        override fun decode(buf: FriendlyByteBuf) = MsgSyncConfigS2C(
            serverConfig = HexxyplanesServerConfig.ServerConfig().decode(buf),
        )

        override fun MsgSyncConfigS2C.encode(buf: FriendlyByteBuf) {
            serverConfig.encode(buf)
        }
    }
}
