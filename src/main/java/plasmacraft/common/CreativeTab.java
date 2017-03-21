package plasmacraft.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs {
    private Item Icon;
    
    // ========== Constructor ==========
    public CreativeTab(int tabID, String modID) {
        super(tabID, modID);
    }
    
    // ========== Tab Icon ==========
    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Icon;
    }
    
    public <T extends Item> void setIcon(T item)
    {
        Icon = item;
    }
}