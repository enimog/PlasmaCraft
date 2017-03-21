package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plasmacraft.util.CausticPoisoning;

public class BlockAcidBarrier extends BlockPlasmaCraft
{
    protected static final AxisAlignedBB COLLISION_AABB = new AxisAlignedBB(0.01, 0.01, 0.01, 0.99, 1, 0.99);
    
    public BlockAcidBarrier()
    {
        super("acid_barrier", Material.GLASS, 5, 10);
        
        setLightLevel(1.0F);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
    
    @Override
    public boolean isOpaqueCube(IBlockState state) 
    {
        return false;
    }
    
    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn)
    {
        CausticPoisoning.DoCausticPoisoning(entityIn);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {
        CausticPoisoning.DoCausticPoisoning(entityIn);
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return COLLISION_AABB;
    }
}
