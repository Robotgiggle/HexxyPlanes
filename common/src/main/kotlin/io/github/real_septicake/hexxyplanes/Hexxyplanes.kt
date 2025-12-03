package io.github.real_septicake.hexxyplanes

import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import io.github.real_septicake.hexxyplanes.config.HexxyplanesServerConfig
import io.github.real_septicake.hexxyplanes.networking.HexxyplanesNetworking
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesActions

object Hexxyplanes {
    const val MODID = "hexxyplanes"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    fun init() {
        HexxyplanesServerConfig.init()
        initRegistries(
            HexxyplanesActions,
        )
        HexxyplanesNetworking.init()
    }

    fun initServer() {
        HexxyplanesServerConfig.initServer()
    }
}
