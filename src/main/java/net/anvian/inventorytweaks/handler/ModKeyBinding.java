package net.anvian.inventorytweaks.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.anvian.inventorytweaks.InventoryTweak;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinding {
    public static KeyMapping keyBinding;

    public static void register() {
        keyBinding = KeyMappingHelper.registerKeyMapping(new KeyMapping(
                "key.inventorytweak.sort_inventory_key",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                KeyMapping.Category.register(Identifier.fromNamespaceAndPath(InventoryTweak.MOD_ID, "inventorytweak"))
        ));
    }
}
