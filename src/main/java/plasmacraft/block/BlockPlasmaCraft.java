package plasmacraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import plasmacraft.PlasmaCraft;

public class BlockPlasmaCraft extends Block
{

    public BlockPlasmaCraft(String unlocalizedName, Material materialIn, float hardnessIn, float resistance)
    {
        super(materialIn);
        
        setUnlocalizedName(unlocalizedName);
        setRegistryName(PlasmaCraft.MODID, unlocalizedName);
        setCreativeTab(PlasmaCraft.creativeTab);
        setHardness(hardnessIn);
        setResistance(resistance);
    }

}
