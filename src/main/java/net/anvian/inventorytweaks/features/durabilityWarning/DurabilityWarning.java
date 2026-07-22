package net.anvian.inventorytweaks.features.durabilityWarning;

import net.anvian.inventorytweaks.InventoryTweak;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;

public class DurabilityWarning implements ClientTickEvents.StartTick {
    private boolean soundPlayed = false;

    @Override
    public void onStartTick(Minecraft client) {
        if (InventoryTweak.CONFIG.activateDurabilityWarning) {
            if (client.player != null) {
                ItemStack itemStack = client.player.getMainHandItem();

                if (!itemStack.isEmpty() && itemStack.isDamaged()) {
                    int durability = itemStack.getMaxDamage() - itemStack.getDamageValue();
                    float durabilityPercentage = ((float) durability / itemStack.getMaxDamage()) * 100;

                    if (durabilityPercentage <= InventoryTweak.CONFIG.percentageDurabilityWarning) {
                        if (InventoryTweak.CONFIG.activateDurabilityWarningSound && !soundPlayed) {
                            float volume = (float) InventoryTweak.CONFIG.durabilityWarningSoundVolume / 100.0F;
                            client.player.playSound(SoundEvents.IRON_GOLEM_REPAIR, volume, 3.0F);
                            soundPlayed = true;
                        }

                        Component text = Component
                                .literal("⚠ ")
                                .append(Component.translatable(InventoryTweak.MOD_ID + ".durabilityWarningMessage"))
                                .append(": ")
                                .append(String.format("%.1f", durabilityPercentage))
                                .append("% ⚠")
                                .withStyle(ChatFormatting.RED);
                        client.player.sendOverlayMessage(text);
                    } else {
                        soundPlayed = false;
                    }
                } else {
                    soundPlayed = false;
                }
            }
        }
    }
}
