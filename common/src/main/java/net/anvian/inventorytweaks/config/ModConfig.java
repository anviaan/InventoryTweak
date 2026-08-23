package net.anvian.inventorytweaks.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.anvian.inventorytweaks.InventoryTweak;

@SuppressWarnings("unused")
@Config(name = InventoryTweak.MOD_ID + "/" + InventoryTweak.MOD_ID)
public class ModConfig implements ConfigData {
    @ConfigEntry.Category("sortingInventorySection")
    public boolean activateSortingInventory = true;

    @ConfigEntry.Category("sortingInventorySection")
    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public SortType sortType = SortType.TYPE;

    public enum SortType {
        TYPE,
        NAME,
        RARITY
    }

    @ConfigEntry.Category("durabilityWarningSection")
    public boolean activateDurabilityWarning = true;

    @ConfigEntry.Category("durabilityWarningSection")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public byte percentageDurabilityWarning = 10;

    @ConfigEntry.Category("durabilityWarningSection")
    public boolean activateDurabilityWarningSound = true;

    @ConfigEntry.Category("durabilityWarningSection")
    @ConfigEntry.BoundedDiscrete(min = 0, max = 100)
    public int durabilityWarningSoundVolume = 100;
}
