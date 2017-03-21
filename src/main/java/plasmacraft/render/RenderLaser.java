package plasmacraft.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityLaser;

@SideOnly(Side.CLIENT)
public class RenderLaser extends RenderArrow<EntityLaser>
{
    public static final ResourceLocation RES_LASER = new ResourceLocation(PlasmaCraft.MODID, "textures/entity/laser.png");
    
    public RenderLaser(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLaser entity)
    {
        return RES_LASER;
    }
}