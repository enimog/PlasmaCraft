package plasmacraft.block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;

public class BlockCryonite extends BlockFluidPlasmaCraft
{
    private final Color LiquidColor = new Color(15, 227, 255);
    
    public BlockCryonite()
    {
        super(PlasmaCraft.FLUID_CRYONITE);
    }

    @Override
    public int getFluidFlag()
    {
        return FLAG_CRYONITE;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        List<BlockPos> blocks = new ArrayList<BlockPos>();
        blocks.add(pos.add(-1,0,0));
        blocks.add(pos.add(1,0,0));
        blocks.add(pos.add(0,-1,0));
        blocks.add(pos.add(0,0,-1));
        blocks.add(pos.add(0,0,1));
        
        for (BlockPos blockpos : blocks)
        {
            // Freeze ALL the things
            IBlockState blockstate = worldIn.getBlockState(blockpos);
            Material material = blockstate.getMaterial();
            
            if (blockstate.getMaterial().isSolid() && blockstate.getBlockHardness(worldIn, blockpos) >= 0 && material != Material.PORTAL)
            {
                worldIn.setBlockState(blockpos, Blocks.PACKED_ICE.getDefaultState());
            }
        }
        
        super.onBlockAdded(worldIn, pos, state);
    }
    
    @Override
    public Color getColor()
    {
        return LiquidColor;
    }
}
