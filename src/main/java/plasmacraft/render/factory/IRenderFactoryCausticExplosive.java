package plasmacraft.render.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import plasmacraft.entity.EntityCausticExplosivePrimed;
import plasmacraft.render.RenderCausticExplosivePrimed;

public class IRenderFactoryCausticExplosive implements IRenderFactory<EntityCausticExplosivePrimed>
{
    @Override
    public Render<EntityCausticExplosivePrimed> createRenderFor(RenderManager manager)
    {
        return new RenderCausticExplosivePrimed(manager);
    }

}
