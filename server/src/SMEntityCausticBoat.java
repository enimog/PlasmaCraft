package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode

import java.util.List;
import java.util.Random;

public class SMEntityCausticBoat extends EntityBoat
{

    public SMEntityCausticBoat(World world)
    {
        super(world);
    }

    public SMEntityCausticBoat(World world, double d, double d1, double d2)
    {
		super(world, d, d1, d2);
    }

    public boolean attackEntityFrom(Entity entity, int i)
    {
        if(worldObj.singleplayerWorld || isDead)
        {
            return true;
        }
        func_41026_d(-func_41029_m());
        func_41027_c(10);
        func_41024_b(func_41025_i() + i * 10);
        setBeenAttacked();
        if(func_41025_i() > 40)
        {
            for(int j = 0; j < 2; j++)
            {
                dropItemWithOffset(mod_PlasmaCraft.ingotRadionite.shiftedIndex, 1, 0.0F);
            }

            setEntityDead();
        }
        return true;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f,
            float f1, int i)
    {
        my_field_9393_e = d;
        my_field_9392_f = d1;
        my_field_9391_g = d2;
        my_field_9390_h = f;
        my_field_9389_i = f1;
        my_field_9394_d = i + 4;
        motionX = my_field_9388_j;
        motionY = my_field_9387_k;
        motionZ = my_field_9386_l;
    }

    public void setVelocity(double d, double d1, double d2)
    {
        my_field_9388_j = motionX = d;
        my_field_9387_k = motionY = d1;
        my_field_9386_l = motionZ = d2;
    }

    public void onUpdate()
    {
        super.onUpdate();
        if(func_41028_k() > 0)
        {
            func_41027_c(func_41028_k() - 1);
        }
        if(func_41025_i() > 0)
        {
            func_41024_b(func_41025_i() - 1);
        }
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        int i = 5;
        double d = 0.0D;
        for(int j = 0; j < i; j++)
        {
            double d4 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 0)) / (double)i) - 0.125D;
            double d8 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(j + 1)) / (double)i) - 0.125D;
            AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBoxFromPool(boundingBox.minX, d4, boundingBox.minZ, boundingBox.maxX, d8, boundingBox.maxZ);
            boolean movementValid = worldObj.isAABBInMaterial(axisalignedbb, Material.water);
            movementValid |= worldObj.isAABBInMaterial(axisalignedbb, mod_PlasmaCraft.caustic);
            if(movementValid)
            {
                d += 1.0D / (double)i;
            }
        }

        if(worldObj.singleplayerWorld)
        {
            if(my_field_9394_d > 0)
            {
                double d1 = posX + (my_field_9393_e - posX) / (double)my_field_9394_d;
                double d5 = posY + (my_field_9392_f - posY) / (double)my_field_9394_d;
                double d9 = posZ + (my_field_9391_g - posZ) / (double)my_field_9394_d;
                double d12;
                for(d12 = my_field_9390_h - (double)rotationYaw; d12 < -180D; d12 += 360D) { }
                for(; d12 >= 180D; d12 -= 360D) { }
                rotationYaw += d12 / (double)my_field_9394_d;
                rotationPitch += (my_field_9389_i - (double)rotationPitch) / (double)my_field_9394_d;
                my_field_9394_d--;
                setPosition(d1, d5, d9);
                setRotation(rotationYaw, rotationPitch);
            } else
            {
                double d2 = posX + motionX;
                double d6 = posY + motionY;
                double d10 = posZ + motionZ;
                setPosition(d2, d6, d10);
                if(onGround)
                {
                    motionX *= 0.5D;
                    motionY *= 0.5D;
                    motionZ *= 0.5D;
                }
                motionX *= 0.99000000953674316D;
                motionY *= 0.94999998807907104D;
                motionZ *= 0.99000000953674316D;
            }
            return;
        }
        double d3 = d * 2D - 1.0D;
        motionY += 0.039999999105930328D * d3;
        if(riddenByEntity != null)
        {
            motionX += riddenByEntity.motionX * 0.20000000000000001D;
            motionZ += riddenByEntity.motionZ * 0.20000000000000001D;
        }
        double d7 = 0.40000000000000002D;
        if(motionX < -d7)
        {
            motionX = -d7;
        }
        if(motionX > d7)
        {
            motionX = d7;
        }
        if(motionZ < -d7)
        {
            motionZ = -d7;
        }
        if(motionZ > d7)
        {
            motionZ = d7;
        }
        if(onGround)
        {
            motionX *= 0.5D;
            motionY *= 0.5D;
            motionZ *= 0.5D;
        }
        moveEntity(motionX, motionY, motionZ);
        double d11 = Math.sqrt(motionX * motionX + motionZ * motionZ);
        if(d11 > 0.14999999999999999D)
        {
            double d13 = Math.cos(((double)rotationYaw * 3.1415926535897931D) / 180D);
            double d15 = Math.sin(((double)rotationYaw * 3.1415926535897931D) / 180D);
            for(int i1 = 0; (double)i1 < 1.0D + d11 * 60D; i1++)
            {
                double d18 = rand.nextFloat() * 2.0F - 1.0F;
                double d20 = (double)(rand.nextInt(2) * 2 - 1) * 0.69999999999999996D;
                if(rand.nextBoolean())
                {
                    double d21 = (posX - d13 * d18 * 0.80000000000000004D) + d15 * d20;
                    double d23 = posZ - d15 * d18 * 0.80000000000000004D - d13 * d20;
                    worldObj.spawnParticle("splash", d21, posY - 0.125D, d23, motionX, motionY, motionZ);
                } else
                {
                    double d22 = posX + d13 + d15 * d18 * 0.69999999999999996D;
                    double d24 = (posZ + d15) - d13 * d18 * 0.69999999999999996D;
                    worldObj.spawnParticle("splash", d22, posY - 0.125D, d24, motionX, motionY, motionZ);
                }
            }

        }
        if(isCollidedHorizontally && d11 > 0.14999999999999999D)
        {
            if(!worldObj.singleplayerWorld)
            {
                setEntityDead();
                for(int k = 0; k < 3; k++)
                {
                    dropItemWithOffset(Block.planks.blockID, 1, 0.0F);
                }

                for(int l = 0; l < 2; l++)
                {
                    dropItemWithOffset(Item.stick.shiftedIndex, 1, 0.0F);
                }

            }
        } else
        {
            motionX *= 0.99000000953674316D;
            motionY *= 0.94999998807907104D;
            motionZ *= 0.99000000953674316D;
        }
        rotationPitch = 0.0F;
        double d14 = rotationYaw;
        double d16 = prevPosX - posX;
        double d17 = prevPosZ - posZ;
        if(d16 * d16 + d17 * d17 > 0.001D)
        {
            d14 = (float)((Math.atan2(d17, d16) * 180D) / 3.1415926535897931D);
        }
        double d19;
        for(d19 = d14 - (double)rotationYaw; d19 >= 180D; d19 -= 360D) { }
        for(; d19 < -180D; d19 += 360D) { }
        if(d19 > 20D)
        {
            d19 = 20D;
        }
        if(d19 < -20D)
        {
            d19 = -20D;
        }
        rotationYaw += d19;
        setRotation(rotationYaw, rotationPitch);
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        if(list != null && list.size() > 0)
        {
            for(int j1 = 0; j1 < list.size(); j1++)
            {
                Entity entity = (Entity)list.get(j1);
                if(entity != riddenByEntity && entity.canBePushed() && (entity instanceof EntityBoat))
                {
                    entity.applyEntityCollision(this);
                }
            }

        }
        if(riddenByEntity != null && riddenByEntity.isDead)
        {
            riddenByEntity = null;
        }
    }

    private int my_field_9394_d;
    private double my_field_9393_e;
    private double my_field_9392_f;
    private double my_field_9391_g;
    private double my_field_9390_h;
    private double my_field_9389_i;
    private double my_field_9388_j;
    private double my_field_9387_k;
    private double my_field_9386_l;
}
