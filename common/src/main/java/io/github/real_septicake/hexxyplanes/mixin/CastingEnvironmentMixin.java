package io.github.real_septicake.hexxyplanes.mixin;

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment;
import io.github.real_septicake.hexxyplanes.Hexxyplanes;
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CastingEnvironment.class)
public abstract class CastingEnvironmentMixin {
    @Shadow
    public abstract ServerLevel getWorld();
    @Shadow
    public abstract LivingEntity getCastingEntity();

    @Inject(method = "isVecInWorld", at = @At("HEAD"), cancellable = true, remap = false)
    private void isInOwnPlane(Vec3 vec, CallbackInfoReturnable<Boolean> cir) {
        if(getWorld().dimension() == HexxyplanesDimension.INSTANCE.getWORLD_KEY()) {
            if(getCastingEntity() instanceof ServerPlayer p) {
                ChunkPos chunkPos = Hexxyplanes.chunkFromUUID(getWorld(), p.getUUID()).getPos();
                boolean inside = vec.x >= chunkPos.getMinBlockX() &&
                        vec.x <= chunkPos.getMinBlockX() + 11 &&
                        vec.z >= chunkPos.getMinBlockZ() &&
                        vec.z <= chunkPos.getMinBlockZ() + 11 &&
                        vec.y >= 1 && vec.y <= 11;
                cir.setReturnValue(inside);
            } else {
                cir.setReturnValue(false);
            }
        }
    }
}
