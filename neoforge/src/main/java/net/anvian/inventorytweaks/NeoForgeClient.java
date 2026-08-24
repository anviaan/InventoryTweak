package net.anvian.inventorytweaks;

import me.shedaniel.autoconfig.AutoConfigClient;
import net.anvian.inventorytweaks.config.ModConfig;
import net.anvian.inventorytweaks.features.durability_warning.DurabilityWarning;
import net.anvian.inventorytweaks.handler.ModKeyBinding;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

public final class NeoForgeClient {
    private NeoForgeClient() {}

    public static void init(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(NeoForgeClient::registerKeyMappings);
        NeoForge.EVENT_BUS.addListener(NeoForgeClient::onClientTick);
        modContainer.registerExtensionPoint(
                IConfigScreenFactory.class,
                (container, parent) -> AutoConfigClient.getConfigScreen(ModConfig.class, parent).get());
    }

    private static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.registerCategory(ModKeyBinding.CATEGORY);
        event.register(ModKeyBinding.KEY_BINDING);
    }

    private static void onClientTick(ClientTickEvent.Post event) {
        DurabilityWarning.onStartTick(Minecraft.getInstance());
    }
}
