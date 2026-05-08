package net.anvian.inventorytweaks.features.sort;

import net.anvian.inventorytweaks.InventoryTweak;
import net.anvian.inventorytweaks.handler.Interaction;
import net.anvian.inventorytweaks.slots.ContainerSlots;
import net.anvian.inventorytweaks.slots.InventorySlots;
import net.anvian.inventorytweaks.slots.PlayerSlots;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Comparator;
import java.util.List;

public class SortInventory {
    public static void sortPlayerInventory(AbstractContainerMenu screenHandler) {
        if (cursorCleared(PlayerSlots.get(), screenHandler)) {
            mergeItemStacks(PlayerSlots.get().excludeOffhand(), screenHandler);
            sortItemStacks(PlayerSlots.get().excludeOffhand(), screenHandler);
        }
    }

    public static void sortContainerInventory(AbstractContainerMenu screenHandler) {
        if (cursorCleared(ContainerSlots.get(), screenHandler)) {
            mergeItemStacks(ContainerSlots.get(), screenHandler);
            sortItemStacks(ContainerSlots.get(), screenHandler);
        }
    }

    private static boolean cursorCleared(InventorySlots inventorySlots, AbstractContainerMenu screenHandler) {
        if (!Interaction.hasEmptyCursor()) {
            clearCursor(inventorySlots, screenHandler);
        }
        return Interaction.hasEmptyCursor();
    }

    private static void mergeItemStacks(InventorySlots inventorySlots, AbstractContainerMenu screenHandler) {
        for (int slot : inventorySlots) {
            ItemStack stack = screenHandler.getSlot(slot).getItem();
            if (!stack.isEmpty() && stack.getCount() < stack.getItem().getDefaultMaxStackSize()) {
                Interaction.clickStack(slot);
                for (int tempSlot = slot + 1; Interaction.getCursorStack().getCount() < Interaction.getCursorStack().getItem().getDefaultMaxStackSize() && tempSlot <= inventorySlots.getLastSlot() && !Interaction.getCursorStack().isEmpty(); tempSlot++) {
                    if (ItemStack.isSameItemSameComponents(Interaction.getCursorStack(), screenHandler.getSlot(tempSlot).getItem())) {
                        Interaction.clickStack(tempSlot);
                    }
                }
                if (!Interaction.hasEmptyCursor()) {
                    Interaction.clickStack(slot);
                }
            }
        }
    }

    private static void sortItemStacks(InventorySlots inventorySlots, AbstractContainerMenu screenHandler) {
        List<Integer> sortedSlots = getSortedSlots(inventorySlots, screenHandler);

        for (int i = 0; i < sortedSlots.size(); i++) {
            Interaction.swapStacks(sortedSlots.get(i), inventorySlots.get(i));
            sortedSlots = getSortedSlots(inventorySlots, screenHandler);
        }
    }

    private static List<Integer> getSortedSlots(InventorySlots inventorySlots, AbstractContainerMenu screenHandler) {
        return switch (InventoryTweak.CONFIG.sortType()) {
            case NAME ->
                    inventorySlots.stream().filter(slot -> !screenHandler.getSlot(slot).getItem().isEmpty()).sorted(Comparator.comparing((Integer slot) -> screenHandler.getSlot(slot).getItem().getDisplayName().getString()).thenComparing(slot -> screenHandler.getSlot(slot).getItem().getCount(), Comparator.reverseOrder())).toList();
            case TYPE ->
                    inventorySlots.stream().filter(slot -> !screenHandler.getSlot(slot).getItem().isEmpty()).sorted(Comparator.comparing((Integer slot) -> Item.getId(screenHandler.getSlot(slot).getItem().getItem())).thenComparing((Integer slot) -> screenHandler.getSlot(slot).getItem().getDisplayName().getString()).thenComparing(slot -> screenHandler.getSlot(slot).getItem().getCount(), Comparator.reverseOrder())).toList();
            case RARITY ->
                    inventorySlots.stream().filter(slot -> !screenHandler.getSlot(slot).getItem().isEmpty()).sorted(Comparator.comparing((Integer slot) -> screenHandler.getSlot(slot).getItem().getRarity()).reversed().thenComparing((Integer slot) -> screenHandler.getSlot(slot).getItem().getDisplayName().getString()).thenComparing(slot -> screenHandler.getSlot(slot).getItem().getCount(), Comparator.reverseOrder())).toList();
        };
    }

    private static void clearCursor(InventorySlots inventorySlots, AbstractContainerMenu screenHandler) {
        for (int slot : inventorySlots) {
            if (ItemStack.isSameItemSameComponents(screenHandler.getSlot(slot).getItem(), Interaction.getCursorStack())) {
                if (!Interaction.hasEmptyCursor()) {
                    Interaction.clickStack(slot);
                }
            }
        }

        for (int slot : inventorySlots) {
            if (screenHandler.getSlot(slot).getItem().isEmpty()) {
                if (!Interaction.hasEmptyCursor()) {
                    Interaction.clickStack(slot);
                }
            }
        }
    }
}
