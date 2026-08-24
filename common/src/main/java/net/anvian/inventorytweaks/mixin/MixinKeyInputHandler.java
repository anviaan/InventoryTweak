package net.anvian.inventorytweaks.mixin;

import net.anvian.inventorytweaks.features.sort.SortInventory;
import net.anvian.inventorytweaks.handler.ModKeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.CreativeModeTab;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public class MixinKeyInputHandler {

    @Inject(method = "keyPressed", at = @At("HEAD"))
    private void onKeyPressed(KeyEvent event, CallbackInfoReturnable<Boolean> cir) {
        if (ModKeyBinding.KEY_BINDING.matches(event)) {
            inventoryTweakSortingKeyPressed();
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    private void onMouseClicked(MouseButtonEvent event, boolean doubleClick, CallbackInfoReturnable<Boolean> cir) {
        if (ModKeyBinding.KEY_BINDING.matchesMouse(event)) {
            inventoryTweakSortingKeyPressed();
        }
    }

    @Unique
    private void inventoryTweakSortingKeyPressed() {
        if (Minecraft.getInstance().player == null) return;

        AbstractContainerMenu screenHandler = Minecraft.getInstance().player.containerMenu;
        Screen screen = Minecraft.getInstance().screen;

        if (screen instanceof InventoryScreen) {
            SortInventory.sortPlayerInventory(screenHandler);
        } else if (screen instanceof CreativeModeInventoryScreen) {
            CreativeModeTab.Type type = ((CreativeModeInventoryScreenAccessor) screen)
                    .getSelectedTab()
                    .getType();
            if (type == CreativeModeTab.Type.CATEGORY || type == CreativeModeTab.Type.SEARCH) return;
            SortInventory.sortPlayerInventory(screenHandler);
        } else {
            SortInventory.sortContainerInventory(screenHandler);
        }
    }
}
