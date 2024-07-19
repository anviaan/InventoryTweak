package net.anvian.smartinventory.event;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInput {
    public static KeyBinding keyBinding;

    public static void register() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "Sort Inventory Key",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                "Smart Inventory"
        ));
    }
}