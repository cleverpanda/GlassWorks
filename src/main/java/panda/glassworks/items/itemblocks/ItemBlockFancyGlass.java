package panda.glassworks.items.itemblocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import panda.glassworks.util.registry.IMeta;

public class ItemBlockFancyGlass extends ItemBlock implements IMeta{

	public ItemBlockFancyGlass(Block block) {
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		setRegistryName(block.getRegistryName());
	}
	
	@Override
	  public int getMetadata(int metadata)
	  {
	    return metadata;
	  }
	
	public String getUnlocalizedName(ItemStack stack)
    {
		 String color = EnumDyeColor.byMetadata(stack.getMetadata()).getName().toLowerCase();
		 return super.getUnlocalizedName() + "." + color;
    }

	@Override
	public int getMaxMeta() {
		return 15;
	}

	@Override
	public List<ModelResourceLocation> getMetaModelLocations() {
		List<ModelResourceLocation> list = new ArrayList<ModelResourceLocation>();
		Block block = this.getBlock();
		for(int i = 0; i <= 15; i++){
			list.add(new ModelResourceLocation(block.getRegistryName(), "color=" + EnumDyeColor.byMetadata(i).getName()));
		}
		return list;
	}
}
