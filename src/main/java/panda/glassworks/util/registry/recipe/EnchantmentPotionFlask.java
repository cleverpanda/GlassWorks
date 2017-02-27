package panda.glassworks.util.registry.recipe;

import panda.glassworks.util.registry.ItemList;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class EnchantmentPotionFlask extends Enchantment{

	public EnchantmentPotionFlask() {
		super(Enchantment.Rarity.COMMON, EnumEnchantmentType.ALL, new EntityEquipmentSlot[] {EntityEquipmentSlot.MAINHAND});
		this.setRegistryName("enchantment_potion_flask");
		GameRegistry.register(this);
		
	}

	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 30;
	}

	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return 50;
	}

	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() == ItemList.CRYSTAL_FLASK_UNFINISHED2;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return canApply(stack);
	}

	@Override
	public boolean isAllowedOnBooks() {
		return false;
	}

}
