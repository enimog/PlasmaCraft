package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockOreRadionite extends BlockOre
{
    public BlockOreRadionite()
    {
        super(BlockOre.EnumType.RADIONITE, Material.ROCK, 4, 8, 3);
    }

    @Override
    public String getSpecialName()
    {
        return BlockOre.EnumType.RADIONITE.getName();
    }

    @Override
    public int getMetadata()
    {
        return BlockOre.EnumType.RADIONITE.getID();
    }

    @Override
    public IBlockState getOreType()
    {
        return getDefaultState().withProperty(ENUM_ORE_TYPE, BlockOre.EnumType.RADIONITE);
    }
}
