package net.anvian.inventorytweaks;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.anvian.anvianslib.util.LibUtil;
import net.anvian.inventorytweaks.config.ModConfig;
import net.anvian.inventorytweaks.features.durabilityWarning.DurabilityWarning;
import net.anvian.inventorytweaks.handler.ModKeyBinding;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InventoryTweak implements ClientModInitializer {
    public static final String MOD_ID = "inventorytweak";
    public static final String MOD_NAME = "Inventory Tweak";
    public static final String MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).get().getMetadata().getVersion().getFriendlyString();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ModConfig CONFIG;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello from " + MOD_NAME + "!");

        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        LibUtil.setupTelemetry(MOD_ID, MOD_VERSION);

        ModKeyBinding.register();
        ClientTickEvents.START_CLIENT_TICK.register(new DurabilityWarning());
    }
}