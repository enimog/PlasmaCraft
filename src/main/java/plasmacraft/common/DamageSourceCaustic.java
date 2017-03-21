package plasmacraft.common;

import net.minecraft.util.DamageSource;

public class DamageSourceCaustic extends DamageSource
{

    public DamageSourceCaustic()
    {
        super("caustic");
        
        setDamageBypassesArmor();
    }

}
