package dev.fix85.selfnametag;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import org.lwjgl.glfw.GLFW;

public class SelfNametagClient implements ClientModInitializer {
    public static final String MOD_ID = "selfnametag";

    public static KeyMapping toggleKey;

    @Override
    public void onInitializeClient() {
        Config.load();

        toggleKey = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.selfnametag.toggle",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.consumeClick()) {
                Config.get().enabled = !Config.get().enabled;
                Config.save();
                if (client.player != null) {
                    String state = Config.get().enabled ? "§aON§r" : "§cOFF§r";
                    client.player.sendOverlayMessage(
                            Component.translatable("selfnametag.chat.toggle", state));
                }
            }
        });
    }
}
