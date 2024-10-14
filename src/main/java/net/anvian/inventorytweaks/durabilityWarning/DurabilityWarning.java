package net.anvian.inventorytweaks.durabilityWarning;

import net.anvian.inventorytweaks.InventoryTweak;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class DurabilityWarning implements ClientTickEvents.StartTick {
    @Override
    public void onStartTick(MinecraftClient client) {
        if (InventoryTweak.CONFIG.activateDurabilityWarning()) {
            if (client.player != null) {
                ItemStack itemStack = client.player.getMainHandStack();

                if (!itemStack.isEmpty() && itemStack.isDamaged()) {
                    int durability = itemStack.getMaxDamage() - itemStack.getDamage();
                    float durabilityPercentage = ((float) durability / itemStack.getMaxDamage()) * 100;

                    if (durabilityPercentage <= InventoryTweak.CONFIG.percentageDurabilityWarning()) {
                        Text text = Text
                                .literal("⚠ ")
                                .append(Text.translatable(InventoryTweak.MOD_ID + ".durabilityWarningMessage"))
                                .append(": ")
                                .append(String.format("%.1f", durabilityPercentage))
                                .append("% ⚠")
                                .formatted(Formatting.RED);
                        client.player.sendMessage(text, true);
                    }
                }
            }
        }
    }
}
