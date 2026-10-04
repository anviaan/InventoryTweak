package net.anvian.inventorytweaks.handler;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

public final class ModKeyBinding {
    private ModKeyBinding() {}

    public static final KeyMapping.Category CATEGORY =
            new KeyMapping.Category(Identifier.fromNamespaceAndPath("inventorytweak", "inventorytweak"));
    public static final KeyMapping KEY_BINDING = new KeyMapping(
            "key.inventorytweak.sort_inventory_key", InputConstants.Type.KEYBOARD, InputConstants.KEY_B, CATEGORY);
}
