package io.github.real_septicake.hexxyplanes

import io.github.real_septicake.hexxyplanes.config.HexxyplanesClientConfig
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object HexxyplanesClient {
    fun init() {
        HexxyplanesClientConfig.init()
    }

    fun getConfigScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(HexxyplanesClientConfig.GlobalConfig::class.java, parent).get()
    }
}
