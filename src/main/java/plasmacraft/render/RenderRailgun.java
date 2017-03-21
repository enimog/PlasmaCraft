package plasmacraft.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityRailgun;

@SideOnly(Side.CLIENT)
public class RenderRailgun extends RenderArrow<EntityRailgun>
{
    public static final ResourceLocation RES_RAILGUN = new ResourceLocation(PlasmaCraft.MODID, "textures/entity/railgunbolt.png");
    
    public RenderRailgun(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityRailgun entity)
    {
        return RES_RAILGUN;
    }
}