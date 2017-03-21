package plasmacraft.render.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import plasmacraft.entity.EntityCryoBlast;
import plasmacraft.render.RenderCryoBlast;

public class IRenderFactoryCryoBlast implements IRenderFactory<EntityCryoBlast>
{
    @Override
    public Render<EntityCryoBlast> createRenderFor(RenderManager manager)
    {
        return new RenderCryoBlast(manager);
    }

}
