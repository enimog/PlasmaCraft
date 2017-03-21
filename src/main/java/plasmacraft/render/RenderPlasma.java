package plasmacraft.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityPlasma;

@SideOnly(Side.CLIENT)
public class RenderPlasma extends RenderArrow<EntityPlasma>
{
    public static final ResourceLocation RES_PLASMA = new ResourceLocation(PlasmaCraft.MODID, "textures/entity/plasmaball.png");
    
    public RenderPlasma(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPlasma entity)
    {
        return RES_PLASMA;
    }
}