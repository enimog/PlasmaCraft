package untouchedwagons.minecraft.plasmacraft.proxy;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;

import untouchedwagons.minecraft.plasmacraft.client.renderers.ModelMutantCow;
import untouchedwagons.minecraft.plasmacraft.client.renderers.RenderAcid;
import untouchedwagons.minecraft.plasmacraft.client.renderers.RenderAcidTNTPrimed;
import untouchedwagons.minecraft.plasmacraft.client.renderers.RenderCausticBoat;
import untouchedwagons.minecraft.plasmacraft.client.renderers.RenderCryoBlast;
import untouchedwagons.minecraft.plasmacraft.client.renderers.RenderLaser;
import untouchedwagons.minecraft.plasmacraft.client.renderers.RenderLaserShotgun;
import untouchedwagons.minecraft.plasmacraft.client.renderers.RenderMutantCow;
import untouchedwagons.minecraft.plasmacraft.client.renderers.RenderPlasma;
import untouchedwagons.minecraft.plasmacraft.client.renderers.RenderRailGun;
import untouchedwagons.minecraft.plasmacraft.entity.EntityAcid;
import untouchedwagons.minecraft.plasmacraft.entity.EntityAcidTNTPrimed;
import untouchedwagons.minecraft.plasmacraft.entity.EntityCausticBoat;
import untouchedwagons.minecraft.plasmacraft.entity.EntityCryoBlast;
import untouchedwagons.minecraft.plasmacraft.entity.EntityLaser;
import untouchedwagons.minecraft.plasmacraft.entity.EntityLaserShotgun;
import untouchedwagons.minecraft.plasmacraft.entity.EntityMutantCow;
import untouchedwagons.minecraft.plasmacraft.entity.EntityPlasma;
import untouchedwagons.minecraft.plasmacraft.entity.EntityRailGun;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityCausticBoat.class, new RenderCausticBoat());
		RenderingRegistry.registerEntityRenderingHandler(EntityAcidTNTPrimed.class, new RenderAcidTNTPrimed());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityAcid.class, new RenderAcid());
		RenderingRegistry.registerEntityRenderingHandler(EntityCryoBlast.class, new RenderCryoBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new RenderLaser());
		RenderingRegistry.registerEntityRenderingHandler(EntityLaserShotgun.class, new RenderLaserShotgun());
		RenderingRegistry.registerEntityRenderingHandler(EntityMutantCow.class, new RenderMutantCow(new ModelMutantCow(), 1.0f));
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasma.class, new RenderPlasma());
		RenderingRegistry.registerEntityRenderingHandler(EntityRailGun.class, new RenderRailGun());
	}
	
	@Override
	public boolean getEntityInstanceOf(Entity entity)
	{
		return entity instanceof EntityPlayerSP;
	}
	
	@Override
	public int addArmor(String name)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(name);
	}
}
