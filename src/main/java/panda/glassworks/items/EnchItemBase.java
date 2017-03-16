package panda.glassworks.items;

import net.minecraft.item.ItemStack;

public class EnchItemBase extends ItemBase {
	public EnchItemBase(String name) {
		super(name);
	}

	@Override
	public int getItemEnchantability() {
		return 35;
	}

	@Override
	public boolean isItemTool(ItemStack stack) {
		return true;
	}

}
