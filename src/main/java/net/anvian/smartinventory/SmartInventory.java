package net.anvian.smartinventory;

import net.anvian.smartinventory.event.KeyInput;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.screen.ScreenHandler;
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

    public static MinecraftClient getClient() {
        return MinecraftClient.getInstance();
    }

    public static ClientPlayerEntity getPlayer() {
        return MinecraftClient.getInstance().player;
    }

    public static Screen getScreen() {
        return MinecraftClient.getInstance().currentScreen;
    }

    public static ScreenHandler getScreenHandler() {
        assert MinecraftClient.getInstance().player != null;
        return MinecraftClient.getInstance().player.currentScreenHandler;
    }
}