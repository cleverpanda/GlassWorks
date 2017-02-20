package panda.glassworks.items;

import net.minecraft.block.Block;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockFancyGlass extends ItemBlock{

	public ItemBlockFancyGlass(Block block) {
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}
	
	@Override
	  public int getMetadata(int metadata)
	  {
	    return metadata;
	  }
	
	public String getUnlocalizedName(ItemStack stack)
    {
		 String color = EnumDyeColor.byMetadata(stack.getMetadata()).getName().toLowerCase();
		 return super.getUnlocalizedName() + "_" + color;
    }
}
