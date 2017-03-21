package plasmacraft.render.factory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityAcid;
import plasmacraft.render.RenderAcid;

public class IRenderFactoryAcid implements IRenderFactory<EntityAcid>
{
    @Override
    public Render<EntityAcid> createRenderFor(RenderManager manager)
    {
        return new RenderAcid(manager, PlasmaCraft.ITEM_GOOP_ACID, Minecraft.getMinecraft().getRenderItem());
    }

}
