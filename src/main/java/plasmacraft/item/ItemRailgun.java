package plasmacraft.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityRailgun;

public class ItemRailgun extends ItemGun
{

    public ItemRailgun()
    {
        super("railgun", PlasmaCraft.SOUND_RAILGUN, PlasmaCraft.ITEM_BATTERY_OVERCHARGED, PlasmaCraft.ITEM_BATTERY_EMPTY);
    }

    @Override
    protected void FireAmmo(World worldIn, EntityPlayer playerIn, Random rand)
    {
        EntityRailgun toShoot = new EntityRailgun(worldIn, playerIn);
        toShoot.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 10, 1.0F);
        worldIn.spawnEntityInWorld(toShoot);
    }

}
