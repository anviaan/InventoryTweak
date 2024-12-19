package net.anvian.inventorytweaks.features.durabilityWarning;

import net.anvian.inventorytweaks.InventoryTweak;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class DurabilityWarning implements ClientTickEvents.StartTick {
    private boolean soundPlayed = false;

    @Override
    public void onStartTick(MinecraftClient client) {
        if (InventoryTweak.CONFIG.activateDurabilityWarning()) {
            if (client.player != null) {
                ItemStack itemStack = client.player.getMainHandStack();

                if (!itemStack.isEmpty() && itemStack.isDamaged()) {
                    int durability = itemStack.getMaxDamage() - itemStack.getDamage();
                    float durabilityPercentage = ((float) durability / itemStack.getMaxDamage()) * 100;

                    if (durabilityPercentage <= InventoryTweak.CONFIG.percentageDurabilityWarning()) {
                        if (InventoryTweak.CONFIG.activateDurabilityWarningSound() && !soundPlayed) {
                            float volume = (float) InventoryTweak.CONFIG.durabilityWarningSoundVolume() / 100.0F;
                            client.player.playSoundToPlayer(SoundEvents.ENTITY_IRON_GOLEM_REPAIR, SoundCategory.PLAYERS, volume, 3.0F);
                            soundPlayed = true;
                        }

                        Text text = Text
                                .literal("⚠ ")
                                .append(Text.translatable(InventoryTweak.MOD_ID + ".durabilityWarningMessage"))
                                .append(": ")
                                .append(String.format("%.1f", durabilityPercentage))
                                .append("% ⚠")
                                .formatted(Formatting.RED);
                        client.player.sendMessage(text, true);
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
