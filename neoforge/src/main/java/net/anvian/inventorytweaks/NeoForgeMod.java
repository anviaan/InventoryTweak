package net.anvian.inventorytweaks;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(InventoryTweak.MOD_ID)
public final class NeoForgeMod {
    public NeoForgeMod(IEventBus modEventBus, ModContainer modContainer, Dist dist) {
        InventoryTweak.init();
        if (dist == Dist.CLIENT) {
            NeoForgeClient.init(modEventBus, modContainer);
        }
    }
}
