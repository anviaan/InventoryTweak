package net.anvian.inventorytweaks.mixin;

import net.anvian.inventorytweaks.features.sort.SortInventory;
import net.anvian.inventorytweaks.handler.ModKeyBinding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public class MixinKeyInputHandler {
    @Inject(method = "keyPressed", at = @At("HEAD"))
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (ModKeyBinding.keyBinding.matchesKey(keyCode, scanCode)) {
            inventoryTweakSortingKeyPressed();
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"))
    private void onMouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (ModKeyBinding.keyBinding.matchesMouse(button)) {
            inventoryTweakSortingKeyPressed();
        }
    }

    @Unique
    private void inventoryTweakSortingKeyPressed() {
        if (MinecraftClient.getInstance().player == null) return;

        ScreenHandler screenHandler = MinecraftClient.getInstance().player.currentScreenHandler;
        Screen screen = MinecraftClient.getInstance().currentScreen;

        if (screen instanceof InventoryScreen || screen instanceof CreativeInventoryScreen) {
            SortInventory.sortPlayerInventory(screenHandler, screen);
        } else {
            SortInventory.sortContainerInventory(screenHandler, screen);
        }
    }
}
