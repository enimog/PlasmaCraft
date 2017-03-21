package plasmacraft.item;

import net.minecraft.block.Block;

public class ItemVialNeptunium extends ItemVial
{

    public ItemVialNeptunium()
    {
        super(EnumType.NEPTUNIUM);
    }

    @Override
    protected String getName()
    {
        return EnumType.NEPTUNIUM.getName();
    }

    @Override
    protected Block getBlock()
    {
        return EnumType.NEPTUNIUM.getBlock();
    }

}
