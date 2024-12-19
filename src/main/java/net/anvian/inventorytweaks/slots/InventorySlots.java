package net.anvian.inventorytweaks.slots;

import net.anvian.inventorytweaks.InventoryTweak;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.screen.PlayerScreenHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class InventorySlots extends ArrayList<Integer> {

    public InventorySlots(int from, int to) {
        super(IntStream.range(from, to).boxed().toList());
    }

    public InventorySlots(int from, int to, int offhand) {
        super(IntStream.concat(IntStream.range(from, to), IntStream.of(offhand)).boxed().toList());
    }

    public InventorySlots(List<Integer> slots) {
        super(slots);
    }

    public int getLastSlot() {
        return this.get(this.size() - 1);
    }

    public InventorySlots excludeOffhand() {
        Screen screen = MinecraftClient.getInstance().currentScreen;
        if (InventoryTweak.isValidScreen(screen)) {
            this.remove(Integer.valueOf(PlayerScreenHandler.OFFHAND_ID));
            return new InventorySlots(this);
        }
        return this;
    }
}
