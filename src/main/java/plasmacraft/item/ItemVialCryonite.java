package plasmacraft.item;

import net.minecraft.block.Block;

public class ItemVialCryonite extends ItemVial
{

    public ItemVialCryonite()
    {
        super(EnumType.CRYONITE);
    }
    
    @Override
    protected String getName()
    {
        return EnumType.CRYONITE.getName();
    }

    @Override
    protected Block getBlock()
    {
        return EnumType.CRYONITE.getBlock();
    }

}
