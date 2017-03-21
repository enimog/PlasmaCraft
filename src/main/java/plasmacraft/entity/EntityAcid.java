package plasmacraft.entity;

import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;

public class EntityAcid extends EntitySnowball
{

    public EntityAcid(World worldIn)
    {
        super(worldIn);
    }

    public EntityAcid(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityAcid(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            worldObj.setBlockState(result.entityHit.getPosition(), PlasmaCraft.BLOCK_ACID.getDefaultState());
        }
        else
        {
            BlockPos blockpos = result.getBlockPos();
            if (!(worldObj.getBlockState(blockpos).getBlock() instanceof BlockBush) && !(worldObj.getBlockState(blockpos).getBlock() instanceof BlockFire))
            {
                switch(result.sideHit)
                {
                    case DOWN:
                        blockpos = blockpos.add(0,-1,0);
                        break;
                    case EAST:
                        blockpos = blockpos.add(1,0,0);
                        break;
                    case NORTH:
                        blockpos = blockpos.add(0,0,-1);
                        break;
                    case SOUTH:
                        blockpos = blockpos.add(0,0,1);
                        break;
                    case UP:
                        blockpos = blockpos.add(0,1,0);
                        break;
                    case WEST:
                        blockpos = blockpos.add(-1,0,0);
                        break;  
                }
            }
            worldObj.setBlockState(blockpos, PlasmaCraft.BLOCK_ACID.getDefaultState());
        }
        
        this.setDead();
    }
}
