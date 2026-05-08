package net.anvian.inventorytweaks.slots;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;

public class PlayerSlots {
    public static InventorySlots get() {
        int from, to;
        if (Minecraft.getInstance().screen instanceof InventoryScreen || Minecraft.getInstance().screen instanceof CreativeModeInventoryScreen) {
            from = InventoryMenu.INV_SLOT_START;
            to = InventoryMenu.INV_SLOT_END;
            return new InventorySlots(from, to, InventoryMenu.SHIELD_SLOT);
        } else {
            AbstractContainerMenu screenHandler = Minecraft.getInstance().player.containerMenu;
            from = screenHandler.slots.size() - Inventory.INVENTORY_SIZE;
            to = screenHandler.slots.size() - Inventory.getSelectionSize();
            return new InventorySlots(from, to);
        }
    }
}
