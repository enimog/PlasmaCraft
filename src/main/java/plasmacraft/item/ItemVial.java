package plasmacraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;
import plasmacraft.block.BlockFluidPlasmaCraft;

public abstract class ItemVial extends ItemPlasmaCraft
{
    public enum EnumType implements IStringSerializable 
    {
        EMPTY(0, "empty", Blocks.AIR),
        ACID(1, "acid", PlasmaCraft.BLOCK_ACID),
        CRYONITE(2, "cryonite", PlasmaCraft.BLOCK_CRYONITE),
        NEPTUNIUM(3, "neptunium", PlasmaCraft.BLOCK_NEPTUNIUM),
        NETHERFLOW(4, "netherflow", PlasmaCraft.BLOCK_NETHERFLOW),
        OBSIDIUM(5, "obsidium", PlasmaCraft.BLOCK_OBSIDIUM),
        PLUTONIUM(6, "plutonium", PlasmaCraft.BLOCK_PLUTONIUM),
        RADIONITE(7, "radionite", PlasmaCraft.BLOCK_RADIONITE),
        URANIUM(8, "uranium", PlasmaCraft.BLOCK_URANIUM);

        private final int ID;
        private final String name;
        private final Block container;
        
        private EnumType(int ID, String name, Block block) 
        {
            this.ID = ID;
            this.name = name;
            this.container = block;
        }
        
        @Override
        public String getName() 
        {
            return name;
        }

        public Block getBlock() 
        {
            return container;
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
    
    public ItemVial(EnumType value)
    {
        super("vial_" + value.getName());
        
        //this.setHasSubtypes(true);
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        RayTraceResult raytraceresult = rayTrace(worldIn, playerIn, true);
        
        if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
        {
            BlockPos blockpos = raytraceresult.getBlockPos();
            IBlockState iblockstate = worldIn.getBlockState(blockpos);
            BlockPos previous = getPreviousBlock(blockpos, raytraceresult.sideHit);
            
            if (getBlock() != Blocks.AIR && playerIn.canPlayerEdit(previous, raytraceresult.sideHit, itemStackIn))
            {
                worldIn.setBlockState(previous, getBlock().getDefaultState());
                playerIn.addStat(StatList.getObjectUseStats(this));
                
                if (playerIn.capabilities.isCreativeMode)
                {
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
                }
                else
                {
                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(PlasmaCraft.ITEM_VIAL_EMPTY));
                }
            }
            else if (iblockstate.getBlock() instanceof BlockFluidPlasmaCraft && getBlock() == Blocks.AIR )
            {
                worldIn.setBlockState(blockpos, getBlock().getDefaultState());
                playerIn.addStat(StatList.getObjectUseStats(this));
                
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(getCorrespondingVial((BlockFluidPlasmaCraft)iblockstate.getBlock())));
            }
            
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
    }
    
    private ItemVial getCorrespondingVial(BlockFluidPlasmaCraft block)
    {
        switch(block.getFluidFlag())
        {
            case BlockFluidPlasmaCraft.FLAG_ACID:
                return PlasmaCraft.ITEM_VIAL_ACID;
            case BlockFluidPlasmaCraft.FLAG_CRYONITE:
                return PlasmaCraft.ITEM_VIAL_CRYONITE;
            case BlockFluidPlasmaCraft.FLAG_NEPTUNIUM:
                return PlasmaCraft.ITEM_VIAL_NEPTUNIUM;
            case BlockFluidPlasmaCraft.FLAG_NETHERFLOW:
                return PlasmaCraft.ITEM_VIAL_NETHERFLOW;
            case BlockFluidPlasmaCraft.FLAG_OBSIDIUM:
                return PlasmaCraft.ITEM_VIAL_OBSIDIUM;
            case BlockFluidPlasmaCraft.FLAG_PLUTONIUM:
                return PlasmaCraft.ITEM_VIAL_PLUTONIUM;
            case BlockFluidPlasmaCraft.FLAG_RADIONITE:
                return PlasmaCraft.ITEM_VIAL_RADIONITE;
            case BlockFluidPlasmaCraft.FLAG_URANIUM:
                return PlasmaCraft.ITEM_VIAL_URANIUM;
        }
        return PlasmaCraft.ITEM_VIAL_EMPTY;
    }
    
    /*@Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "_" + getName();
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        subItems.add(new ItemStack(itemIn, 1, 0));
        subItems.add(new ItemStack(itemIn, 1, 1));
        subItems.add(new ItemStack(itemIn, 1, 2));
        subItems.add(new ItemStack(itemIn, 1, 3));
        subItems.add(new ItemStack(itemIn, 1, 4));
        subItems.add(new ItemStack(itemIn, 1, 5));
        subItems.add(new ItemStack(itemIn, 1, 6));
        subItems.add(new ItemStack(itemIn, 1, 7));
    }*/
    
    protected abstract String getName();
    protected abstract Block getBlock();
    
    private BlockPos getPreviousBlock(BlockPos blockpos, EnumFacing side)
    {
        BlockPos value = blockpos;
        switch(side)
        {
            case DOWN:
                value = blockpos.add(0,-1,0);
                break;
            case EAST:
                value = blockpos.add(1,0,0);
                break;
            case NORTH:
                value = blockpos.add(0,0,-1);
                break;
            case SOUTH:
                value = blockpos.add(0,0,1);
                break;
            case UP:
                value = blockpos.add(0,1,0);
                break;
            case WEST:
                value = blockpos.add(-1,0,0);
                break;  
        }
        return value;
    }
    
}
