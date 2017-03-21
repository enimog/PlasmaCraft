package plasmacraft.item;

import net.minecraft.block.Block;

public class ItemVialRadionite extends ItemVial
{

    public ItemVialRadionite()
    {
        super(EnumType.RADIONITE);
    }

    @Override
    protected String getName()
    {
        return EnumType.RADIONITE.getName();
    }

    @Override
    protected Block getBlock()
    {
        return EnumType.RADIONITE.getBlock();
    }

}
