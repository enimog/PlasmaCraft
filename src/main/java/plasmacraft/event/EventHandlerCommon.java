package plasmacraft.event;

import java.awt.Color;

import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import plasmacraft.block.BlockFluidPlasmaCraft;

public class EventHandlerCommon
{
    @SubscribeEvent
    public void OnRenderFogColors(FogColors e)
    {
        if (e.getState().getBlock() instanceof BlockFluidPlasmaCraft)
        {
            Color FogColor = ((BlockFluidPlasmaCraft)e.getState().getBlock()).getColor();
            e.setGreen((float)FogColor.getGreen() / 255);
            e.setRed((float)FogColor.getRed() / 255);
            e.setBlue((float)FogColor.getBlue() / 255);
        }
    }
    
    @SubscribeEvent
    public void OnRenderFogDensity(FogDensity e)
    {
        if (e.getState().getBlock() instanceof BlockFluidPlasmaCraft)
        {
            e.setCanceled(true);
            e.setDensity(0.5F);
        }
    }
}
