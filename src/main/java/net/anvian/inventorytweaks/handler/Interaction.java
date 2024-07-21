package net.anvian.inventorytweaks.handler;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;

public class Interaction {
    private static final int LEFT_CLICK = 0;

    public static ItemStack getCursorStack() {
        return MinecraftClient.getInstance().player.currentScreenHandler.getCursorStack();
    }

    public static boolean hasEmptyCursor() {
        return getCursorStack().isEmpty();
    }

    public static void clickStack(int slot) {
        ClientPlayerInteractionManager manager = MinecraftClient.getInstance().interactionManager;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        manager.clickSlot(getSyncId(), slot, LEFT_CLICK, SlotActionType.PICKUP, player);
    }

    public static void swapStacks(int slot, int target) {
        ClientPlayerInteractionManager manager = MinecraftClient.getInstance().interactionManager;
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        manager.clickSlot(getSyncId(), slot, LEFT_CLICK, SlotActionType.PICKUP, player);
        manager.clickSlot(getSyncId(), target, LEFT_CLICK, SlotActionType.PICKUP, player);
        if (!Interaction.hasEmptyCursor()) {
            manager.clickSlot(getSyncId(), slot, LEFT_CLICK, SlotActionType.PICKUP, player);
        }
    }

    private static int getSyncId() {
        return MinecraftClient.getInstance().player.currentScreenHandler.syncId;
    }
}
