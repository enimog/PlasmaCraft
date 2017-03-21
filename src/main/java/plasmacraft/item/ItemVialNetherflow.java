package plasmacraft.item;

import net.minecraft.block.Block;

public class ItemVialNetherflow extends ItemVial
{

    public ItemVialNetherflow()
    {
        super(EnumType.NETHERFLOW);
    }

    @Override
    protected String getName()
    {
        return EnumType.NETHERFLOW.getName();
    }

    @Override
    protected Block getBlock()
    {
        return EnumType.NETHERFLOW.getBlock();
    }

}
