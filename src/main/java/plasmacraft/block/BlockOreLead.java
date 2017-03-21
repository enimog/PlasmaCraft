package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockOreLead extends BlockOre
{
    public BlockOreLead()
    {
        super(BlockOre.EnumType.LEAD, Material.ROCK, 5, 20, 2);
    }

    @Override
    public String getSpecialName()
    {
        return BlockOre.EnumType.LEAD.getName();
    }

    @Override
    public int getMetadata()
    {
        // TODO Auto-generated method stub
        return BlockOre.EnumType.LEAD.getID();
    }

    @Override
    public IBlockState getOreType()
    {
        return getDefaultState().withProperty(ENUM_ORE_TYPE, BlockOre.EnumType.LEAD);
    }
}
