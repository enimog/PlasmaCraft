package plasmacraft.block;

import java.awt.Color;

import plasmacraft.PlasmaCraft;

public class BlockNetherflow extends BlockFluidPlasmaCraft
{
    private final Color LiquidColor = new Color(242, 15, 5);
    
    public BlockNetherflow()
    {
        super(PlasmaCraft.FLUID_NETHERFLOW);
    }

    @Override
    public int getFluidFlag()
    {
        return FLAG_NETHERFLOW;
    }

    @Override
    public Color getColor()
    {
        return LiquidColor;
    }
}
