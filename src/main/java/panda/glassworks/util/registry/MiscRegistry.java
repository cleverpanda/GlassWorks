package panda.glassworks.util.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import panda.glassworks.util.registry.recipe.EnchantmentPotionFlask;

public class MiscRegistry {

	public static final Enchantment POTION_FLASK_ENCH = new EnchantmentPotionFlask();
	
	public static List<Block> list = new ArrayList<Block>();
	
}