package net.anvian.inventorytweaks.slots;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;

public class ContainerSlots {
    public static InventorySlots get() {
        ScreenHandler screenHandler = MinecraftClient.getInstance().player.currentScreenHandler;
        int from = 0;
        int to = screenHandler.slots.size() - PlayerInventory.MAIN_SIZE;
        return new InventorySlots(from, to);
    }
}
