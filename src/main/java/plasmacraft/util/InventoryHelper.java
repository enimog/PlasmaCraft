package plasmacraft.util;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class InventoryHelper {
    public static boolean consumeInventoryItem(InventoryPlayer inventoryPlayer, ItemStack stack)
    {
        int slot = inventoryPlayer.getSlotFor(stack);
        
        if (slot >= 0)
        {
            --inventoryPlayer.getStackInSlot(slot).stackSize;
            return true;
        }

        return false;
    }
}
