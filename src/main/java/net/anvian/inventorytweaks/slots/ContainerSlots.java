package net.anvian.inventorytweaks.slots;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class ContainerSlots {
    public static InventorySlots get() {
        AbstractContainerMenu screenHandler = Minecraft.getInstance().player.containerMenu;
        int from = 0;
        int to = screenHandler.slots.size() - Inventory.INVENTORY_SIZE;
        return new InventorySlots(from, to);
    }
}
