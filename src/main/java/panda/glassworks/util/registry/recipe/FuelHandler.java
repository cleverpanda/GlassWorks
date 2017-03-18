package panda.glassworks.util.registry.recipe;

import panda.glassworks.util.registry.ItemList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		return fuel.getItem() == ItemList.TAR_BUCKET? 4800:0;
	}

}
