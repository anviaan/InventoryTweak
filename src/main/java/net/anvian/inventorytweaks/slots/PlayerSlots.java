package net.anvian.inventorytweaks.slots;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;

public class PlayerSlots {
    public static InventorySlots get() {
        int from, to;
        if (MinecraftClient.getInstance().currentScreen instanceof InventoryScreen || MinecraftClient.getInstance().currentScreen instanceof CreativeInventoryScreen) {
            from = PlayerScreenHandler.INVENTORY_START;
            to = PlayerScreenHandler.INVENTORY_END;
            return new InventorySlots(from, to, PlayerScreenHandler.OFFHAND_ID);
        } else {
            ScreenHandler screenHandler = MinecraftClient.getInstance().player.currentScreenHandler;
            from = screenHandler.slots.size() - PlayerInventory.MAIN_SIZE;
            to = screenHandler.slots.size() - PlayerInventory.getHotbarSize();
            return new InventorySlots(from, to);
        }
    }
}
