package panda.glassworks.items;

import panda.glassworks.GlassWorks;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item{

	public ItemBase(String name)
    {
    	super();
    	this.setCreativeTab(GlassWorks.GlassTab);
    	setRegistryName(name);
    }
}
