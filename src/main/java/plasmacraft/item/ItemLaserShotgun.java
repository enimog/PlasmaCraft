package plasmacraft.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityLaser;

public class ItemLaserShotgun extends ItemGun
{

    public ItemLaserShotgun()
    {
        super("laser_shotgun", PlasmaCraft.SOUND_LASER_GUN, PlasmaCraft.ITEM_ENERGY_CELL, null);
    }

    @Override
    protected void FireAmmo(World worldIn, EntityPlayer playerIn, Random rand)
    {
        for (int i = 0; i < 5; i++)
        {
            EntityLaser toShoot = new EntityLaser(worldIn, playerIn);
            toShoot.setAim(playerIn, playerIn.rotationPitch * MathHelper.randomFloatClamp(rand, 0.9F, 1.1F), playerIn.rotationYaw  * MathHelper.randomFloatClamp(rand, 0.9F, 1.1F), 0.0F, 3, 1.0F);
            worldIn.spawnEntityInWorld(toShoot);
        }
    }

}
