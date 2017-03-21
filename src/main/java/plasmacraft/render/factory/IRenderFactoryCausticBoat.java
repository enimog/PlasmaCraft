package plasmacraft.render.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import plasmacraft.entity.EntityCausticBoat;
import plasmacraft.render.RenderCausticBoat;

public class IRenderFactoryCausticBoat implements IRenderFactory<EntityCausticBoat>
{
    @Override
    public Render<EntityCausticBoat> createRenderFor(RenderManager manager)
    {
        return new RenderCausticBoat(manager);
    }

}
