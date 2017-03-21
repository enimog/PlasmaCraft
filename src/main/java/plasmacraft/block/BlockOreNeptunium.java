package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockOreNeptunium extends BlockOre
{
    public BlockOreNeptunium()
    {
        super(BlockOre.EnumType.NEPTUNIUM, Material.ROCK, 5, 20, 3);
    }

    @Override
    public String getSpecialName()
    {
        return BlockOre.EnumType.NEPTUNIUM.getName();
    }

    @Override
    public int getMetadata()
    {
        // TODO Auto-generated method stub
        return BlockOre.EnumType.NEPTUNIUM.getID();
    }

    @Override
    public IBlockState getOreType()
    {
        return getDefaultState().withProperty(ENUM_ORE_TYPE, BlockOre.EnumType.NEPTUNIUM);
    }
}
