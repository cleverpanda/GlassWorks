package panda.glassworks.util.registry.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import panda.glassworks.util.inventory.GlassResultStack;
import panda.glassworks.util.registry.ItemList;

public class GlassBlowingRecipes {

	private static Map<Item, List<GlassResultStack>> glassMultiList = new HashMap<Item, List<GlassResultStack>>();

	public static void initGlassBlowingRecipes() {

		addBlowingRecipe(ItemList.MOLTEN_GLASS, ItemList.BOTTLE_UNFINISHED);
		addBlowingRecipe(ItemList.MOLTEN_GLASS, ItemList.GLASS_LAMP);
		addBlowingRecipe(ItemList.MOLTEN_GLASS, ItemList.GLASS_BULB);
		addBlowingRecipe(ItemList.MOLTEN_GLASS, ItemList.GLASS_JAR_UNF);

		addBlowingRecipe(Items.NETHER_STAR, new ItemStack(Blocks.BEACON));

		addBlowingRecipe(new ItemStack(ItemList.MOLTEN_GLASS, 2), ItemList.GLASS_LENS);
		addBlowingRecipe(new ItemStack(ItemList.MOLTEN_GLASS, 2), ItemList.GLASS_BOWL);

		addBlowingRecipe(new ItemStack(ItemList.MOLTEN_GLASS, 2, 1),
				new ItemStack(ItemList.CRYSTAL_FLASK_UNFINISHED, 1, 0));
	}

	public static void addBlowingRecipe(ItemStack input, ItemStack output) {

		Item item = input.getItem();
		int meta = input.getMetadata();
		int amount = input.stackSize;
		GlassResultStack stack = new GlassResultStack(output, amount, meta);

		if (getAllBlowingResults(item) == null) {
			List<GlassResultStack> list = new ArrayList<GlassResultStack>();
			list.add(stack);
			glassMultiList.put(item, list);
			return;
		}

		List<GlassResultStack> list = getAllBlowingResults(item);
		if (list != null && !list.contains(stack)) {
			list.add(stack);
			glassMultiList.replace(item, list);
		}

	}

	public static void addBlowingRecipe(Item input, ItemStack output) {
		addBlowingRecipe(new ItemStack(input), output);
	}

	public static void addBlowingRecipe(ItemStack input, Item output) {
		addBlowingRecipe(input, new ItemStack(output));
	}

	public static void addBlowingRecipe(Item input, Item output) {
		addBlowingRecipe(new ItemStack(input), new ItemStack(output));
	}

	private static List<GlassResultStack> getAllBlowingResults(Item input) {
		return glassMultiList.get(input);
	}

	// getAllBlowingResults is internal for THIS CLASS only! DO NOT USE IT!

	private static List<GlassResultStack> getAllBlowingResults(ItemStack input) {
		return getAllBlowingResults(input.getItem());
	}

	@Nullable
	public static List<GlassResultStack> getBlowingResults(ItemStack input) {
		List<GlassResultStack> results = getAllBlowingResults(input);
		List<GlassResultStack> returned = new ArrayList<GlassResultStack>();
		if (results != null) {
			for (GlassResultStack theResult : results) {
				if (theResult.getMeta() == input.getMetadata() && theResult.getAmount() <= input.stackSize) {
					returned.add(theResult);
				}
			}
		}
		if (returned.size() == 0)
			return null;
		return returned;
	}

}
