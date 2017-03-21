package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

public class BlockGlowCloth extends BlockPlasmaCraft
{
    public static final PropertyEnum<EnumType> TYPE = PropertyEnum.create("type", BlockGlowCloth.EnumType.class);
    
    public enum EnumType implements IStringSerializable {
        ACID(0, "acid"),
        CRYONITE(1, "cryonite"),
        NEPTUNIUM(2, "neptunium"),
        NETHERFLOW(3, "netherflow"),
        OBSIDIUM(4, "obsidium"),
        PLUTONIUM(5, "plutonium"),
        RADIONITE(6, "radionite"),
        URANIUM(7, "uranium");

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
    
    public BlockGlowCloth(EnumType value)
    {
        super("glowcloth_" + value.getName(), Material.CLOTH, 0.8f, 1);
        setLightLevel(1.0F);
        //this.setDefaultState(blockState.getBaseState().withProperty(TYPE, EnumType.ACID));
    }

    /*protected String getSpecialName()
    {
        return blockState.getBaseState().getValue(TYPE).getName();
    }

    public int getMetadata()
    {
        return blockState.getBaseState().getValue(TYPE).getID();
    }
    
    @Override
    protected BlockStateContainer createBlockState() 
    {
        return new BlockStateContainer(this, new IProperty[] { TYPE });
    }
    
    @Override
    public IBlockState getStateFromMeta(int meta) 
    {
        switch(meta)
        {
            case 0:
                return getDefaultState().withProperty(TYPE, EnumType.ACID);
            case 1:
                return getDefaultState().withProperty(TYPE, EnumType.CRYONITE);
            case 2:
                return getDefaultState().withProperty(TYPE, EnumType.NEPTUNIUM);
            case 3:
                return getDefaultState().withProperty(TYPE, EnumType.NETHERFLOW);
            case 4:
                return getDefaultState().withProperty(TYPE, EnumType.OBSIDIUM);
            case 5:
                return getDefaultState().withProperty(TYPE, EnumType.PLUTONIUM);
            case 6:
                return getDefaultState().withProperty(TYPE, EnumType.RADIONITE);
            case 7:
                return getDefaultState().withProperty(TYPE, EnumType.URANIUM);
        }
        return getDefaultState().withProperty(TYPE, EnumType.ACID);
    }

    @Override
    public int getMetaFromState(IBlockState state) 
    {
        EnumType type = (EnumType) state.getValue(TYPE);
        return type.getID();
    }
    
    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        list.add(new ItemStack(itemIn, 1, 0));
        list.add(new ItemStack(itemIn, 1, 1));
        list.add(new ItemStack(itemIn, 1, 2));
        list.add(new ItemStack(itemIn, 1, 3));
        list.add(new ItemStack(itemIn, 1, 4));
        list.add(new ItemStack(itemIn, 1, 5));
        list.add(new ItemStack(itemIn, 1, 6));
        list.add(new ItemStack(itemIn, 1, 7));
    }*/
}
