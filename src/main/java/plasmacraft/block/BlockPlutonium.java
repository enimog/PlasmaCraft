package plasmacraft.block;

import java.awt.Color;

import plasmacraft.PlasmaCraft;

public class BlockPlutonium extends BlockFluidPlasmaCraft
{
    private final Color LiquidColor = new Color(100, 167, 130);
    
    public BlockPlutonium()
    {
        super(PlasmaCraft.FLUID_PLUTONIUM);
    }

    @Override
    public int getFluidFlag()
    {
        return FLAG_PLUTONIUM;
    }

    @Override
    public Color getColor()
    {
        return LiquidColor;
    }
}
