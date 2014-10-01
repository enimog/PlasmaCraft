package com.malkierian.plasmacraft.common.Entities;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import com.malkierian.plasmacraft.common.PlasmaCraft;

public class EntityMutantCow extends EntityAnimal
{
	public EntityMutantCow(World world)
	{
		super(world);
		setSize(0.9F, 1.3F);
	}

	public EntityMutantCow(World world, double d, double d1, double d2)
	{
		super(world);
		setPosition(d, d1, d2);
	}

//	public float getMaxHealth()
//	{
//		return 15;
//	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		super.readEntityFromNBT(nbttagcompound);
	}

	protected String getLivingSound()
	{
		return "mob.cow";
	}

	protected String getHurtSound()
	{
		return "mob.cowhurt";
	}

	protected String getDeathSound()
	{
		return "mob.cowhurt";
	}

	protected float getSoundVolume()
	{
		return 0.4F;
	}

	protected Item getDropItem()
	{
		return PlasmaCraft.plasmaLeather;
	}

	public boolean interact(EntityPlayer entityplayer)
	{
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if(itemstack != null && itemstack.getItem() == PlasmaCraft.causticVial)
		{
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(PlasmaCraft.acidVial));
			return true;
		}
		else
		{
			return super.interact(entityplayer);
		}
	}

	protected EntityAnimal func_40145_a(EntityAnimal entityanimal)
	{
		return new EntityMutantCow(worldObj);
	}

	public EntityAnimal spawnBabyAnimal(EntityAnimal entityanimal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable var1) {
		// TODO Auto-generated method stub
		return null;
	}
}