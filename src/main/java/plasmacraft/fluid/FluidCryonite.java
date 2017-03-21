package plasmacraft.fluid;

public class FluidCryonite extends FluidBase
{    
    public FluidCryonite()
    {
        super("cryonite");
        
        setLuminosity(4);
        setDensity(250);
        setViscosity(500);
        setTemperature(-1000);
    }

}
