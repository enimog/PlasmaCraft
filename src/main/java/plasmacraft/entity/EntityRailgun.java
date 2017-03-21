package plasmacraft.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityRailgun extends EntityArrow
{
    public EntityRailgun(World worldIn)
    {
        super(worldIn);
        
        this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
        setDamage(30);
        setNoGravity(true);
        setIsCritical(false);
    }
    
    public EntityRailgun(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
        
        this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
        setDamage(30);
        setNoGravity(true);
        setIsCritical(false);
    }

    public EntityRailgun(World worldIn, EntityLivingBase shooter)
    {
        super(worldIn, shooter);
        
        this.pickupStatus = EntityArrow.PickupStatus.DISALLOWED;
        setDamage(30);
        setNoGravity(true);
        setIsCritical(false);
    }
    
    @Override
    protected ItemStack getArrowStack()
    {
        return null;
    }
    
    @Override
    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (!this.worldObj.isRemote)
        {
            this.setFlag(6, this.isGlowing());
        }

        this.onEntityUpdate();

        Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.worldObj.rayTraceBlocks(vec3d1, vec3d, false, true, false);
        vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if (raytraceresult != null)
        {
            vec3d = new Vec3d(raytraceresult.hitVec.xCoord, raytraceresult.hitVec.yCoord, raytraceresult.hitVec.zCoord);
        }

        Entity entity = this.findEntityOnPath(vec3d1, vec3d);

        if (entity != null)
        {
            raytraceresult = new RayTraceResult(entity);
        }

        if (raytraceresult != null && raytraceresult.entityHit != null && raytraceresult.entityHit instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)raytraceresult.entityHit;

            if (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
            {
                raytraceresult = null;
            }
        }

        if (raytraceresult != null)
        {
            this.onHit(raytraceresult);
        }

        this.posX += this.motionX;
        this.posY += this.motionY;
        this.posZ += this.motionZ;

        if (this.isInWater())
        {
            for (int i = 0; i < 4; ++i)
            {
                this.worldObj.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ, new int[0]);
            }
        }

        this.setPosition(this.posX, this.posY, this.posZ);
        this.doBlockCollisions();
    }
    
    @Override
    protected void onHit(RayTraceResult raytraceResultIn)
    {
        Entity entity = raytraceResultIn.entityHit;

        if (entity != null)
        {
            float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            int i = MathHelper.ceiling_double_int((double)f * this.getDamage());

            DamageSource damagesource;

            if (this.shootingEntity == null)
            {
                damagesource = DamageSource.causeArrowDamage(this, this);
            }
            else
            {
                damagesource = DamageSource.causeArrowDamage(this, this.shootingEntity);
            }

            if (!(entity instanceof EntityEnderman))
            {
                entity.setFire(20);
            }

            if (entity.attackEntityFrom(damagesource, (float)i))
            {
                if (entity instanceof EntityLivingBase)
                {
                    EntityLivingBase entitylivingbase = (EntityLivingBase)entity;

                    this.arrowHit(entitylivingbase);

                    if (this.shootingEntity != null && entitylivingbase != this.shootingEntity && entitylivingbase instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                    }
                }

                if (!(entity instanceof EntityEnderman))
                {
                    worldObj.newExplosion(this, posX, posY, posZ, 2, true, true);
                    this.setDead();
                }
            }
            else
            {
                worldObj.newExplosion(this, posX, posY, posZ, 2, true, true);
                this.setDead();
            }
        }
        else
        {            
            BlockPos blockpos = raytraceResultIn.getBlockPos();
            IBlockState iblockstate = this.worldObj.getBlockState(blockpos);

            iblockstate.getBlock().onEntityCollidedWithBlock(this.worldObj, blockpos, iblockstate, this);

            worldObj.newExplosion(this, posX, posY, posZ, 2, true, true);
            this.setDead();
        }
    }

}
