package panda.glassworks.items.itemblocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import panda.glassworks.util.registry.BlockList;
import panda.glassworks.util.registry.IMeta;

public class ItemBlockSoulGlass extends ItemBlock implements IMeta{

	public ItemBlockSoulGlass(Block block) {
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
		 ;
		 return stack.getMetadata() == 0? BlockList.SOUL_GLASS.getUnlocalizedName(): BlockList.SOUL_GLASS.getUnlocalizedName()+"_on";
    }

	@Override
	public int getMaxMeta() {
		return 1;
	}

	@Override
	public List<ModelResourceLocation> getMetaModelLocations() {
		List<ModelResourceLocation> list = new ArrayList<ModelResourceLocation>();
		Block block = this.getBlock();

			list.add(new ModelResourceLocation(block.getRegistryName(), "ison=false"));
			list.add(new ModelResourceLocation(block.getRegistryName(), "ison=true"));
		
		return list;
	}
}
