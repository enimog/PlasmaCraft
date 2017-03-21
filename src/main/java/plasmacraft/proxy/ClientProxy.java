package plasmacraft.proxy;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityAcid;
import plasmacraft.entity.EntityAcidGrenade;
import plasmacraft.entity.EntityCausticBoat;
import plasmacraft.entity.EntityCausticExplosivePrimed;
import plasmacraft.entity.EntityCryoBlast;
import plasmacraft.entity.EntityLaser;
import plasmacraft.entity.EntityPlasma;
import plasmacraft.entity.EntityRailgun;
import plasmacraft.event.EventHandlerCommon;
import plasmacraft.render.factory.IRenderFactoryAcid;
import plasmacraft.render.factory.IRenderFactoryAcidGrenade;
import plasmacraft.render.factory.IRenderFactoryCausticBoat;
import plasmacraft.render.factory.IRenderFactoryCausticExplosive;
import plasmacraft.render.factory.IRenderFactoryCryoBlast;
import plasmacraft.render.factory.IRenderFactoryLaser;
import plasmacraft.render.factory.IRenderFactoryPlasma;
import plasmacraft.render.factory.IRenderFactoryRailgun;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        
        for (Item item : PlasmaCraft.ItemMap.values())
        {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
        
        for (Block block : PlasmaCraft.BlockMap.values())
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
        
        // Rendering registration
        RenderingRegistry.registerEntityRenderingHandler(EntityCausticExplosivePrimed.class, new IRenderFactoryCausticExplosive());
        RenderingRegistry.registerEntityRenderingHandler(EntityCausticBoat.class, new IRenderFactoryCausticBoat());
        RenderingRegistry.registerEntityRenderingHandler(EntityAcid.class, new IRenderFactoryAcid());
        RenderingRegistry.registerEntityRenderingHandler(EntityAcidGrenade.class, new IRenderFactoryAcidGrenade());
        RenderingRegistry.registerEntityRenderingHandler(EntityCryoBlast.class, new IRenderFactoryCryoBlast());
        RenderingRegistry.registerEntityRenderingHandler(EntityLaser.class, new IRenderFactoryLaser());
        RenderingRegistry.registerEntityRenderingHandler(EntityPlasma.class, new IRenderFactoryPlasma());
        RenderingRegistry.registerEntityRenderingHandler(EntityRailgun.class, new IRenderFactoryRailgun());
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        
        MinecraftForge.EVENT_BUS.register(new EventHandlerCommon());
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}