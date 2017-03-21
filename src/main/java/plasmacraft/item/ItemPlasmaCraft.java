package plasmacraft.item;

import net.minecraft.item.Item;
import plasmacraft.PlasmaCraft;

public class ItemPlasmaCraft extends Item
{   
    public ItemPlasmaCraft(String unlocalizedName) 
    {
        super();
        
        setUnlocalizedName(unlocalizedName);
        setRegistryName(PlasmaCraft.MODID, unlocalizedName);
        setCreativeTab(PlasmaCraft.creativeTab);
    }
}
