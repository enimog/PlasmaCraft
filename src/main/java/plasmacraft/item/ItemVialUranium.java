package plasmacraft.item;

import net.minecraft.block.Block;

public class ItemVialUranium extends ItemVial
{

    public ItemVialUranium()
    {
        super(EnumType.URANIUM);
    }

    @Override
    protected String getName()
    {
        return EnumType.URANIUM.getName();
    }

    @Override
    protected Block getBlock()
    {
        return EnumType.URANIUM.getBlock();
    }

}
