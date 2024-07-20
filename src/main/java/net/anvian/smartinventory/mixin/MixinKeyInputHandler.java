package net.anvian.smartinventory.mixin;

import net.anvian.smartinventory.event.KeyInput;
import net.anvian.smartinventory.sort.SortInventory;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public class MixinKeyInputHandler {
    @Inject(method = "keyPressed", at = @At("HEAD"))
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (keyCode == KeyInput.keyBinding.getDefaultKey().getCode()) {
            MinecraftClient client = MinecraftClient.getInstance();
            Screen screen = client.currentScreen;

            if (screen instanceof InventoryScreen || screen instanceof CreativeInventoryScreen) {
                SortInventory.sortPlayerInventory(client);
            }
        }
    }
}
