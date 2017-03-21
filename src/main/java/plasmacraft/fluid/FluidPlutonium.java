package plasmacraft.fluid;

public class FluidPlutonium extends FluidBase
{    
    public FluidPlutonium()
    {
        super("plutonium");
        
        setLuminosity(2);
        setDensity(8000);
        setViscosity(14000);
        setTemperature(400);
    }

}
