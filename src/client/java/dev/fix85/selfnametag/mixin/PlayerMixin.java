package dev.fix85.selfnametag.mixin;

import dev.fix85.selfnametag.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(method = "shouldShowName", at = @At("HEAD"), cancellable = true)
    private void selfnametag$forceShowOwnName(CallbackInfoReturnable<Boolean> cir) {
        if (!Config.get().enabled) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc == null) return;
        if (mc.player == null) return;
        if ((Object) this != mc.player) return;
        if (mc.options == null || mc.options.getCameraType() == null) return;
        if (mc.options.getCameraType().isFirstPerson()) return;

        cir.setReturnValue(true);
    }
}
