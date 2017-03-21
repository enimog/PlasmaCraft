package plasmacraft.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityPlasma;

public class ItemPlasmaGunSplitter extends ItemGun
{
    public ItemPlasmaGunSplitter()
    {
        super("plasma_gun_splitter", PlasmaCraft.SOUND_RAILGUN, PlasmaCraft.ITEM_BATTERY_PLASMA, PlasmaCraft.ITEM_BATTERY_EMPTY);
    }

    @Override
    protected void FireAmmo(World worldIn, EntityPlayer playerIn, Random rand)
    {
        for (int i = 0; i < 3; i++)
        {
            EntityPlasma toShoot = new EntityPlasma(worldIn, playerIn);
            toShoot.setAim(playerIn, playerIn.rotationPitch * MathHelper.randomFloatClamp(rand, 0.9F, 1.1F), playerIn.rotationYaw  * MathHelper.randomFloatClamp(rand, 0.9F, 1.1F), 0.0F, 2.8F, 1.0F);
            worldIn.spawnEntityInWorld(toShoot);
        }
    }
}
