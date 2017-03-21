package plasmacraft.block;

import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityCausticExplosivePrimed;

public class BlockCausticExplosive extends BlockTNT
{
    public BlockCausticExplosive()
    {
        super();
        setUnlocalizedName("acid_tnt");
        setRegistryName(PlasmaCraft.MODID, "acid_tnt");
        setCreativeTab(PlasmaCraft.creativeTab);
    }
    
    /**
     * Called when this Block is destroyed by an Explosion
     */
    @Override
    public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        if (!worldIn.isRemote)
        {
            EntityCausticExplosivePrimed entitytntprimed = new EntityCausticExplosivePrimed(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy());
            entitytntprimed.setFuse((short)(worldIn.rand.nextInt(entitytntprimed.getFuse() / 4) + entitytntprimed.getFuse() / 8));
            worldIn.spawnEntityInWorld(entitytntprimed);
        }
    }
    
    @Override
    public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter)
    {
        if (!worldIn.isRemote)
        {
            if (((Boolean)state.getValue(EXPLODE)).booleanValue())
            {
                EntityCausticExplosivePrimed entitytntprimed = new EntityCausticExplosivePrimed(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter);
                worldIn.spawnEntityInWorld(entitytntprimed);
                worldIn.playSound((EntityPlayer)null, entitytntprimed.posX, entitytntprimed.posY, entitytntprimed.posZ, SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
    }
}
