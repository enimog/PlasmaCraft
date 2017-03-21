package plasmacraft.sound;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import plasmacraft.PlasmaCraft;

public class SoundLaserGun extends SoundEvent
{
    public SoundLaserGun()
    {
        super(new ResourceLocation(PlasmaCraft.MODID, "weapon.lasergun"));
        setRegistryName(PlasmaCraft.MODID, "soundLaserGun");
    }
}
