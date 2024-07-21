package net.anvian.inventorytweaks.handler;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinding {
    public static KeyBinding keyBinding;

    public static void register() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.inventorytweak.sort_inventory_key",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "key.inventorytweak.inventorytweak"
        ));
    }
}
