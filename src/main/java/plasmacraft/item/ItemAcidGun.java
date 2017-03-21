package plasmacraft.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityAcid;

public class ItemAcidGun extends ItemGun
{

    public ItemAcidGun()
    {
        super("acid_gun", SoundEvents.ENTITY_ARROW_SHOOT, PlasmaCraft.ITEM_VIAL_ACID, PlasmaCraft.ITEM_VIAL_EMPTY);
    }

    @Override
    protected void FireAmmo(World worldIn, EntityPlayer playerIn, Random rand)
    {
        EntityAcid entity = new EntityAcid(worldIn, playerIn);
        entity.setHeadingFromThrower(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 3.5F, 1.0F);
        worldIn.spawnEntityInWorld(entity);
    }

}