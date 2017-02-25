package panda.glassworks.items;

import java.util.HashMap;
import java.util.Map;

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
		 return super.getUnlocalizedName() + "_" + color;
    }

	@Override
	public int getMaxMeta() {
		return 15;
	}

	@Override
	public Map<Integer, ModelResourceLocation> getMetaModelLocations() {
		Map<Integer, ModelResourceLocation> map = new HashMap<Integer, ModelResourceLocation>();
		Block block = this.getBlock();
		for(int i = 0; i <= 15; i++){
			map.put(i, new ModelResourceLocation(block.getRegistryName(), "color=" + EnumDyeColor.byMetadata(i).getName()));
		}
		return map;
	}
}
