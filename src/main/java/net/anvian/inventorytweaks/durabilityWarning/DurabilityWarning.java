package net.anvian.inventorytweaks.durabilityWarning;

import net.anvian.inventorytweaks.InventoryTweak;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class DurabilityWarning implements ClientTickEvents.EndTick {
    @Override
    public void onEndTick(MinecraftClient client) {
        if (InventoryTweak.CONFIG.activateDurabilityWarning()) {
            if (client.player != null) {
                ItemStack itemStack = client.player.getMainHandStack();

                if (itemStack.isDamageable()) {
                    int maxDurability = itemStack.getMaxDamage();
                    int currentDurability = maxDurability - itemStack.getDamage();
                    int maxDurabilityWarning = maxDurability * (InventoryTweak.CONFIG.percentageDurabilityWarning()) / 100;

                    if (currentDurability == maxDurability * maxDurabilityWarning) {
                        client.player.playSoundToPlayer(SoundEvents.ENTITY_ENDER_DRAGON_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    }

                    if (currentDurability < maxDurability * maxDurabilityWarning) {
                        Text durabilityWaringMessage = Text.translatable(InventoryTweak.MOD_ID + ".durabilityWarningMessage").formatted(Formatting.RED);
                        client.player.sendMessage(durabilityWaringMessage, true);
                    }
                }
            }
        }
    }
}
