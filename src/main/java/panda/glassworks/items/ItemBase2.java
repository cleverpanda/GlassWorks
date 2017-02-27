package panda.glassworks.items;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;

public class ItemBase2 extends Item{
	public ItemBase2(String name)
    {
    	super();
    	this.setCreativeTab(GlassWorks.GlassTab);
    	setRegistryName(name);
    }
	
	 @Override 
	 public int getItemEnchantability()
	 {
	    	System.out.println("DOOOOTGET");
	    	return 35;
	 }
		 
}
