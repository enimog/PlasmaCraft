package plasmacraft.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityCryoBlast;

public class ItemCryoBlaster extends ItemGun
{

    public ItemCryoBlaster()
    {
        super("cryo_blaster", PlasmaCraft.SOUND_LASER_GUN, PlasmaCraft.ITEM_BATTERY_CRYONITE, PlasmaCraft.ITEM_BATTERY_EMPTY);
    }

    @Override
    protected void FireAmmo(World worldIn, EntityPlayer playerIn, Random rand)
    {
        EntityCryoBlast toShoot = new EntityCryoBlast(worldIn, playerIn);
        toShoot.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 5, 1.0F);
        worldIn.spawnEntityInWorld(toShoot);
    }

}