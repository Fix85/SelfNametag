package dev.fix85.selfnametag.mixin;

import dev.fix85.selfnametag.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {

    @Inject(method = "shouldRenderName", at = @At("HEAD"), cancellable = true)
    private void selfnametag$forceShowOwnName(CallbackInfoReturnable<Boolean> cir) {
        if (!Config.get().enabled) return;

        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc == null) return;
        if (mc.player == null) return;
        if ((Object) this != mc.player) return;
        if (mc.options == null || mc.options.getPerspective() == null) return;
        if (mc.options.getPerspective().isFirstPerson()) return;

        cir.setReturnValue(true);
    }
}
