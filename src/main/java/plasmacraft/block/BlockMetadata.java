package plasmacraft.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public abstract class BlockMetadata extends BlockPlasmaCraft
{    
    
    BlockMetadata(String unlocalizedName, Material materialIn, float hardnessIn, float resistance)
    {
        super(unlocalizedName, materialIn, hardnessIn, resistance);
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) 
    {
        return new ItemStack(Item.getItemFromBlock(this), getMetadata(), getMetaFromState(world.getBlockState(pos)));
    }
    
    @Override
    public String getUnlocalizedName() 
    {
        return super.getUnlocalizedName() + "." + getSpecialName();
    }
    
    protected abstract String getSpecialName();
    
    public abstract int getMetadata();
    
    @Override
    public int damageDropped(IBlockState state) 
    {
        return getMetaFromState(state);
    }
}
