package net.anvian.smartinventory;

import net.anvian.smartinventory.event.KeyInput;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmartInventory implements ClientModInitializer {
    public static final String MOD_ID = "smartinventory";
    public static final String MOD_NAME = "Smart Inventory";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);


    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello from " + MOD_NAME + "!");

        KeyInput.register();
    }
}