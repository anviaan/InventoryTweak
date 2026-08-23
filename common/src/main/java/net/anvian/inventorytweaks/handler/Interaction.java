package net.anvian.inventorytweaks.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.item.ItemStack;

public class Interaction {
    private Interaction() {}

    private static final int LEFT_CLICK = 0;

    public static ItemStack getCursorStack() {
        return Minecraft.getInstance().player.containerMenu.getCarried();
    }

    public static boolean hasEmptyCursor() {
        return getCursorStack().isEmpty();
    }

    public static void clickStack(int slot) {
        MultiPlayerGameMode gameMode = Minecraft.getInstance().gameMode;
        LocalPlayer player = Minecraft.getInstance().player;
        gameMode.handleContainerInput(getSyncId(), slot, LEFT_CLICK, ContainerInput.PICKUP, player);
    }

    public static void swapStacks(int slot, int target) {
        MultiPlayerGameMode gameMode = Minecraft.getInstance().gameMode;
        LocalPlayer player = Minecraft.getInstance().player;
        gameMode.handleContainerInput(getSyncId(), slot, LEFT_CLICK, ContainerInput.PICKUP, player);
        gameMode.handleContainerInput(getSyncId(), target, LEFT_CLICK, ContainerInput.PICKUP, player);
        if (!Interaction.hasEmptyCursor()) {
            gameMode.handleContainerInput(getSyncId(), slot, LEFT_CLICK, ContainerInput.PICKUP, player);
        }
    }

    private static int getSyncId() {
        return Minecraft.getInstance().player.containerMenu.containerId;
    }
}
