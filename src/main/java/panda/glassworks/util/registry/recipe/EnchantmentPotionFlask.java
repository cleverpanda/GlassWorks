package panda.glassworks.util.registry.recipe;

import panda.glassworks.util.registry.ItemList;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;

public class EnchantmentPotionFlask extends Enchantment{

	public EnchantmentPotionFlask() {
		super(Enchantment.Rarity.COMMON, EnumEnchantmentType.ALL, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		this.setRegistryName("enchantment_potion_flask");
		
		
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public boolean canApply(ItemStack stack) {
		//System.out.println("DOOOTOOOOOOOTTTT");
		return stack.getItem() == ItemList.CRYSTAL_FLASK_UNFINISHED2;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		//System.out.println("DOOOTOOOOOOOTTTT");
		return canApply(stack);
	}

	@Override
	public boolean isAllowedOnBooks() {
		return false;
	}

}
