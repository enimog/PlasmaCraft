package plasmacraft.block;

import java.awt.Color;

import plasmacraft.PlasmaCraft;

public class BlockRadionite extends BlockFluidPlasmaCraft
{
    private final Color LiquidColor = new Color(28, 14, 169);
    
    public BlockRadionite()
    {
        super(PlasmaCraft.FLUID_RADIONITE);
    }

    @Override
    public int getFluidFlag()
    {
        return FLAG_RADIONITE;
    }

    @Override
    public Color getColor()
    {
        return LiquidColor;
    }
}
