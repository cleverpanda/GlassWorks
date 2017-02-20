package panda.glassworks.items;

import panda.glassworks.GlassWorks;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemCrafting extends Item{

	public ItemCrafting()
    {
    	super();
    	this.setCreativeTab(GlassWorks.GlassTab);
    }
}
