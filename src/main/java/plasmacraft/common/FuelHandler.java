package plasmacraft.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import plasmacraft.PlasmaCraft;

public class FuelHandler implements IFuelHandler
{
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        if(fuel.getItem() == PlasmaCraft.ITEM_VIAL_NETHERFLOW)
        {
            return 500000;
        }
        else if (fuel.getItem() == PlasmaCraft.ITEM_THERMOPELLET)
        {
            return 1000000;
        }

        return 0;
    }
}
