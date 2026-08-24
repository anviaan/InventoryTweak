package net.anvian.inventorytweaks.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;

public final class Interaction {
    private static final int LEFT_CLICK = 0;

    private Interaction() {}

    public static ItemStack getCursorStack() {
        return Minecraft.getInstance().player.containerMenu.getCarried();
    }

    public static boolean hasEmptyCursor() {
        return getCursorStack().isEmpty();
    }

    public static void clickStack(int slot) {
        MultiPlayerGameMode gameMode = Minecraft.getInstance().gameMode;
        LocalPlayer player = Minecraft.getInstance().player;
        gameMode.handleInventoryMouseClick(getSyncId(), slot, LEFT_CLICK, ClickType.PICKUP, player);
    }

    public static void swapStacks(int slot, int target) {
        MultiPlayerGameMode gameMode = Minecraft.getInstance().gameMode;
        LocalPlayer player = Minecraft.getInstance().player;
        gameMode.handleInventoryMouseClick(getSyncId(), slot, LEFT_CLICK, ClickType.PICKUP, player);
        gameMode.handleInventoryMouseClick(getSyncId(), target, LEFT_CLICK, ClickType.PICKUP, player);
        if (!Interaction.hasEmptyCursor()) {
            gameMode.handleInventoryMouseClick(getSyncId(), slot, LEFT_CLICK, ClickType.PICKUP, player);
        }
    }

    private static int getSyncId() {
        return Minecraft.getInstance().player.containerMenu.containerId;
    }
}
