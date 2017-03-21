package plasmacraft.world;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plasmacraft.PlasmaCraft;

public class AcidExplosion extends Explosion
{
    // Private members that I need to also have
    private final World world;
    private final float radius;
    private final double posX;
    private final double posY;
    private final double posZ;
    
    @SideOnly(Side.CLIENT)
    public AcidExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, List<BlockPos> affectedPositions)
    {
        super(worldIn, entityIn, x, y, z, size, false, true, affectedPositions);
        
        world = worldIn;
        radius = size;
        posX = x;
        posY = y;
        posZ = z;
    }

    @SideOnly(Side.CLIENT)
    public AcidExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean smoking, List<BlockPos> affectedPositions)
    {
        super(worldIn, entityIn, x, y, z, size, flaming, smoking);
        
        world = worldIn;
        radius = size;
        posX = x;
        posY = y;
        posZ = z;
    }

    public AcidExplosion(World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean smoking)
    {
        super(worldIn, entityIn, x, y, z, size, flaming, smoking);
        
        world = worldIn;
        radius = size;
        posX = x;
        posY = y;
        posZ = z;
    }
    
    @Override
    public void doExplosionB(boolean spawnParticles)
    {
        super.doExplosionB(spawnParticles);
        
        for (float x = -radius; x <= radius; x++)
        {
            for (float z = -radius; z <= radius; z++)
            {
                for (float y = -radius; y < 0; y++)
                {
                    BlockPos pos = new BlockPos(posX + x, posY + y, posZ + z);
                    if (world.getBlockState(pos).getMaterial() == Material.AIR)
                    {
                        world.setBlockState(pos, PlasmaCraft.BLOCK_ACID.getDefaultState(), 2);
                    }
                }
            }
        }
    }
}
