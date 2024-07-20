package net.anvian.smartinventory.sort;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortInventory {
    private static final int HOTBAR_SIZE = 9;

    public static void sortPlayerInventory(MinecraftClient client) {
        PlayerEntity player = client.player;
        if (player == null) return;

        DefaultedList<ItemStack> inventory = player.getInventory().main;
        Map<String, ItemStack> groupedItems = groupItems(inventory, HOTBAR_SIZE);
        DefaultedList<ItemStack> sortedInventory = sortAndFilterInventory(groupedItems);

        updateInventory(inventory, sortedInventory, HOTBAR_SIZE);
        player.sendMessage(Text.of("Inventario ordenado!"), false);
    }

    private static Map<String, ItemStack> groupItems(DefaultedList<ItemStack> inventory, int start) {
        Map<String, ItemStack> groupedItems = new HashMap<>();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return groupedItems;

        for (int i = start; i < inventory.size(); i++) {
            ItemStack stack = inventory.get(i);
            if (!stack.isEmpty()) {
                String key = stack.getItem().toString();
                groupedItems.merge(key, stack, SortInventory::mergeStacks);
            }
        }
        return groupedItems;
    }

    private static ItemStack mergeStacks(ItemStack existing, ItemStack incoming) {
        int transferAmount = Math.min(incoming.getCount(), existing.getMaxCount() - existing.getCount());
        existing.increment(transferAmount);
        incoming.decrement(transferAmount);
        return existing.getCount() == 0 ? incoming : existing;
    }

    private static DefaultedList<ItemStack> sortAndFilterInventory(Map<String, ItemStack> groupedItems) {
        List<ItemStack> sortedList = groupedItems.values().stream()
                .sorted(Comparator.comparing(stack -> stack.getItem().getName(stack).getString()))
                .toList();

        return DefaultedList.copyOf(ItemStack.EMPTY, sortedList.toArray(new ItemStack[0]));
    }

    private static void updateInventory(DefaultedList<ItemStack> inventory, DefaultedList<ItemStack> sortedInventory, int start) {
        for (int i = start; i < inventory.size(); i++) {
            inventory.set(i, i - start < sortedInventory.size() ? sortedInventory.get(i - start) : ItemStack.EMPTY);
        }
    }
}
