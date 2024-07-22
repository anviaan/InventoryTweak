package net.anvian.inventorytweaks.sort;

import net.anvian.inventorytweaks.InventoryTweak;
import net.anvian.inventorytweaks.config.ModConfig;
import net.anvian.inventorytweaks.handler.Interaction;
import net.anvian.inventorytweaks.slots.ContainerSlots;
import net.anvian.inventorytweaks.slots.InventorySlots;
import net.anvian.inventorytweaks.slots.PlayerSlots;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;

import java.util.Comparator;
import java.util.List;

public class SortInventory {
    public static void sortPlayerInventory(ScreenHandler screenHandler) {
        if (cursorCleared(PlayerSlots.get(), screenHandler)) {
            mergeItemStacks(PlayerSlots.get().excludeOffhand(), screenHandler);
            sortItemStacks(PlayerSlots.get().excludeOffhand(), screenHandler);
        }
    }

    public static void sortContainerInventory(ScreenHandler screenHandler) {
        if (cursorCleared(ContainerSlots.get(), screenHandler)) {
            mergeItemStacks(ContainerSlots.get(), screenHandler);
            sortItemStacks(ContainerSlots.get(), screenHandler);
        }
    }

    private static boolean cursorCleared(InventorySlots inventorySlots, ScreenHandler screenHandler) {
        if (!Interaction.hasEmptyCursor()) {
            clearCursor(inventorySlots, screenHandler);
        }
        return Interaction.hasEmptyCursor();
    }

    private static void mergeItemStacks(InventorySlots inventorySlots, ScreenHandler screenHandler) {
        for (int slot : inventorySlots) {
            ItemStack stack = screenHandler.getSlot(slot).getStack();
            if (!stack.isEmpty() && stack.getCount() < stack.getMaxCount()) {
                Interaction.clickStack(slot);
                for (int tempSlot = slot + 1; Interaction.getCursorStack().getCount() < Interaction.getCursorStack().getMaxCount()
                        && tempSlot <= inventorySlots.getLastSlot() && !Interaction.getCursorStack().isEmpty(); tempSlot++) {
                    if (ItemStack.areItemsEqual(Interaction.getCursorStack(), screenHandler.getSlot(tempSlot).getStack())) {
                        Interaction.clickStack(tempSlot);
                    }
                }
                if (!Interaction.hasEmptyCursor()) {
                    Interaction.clickStack(slot);
                }
            }
        }
    }

    private static void sortItemStacks(InventorySlots inventorySlots, ScreenHandler screenHandler) {
        List<Integer> sortedSlots = getSortedSlots(inventorySlots, screenHandler);

        for (int i = 0; i < sortedSlots.size(); i++) {
            Interaction.swapStacks(sortedSlots.get(i), inventorySlots.get(i));
            sortedSlots = getSortedSlots(inventorySlots, screenHandler);
        }
    }

    private static List<Integer> getSortedSlots(InventorySlots inventorySlots, ScreenHandler screenHandler) {
        if (InventoryTweak.CONFIG.sortType() == ModConfig.SortType.NAME) {
            return inventorySlots.stream()
                    .filter(slot -> !screenHandler.getSlot(slot).getStack().isEmpty())
                    .sorted(Comparator.comparing((Integer slot) -> screenHandler.getSlot(slot).getStack().getName().getString())
                            .thenComparing(slot -> screenHandler.getSlot(slot).getStack().getCount(), Comparator.reverseOrder()))
                    .toList();
        } else {
            return inventorySlots.stream()
                    .filter(slot -> !screenHandler.getSlot(slot).getStack().isEmpty())
                    .sorted(Comparator.comparing((Integer slot) -> Item.getRawId(screenHandler.getSlot(slot).getStack().getItem()))
                            .thenComparing(slot -> screenHandler.getSlot(slot).getStack().getCount(), Comparator.reverseOrder()))
                    .toList();
        }

    }

    private static void clearCursor(InventorySlots inventorySlots, ScreenHandler screenHandler) {
        for (int slot : inventorySlots) {
            if (ItemStack.areItemsEqual(screenHandler.getSlot(slot).getStack(), Interaction.getCursorStack())) {
                if (!Interaction.hasEmptyCursor()) {
                    Interaction.clickStack(slot);
                }
            }
        }

        for (int slot : inventorySlots) {
            if (screenHandler.getSlot(slot).getStack().isEmpty()) {
                if (!Interaction.hasEmptyCursor()) {
                    Interaction.clickStack(slot);
                }
            }
        }
    }
}
