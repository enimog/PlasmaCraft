package plasmacraft.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityLaser;

public class ItemLaserGun extends ItemGun
{

    public ItemLaserGun()
    {
        super("laser_gun", PlasmaCraft.SOUND_LASER_GUN, PlasmaCraft.ITEM_ENERGY_CELL, null);
    }

    @Override
    protected void FireAmmo(World worldIn, EntityPlayer playerIn, Random rand)
    {
        EntityLaser toShoot = new EntityLaser(worldIn, playerIn);
        toShoot.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 5, 1.0F);
        worldIn.spawnEntityInWorld(toShoot);
    }

}