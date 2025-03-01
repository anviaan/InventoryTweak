package net.anvian.inventorytweaks;

import net.anvian.anvianslib.config.TelemetryConfigManager;
import net.anvian.anvianslib.util.LibUtil;
import net.anvian.inventorytweaks.config.InventoryTweakConfig;
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
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final InventoryTweakConfig CONFIG = InventoryTweakConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello from " + MOD_NAME + "!");

        LibUtil.generateConfigPath(MOD_ID, FabricLoader.getInstance().getConfigDir());

        TelemetryConfigManager.initialize(FabricLoader.getInstance().getConfigDir().resolve(MOD_ID).toFile());
        if (TelemetryConfigManager.getConfig().enableTelemetry) {
            TelemetryConfigManager.sendTelemetryData(
                    MOD_ID,
                    "3.1",
                    LibUtil.getMinecraftVersion(),
                    "Fabric",
                    !FabricLoader.getInstance().isDevelopmentEnvironment()
            );
        }

        ModKeyBinding.register();
        ClientTickEvents.START_CLIENT_TICK.register(new DurabilityWarning());
    }
}