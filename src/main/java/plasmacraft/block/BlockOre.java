package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;

public abstract class BlockOre extends BlockPlasmaCraft
{
    public static final PropertyEnum<EnumType> ENUM_ORE_TYPE = PropertyEnum.create("type", BlockOre.EnumType.class);
    public enum EnumType implements IStringSerializable 
    {
        LEAD(0, "lead"),
        NEPTUNIUM(1, "neptunium"),
        OBSIDIUM(2, "obsidium"),
        PLUTONIUM(3, "plutonium"),
        RADIONITE(4, "radionite"),
        URANIUM(5, "uranium");

        private int ID;
        private String name;
        
        private EnumType(int ID, String name) 
        {
            this.ID = ID;
            this.name = name;
        }
        
        @Override
        public String getName() 
        {
            return name;
        }

        public int getID() 
        {
            return ID;
        }
        
        @Override
        public String toString() 
        {
            return getName();
        }
    }
    
    public BlockOre(EnumType OreEnum, Material materialIn, float hardnessIn, float resistance, int harvestLevel)
    {
        //super("ore_plasmacraft", materialIn, hardnessIn, resistance);
        super("ore_" + OreEnum.getName(), materialIn, hardnessIn, resistance);
        
        setHarvestLevel("pickaxe", harvestLevel);
        //setDefaultState(blockState.getBaseState().withProperty(ENUM_ORE_TYPE, EnumType.LEAD));
    }
    
    @Override
    public String getUnlocalizedName() 
    {
        return "tile.ore_" + getSpecialName();
    }
    
    public abstract String getSpecialName();
    
    public abstract int getMetadata();
    
    public abstract IBlockState getOreType();
    
    /*@Override
    public int damageDropped(IBlockState state) 
    {
        return getMetadata();
    }
    
    @Override
    protected BlockStateContainer createBlockState() 
    {
        return new BlockStateContainer(this, new IProperty[] { ENUM_ORE_TYPE });
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) 
    {
        return getOreType();
    }

    @Override
    public int getMetaFromState(IBlockState state) 
    {
        return getMetadata();
    }
    
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) 
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, getMetadata());
    }
    
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) 
    {
        for (int i = 0; i <= 5; i++ )
        {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }*/
}
