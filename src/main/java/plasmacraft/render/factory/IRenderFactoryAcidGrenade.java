package plasmacraft.render.factory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import plasmacraft.PlasmaCraft;
import plasmacraft.entity.EntityAcidGrenade;
import plasmacraft.render.RenderAcidGrenade;

public class IRenderFactoryAcidGrenade implements IRenderFactory<EntityAcidGrenade>
{
    @Override
    public Render<EntityAcidGrenade> createRenderFor(RenderManager manager)
    {
        return new RenderAcidGrenade(manager, PlasmaCraft.ITEM_ACID_GRENADE, Minecraft.getMinecraft().getRenderItem());
    }

}
