package plasmacraft.item;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import plasmacraft.util.InventoryHelper;

public abstract class ItemGun extends ItemPlasmaCraft
{
    private SoundEvent ShotSound;
    private Item Ammunition;
    private Item EmptyItem = null;
    
    public ItemGun(String unlocalizedName, SoundEvent sound, Item Ammo, Item empty) 
    {
        super(unlocalizedName);
        
        ShotSound = sound;
        setMaxStackSize(1);
        Ammunition = Ammo;
        EmptyItem = empty;
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if(playerIn.capabilities.isCreativeMode || InventoryHelper.consumeInventoryItem(playerIn.inventory, new ItemStack(Ammunition)))
        {
            FireAmmo(worldIn, playerIn, worldIn.rand);
            
            worldIn.playSound(playerIn, playerIn.getPosition(), ShotSound, SoundCategory.NEUTRAL, 100, 1.0f / (itemRand.nextFloat() * 0.4f + 0.8f));
            
            if (EmptyItem != null && !playerIn.capabilities.isCreativeMode && !playerIn.inventory.addItemStackToInventory(new ItemStack(EmptyItem)))
            {
                playerIn.dropItem(new ItemStack(EmptyItem), false);
            }
            playerIn.addStat(StatList.getObjectUseStats(this));
            
            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
        }
        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
    }
    
    protected abstract void FireAmmo(World worldIn, EntityPlayer playerIn, Random rand);
    
    
}
