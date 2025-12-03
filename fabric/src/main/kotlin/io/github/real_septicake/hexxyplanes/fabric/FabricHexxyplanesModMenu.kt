package io.github.real_septicake.hexxyplanes.fabric

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import io.github.real_septicake.hexxyplanes.HexxyplanesClient

object FabricHexxyplanesModMenu : ModMenuApi {
    override fun getModConfigScreenFactory() = ConfigScreenFactory(HexxyplanesClient::getConfigScreen)
}
