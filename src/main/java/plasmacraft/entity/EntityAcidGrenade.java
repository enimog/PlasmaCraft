package plasmacraft.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import plasmacraft.world.AcidExplosion;

public class EntityAcidGrenade extends EntitySnowball
{

    public EntityAcidGrenade(World worldIn)
    {
        super(worldIn);
    }

    public EntityAcidGrenade(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityAcidGrenade(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }
    
    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            Explosion explosion = new AcidExplosion(worldObj, this, result.entityHit.posX, result.entityHit.posY, result.entityHit.posZ, 2.5F, false, true);
            explosion.doExplosionA();
            explosion.doExplosionB(true);
        }
        else
        {
            Explosion explosion = new AcidExplosion(worldObj, this, result.getBlockPos().getX(), result.getBlockPos().getY(), result.getBlockPos().getZ(), 2.5F, false, true);
            explosion.doExplosionA();
            explosion.doExplosionB(true);
        }
        
        this.setDead();
    }
    
}
