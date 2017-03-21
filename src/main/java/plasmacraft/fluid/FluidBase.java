package plasmacraft.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import plasmacraft.PlasmaCraft;

public class FluidBase extends Fluid
{

    public FluidBase(String fluidName)
    {
        super(fluidName, new ResourceLocation(PlasmaCraft.MODID + ":fluids/" + fluidName), new ResourceLocation(PlasmaCraft.MODID + ":fluids/" + fluidName + "_flow"));

        FluidRegistry.registerFluid(this);
    }
}
