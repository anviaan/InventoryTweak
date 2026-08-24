package net.anvian.inventorytweaks.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public final class ModKeyBinding {
    private ModKeyBinding() {}

    public static final String CATEGORY = "key.inventorytweak.inventorytweak";
    public static final KeyMapping KEY_BINDING = new KeyMapping(
            "key.inventorytweak.sort_inventory_key", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, CATEGORY);
}
