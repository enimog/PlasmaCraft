package plasmacraft.fluid;

public class FluidRadionite extends FluidBase
{    
    public FluidRadionite()
    {
        super("radionite");
        
        setLuminosity(15);
        setDensity(100);
        setViscosity(100);
        setTemperature(500);
    }

}
