package io.github.real_septicake.hexxyplanes.forge.mixin;

import at.petrak.hexcasting.forge.ForgeHexConfig;
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ForgeHexConfig.Server.class)
public class ForgeServerConfigAccessMixin {
    @Inject(method = "canTeleportInThisDimension", at = @At(value = "HEAD"), cancellable = true, remap = false)
    private void noDemiplane(ResourceKey<Level> dimension, CallbackInfoReturnable<Boolean> cir) {
        if(dimension == HexxyplanesDimension.INSTANCE.getWORLD_KEY())
            cir.setReturnValue(false);
    }
}
