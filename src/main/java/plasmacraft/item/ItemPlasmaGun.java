package plasmacraft.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityPlasma;

public class ItemPlasmaGun extends ItemGun
{

    public ItemPlasmaGun()
    {
        super("plasma_gun", PlasmaCraft.SOUND_RAILGUN, PlasmaCraft.ITEM_BATTERY_PLASMA, PlasmaCraft.ITEM_BATTERY_EMPTY);
    }

    @Override
    protected void FireAmmo(World worldIn, EntityPlayer playerIn, Random rand)
    {
        EntityPlasma toShoot = new EntityPlasma(worldIn, playerIn);
        toShoot.setAim(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 4, 1.0F);
        worldIn.spawnEntityInWorld(toShoot);
    }

}
