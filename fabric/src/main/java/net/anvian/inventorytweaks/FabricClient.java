package net.anvian.inventorytweaks;

import net.anvian.inventorytweaks.features.durability_warning.DurabilityWarning;
import net.anvian.inventorytweaks.handler.ModKeyBinding;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public final class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InventoryTweak.init();
        KeyBindingHelper.registerKeyBinding(ModKeyBinding.KEY_BINDING);
        ClientTickEvents.START_CLIENT_TICK.register(DurabilityWarning::onStartTick);
    }
}
