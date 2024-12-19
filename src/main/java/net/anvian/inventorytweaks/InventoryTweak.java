package net.anvian.inventorytweaks;

import net.anvian.inventorytweaks.config.InventoryTweakConfig;
import net.anvian.inventorytweaks.features.durabilityWarning.DurabilityWarning;
import net.anvian.inventorytweaks.handler.ModKeyBinding;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.ItemGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryTweak implements ClientModInitializer {
    public static final String MOD_ID = "inventorytweak";
    public static final String MOD_NAME = "Inventory Tweak";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final InventoryTweakConfig CONFIG = InventoryTweakConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello from " + MOD_NAME + "!");
        ModKeyBinding.register();
        ClientTickEvents.START_CLIENT_TICK.register(new DurabilityWarning());
    }

    public static boolean isValidScreen(Screen screen) {
        return screen instanceof InventoryScreen || (screen instanceof CreativeInventoryScreen creativeInventoryScreen && creativeInventoryScreen.getSelectedItemGroup().getType() == ItemGroup.Type.INVENTORY);
    }
}