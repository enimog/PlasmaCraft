package plasmacraft.block;

import java.awt.Color;

import plasmacraft.PlasmaCraft;

public class BlockUranium extends BlockFluidPlasmaCraft
{
    private final Color LiquidColor = new Color(229, 242, 25);
    
    public BlockUranium()
    {
        super(PlasmaCraft.FLUID_URANIUM);
    }

    @Override
    public int getFluidFlag()
    {
        return FLAG_URANIUM;
    }

    @Override
    public Color getColor()
    {
        return LiquidColor;
    }
}
