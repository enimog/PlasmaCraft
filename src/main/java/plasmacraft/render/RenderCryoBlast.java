package plasmacraft.render;

import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityCryoBlast;

@SideOnly(Side.CLIENT)
public class RenderCryoBlast extends RenderArrow<EntityCryoBlast>
{
    public static final ResourceLocation RES_CRYOBLAST = new ResourceLocation(PlasmaCraft.MODID, "textures/entity/cryoblast.png");
    
    public RenderCryoBlast(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityCryoBlast entity)
    {
        return RES_CRYOBLAST;
    }
}