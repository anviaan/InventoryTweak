package net.anvian.inventorytweaks.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import net.anvian.inventorytweaks.InventoryTweak;

@SuppressWarnings("unused")
@Modmenu(modId = InventoryTweak.MOD_ID)
@Config(name = "inventorytweak-config", wrapperName = "InventoryTweakConfig")
public class ModConfig {
    public SortType sortType = SortType.NAME;

    public enum SortType {
        NAME, TYPE
    }
}
