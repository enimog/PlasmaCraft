package plasmacraft.block;

import java.awt.Color;

import plasmacraft.PlasmaCraft;

public class BlockAcid extends BlockFluidPlasmaCraft
{
    private final Color LiquidColor = new Color(70, 243, 7);
    
    public BlockAcid()
    {
        super(PlasmaCraft.FLUID_ACID);
    }

    @Override
    public int getFluidFlag()
    {
        return FLAG_ACID;
    }

    @Override
    public Color getColor()
    {
        return LiquidColor;
    }

}
