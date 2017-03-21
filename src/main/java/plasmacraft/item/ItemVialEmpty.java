package plasmacraft.item;

import net.minecraft.block.Block;

public class ItemVialEmpty extends ItemVial
{

    public ItemVialEmpty()
    {
        super(EnumType.EMPTY);
    }

    @Override
    protected String getName()
    {
        return EnumType.EMPTY.getName();
    }

    @Override
    protected Block getBlock()
    {
        return EnumType.EMPTY.getBlock();
    }

}
