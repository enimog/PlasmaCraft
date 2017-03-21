package plasmacraft.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import plasmacraft.PlasmaCraft;

public class SoundRailgun extends SoundEvent
{
    public SoundRailgun()
    {
        super(new ResourceLocation(PlasmaCraft.MODID, "weapon.railgun"));
        setRegistryName(PlasmaCraft.MODID, "soundRailGun");
    }
}
