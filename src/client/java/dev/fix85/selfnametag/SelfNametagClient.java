package dev.fix85.selfnametag;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class SelfNametagClient implements ClientModInitializer {
    public static final String MOD_ID = "selfnametag";

    public static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        Config.load();

        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.selfnametag.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_N,
                KeyBinding.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                Config.get().enabled = !Config.get().enabled;
                Config.save();
                if (client.player != null) {
                    String state = Config.get().enabled ? "§aON§r" : "§cOFF§r";
                    client.player.sendMessage(
                            Text.translatable("selfnametag.chat.toggle", state),
                            true);
                }
            }
        });
    }
}
