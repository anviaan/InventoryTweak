package net.anvian.inventorytweaks;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.anvian.anvianslib.util.LibUtil;
import net.anvian.inventorytweaks.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class InventoryTweak {
    public static final String MOD_ID = "inventorytweak";
    public static final String MOD_NAME = "Inventory Tweak";
    public static final String MOD_VERSION = "3.5";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static ModConfig CONFIG;

    private InventoryTweak() {}

    public static void init() {
        LOGGER.info("Hello from " + MOD_NAME + "!");

        AutoConfig.register(ModConfig.class, Toml4jConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

        LibUtil.setupTelemetry(MOD_ID, MOD_VERSION);
    }
}
