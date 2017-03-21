package plasmacraft.item;

import net.minecraft.block.Block;

public class ItemVialAcid extends ItemVial
{

    public ItemVialAcid()
    {
        super(EnumType.ACID);
    }

    @Override
    protected String getName()
    {
        return EnumType.ACID.getName();
    }

    @Override
    protected Block getBlock()
    {
        return EnumType.ACID.getBlock();
    }

}
