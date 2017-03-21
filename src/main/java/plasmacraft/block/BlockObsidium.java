package plasmacraft.block;

import java.awt.Color;

import plasmacraft.PlasmaCraft;

public class BlockObsidium extends BlockFluidPlasmaCraft
{
    private final Color LiquidColor = new Color(80, 4, 150);
    
    public BlockObsidium()
    {
        super(PlasmaCraft.FLUID_OBSIDIUM);
    }

    @Override
    public int getFluidFlag()
    {
        return FLAG_OBSIDIUM;
    }

    @Override
    public Color getColor()
    {
        return LiquidColor;
    }
}
