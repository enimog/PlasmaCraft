package plasmacraft.render.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import plasmacraft.entity.EntityLaser;
import plasmacraft.render.RenderLaser;

public class IRenderFactoryLaser implements IRenderFactory<EntityLaser>
{
    @Override
    public Render<EntityLaser> createRenderFor(RenderManager manager)
    {
        return new RenderLaser(manager);
    }

}
