package plasmacraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityCausticBoat;

public class CausticPoisoning
{    
    public static boolean DoCausticPoisoning(Entity entityIn)
    {
        if (entityIn instanceof EntityCausticBoat || IsWearingHazmatSuit(entityIn))
        {
            return false;
        }
        entityIn.attackEntityFrom(PlasmaCraft.DAMAGE_SOURCE_CAUSTIC, 2);
        
        if (entityIn instanceof EntityLivingBase && PlasmaCraft.IC2_POTION_RADIATION != null)
        {
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(PlasmaCraft.IC2_POTION_RADIATION, 600, 0));
        }
        
        return true;
    }
    
    private static boolean IsWearingHazmatSuit(Entity entityIn)
    {
        if (entityIn instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entityIn;
            
            if ( 
                    PlasmaCraft.IC2_HAZMAT_HEAD != null && 
                    PlasmaCraft.IC2_HAZMAT_BODY != null && 
                    PlasmaCraft.IC2_HAZMAT_LEG != null && 
                    PlasmaCraft.IC2_HAZMAT_FOOT != null &&
                    player.inventory.armorItemInSlot(0) != null &&
                    player.inventory.armorItemInSlot(1) != null &&
                    player.inventory.armorItemInSlot(2) != null &&
                    player.inventory.armorItemInSlot(3) != null
               )
            {
                return  player.inventory.armorItemInSlot(0).getItem() == PlasmaCraft.IC2_HAZMAT_FOOT &&
                        player.inventory.armorItemInSlot(1).getItem() == PlasmaCraft.IC2_HAZMAT_LEG &&
                        player.inventory.armorItemInSlot(2).getItem() == PlasmaCraft.IC2_HAZMAT_BODY &&
                        player.inventory.armorItemInSlot(3).getItem() == PlasmaCraft.IC2_HAZMAT_HEAD;
            }
        }
        
        return false;
    }
}
