package plasmacraft.block;

import java.awt.Color;

import plasmacraft.PlasmaCraft;

public class BlockNeptunium extends BlockFluidPlasmaCraft
{
    private final Color LiquidColor = new Color(249, 169, 15);
    
    public BlockNeptunium()
    {
        super(PlasmaCraft.FLUID_NEPTUNIUM);
    }

    @Override
    public int getFluidFlag()
    {
        return FLAG_NEPTUNIUM;
    }

    @Override
    public Color getColor()
    {
        return LiquidColor;
    }
}
