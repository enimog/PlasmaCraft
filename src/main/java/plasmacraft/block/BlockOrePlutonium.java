package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockOrePlutonium extends BlockOre
{
    public BlockOrePlutonium()
    {
        super(BlockOre.EnumType.PLUTONIUM, Material.ROCK, 5, 20, 3);
    }

    @Override
    public String getSpecialName()
    {
        return BlockOre.EnumType.PLUTONIUM.getName();
    }

    @Override
    public int getMetadata()
    {
        // TODO Auto-generated method stub
        return BlockOre.EnumType.PLUTONIUM.getID();
    }

    @Override
    public IBlockState getOreType()
    {
        return getDefaultState().withProperty(ENUM_ORE_TYPE, BlockOre.EnumType.PLUTONIUM);
    }
}
