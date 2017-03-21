package plasmacraft.proxy;

import ic2.api.item.IC2Items;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import plasmacraft.PlasmaCraft;
import plasmacraft.worldgen.WorldGeneratorImpl;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {

    }

    public void init(FMLInitializationEvent e) 
    {
        GameRegistry.registerWorldGenerator(new WorldGeneratorImpl(), 0);
    }

    public void postInit(FMLPostInitializationEvent e) 
    {
        if (Loader.isModLoaded("IC2"))
        {
            PlasmaCraft.IC2_HAZMAT_BODY = IC2Items.getItem("hazmat_chestplate").getItem();
            PlasmaCraft.IC2_HAZMAT_FOOT = IC2Items.getItem("rubber_boots").getItem();
            PlasmaCraft.IC2_HAZMAT_HEAD = IC2Items.getItem("hazmat_helmet").getItem();
            PlasmaCraft.IC2_HAZMAT_LEG = IC2Items.getItem("hazmat_leggings").getItem();
            PlasmaCraft.IC2_POTION_RADIATION = Potion.REGISTRY.getObject(new ResourceLocation("ic2","radiation"));
        }
    }
}