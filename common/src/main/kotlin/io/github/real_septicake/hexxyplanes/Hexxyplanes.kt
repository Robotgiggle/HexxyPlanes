package io.github.real_septicake.hexxyplanes

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import io.github.real_septicake.hexxyplanes.config.HexxyplanesServerConfig
import io.github.real_septicake.hexxyplanes.networking.HexxyplanesNetworking
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesActions
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesBlocks
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesItems
import java.util.UUID

object Hexxyplanes {
    const val MODID = "hexxyplanes"
    val planes = mutableSetOf<String>()

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    @JvmStatic
    val DEMIPLANE_RL = id("demiplane")

    fun rlFromUuid(uuid: UUID) = id("demiplane/$uuid")

    fun init() {
        HexxyplanesServerConfig.init()
        initRegistries(
            HexxyplanesActions,
            HexxyplanesBlocks,
            HexxyplanesItems
        )
        HexxyplanesNetworking.init()
    }

    fun initServer() {
        HexxyplanesServerConfig.initServer()
    }
}
