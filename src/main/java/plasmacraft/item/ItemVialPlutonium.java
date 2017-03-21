package plasmacraft.item;

import net.minecraft.block.Block;

public class ItemVialPlutonium extends ItemVial
{

    public ItemVialPlutonium()
    {
        super(EnumType.PLUTONIUM);
    }

    @Override
    protected String getName()
    {
        return EnumType.PLUTONIUM.getName();
    }

    @Override
    protected Block getBlock()
    {
        return EnumType.PLUTONIUM.getBlock();
    }

}
