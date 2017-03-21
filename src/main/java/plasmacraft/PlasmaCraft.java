package plasmacraft;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import plasmacraft.block.BlockAcid;
import plasmacraft.block.BlockAcidBarrier;
import plasmacraft.block.BlockCausticExplosive;
import plasmacraft.block.BlockCryonite;
import plasmacraft.block.BlockFluidPlasmaCraft;
import plasmacraft.block.BlockFrozenCryonite;
import plasmacraft.block.BlockGlowCloth;
import plasmacraft.block.BlockNeptunium;
import plasmacraft.block.BlockNetherflow;
import plasmacraft.block.BlockObsidium;
import plasmacraft.block.BlockOre;
import plasmacraft.block.BlockOreLead;
import plasmacraft.block.BlockOreNeptunium;
import plasmacraft.block.BlockOreObsidium;
import plasmacraft.block.BlockOrePlutonium;
import plasmacraft.block.BlockOreRadionite;
import plasmacraft.block.BlockOreUranium;
import plasmacraft.block.BlockPlutonium;
import plasmacraft.block.BlockRadionite;
import plasmacraft.block.BlockReinforcedGlass;
import plasmacraft.block.BlockUranium;
import plasmacraft.common.CreativeTab;
import plasmacraft.common.DamageSourceCaustic;
import plasmacraft.common.FuelHandler;
import plasmacraft.entity.EntityAcid;
import plasmacraft.entity.EntityAcidGrenade;
import plasmacraft.entity.EntityCausticBoat;
import plasmacraft.entity.EntityCausticExplosivePrimed;
import plasmacraft.entity.EntityCryoBlast;
import plasmacraft.entity.EntityLaser;
import plasmacraft.entity.EntityManager;
import plasmacraft.entity.EntityPlasma;
import plasmacraft.entity.EntityRailgun;
import plasmacraft.fluid.FluidAcid;
import plasmacraft.fluid.FluidCryonite;
import plasmacraft.fluid.FluidNeptunium;
import plasmacraft.fluid.FluidNetherflow;
import plasmacraft.fluid.FluidObsidium;
import plasmacraft.fluid.FluidPlutonium;
import plasmacraft.fluid.FluidRadionite;
import plasmacraft.fluid.FluidUranium;
import plasmacraft.item.ItemAcidGrenade;
import plasmacraft.item.ItemAcidGun;
import plasmacraft.item.ItemCausticBoat;
import plasmacraft.item.ItemCryoBlaster;
import plasmacraft.item.ItemLaserGun;
import plasmacraft.item.ItemLaserGunSplitter;
import plasmacraft.item.ItemLaserShotgun;
import plasmacraft.item.ItemPlasmaCraft;
import plasmacraft.item.ItemPlasmaGun;
import plasmacraft.item.ItemPlasmaGunSplitter;
import plasmacraft.item.ItemRailgun;
import plasmacraft.item.ItemVial;
import plasmacraft.item.ItemVialAcid;
import plasmacraft.item.ItemVialCryonite;
import plasmacraft.item.ItemVialEmpty;
import plasmacraft.item.ItemVialNeptunium;
import plasmacraft.item.ItemVialNetherflow;
import plasmacraft.item.ItemVialObsidium;
import plasmacraft.item.ItemVialPlutonium;
import plasmacraft.item.ItemVialRadionite;
import plasmacraft.item.ItemVialUranium;
import plasmacraft.material.MaterialCaustic;
import plasmacraft.proxy.CommonProxy;
import plasmacraft.sound.SoundLaserGun;
import plasmacraft.sound.SoundRailgun;

@Mod(modid = PlasmaCraft.MODID, name = PlasmaCraft.MODNAME, version = PlasmaCraft.VERSION)
public class PlasmaCraft {

    @Instance
    public static PlasmaCraft instance;
    
    @SidedProxy(clientSide="plasmacraft.proxy.ClientProxy", serverSide="plasmacraft.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    public static final String MODID = "plasmacraft";
    public static final String MODNAME = "PlasmaCraft";
    public static final String VERSION = "1.0.0";
    public static CreativeTab creativeTab = new CreativeTab(CreativeTabs.getNextID(), MODID);
    
    /*************************************************************/
    /** public sounds
    /*************************************************************/
    public static SoundEvent SOUND_LASER_GUN = new SoundLaserGun();
    public static SoundEvent SOUND_RAILGUN = new SoundRailgun();
    
    /*************************************************************/
    /** IC2 api objects
    /*************************************************************/
    public static Potion IC2_POTION_RADIATION = null;
    public static Item IC2_HAZMAT_HEAD = null;
    public static Item IC2_HAZMAT_BODY = null;
    public static Item IC2_HAZMAT_LEG = null;
    public static Item IC2_HAZMAT_FOOT = null;
    
    /*************************************************************/
    /** public Misc
    /*************************************************************/
    public static DamageSource DAMAGE_SOURCE_CAUSTIC = new DamageSourceCaustic();
    public static Material MATERIAL_CAUSTIC = new MaterialCaustic();
    
    /*************************************************************/
    /** public ore registry names
    /*************************************************************/
    // Forge
    public static final String ingotIron = "ingotIron";
    public static final String diamond = "gemDiamond";
    
    // PlasmaCraft
    public static final String REGISTRY_GOOP_ACID = "goopAcid";
    public static final String REGISTRY_GOOP_CRYONITE = "goopCryonite";
    public static final String REGISTRY_GOOP_NEPTUNIUM = "goopNeptunium";
    public static final String REGISTRY_GOOP_NETHERFLOW = "goopNetherflow";
    public static final String REGISTRY_GOOP_OBSIDIUM = "goopObsidium";
    public static final String REGISTRY_GOOP_PLUTONIUM = "goopPlutonium";
    public static final String REGISTRY_GOOP_RADIONITE = "goopRadionite";
    public static final String REGISTRY_GOOP_URANIUM = "goopUranium";
    public static final String REGISTRY_INGOT_CRYONITE = "ingotCryonite";
    public static final String REGISTRY_INGOT_NEPTUNIUM = "ingotNeptunium";
    public static final String REGISTRY_INGOT_NETHERFLOW = "ingotNetherflow";
    public static final String REGISTRY_INGOT_OBSIDIUM = "ingotObsidium";
    public static final String REGISTRY_INGOT_PLUTONIUM = "ingotPlutonium";
    public static final String REGISTRY_INGOT_RADIONITE = "ingotRadionite";
    public static final String REGISTRY_INGOT_URANIUM = "ingotUranium";
    public static final String REGISTRY_INGOT_LEAD = "ingotLead";
    public static final String REGISTRY_GOOP_CAUSTIC = "goopCaustic";
    public static final String REGISTRY_INGOT_CAUSTIC = "ingotCaustic";
    
    /*************************************************************/
    /** Public fluid list
    /*************************************************************/
    public static Fluid FLUID_ACID = new FluidAcid();
    public static Fluid FLUID_CRYONITE = new FluidCryonite();
    public static Fluid FLUID_NEPTUNIUM = new FluidNeptunium();
    public static Fluid FLUID_OBSIDIUM = new FluidObsidium();
    public static Fluid FLUID_NETHERFLOW = new FluidNetherflow();
    public static Fluid FLUID_PLUTONIUM = new FluidPlutonium();
    public static Fluid FLUID_RADIONITE = new FluidRadionite();
    public static Fluid FLUID_URANIUM = new FluidUranium();
    
    /*************************************************************/
    /** Public block list
    /*************************************************************/
    public static Map<String, Block> BlockMap = new HashMap<String, Block>();
    public static Block BLOCK_ACID_BARRIER = new BlockAcidBarrier();
    public static Block BLOCK_ACID_TNT = new BlockCausticExplosive();
    public static Block BLOCK_FROZEN_CRYONITE = new BlockFrozenCryonite();
    public static Block BLOCK_GLOWCLOTH_ACID = new BlockGlowCloth(BlockGlowCloth.EnumType.ACID);
    public static Block BLOCK_GLOWCLOTH_CRYONITE = new BlockGlowCloth(BlockGlowCloth.EnumType.CRYONITE);
    public static Block BLOCK_GLOWCLOTH_NEPTUNIUM = new BlockGlowCloth(BlockGlowCloth.EnumType.NEPTUNIUM);
    public static Block BLOCK_GLOWCLOTH_NETHERFLOW = new BlockGlowCloth(BlockGlowCloth.EnumType.NETHERFLOW);
    public static Block BLOCK_GLOWCLOTH_OBSIDIUM = new BlockGlowCloth(BlockGlowCloth.EnumType.OBSIDIUM);
    public static Block BLOCK_GLOWCLOTH_PLUTONIUM = new BlockGlowCloth(BlockGlowCloth.EnumType.PLUTONIUM);
    public static Block BLOCK_GLOWCLOTH_RADIONITE = new BlockGlowCloth(BlockGlowCloth.EnumType.RADIONITE);
    public static Block BLOCK_GLOWCLOTH_URANIUM = new BlockGlowCloth(BlockGlowCloth.EnumType.URANIUM);
    public static BlockOre BLOCK_ORE_LEAD = new BlockOreLead();
    public static BlockOre BLOCK_ORE_NEPTUNIUM = new BlockOreNeptunium();
    public static BlockOre BLOCK_ORE_OBSIDIUM = new BlockOreObsidium();
    public static BlockOre BLOCK_ORE_PLUTONIUM = new BlockOrePlutonium();
    public static BlockOre BLOCK_ORE_RADIONITE = new BlockOreRadionite();
    public static BlockOre BLOCK_ORE_URANIUM = new BlockOreUranium();
    public static Block BLOCK_REINFORCED_GLASS = new BlockReinforcedGlass();
    public static BlockFluidPlasmaCraft BLOCK_ACID = new BlockAcid();
    public static BlockFluidPlasmaCraft BLOCK_CRYONITE = new BlockCryonite();
    public static BlockFluidPlasmaCraft BLOCK_NEPTUNIUM = new BlockNeptunium();
    public static BlockFluidPlasmaCraft BLOCK_OBSIDIUM = new BlockObsidium();
    public static BlockFluidPlasmaCraft BLOCK_NETHERFLOW = new BlockNetherflow();
    public static BlockFluidPlasmaCraft BLOCK_PLUTONIUM = new BlockPlutonium();
    public static BlockFluidPlasmaCraft BLOCK_RADIONITE = new BlockRadionite();
    public static BlockFluidPlasmaCraft BLOCK_URANIUM = new BlockUranium();
    
    /*************************************************************/
    /** public Item list
    /*************************************************************/
    public static Map<String, Item> ItemMap = new HashMap<String, Item>();
    public static Item ITEM_BATTERY_EMPTY = new ItemPlasmaCraft("battery_empty");
    public static Item ITEM_BATTERY_CHARGED = new ItemPlasmaCraft("battery_charged");
    public static Item ITEM_BATTERY_OVERCHARGED = new ItemPlasmaCraft("battery_overcharged");
    public static Item ITEM_BATTERY_CRYONITE = new ItemPlasmaCraft("battery_cryonite");
    public static Item ITEM_BATTERY_PLASMA = new ItemPlasmaCraft("battery_plasma");
    public static Item ITEM_ACID_GRENADE = new ItemAcidGrenade();
    public static Item ITEM_ACID_GUN = new ItemAcidGun();
    public static Item ITEM_BEAM_SPLITTER = new ItemPlasmaCraft("beam_splitter");
    public static Item ITEM_CAUSTIC_BOAT = new ItemCausticBoat();
    public static Item ITEM_CRYO_BLASTER = new ItemCryoBlaster();
    public static Item ITEM_ENERGY_CELL = new ItemPlasmaCraft("energy_cell");
    public static Item ITEM_GOOP_ACID = new ItemPlasmaCraft("goop_acid");
    public static Item ITEM_GOOP_CRYONITE = new ItemPlasmaCraft("goop_cryonite");
    public static Item ITEM_GOOP_NEPTUNIUM = new ItemPlasmaCraft("goop_neptunium");
    public static Item ITEM_GOOP_NETHERFLOW = new ItemPlasmaCraft("goop_netherflow");
    public static Item ITEM_GOOP_OBSIDIUM = new ItemPlasmaCraft("goop_obsidium");
    public static Item ITEM_GOOP_PLUTONIUM = new ItemPlasmaCraft("goop_plutonium");
    public static Item ITEM_GOOP_RADIONITE = new ItemPlasmaCraft("goop_radionite");
    public static Item ITEM_GOOP_URANIUM = new ItemPlasmaCraft("goop_uranium");
    public static Item ITEM_INGOT_CRYONITE = new ItemPlasmaCraft("ingot_cryonite");
    public static Item ITEM_INGOT_NEPTUNIUM = new ItemPlasmaCraft("ingot_neptunium");
    public static Item ITEM_INGOT_NETHERFLOW = new ItemPlasmaCraft("ingot_netherflow");
    public static Item ITEM_INGOT_OBSIDIUM = new ItemPlasmaCraft("ingot_obsidium");
    public static Item ITEM_INGOT_PLUTONIUM = new ItemPlasmaCraft("ingot_plutonium");
    public static Item ITEM_INGOT_RADIONITE = new ItemPlasmaCraft("ingot_radionite");
    public static Item ITEM_INGOT_URANIUM = new ItemPlasmaCraft("ingot_uranium");
    public static Item ITEM_INGOT_LEAD = new ItemPlasmaCraft("ingot_lead");
    public static Item ITEM_LASER_GUN = new ItemLaserGun();
    public static Item ITEM_LASER_GUN_SPLITTER = new ItemLaserGunSplitter();
    public static Item ITEM_LASER_SHOTGUN = new ItemLaserShotgun();
    public static Item ITEM_PLASMA_GUN = new ItemPlasmaGun();
    public static Item ITEM_PLASMA_GUN_SPLITTER = new ItemPlasmaGunSplitter();
    public static Item ITEM_RAILGUN = new ItemRailgun();
    public static Item ITEM_THERMOPELLET = new ItemPlasmaCraft("thermopellet");
    public static ItemVial ITEM_VIAL_ACID = new ItemVialAcid();
    public static ItemVial ITEM_VIAL_CRYONITE = new ItemVialCryonite();
    public static ItemVial ITEM_VIAL_EMPTY = new ItemVialEmpty();
    public static ItemVial ITEM_VIAL_NEPTUNIUM = new ItemVialNeptunium();
    public static ItemVial ITEM_VIAL_NETHERFLOW = new ItemVialNetherflow();
    public static ItemVial ITEM_VIAL_OBSIDIUM = new ItemVialObsidium();
    public static ItemVial ITEM_VIAL_PLUTONIUM = new ItemVialPlutonium();
    public static ItemVial ITEM_VIAL_RADIONITE = new ItemVialRadionite();
    public static ItemVial ITEM_VIAL_URANIUM = new ItemVialUranium();
    
    /*************************************************************/
    /** Public ItemBlock list
    /*************************************************************/
    //public static ItemBlock ITEMBLOCK_BLOCKORE = new ItemBlockOre(BLOCK_ORE_LEAD);
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {        
        /*************************************************************/
        /** Block registration and creation
        /*************************************************************/
        newBlock(BLOCK_ACID_BARRIER);
        newBlock(BLOCK_ACID_TNT);
        newBlock(BLOCK_FROZEN_CRYONITE);
        newBlock(BLOCK_GLOWCLOTH_ACID);
        newBlock(BLOCK_GLOWCLOTH_CRYONITE);
        newBlock(BLOCK_GLOWCLOTH_NEPTUNIUM);
        newBlock(BLOCK_GLOWCLOTH_NETHERFLOW);
        newBlock(BLOCK_GLOWCLOTH_OBSIDIUM);
        newBlock(BLOCK_GLOWCLOTH_PLUTONIUM);
        newBlock(BLOCK_GLOWCLOTH_RADIONITE);
        newBlock(BLOCK_GLOWCLOTH_URANIUM);
        newBlock(BLOCK_ORE_LEAD);
        newBlock(BLOCK_ORE_NEPTUNIUM);
        newBlock(BLOCK_ORE_OBSIDIUM);
        newBlock(BLOCK_ORE_PLUTONIUM);
        newBlock(BLOCK_ORE_RADIONITE);
        newBlock(BLOCK_ORE_URANIUM);
        newBlock(BLOCK_REINFORCED_GLASS);
        newBlock(BLOCK_ACID);
        newBlock(BLOCK_CRYONITE);
        newBlock(BLOCK_NEPTUNIUM);
        newBlock(BLOCK_OBSIDIUM);
        newBlock(BLOCK_NETHERFLOW);
        newBlock(BLOCK_PLUTONIUM);
        newBlock(BLOCK_RADIONITE);
        newBlock(BLOCK_URANIUM);
        
        /*************************************************************/
        /** Item registration and creation
        /*************************************************************/
        newItem(ITEM_ACID_GRENADE);
        newItem(ITEM_ACID_GUN);
        newItem(ITEM_BATTERY_EMPTY);
        newItem(ITEM_BATTERY_CHARGED);
        newItem(ITEM_BATTERY_OVERCHARGED);
        newItem(ITEM_BATTERY_CRYONITE);
        newItem(ITEM_BATTERY_PLASMA);
        newItem(ITEM_BEAM_SPLITTER);
        newItem(ITEM_CAUSTIC_BOAT);
        newItem(ITEM_CRYO_BLASTER);
        newItem(ITEM_ENERGY_CELL);
        newItem(ITEM_GOOP_ACID);
        newItem(ITEM_GOOP_CRYONITE);
        newItem(ITEM_GOOP_NEPTUNIUM);
        newItem(ITEM_GOOP_NETHERFLOW);
        newItem(ITEM_GOOP_OBSIDIUM);
        newItem(ITEM_GOOP_PLUTONIUM);
        newItem(ITEM_GOOP_RADIONITE);
        newItem(ITEM_GOOP_URANIUM);
        newItem(ITEM_INGOT_CRYONITE);
        newItem(ITEM_INGOT_NEPTUNIUM);
        newItem(ITEM_INGOT_NETHERFLOW);
        newItem(ITEM_INGOT_OBSIDIUM);
        newItem(ITEM_INGOT_PLUTONIUM);
        newItem(ITEM_INGOT_RADIONITE);
        newItem(ITEM_INGOT_URANIUM);
        newItem(ITEM_INGOT_LEAD);
        newItem(ITEM_LASER_GUN);
        newItem(ITEM_LASER_GUN_SPLITTER);
        newItem(ITEM_LASER_SHOTGUN);
        newItem(ITEM_PLASMA_GUN);
        newItem(ITEM_PLASMA_GUN_SPLITTER);
        newItem(ITEM_RAILGUN);
        newItem(ITEM_THERMOPELLET);
        newItem(ITEM_VIAL_ACID);
        newItem(ITEM_VIAL_CRYONITE);
        newItem(ITEM_VIAL_EMPTY);
        newItem(ITEM_VIAL_NEPTUNIUM);
        newItem(ITEM_VIAL_NETHERFLOW);
        newItem(ITEM_VIAL_OBSIDIUM);
        newItem(ITEM_VIAL_PLUTONIUM);
        newItem(ITEM_VIAL_RADIONITE);
        newItem(ITEM_VIAL_URANIUM);
        
        GameRegistry.register(SOUND_LASER_GUN);
        GameRegistry.register(SOUND_RAILGUN);
        
        EntityRegistry.registerModEntity(EntityCausticExplosivePrimed.class, "caustic_tnt_primed", EntityManager.getNextEntityID(), instance, 32, 5, false);
        EntityRegistry.registerModEntity(EntityCausticBoat.class, "caustic_boat", EntityManager.getNextEntityID(), instance, 32, 5, false);
        EntityRegistry.registerModEntity(EntityAcid.class, "projectile_acid", EntityManager.getNextEntityID(), instance, 128, 6, true);
        EntityRegistry.registerModEntity(EntityAcidGrenade.class, "projectile_grenade", EntityManager.getNextEntityID(), instance, 128, 6, true);
        EntityRegistry.registerModEntity(EntityCryoBlast.class, "projectile_cryoblast", EntityManager.getNextEntityID(), instance, 128, 6, true);
        EntityRegistry.registerModEntity(EntityLaser.class, "projectile_laser", EntityManager.getNextEntityID(), instance, 128, 6, true);
        EntityRegistry.registerModEntity(EntityPlasma.class, "projectile_plasma", EntityManager.getNextEntityID(), instance, 128, 6, true);
        EntityRegistry.registerModEntity(EntityRailgun.class, "projectile_railgun", EntityManager.getNextEntityID(), instance, 128, 6, true);
        
        // Fuel
        GameRegistry.registerFuelHandler(new FuelHandler());
        
        creativeTab.setIcon(ITEM_VIAL_ACID);
        
        PlasmaCraft.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        /*************************************************************/
        /** Ore dictionary registration
        /*************************************************************/
        OreDictionary.registerOre(REGISTRY_GOOP_ACID,  ITEM_GOOP_ACID);
        OreDictionary.registerOre(REGISTRY_GOOP_NEPTUNIUM,  ITEM_GOOP_NEPTUNIUM);
        OreDictionary.registerOre(REGISTRY_GOOP_NETHERFLOW,  ITEM_GOOP_NETHERFLOW);
        OreDictionary.registerOre(REGISTRY_GOOP_OBSIDIUM,  ITEM_GOOP_OBSIDIUM);
        OreDictionary.registerOre(REGISTRY_GOOP_PLUTONIUM,  ITEM_GOOP_PLUTONIUM);
        OreDictionary.registerOre(REGISTRY_GOOP_RADIONITE,  ITEM_GOOP_RADIONITE);
        OreDictionary.registerOre(REGISTRY_GOOP_URANIUM,  ITEM_GOOP_URANIUM);
        OreDictionary.registerOre(REGISTRY_GOOP_CRYONITE,  ITEM_GOOP_CRYONITE);
        OreDictionary.registerOre(REGISTRY_INGOT_CRYONITE,  ITEM_INGOT_CRYONITE);
        OreDictionary.registerOre(REGISTRY_INGOT_NEPTUNIUM,  ITEM_INGOT_NEPTUNIUM);
        OreDictionary.registerOre(REGISTRY_INGOT_NETHERFLOW,  ITEM_INGOT_NETHERFLOW);
        OreDictionary.registerOre(REGISTRY_INGOT_OBSIDIUM,  ITEM_INGOT_OBSIDIUM);
        OreDictionary.registerOre(REGISTRY_INGOT_PLUTONIUM,  ITEM_INGOT_PLUTONIUM);
        OreDictionary.registerOre(REGISTRY_INGOT_RADIONITE,  ITEM_INGOT_RADIONITE);
        OreDictionary.registerOre(REGISTRY_INGOT_URANIUM,  ITEM_INGOT_URANIUM);
        OreDictionary.registerOre(REGISTRY_INGOT_LEAD,  ITEM_INGOT_LEAD);
        OreDictionary.registerOre(REGISTRY_GOOP_CAUSTIC,  ITEM_GOOP_CRYONITE);
        OreDictionary.registerOre(REGISTRY_GOOP_CAUSTIC,  ITEM_GOOP_NEPTUNIUM);
        OreDictionary.registerOre(REGISTRY_GOOP_CAUSTIC,  ITEM_GOOP_NETHERFLOW);
        OreDictionary.registerOre(REGISTRY_GOOP_CAUSTIC,  ITEM_GOOP_OBSIDIUM);
        OreDictionary.registerOre(REGISTRY_GOOP_CAUSTIC,  ITEM_GOOP_PLUTONIUM);
        OreDictionary.registerOre(REGISTRY_GOOP_CAUSTIC,  ITEM_GOOP_RADIONITE);
        OreDictionary.registerOre(REGISTRY_GOOP_CAUSTIC,  ITEM_GOOP_URANIUM);
        OreDictionary.registerOre(REGISTRY_GOOP_CAUSTIC,  ITEM_GOOP_ACID);
        OreDictionary.registerOre(REGISTRY_INGOT_CAUSTIC,  ITEM_INGOT_CRYONITE);
        OreDictionary.registerOre(REGISTRY_INGOT_CAUSTIC,  ITEM_INGOT_NEPTUNIUM);
        OreDictionary.registerOre(REGISTRY_INGOT_CAUSTIC,  ITEM_INGOT_NETHERFLOW);
        OreDictionary.registerOre(REGISTRY_INGOT_CAUSTIC,  ITEM_INGOT_OBSIDIUM);
        OreDictionary.registerOre(REGISTRY_INGOT_CAUSTIC,  ITEM_INGOT_PLUTONIUM);
        OreDictionary.registerOre(REGISTRY_INGOT_CAUSTIC,  ITEM_INGOT_RADIONITE);
        OreDictionary.registerOre(REGISTRY_INGOT_CAUSTIC,  ITEM_INGOT_URANIUM);
        
        /*************************************************************/
        /** Adding recipes
        /*************************************************************/
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BLOCK_GLOWCLOTH_ACID), REGISTRY_GOOP_ACID, Blocks.WOOL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BLOCK_GLOWCLOTH_PLUTONIUM), REGISTRY_GOOP_PLUTONIUM, Blocks.WOOL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BLOCK_GLOWCLOTH_RADIONITE), REGISTRY_GOOP_RADIONITE, Blocks.WOOL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BLOCK_GLOWCLOTH_NEPTUNIUM), REGISTRY_GOOP_NEPTUNIUM, Blocks.WOOL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BLOCK_GLOWCLOTH_NETHERFLOW), REGISTRY_GOOP_NETHERFLOW, Blocks.WOOL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BLOCK_GLOWCLOTH_OBSIDIUM), REGISTRY_GOOP_OBSIDIUM, Blocks.WOOL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BLOCK_GLOWCLOTH_CRYONITE), REGISTRY_GOOP_CRYONITE, Blocks.WOOL));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BLOCK_GLOWCLOTH_URANIUM), REGISTRY_GOOP_URANIUM, Blocks.WOOL));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BLOCK_REINFORCED_GLASS, 4), " X ", "X#X", " X ", '#', Blocks.GLASS, 'X', REGISTRY_INGOT_LEAD));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_VIAL_EMPTY), "X#X", "Y Y", "X#X", '#', REGISTRY_INGOT_LEAD, 'Y', BLOCK_REINFORCED_GLASS, 'X', Blocks.GLASS));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BLOCK_ACID_BARRIER), " X ", "XZX", " X ", 'Z', BLOCK_REINFORCED_GLASS, 'X', REGISTRY_GOOP_ACID));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_CAUSTIC_BOAT), "R R", "RRR", 'R', REGISTRY_INGOT_RADIONITE));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BLOCK_ACID_TNT, 4), "APA", "GAG", "APA", 'A', ITEM_VIAL_ACID, 'G', Items.GUNPOWDER, 'P', REGISTRY_INGOT_CAUSTIC));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ACID_GRENADE, 4), "X", "Y", "Z", 'X', ingotIron, 'Y', ITEM_VIAL_ACID, 'Z', REGISTRY_INGOT_CAUSTIC));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_PLASMA_GUN_SPLITTER), "YB", 'B', ITEM_PLASMA_GUN, 'Y', ITEM_BEAM_SPLITTER));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_LASER_GUN_SPLITTER), "YB", 'B', ITEM_LASER_GUN, 'Y', ITEM_BEAM_SPLITTER));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_CRYO_BLASTER), "  A", "CBX", " DE", 'A', REGISTRY_INGOT_URANIUM, 'B', REGISTRY_GOOP_CRYONITE, 'C', REGISTRY_INGOT_CRYONITE, 'D', REGISTRY_INGOT_OBSIDIUM, 'X', ITEM_BATTERY_CRYONITE, 'E', REGISTRY_INGOT_PLUTONIUM));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_LASER_SHOTGUN), "  A", "BCD", " EF", 'A', Items.REPEATER, 'B', ITEM_BEAM_SPLITTER, 'C', REGISTRY_INGOT_NETHERFLOW, 'D', ITEM_BATTERY_CHARGED, 'E', REGISTRY_INGOT_RADIONITE, 'F', REGISTRY_INGOT_PLUTONIUM));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_LASER_GUN), "ABC", " BD", 'A', REGISTRY_INGOT_NETHERFLOW, 'B', REGISTRY_INGOT_OBSIDIUM, 'C', REGISTRY_GOOP_NETHERFLOW, 'D', REGISTRY_INGOT_PLUTONIUM));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_PLASMA_GUN), "ABC", " DC", 'A', diamond, 'B', REGISTRY_INGOT_LEAD, 'C', REGISTRY_INGOT_PLUTONIUM, 'D', REGISTRY_INGOT_OBSIDIUM));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_RAILGUN), "ABC", " DE", "AB ", 'A', REGISTRY_INGOT_LEAD, 'B', REGISTRY_INGOT_PLUTONIUM, 'C', ITEM_BATTERY_PLASMA, 'D', REGISTRY_GOOP_PLUTONIUM, 'E', REGISTRY_INGOT_URANIUM));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ACID_GUN), "  A", "BCD", " EF", 'A', ITEM_VIAL_EMPTY, 'B', REGISTRY_INGOT_LEAD, 'C', REGISTRY_INGOT_PLUTONIUM, 'D', BLOCK_REINFORCED_GLASS, 'E', ITEM_BATTERY_PLASMA, 'F', ingotIron));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_BEAM_SPLITTER), " A ", "BCD", " A ", 'A', REGISTRY_INGOT_NETHERFLOW, 'B', diamond, 'C', ITEM_BATTERY_PLASMA, 'D', REGISTRY_INGOT_URANIUM));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_THERMOPELLET), "AAA", "ABA", "AAA", 'A', REGISTRY_GOOP_URANIUM, 'B', REGISTRY_INGOT_PLUTONIUM));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_BATTERY_OVERCHARGED), ITEM_BATTERY_CHARGED, REGISTRY_INGOT_URANIUM));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_ENERGY_CELL, 5), " R ", "RXR", " R ", 'R', REGISTRY_INGOT_NEPTUNIUM, 'X', REGISTRY_GOOP_CAUSTIC));
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ITEM_BATTERY_EMPTY, 8), "IRI", "I I", "IRI", 'R', REGISTRY_INGOT_RADIONITE, 'I', ingotIron));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_BATTERY_CRYONITE), ITEM_GOOP_CRYONITE, ITEM_BATTERY_EMPTY));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_BATTERY_PLASMA), REGISTRY_INGOT_CAUSTIC, ITEM_BATTERY_EMPTY));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_BATTERY_CHARGED), REGISTRY_INGOT_PLUTONIUM, ITEM_BATTERY_EMPTY));

        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_GOOP_CRYONITE), REGISTRY_GOOP_ACID, REGISTRY_INGOT_CRYONITE, REGISTRY_INGOT_CRYONITE, REGISTRY_INGOT_CRYONITE, REGISTRY_INGOT_CRYONITE));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_GOOP_NEPTUNIUM), REGISTRY_GOOP_ACID, REGISTRY_INGOT_NEPTUNIUM, REGISTRY_INGOT_NEPTUNIUM, REGISTRY_INGOT_NEPTUNIUM, REGISTRY_INGOT_NEPTUNIUM));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_GOOP_NETHERFLOW), REGISTRY_GOOP_ACID, REGISTRY_INGOT_NETHERFLOW, REGISTRY_INGOT_NETHERFLOW, REGISTRY_INGOT_NETHERFLOW, REGISTRY_INGOT_NETHERFLOW));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_GOOP_OBSIDIUM), REGISTRY_GOOP_ACID, REGISTRY_INGOT_OBSIDIUM, REGISTRY_INGOT_OBSIDIUM, REGISTRY_INGOT_OBSIDIUM, REGISTRY_INGOT_OBSIDIUM));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_GOOP_PLUTONIUM), REGISTRY_GOOP_ACID, REGISTRY_INGOT_PLUTONIUM, REGISTRY_INGOT_PLUTONIUM, REGISTRY_INGOT_PLUTONIUM, REGISTRY_INGOT_PLUTONIUM));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_GOOP_RADIONITE), REGISTRY_GOOP_ACID, REGISTRY_INGOT_RADIONITE, REGISTRY_INGOT_RADIONITE, REGISTRY_INGOT_RADIONITE, REGISTRY_INGOT_RADIONITE));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_GOOP_URANIUM), REGISTRY_GOOP_ACID, REGISTRY_INGOT_URANIUM, REGISTRY_INGOT_URANIUM, REGISTRY_INGOT_URANIUM, REGISTRY_INGOT_URANIUM));
        
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_VIAL_ACID), REGISTRY_GOOP_ACID, ITEM_VIAL_EMPTY));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_VIAL_CRYONITE), REGISTRY_GOOP_CRYONITE, ITEM_VIAL_EMPTY));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_VIAL_NEPTUNIUM), REGISTRY_GOOP_NEPTUNIUM, ITEM_VIAL_EMPTY));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_VIAL_NETHERFLOW), REGISTRY_GOOP_NETHERFLOW, ITEM_VIAL_EMPTY));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_VIAL_OBSIDIUM), REGISTRY_GOOP_OBSIDIUM, ITEM_VIAL_EMPTY));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_VIAL_PLUTONIUM), REGISTRY_GOOP_PLUTONIUM, ITEM_VIAL_EMPTY));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_VIAL_RADIONITE), REGISTRY_GOOP_RADIONITE, ITEM_VIAL_EMPTY));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ITEM_VIAL_URANIUM), REGISTRY_GOOP_URANIUM, ITEM_VIAL_EMPTY));

        GameRegistry.addSmelting(BLOCK_ORE_PLUTONIUM, new ItemStack(ITEM_INGOT_PLUTONIUM), 0.1f);
        GameRegistry.addSmelting(BLOCK_ORE_RADIONITE, new ItemStack(ITEM_INGOT_RADIONITE), 0.1f);
        GameRegistry.addSmelting(BLOCK_ORE_NEPTUNIUM, new ItemStack(ITEM_INGOT_NEPTUNIUM), 0.1f );
        GameRegistry.addSmelting(BLOCK_ORE_OBSIDIUM, new ItemStack(ITEM_INGOT_OBSIDIUM), 0.1f );
        GameRegistry.addSmelting(BLOCK_ORE_URANIUM, new ItemStack(ITEM_INGOT_URANIUM), 0.1f );
        GameRegistry.addSmelting(BLOCK_ORE_LEAD, new ItemStack(ITEM_INGOT_LEAD), 0.1f );
        GameRegistry.addSmelting(ITEM_VIAL_CRYONITE, new ItemStack(ITEM_INGOT_CRYONITE), 0.1f);
        GameRegistry.addSmelting(ITEM_VIAL_NETHERFLOW, new ItemStack(ITEM_INGOT_NETHERFLOW), 0.1f);
        GameRegistry.addSmelting(Items.SLIME_BALL, new ItemStack(ITEM_GOOP_ACID), 0f);
        
        PlasmaCraft.proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        PlasmaCraft.proxy.postInit(e);
        
        // communicate with other mods and adjust your setup based on this
    }
    
    private <T extends Item> void newItem(T item)
    {
        ItemMap.put(item.getUnlocalizedName().substring(5), item);
        GameRegistry.register(item); 
    }
    
    private <T extends Block> void newBlock(T block)
    {        
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block), block.getRegistryName());
        BlockMap.put(block.getRegistryName().getResourcePath(), block);
    }
}