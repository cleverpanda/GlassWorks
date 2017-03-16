package panda.glassworks.items;

import net.minecraft.item.Item;
import panda.glassworks.GlassWorks;

public class ItemBase extends Item {

	public ItemBase(String name) {
		super();
		this.setCreativeTab(GlassWorks.GlassTab);
		setRegistryName(name);
	}
}
