package plasmacraft.block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import plasmacraft.PlasmaCraft;
import plasmacraft.util.CausticPoisoning;

public abstract class BlockFluidPlasmaCraft  extends BlockFluidClassic
{
    public static final int FLAG_ACID          = 0b0000000001;
    public static final int FLAG_CRYONITE      = 0b0000000010;
    public static final int FLAG_NEPTUNIUM     = 0b0000000100;
    public static final int FLAG_NETHERFLOW    = 0b0000001000;
    public static final int FLAG_OBSIDIUM      = 0b0000010000;
    public static final int FLAG_PLUTONIUM     = 0b0000100000;
    public static final int FLAG_RADIONITE     = 0b0001000000;
    public static final int FLAG_URANIUM       = 0b0010000000;
    protected static final int FLAG_LAVA       = 0b0100000000;
    protected static final int FLAG_WATER      = 0b1000000000;
    
    private static final Map<Integer, Block> MapBlockHarden = new HashMap<Integer, Block>() {
        private static final long serialVersionUID = 6118554374151187864L;
    {
        put(FLAG_ACID + FLAG_CRYONITE, Blocks.COBBLESTONE);
        put(FLAG_RADIONITE + FLAG_NETHERFLOW, Blocks.GLOWSTONE);
        put(FLAG_PLUTONIUM + FLAG_NETHERFLOW, Blocks.NETHERRACK);
        put(FLAG_NEPTUNIUM + FLAG_NETHERFLOW, Blocks.NETHERRACK);
        put(FLAG_NEPTUNIUM + FLAG_URANIUM, Blocks.SOUL_SAND);
        put(FLAG_NETHERFLOW + FLAG_URANIUM, Blocks.SOUL_SAND);
        put(FLAG_NETHERFLOW + FLAG_CRYONITE, Blocks.GLOWSTONE);
        put(FLAG_RADIONITE + FLAG_LAVA, Blocks.SAND);
        put(FLAG_CRYONITE + FLAG_LAVA, Blocks.COBBLESTONE);
        put(FLAG_NEPTUNIUM + FLAG_LAVA, Blocks.LAVA);
        put(FLAG_NETHERFLOW + FLAG_LAVA, Blocks.LAVA);
        put(FLAG_ACID + FLAG_WATER, Blocks.CLAY);
        put(FLAG_RADIONITE + FLAG_WATER, Blocks.CLAY);
        put(FLAG_NETHERFLOW + FLAG_WATER, Blocks.NETHERRACK);
        put(FLAG_CRYONITE + FLAG_WATER, Blocks.ICE);
    }};
    
    public <T extends Fluid> BlockFluidPlasmaCraft(T fluid)
    {
        super(fluid, Material.WATER);
        
        setUnlocalizedName(fluid.getUnlocalizedName().substring(6));
        setRegistryName(PlasmaCraft.MODID, fluid.getUnlocalizedName().substring(6));
        setCreativeTab(PlasmaCraft.creativeTab);

        setRenderLayer(BlockRenderLayer.TRANSLUCENT);
    }
    
    @Override
    public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn)
    {   
        if (CausticPoisoning.DoCausticPoisoning(entityIn))
        {
            entityIn.setFire(2);
        }
    }
    
    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        // To combat lag and overflow crash, I only explode far away liquids
        if (explosionIn.getPosition().squareDistanceTo(new Vec3d(pos)) == 9)
        {
            worldIn.createExplosion(explosionIn.getExplosivePlacedBy(), pos.getX(), pos.getY(), pos.getZ(), 3, true);
        }
    }
    
    public abstract int getFluidFlag();
    
    public abstract Color getColor();
    
    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor)
    {
        super.onNeighborChange(world, pos, neighbor);
        
        HardenBlock(world, world.getBlockState(neighbor).getBlock(), neighbor);
    }
    
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        super.onBlockAdded(worldIn, pos, state);
        
        List<BlockPos> blocks = new ArrayList<BlockPos>();
        blocks.add(pos.add(-1,0,0));
        blocks.add(pos.add(1,0,0));
        blocks.add(pos.add(0,-1,0));
        blocks.add(pos.add(0,0,-1));
        blocks.add(pos.add(0,0,1));
        
        for (BlockPos blockpos : blocks)
        {
            HardenBlock(worldIn, worldIn.getBlockState(blockpos).getBlock(), blockpos);
        }
    }
    
    @Override
    public boolean canDisplace(IBlockAccess world, BlockPos pos)
    {
        if (world.isAirBlock(pos)) return true;

        IBlockState state = world.getBlockState(pos);

        if (state.getMaterial() == Material.PORTAL)
        {
            return false;
        }

        return HardenBlock(world, state.getBlock(), pos);
    }
    
    private boolean HardenBlock(IBlockAccess world, Block Neighbor, BlockPos pos)
    {
        Block Replacement = null;
        
        if (Neighbor != this && (Neighbor instanceof BlockFire || Neighbor.getDefaultState().getMaterial().isLiquid()))
        {
            int Flag = 0;
            int iExplosionPower = 3;
            
            if (Neighbor == Blocks.WATER || Neighbor == Blocks.FLOWING_WATER)
            {
                Flag = FLAG_WATER + getFluidFlag();
            }
            else if (Neighbor == Blocks.LAVA || Neighbor == Blocks.FLOWING_LAVA)
            {
                Flag = FLAG_LAVA + getFluidFlag();
            }
            else if (Neighbor instanceof BlockFluidPlasmaCraft)
            {
                if (Neighbor == PlasmaCraft.BLOCK_OBSIDIUM)
                {
                    Replacement = Blocks.OBSIDIAN;
                }
                else
                {
                    Flag = getFluidFlag() + ((BlockFluidPlasmaCraft)Neighbor).getFluidFlag();
                }
            }
            
            if (Replacement == null && Flag != 0)
            {
                Replacement = MapBlockHarden.get(Flag);
                
                if (Flag == FLAG_URANIUM + FLAG_CRYONITE)
                {
                    iExplosionPower = 6;
                }
            }
            
            if (Replacement != null)
            {
                if (Replacement.getDefaultState().getBlock() != Neighbor)
                {
                    return ((World)world).setBlockState(pos, Replacement.getDefaultState());
                }
                return false;
            }
            else
            {
                ((World)world).setBlockToAir(pos);
                ((World)world).createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), iExplosionPower, true);
            }
        }
        
        return Neighbor instanceof BlockBush;
    }
}
