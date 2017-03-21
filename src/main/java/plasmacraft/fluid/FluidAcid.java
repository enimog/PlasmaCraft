package plasmacraft.fluid;

public class FluidAcid extends FluidBase
{    
    public FluidAcid()
    {
        super("acid");
        
        setLuminosity(15);
        setDensity(80);
        setViscosity(400);
        setTemperature(400);
    }

}
