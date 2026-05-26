package dev.fix85.selfnametag.mixin;

import dev.fix85.selfnametag.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity> {

    @Inject(method = "hasLabel", at = @At("HEAD"), cancellable = true)
    private void selfnametag$forceShowOwnName(T entity, double distanceSq, CallbackInfoReturnable<Boolean> cir) {
        if (!Config.get().enabled) return;

        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null) return;
        if (mc.player == null) return;
        if (entity != mc.player) return;
        if (mc.options == null || mc.options.getPerspective() == null) return;
        if (mc.options.getPerspective().isFirstPerson()) return;

        cir.setReturnValue(MinecraftClient.isHudEnabled());
    }
}
