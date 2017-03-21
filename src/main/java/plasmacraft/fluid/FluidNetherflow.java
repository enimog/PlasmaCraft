package plasmacraft.fluid;

public class FluidNetherflow extends FluidBase
{    
    public FluidNetherflow()
    {
        super("netherflow");
        
        setLuminosity(15);
        setDensity(500);
        setViscosity(2000);
        setTemperature(1000);
    }

}
