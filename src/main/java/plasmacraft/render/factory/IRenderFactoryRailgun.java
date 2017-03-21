package plasmacraft.render.factory;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import plasmacraft.entity.EntityRailgun;
import plasmacraft.render.RenderRailgun;

public class IRenderFactoryRailgun implements IRenderFactory<EntityRailgun>
{
    @Override
    public Render<EntityRailgun> createRenderFor(RenderManager manager)
    {
        return new RenderRailgun(manager);
    }

}
