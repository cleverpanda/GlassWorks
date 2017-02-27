package panda.glassworks.items;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import panda.glassworks.GlassWorks;

public class ItemApron extends ItemArmor{
	
	public ItemApron() {
		super(ArmorMaterial.LEATHER, 1, EntityEquipmentSlot.CHEST);
		this.setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("apron");
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type){
		return GlassWorks.MODID +":textures/models/armor/apron_layer_1.png";
		
	}



	
}
