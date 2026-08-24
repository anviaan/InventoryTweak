package net.anvian.inventorytweaks.slots;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.inventory.InventoryMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InventorySlots extends ArrayList<Integer> {

    public InventorySlots(int from, int to) {
        super(IntStream.range(from, to).boxed().toList());
    }

    public InventorySlots(int from, int to, int offhand) {
        super(IntStream.concat(IntStream.range(from, to), IntStream.of(offhand))
                .boxed()
                .toList());
    }

    public InventorySlots(List<Integer> slots) {
        super(slots);
    }

    public int getLastSlot() {
        return this.get(this.size() - 1);
    }

    public InventorySlots excludeOffhand() {
        Screen screen = Minecraft.getInstance().screen;
        if (screen instanceof InventoryScreen || screen instanceof CreativeModeInventoryScreen) {
            this.remove(Integer.valueOf(InventoryMenu.SHIELD_SLOT));
            return new InventorySlots(this);
        }
        return this;
    }
}
