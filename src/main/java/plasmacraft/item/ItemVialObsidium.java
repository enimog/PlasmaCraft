package plasmacraft.item;

import net.minecraft.block.Block;

public class ItemVialObsidium extends ItemVial
{

    public ItemVialObsidium()
    {
        super(EnumType.OBSIDIUM);
    }

    @Override
    protected String getName()
    {
        return EnumType.OBSIDIUM.getName();
    }

    @Override
    protected Block getBlock()
    {
        return EnumType.OBSIDIUM.getBlock();
    }

}
