package net.anvian.inventorytweaks;

import net.anvian.inventorytweaks.handler.ModKeyBinding;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartInventory implements ClientModInitializer {
    public static final String MOD_ID = "inventorytweaks";
    public static final String MOD_NAME = "Inventory Tweaks";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello from " + MOD_NAME + "!");
        ModKeyBinding.register();
    }
}