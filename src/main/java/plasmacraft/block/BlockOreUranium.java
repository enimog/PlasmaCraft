package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockOreUranium extends BlockOre
{
    public BlockOreUranium()
    {
        super(BlockOre.EnumType.URANIUM, Material.ROCK, 5, 12, 2);
    }

    @Override
    public String getSpecialName()
    {
        return BlockOre.EnumType.URANIUM.getName();
    }

    @Override
    public int getMetadata()
    {
        // TODO Auto-generated method stub
        return BlockOre.EnumType.URANIUM.getID();
    }

    @Override
    public IBlockState getOreType()
    {
        return getDefaultState().withProperty(ENUM_ORE_TYPE, BlockOre.EnumType.URANIUM);
    }
}
