package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockOreObsidium extends BlockOre
{
    public BlockOreObsidium()
    {
        super(BlockOre.EnumType.OBSIDIUM, Material.ROCK, 15, 1800, 3);
    }

    @Override
    public String getSpecialName()
    {
        return BlockOre.EnumType.OBSIDIUM.getName();
    }

    @Override
    public int getMetadata()
    {
        // TODO Auto-generated method stub
        return BlockOre.EnumType.OBSIDIUM.getID();
    }

    @Override
    public IBlockState getOreType()
    {
        return getDefaultState().withProperty(ENUM_ORE_TYPE, BlockOre.EnumType.OBSIDIUM);
    }
}
