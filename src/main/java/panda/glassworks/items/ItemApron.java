package panda.glassworks.items;

import panda.glassworks.GlassWorks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemApron extends ItemArmor{
	
	public ItemApron(ArmorMaterial materialIn, int renderIndexIn,EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
		this.setCreativeTab(GlassWorks.GlassTab);
	}
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type){
		return GlassWorks.MODID +":textures/models/armor/apron_layer_1.png";
		
	}


	
}
