package net.anvian.inventorytweaks.mixin;

import net.anvian.inventorytweaks.features.sort.SortInventory;
import net.anvian.inventorytweaks.handler.ModKeyBinding;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.ItemGroup;
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

//        Only for debugging
//        System.out.println(screen.getClass().getName());

        if (screen instanceof InventoryScreen) {
            SortInventory.sortPlayerInventory(screenHandler);
        } else if (screen instanceof CreativeInventoryScreen creativeInventoryScreen) {
            ItemGroup.Type type = creativeInventoryScreen.getSelectedItemGroup().getType();
            if (type == ItemGroup.Type.CATEGORY || type == ItemGroup.Type.SEARCH) return;
            SortInventory.sortPlayerInventory(screenHandler);
        } else {
            SortInventory.sortContainerInventory(screenHandler);
        }
    }
}
