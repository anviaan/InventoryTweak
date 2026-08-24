package net.anvian.inventorytweaks.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public final class ModKeyBinding {
    private ModKeyBinding() {}

    public static final KeyMapping.Category CATEGORY =
            new KeyMapping.Category(ResourceLocation.fromNamespaceAndPath("inventorytweak", "inventorytweak"));
    public static final KeyMapping KEY_BINDING = new KeyMapping(
            "key.inventorytweak.sort_inventory_key", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_B, CATEGORY);
}
