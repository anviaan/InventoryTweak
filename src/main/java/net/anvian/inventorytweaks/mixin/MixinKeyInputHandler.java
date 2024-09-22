package net.anvian.inventorytweaks.mixin;

import net.anvian.inventorytweaks.InventoryTweak;
import net.anvian.inventorytweaks.handler.ModKeyBinding;
import net.anvian.inventorytweaks.sort.SortInventory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.screen.ScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public class MixinKeyInputHandler {
    @Inject(method = "keyPressed", at = @At("HEAD"))
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (InventoryTweak.CONFIG.activateSortingInventory()) {
            if (ModKeyBinding.keyBinding.matchesKey(keyCode, scanCode)) {
                ScreenHandler screenHandler = MinecraftClient.getInstance().player.currentScreenHandler;
                Screen screen = MinecraftClient.getInstance().currentScreen;

                if (screen instanceof InventoryScreen || screen instanceof CreativeInventoryScreen) {
                    SortInventory.sortPlayerInventory(screenHandler);
                } else {
                    SortInventory.sortContainerInventory(screenHandler);
                }
            }
        }
    }
}
