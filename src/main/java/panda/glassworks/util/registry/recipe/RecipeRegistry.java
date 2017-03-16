package panda.glassworks.util.registry.recipe;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import panda.glassworks.GlassWorks;
import panda.glassworks.util.registry.BlockList;
import panda.glassworks.util.registry.ItemList;


public class RecipeRegistry {

	
	public static void register() {
		initGlassRecipes();
		GlassBlowingRecipes.initGlassBlowingRecipes();
		PotionHelper.registerPotionItem(new PotionHelper.ItemPredicateInstance(ItemList.POTION_FLASK));
		BrewingRecipeRegistry.addRecipe(new CrystalBrewingRecipe());
	}


	private static void initGlassRecipes() {

		removeRecipe(Items.GLASS_BOTTLE);	
		

		
		GameRegistry.addSmelting(BlockList.SEAWEED, new ItemStack(ItemList.SODA_ASH), 0.2f);
		
		if(GlassWorks.isBOPInstalled){
			Item BOPWeed = Item.getByNameOrId("biomesoplenty:seaweed");
			GameRegistry.addSmelting(BOPWeed, new ItemStack(ItemList.SODA_ASH), 0.2f);
		}
		
		
				GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(ItemList.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(ItemList.BOTTLE_UNFINISHED, new ItemStack(ItemList.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(Items.GLASS_BOTTLE, new ItemStack(ItemList.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(ItemList.GLASS_BOWL, new ItemStack(ItemList.MOLTEN_GLASS,2), 0.2f);
				GameRegistry.addSmelting(ItemList.GLASS_BULB, new ItemStack(ItemList.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(ItemList.GLASS_LENS, new ItemStack(ItemList.MOLTEN_GLASS,2), 0.2f);
				GameRegistry.addSmelting(ItemList.GLASS_LAMP, new ItemStack(ItemList.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(Blocks.STAINED_GLASS, new ItemStack(ItemList.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(ItemList.TAR_BUCKET, new ItemStack(ItemList.LAMP_OIL_BUCKET), 0.2f);
				GameRegistry.addSmelting(BlockList.FANCY_STAINED_GLASS, new ItemStack(ItemList.MOLTEN_GLASS), 0.2f);
				
				GameRegistry.addShapelessRecipe(new ItemStack(ItemList.GLASS_MIX,2), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND));
				GameRegistry.addShapelessRecipe(new ItemStack(ItemList.OBSIDIAN_GLASS_MIX,3), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.OBSIDIAN),new ItemStack(Blocks.SAND));
				GameRegistry.addShapelessRecipe(new ItemStack(ItemList.GLASS_MIX,4), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND));
				GameRegistry.addShapelessRecipe(new ItemStack(ItemList.GLASS_MIX,6), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND));
				GameRegistry.addShapelessRecipe(new ItemStack(ItemList.CRYSTAL_GLASS_MIX,2), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.SAND,1,1),new ItemStack(Blocks.SAND,1,1),new ItemStack(Items.EMERALD));
				GameRegistry.addShapelessRecipe(new ItemStack(ItemList.CRYSTAL_GLASS_MIX,4), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.SAND,1,1),new ItemStack(Blocks.SAND,1,1),new ItemStack(Items.EMERALD), new ItemStack(ItemList.SODA_ASH),new ItemStack(Blocks.SAND,1,1),new ItemStack(Blocks.SAND,1,1),new ItemStack(Items.EMERALD));
				
				GameRegistry.addSmelting(ItemList.GLASS_MIX, new ItemStack(ItemList.MOLTEN_GLASS,1, 0), 0.2f);
				GameRegistry.addSmelting(ItemList.CRYSTAL_GLASS_MIX, new ItemStack(ItemList.MOLTEN_GLASS,1, 1), 0.4f);
				GameRegistry.addSmelting(ItemList.OBSIDIAN_GLASS_MIX, new ItemStack(ItemList.MOLTEN_GLASS,1, 2), 0.2f);
				
				GameRegistry.addRecipe(new ItemStack(Blocks.GLASS,4), new Object[] {"##", "##", '#', new ItemStack(ItemList.MOLTEN_GLASS,1,0)});
				GameRegistry.addRecipe(new ItemStack(BlockList.BLAST_GLASS,4), new Object[] {"##", "##", '#', new ItemStack(ItemList.MOLTEN_GLASS,1,2)});
				GameRegistry.addRecipe(new ItemStack(Blocks.GLASS,4), new Object[] {"##", "##", '#', new ItemStack(ItemList.MOLTEN_GLASS,1,1)});
				GameRegistry.addRecipe(new ItemStack(Items.GLASS_BOTTLE), new Object[] {"B", "O", 'B', new ItemStack(Blocks.WOODEN_BUTTON), 'O', new ItemStack(ItemList.BOTTLE_UNFINISHED)});
				GameRegistry.addRecipe(new ItemStack(ItemList.GLASS_JAR), new Object[] {"B", "O", 'B', new ItemStack(Blocks.WOODEN_BUTTON), 'O', new ItemStack(ItemList.GLASS_JAR_UNF)});
				GameRegistry.addRecipe(new ItemStack(ItemList.BLOWPIPE), new Object[] {"#  ", " # ", "  #",'#', new ItemStack(Items.IRON_INGOT)});
				
				GameRegistry.addSmelting(ItemList.BROKEN_GLASS, new ItemStack(ItemList.MOLTEN_GLASS,1, 0), 0.2f);
				GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE,1, 2),new ItemStack( BlockList.SEAWEED));
				GameRegistry.addShapelessRecipe(new ItemStack(ItemList.LAMP_OIL_BOTTLE),new ItemStack( ItemList.LAMP_OIL_BUCKET),new ItemStack(Items.GLASS_BOTTLE));
				GameRegistry.addShapelessRecipe(new ItemStack(ItemList.LAMP_OIL_BOTTLE,3),new ItemStack( ItemList.LAMP_OIL_BUCKET),new ItemStack(Items.GLASS_BOTTLE),new ItemStack(Items.GLASS_BOTTLE),new ItemStack(Items.GLASS_BOTTLE));
				
				GameRegistry.addShapelessRecipe(new ItemStack(ItemList.MILK_BOTTLE),new ItemStack(Items.MILK_BUCKET),new ItemStack(Items.GLASS_BOTTLE));
				GameRegistry.addShapelessRecipe(new ItemStack(BlockList.BLAST_GLASS),new ItemStack(ItemList.MOLTEN_GLASS,1,2));
				
				GameRegistry.addRecipe(new ItemStack(ItemList.GLASSCUTTER), new Object[] {"# ", " D",'#', new ItemStack(Items.STICK),'D', new ItemStack(Items.DIAMOND)});
			
				
				//GameRegistry.addRecipe(new ItemStack(door,3), new Object[] {"##", "##", "##", '#', new ItemStack(planks)});
				
				//removeFurnaceRecipe(new ItemStack(Blocks.GLASS));
				GameRegistry.addRecipe(new ItemStack(ItemList.CRYSTAL_FLASK_UNFINISHED2), new Object[] {"n","F",'n', new ItemStack(Items.GOLD_NUGGET),'F', new ItemStack(ItemList.CRYSTAL_FLASK_UNFINISHED)});
				//GLASS CUTTER RECIPES
				for(int meta =0;meta<16;meta++){
					GameRegistry.addRecipe(new ItemStack(BlockList.FANCY_STAINED_GLASS,1,meta), new Object[] {"C", "G",'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE),'G', new ItemStack(Blocks.STAINED_GLASS,1,meta)});
					GameRegistry.addRecipe(new ItemStack(Blocks.STAINED_GLASS_PANE,3,meta), new Object[] {" C", "G ",'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE),'G', new ItemStack(Blocks.STAINED_GLASS,1,meta)});
					//GameRegistry.addRecipe(new ItemStack(BlockList.STAINED_GLASS_SLAB,2,meta), new Object[] {"CG ", 'G', new ItemStack(Blocks.STAINED_GLASS,1,meta),'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE)});
					//GameRegistry.addRecipe(new ItemStack(BlockList.STAINED_GLASS_SLAB,2,meta), new Object[] {"GC", 'G', new ItemStack(Blocks.STAINED_GLASS,1,meta),'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE)});
				}
				GameRegistry.addRecipe(new ItemStack(Blocks.GLASS_PANE,3), new Object[] {" C", "G ",'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE),'G', new ItemStack(Blocks.GLASS)});
				GameRegistry.addRecipe(new ItemStack(BlockList.GLASS_SLAB,2), new Object[] {"  ", "CG",'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE),'G', new ItemStack(Blocks.GLASS)});
				GameRegistry.addRecipe(new ItemStack(BlockList.BLAST_GLASS_SLAB,2), new Object[] {"  ", "CG",'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE),'G', new ItemStack(BlockList.BLAST_GLASS)});
				//GameRegistry.addRecipe(new ItemStack(Blocks.GLASS_PANE,3), new Object[] {" C", "G ",'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE),'G', new ItemStack(Blocks.GLASS)});
				//GameRegistry.addRecipe(new ItemStack(BlockList.GLASS_SLAB,2), new Object[] {"CG ", 'G', new ItemStack(Blocks.GLASS),'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE)});
				//GameRegistry.addRecipe(new ItemStack(BlockList.GLASS_SLAB,2), new Object[] {"GC", 'G', new ItemStack(Blocks.GLASS),'C', new ItemStack(ItemList.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE)});

				
				GameRegistry.addShapelessRecipe(new ItemStack(BlockList.LANTERN),new ItemStack( ItemList.LAMP_OIL_BOTTLE),new ItemStack(ItemList.EMPTY_LANTERN));
				GameRegistry.addShapelessRecipe(new ItemStack(BlockList.LAMP),new ItemStack( ItemList.LAMP_OIL_BOTTLE),new ItemStack(ItemList.EMPTY_LAMP));
				GameRegistry.addRecipe(new ItemStack(ItemList.EMPTY_LAMP,2), new Object[] {"L ","In",'L', new ItemStack(ItemList.GLASS_LAMP),'n', new ItemStack(Items.GOLD_NUGGET),'I', new ItemStack(Items.GOLD_INGOT)});
				GameRegistry.addRecipe(new ItemStack(ItemList.EMPTY_LANTERN,2), new Object[] {"III"," L "," I ",'L', new ItemStack(ItemList.GLASS_LAMP),'I', new ItemStack(Items.IRON_INGOT)});
				//GameRegistry.addRecipe(new ItemStack(ItemList.LARGE_LANTERN), new Object[] {" I ","ILI"," I ",'L', new ItemStack(ItemList.GLASS_LAMP),'I', new ItemStack(Items.IRON_INGOT)});
				
				GameRegistry.addRecipe(new ItemStack(ItemList.SPYGLASS), new Object[] {"nln","n n","nln",'l', new ItemStack(ItemList.GLASS_LENS),'n', new ItemStack(Items.GOLD_NUGGET)});
	}
	
	public static void removeFurnaceRecipe (ItemStack resultItem)
	{
		Map<ItemStack, ItemStack> recipes = FurnaceRecipes.instance().getSmeltingList();
		for (Iterator<Map.Entry<ItemStack,ItemStack>> entries = recipes.entrySet().iterator(); entries.hasNext(); ){
			Map.Entry<ItemStack,ItemStack> entry = entries.next();
			ItemStack result = entry.getValue();
			if (ItemStack.areItemStacksEqual(result, resultItem)){ // If the output matches the specified ItemStack,
				entries.remove(); // Remove the recipe
			}
		}
	}

	/**
	 *
	 * @param oreDictEntries
	 * @param name
	 */
	public static void addOredicts(String[] oreDictEntries, Block name) {
		addOredicts(oreDictEntries, new ItemStack(name));
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], name);
	}

	/**
	 *
	 * @param oreDictEntries
	 * @param name
	 */
	public static void addOredicts(String[] oreDictEntries, Item name) {
		addOredicts(oreDictEntries, new ItemStack(name));
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], name);
	}

	/**
	 *
	 * @param oreDictEntries
	 * @param itemStackName
	 */
	public static void addOredicts(String[] oreDictEntries, ItemStack itemStackName) {
		// for(int i = 0; i < oreDictEntries.length; i++)
		// 	OreDictionary.registerOre(oreDictEntries[i], itemStackName);
		for (final String oreDictEntry : oreDictEntries)
			OreDictionary.registerOre(oreDictEntry, itemStackName);
	}
	
	public static void removeRecipe(Item item)
	{
	
		List<IRecipe> recipies = CraftingManager.getInstance().getRecipeList();
	
		Iterator<IRecipe> remover = recipies.iterator();
		
		while (remover.hasNext())
		{
			ItemStack itemstack = remover.next().getRecipeOutput();
			
			if(itemstack != null && itemstack.getItem() == item)
			{
				remover.remove();
			}
			
			
		}
		
	}
}