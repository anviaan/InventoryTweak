package net.anvian.inventorytweaks.features.durability_warning;

import net.anvian.inventorytweaks.InventoryTweak;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;

public final class DurabilityWarning {
    private static boolean soundPlayed;

    private DurabilityWarning() {}

    public static void onStartTick(Minecraft client) {
        if (!InventoryTweak.CONFIG.activateDurabilityWarning || client.player == null) {
            return;
        }

        ItemStack itemStack = client.player.getMainHandItem();
        if (itemStack.isEmpty() || !itemStack.isDamaged()) {
            soundPlayed = false;
            return;
        }

        float durabilityPercentage = getDurabilityPercentage(itemStack);
        if (durabilityPercentage > InventoryTweak.CONFIG.percentageDurabilityWarning) {
            soundPlayed = false;
            return;
        }

        if (InventoryTweak.CONFIG.activateDurabilityWarningSound && !soundPlayed) {
            float volume = InventoryTweak.CONFIG.durabilityWarningSoundVolume / 100.0F;
            client.player.playSound(SoundEvents.IRON_GOLEM_REPAIR, volume, 3.0F);
            soundPlayed = true;
        }

        client.player.sendOverlayMessage(buildWarningMessage(durabilityPercentage));
    }

    private static float getDurabilityPercentage(ItemStack itemStack) {
        int durability = itemStack.getMaxDamage() - itemStack.getDamageValue();
        return ((float) durability / itemStack.getMaxDamage()) * 100;
    }

    private static Component buildWarningMessage(float durabilityPercentage) {
        return Component.literal("⚠ ")
                .append(Component.translatable(InventoryTweak.MOD_ID + ".durabilityWarningMessage"))
                .append(": ")
                .append(String.format("%.1f", durabilityPercentage))
                .append("% ⚠")
                .withStyle(ChatFormatting.RED);
    }
}
