package plasmacraft.render.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import plasmacraft.entity.EntityPlasma;
import plasmacraft.render.RenderPlasma;

public class IRenderFactoryPlasma implements IRenderFactory<EntityPlasma>
{
    @Override
    public Render<EntityPlasma> createRenderFor(RenderManager manager)
    {
        return new RenderPlasma(manager);
    }

}
