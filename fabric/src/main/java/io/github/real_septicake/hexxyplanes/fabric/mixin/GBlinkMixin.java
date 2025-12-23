package io.github.real_septicake.hexxyplanes.fabric.mixin;

import at.petrak.hexcasting.api.casting.castables.SpellAction;
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import at.petrak.hexcasting.api.casting.iota.Iota;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension;
import miyucomics.hexical.features.misc_actions.*;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(OpGreaterBlink.class)
@Pseudo
public class GBlinkMixin {
    @Inject(method = "execute", at = @At(value = "RETURN"), remap = false)
    private void inWorld(List<? extends Iota> args, CastingEnvironment env, CallbackInfoReturnable<SpellAction.Result> cir, @Local(name = "destination") Vec3 destination) {
        if(HexxyplanesDimension.INSTANCE.getWORLD_KEY() == env.getWorld().dimension()){
            env.assertVecInWorld(destination);
        }
    }
}
