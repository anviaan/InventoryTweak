package net.anvian.inventorytweaks.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.config.annotation.RangeConstraint;
import io.wispforest.owo.config.annotation.SectionHeader;
import net.anvian.inventorytweaks.InventoryTweak;

@SuppressWarnings("unused")
@Modmenu(modId = InventoryTweak.MOD_ID)
@Config(name = InventoryTweak.MOD_ID + "-config", wrapperName = "InventoryTweakConfig")
public class ModConfig {
    @SectionHeader("sortingInventorySection")
    public boolean activateSortingInventory = true;
    public SortType sortType = SortType.NAME;
    public enum SortType {
        NAME, TYPE
    }

    @SectionHeader("durabilityWarningSection")
    public boolean activateDurabilityWarning = true;
    @RangeConstraint(min = 0, max = 100)
    public byte percentageDurabilityWarning = 10;
    public boolean activateDurabilityWarningSound = true;
}
