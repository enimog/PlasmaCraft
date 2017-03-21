package plasmacraft.block.itemblock;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import plasmacraft.block.BlockOre;

public class ItemBlockOre extends ItemBlock {

    public ItemBlockOre(Block block)
    {
        super(block);
        
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile.ore_" + ((BlockOre)block).getSpecialName();
    }
}