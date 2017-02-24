package panda.glassworks.init;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import panda.glassworks.GlassWorks;
import panda.glassworks.brewing.CrystalBrewingRecipe;
import panda.glassworks.util.GlassBlowingRecipes;

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


public abstract class Recipes {

	private static boolean initDone = false;
	//public static GlassBlowingRecipes GlassRecipes;
	
	public static void init() {
		if (initDone)
			return;

		GlassBlocks.init();
		GlassItems.init();

		initGlassRecipes();
		GlassBlowingRecipes.instance().InitGlassBlowingRecipes();
		//GlassRecipes = GlassBlowingRecipes.instance();
		PotionHelper.registerPotionItem(new PotionHelper.ItemPredicateInstance(GlassItems.POTION_FLASK));
		BrewingRecipeRegistry.addRecipe(new CrystalBrewingRecipe());
		

		initDone = true;
	}


	private static void initGlassRecipes() {

		removeRecipe(Items.GLASS_BOTTLE);	
		
		//TEMP RECIPES
		GameRegistry.addRecipe(new ItemStack(GlassItems.BOTTLE_UNFINISHED), new Object[] {" ", "#", '#', new ItemStack(GlassItems.MOLTEN_GLASS,1,0)});
		GameRegistry.addRecipe(new ItemStack(GlassItems.GLASS_LAMP), new Object[] {"#", " ", '#', new ItemStack(GlassItems.MOLTEN_GLASS,1,0)});
		GameRegistry.addRecipe(new ItemStack(GlassItems.GLASS_LENS), new Object[] {"  ", "##", '#', new ItemStack(GlassItems.MOLTEN_GLASS,1,0)});
		GameRegistry.addRecipe(new ItemStack(GlassItems.GLASS_BOWL), new Object[] {"##", "  ", '#', new ItemStack(GlassItems.MOLTEN_GLASS,1,0)});
		GameRegistry.addRecipe(new ItemStack(GlassItems.CRYSTAL_FLASK_UNFINISHED), new Object[] {"#", "#", '#', new ItemStack(GlassItems.MOLTEN_GLASS,1,1)});
		/////////////
		
		GameRegistry.addSmelting(GlassItems.SEAWEED, new ItemStack(GlassItems.SODA_ASH), 0.2f);
		
		if(GlassWorks.isBOPInstalled){
			Item BOPWeed = GameRegistry.findItem("biomesoplenty", "seaweed");
			GameRegistry.addSmelting(BOPWeed, new ItemStack(GlassItems.SODA_ASH), 0.2f);
		}
		
		
				GameRegistry.addSmelting(Blocks.GLASS, new ItemStack(GlassItems.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(GlassItems.BOTTLE_UNFINISHED, new ItemStack(GlassItems.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(Items.GLASS_BOTTLE, new ItemStack(GlassItems.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(GlassItems.GLASS_BOWL, new ItemStack(GlassItems.MOLTEN_GLASS,2), 0.2f);
				GameRegistry.addSmelting(GlassItems.GLASS_BULB, new ItemStack(GlassItems.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(GlassItems.GLASS_LENS, new ItemStack(GlassItems.MOLTEN_GLASS,2), 0.2f);
				GameRegistry.addSmelting(GlassItems.GLASS_LAMP, new ItemStack(GlassItems.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(Blocks.STAINED_GLASS, new ItemStack(GlassItems.MOLTEN_GLASS), 0.2f);
				GameRegistry.addSmelting(GlassItems.TAR_BUCKET, new ItemStack(GlassItems.LAMP_OIL_BUCKET), 0.2f);
				GameRegistry.addSmelting(GlassBlocks.FANCY_STAINED_GLASS, new ItemStack(GlassItems.MOLTEN_GLASS), 0.2f);
				
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.GLASS_MIX,2), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND));
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.OBSIDIAN_GLASS_MIX,3), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.OBSIDIAN),new ItemStack(Blocks.SAND));
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.GLASS_MIX,4), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND));
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.GLASS_MIX,6), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.SAND),new ItemStack(Blocks.SAND));
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.CRYSTAL_GLASS_MIX,2), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.SAND,1,1),new ItemStack(Blocks.SAND,1,1),new ItemStack(Items.EMERALD));
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.CRYSTAL_GLASS_MIX,4), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.SAND,1,1),new ItemStack(Blocks.SAND,1,1),new ItemStack(Items.EMERALD), new ItemStack(GlassItems.SODA_ASH),new ItemStack(Blocks.SAND,1,1),new ItemStack(Blocks.SAND,1,1),new ItemStack(Items.EMERALD));
				
				GameRegistry.addSmelting(GlassItems.GLASS_MIX, new ItemStack(GlassItems.MOLTEN_GLASS,1, 0), 0.2f);
				GameRegistry.addSmelting(GlassItems.CRYSTAL_GLASS_MIX, new ItemStack(GlassItems.MOLTEN_GLASS,1, 1), 0.4f);
				GameRegistry.addSmelting(GlassItems.OBSIDIAN_GLASS_MIX, new ItemStack(GlassItems.MOLTEN_GLASS,1, 2), 0.2f);
				
				GameRegistry.addRecipe(new ItemStack(Blocks.GLASS,4), new Object[] {"##", "##", '#', new ItemStack(GlassItems.MOLTEN_GLASS,1,0)});
				GameRegistry.addRecipe(new ItemStack(GlassBlocks.BLAST_GLASS,4), new Object[] {"##", "##", '#', new ItemStack(GlassItems.MOLTEN_GLASS,1,2)});
				//GameRegistry.addRecipe(new ItemStack(Blocks.GLASS,4), new Object[] {"##", "##", '#', new ItemStack(GlassItems.MOLTEN_GLASS,1,1)});
				GameRegistry.addRecipe(new ItemStack(Items.GLASS_BOTTLE), new Object[] {"B", "O", 'B', new ItemStack(Blocks.WOODEN_BUTTON), 'O', new ItemStack(GlassItems.BOTTLE_UNFINISHED)});
				GameRegistry.addRecipe(new ItemStack(GlassItems.BLOWPIPE), new Object[] {"#  ", " # ", "  #",'#', new ItemStack(Items.IRON_INGOT)});
				
				GameRegistry.addSmelting(GlassItems.BROKEN_GLASS, new ItemStack(GlassItems.MOLTEN_GLASS,1, 0), 0.2f);
				GameRegistry.addShapelessRecipe(new ItemStack(Items.DYE,1, 2),new ItemStack( GlassItems.SEAWEED));
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.LAMP_OIL_BOTTLE),new ItemStack( GlassItems.LAMP_OIL_BUCKET),new ItemStack(Items.GLASS_BOTTLE));
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.LAMP_OIL_BOTTLE,3),new ItemStack( GlassItems.LAMP_OIL_BUCKET),new ItemStack(Items.GLASS_BOTTLE),new ItemStack(Items.GLASS_BOTTLE),new ItemStack(Items.GLASS_BOTTLE));
				
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.MILK_BOTTLE),new ItemStack(Items.MILK_BUCKET),new ItemStack(Items.GLASS_BOTTLE));
				GameRegistry.addShapelessRecipe(new ItemStack(GlassBlocks.BLAST_GLASS),new ItemStack(GlassItems.MOLTEN_GLASS,1,2));
				
				GameRegistry.addRecipe(new ItemStack(GlassItems.GLASSCUTTER), new Object[] {"# ", " D",'#', new ItemStack(Items.STICK),'D', new ItemStack(Items.DIAMOND)});
				GameRegistry.addRecipe(new ItemStack(GlassItems.APRON), new Object[] {" S ","SLS", " L ",'L', new ItemStack(Items.LEATHER),'S', new ItemStack(Items.STRING)});
				
				//GameRegistry.addRecipe(new ItemStack(door,3), new Object[] {"##", "##", "##", '#', new ItemStack(planks)});
				
				removeFurnaceRecipe(new ItemStack(Blocks.GLASS));
				GameRegistry.addRecipe(new ItemStack(GlassItems.CRYSTAL_FLASK), new Object[] {"n","F",'n', new ItemStack(Items.GOLD_NUGGET),'F', new ItemStack(GlassItems.CRYSTAL_FLASK_UNFINISHED)});
				
				//GLASS CUTTER RECIPES
				for(int meta =0;meta<16;meta++){
					GameRegistry.addRecipe(new ItemStack(GlassBlocks.FANCY_STAINED_GLASS,1,meta), new Object[] {"C", "G",'C', new ItemStack(GlassItems.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE),'G', new ItemStack(Blocks.STAINED_GLASS,1,meta)});
					GameRegistry.addRecipe(new ItemStack(Blocks.STAINED_GLASS_PANE,3,meta), new Object[] {" C", "G ",'C', new ItemStack(GlassItems.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE),'G', new ItemStack(Blocks.STAINED_GLASS,1,meta)});
					//GameRegistry.addRecipe(new ItemStack(GlassBlocks.STAINED_GLASS_SLAB,2,meta), new Object[] {"CG ", 'G', new ItemStack(Blocks.STAINED_GLASS,1,meta),'C', new ItemStack(GlassItems.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE)});
					//GameRegistry.addRecipe(new ItemStack(GlassBlocks.STAINED_GLASS_SLAB,2,meta), new Object[] {"GC", 'G', new ItemStack(Blocks.STAINED_GLASS,1,meta),'C', new ItemStack(GlassItems.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE)});
				}
				GameRegistry.addRecipe(new ItemStack(Blocks.GLASS_PANE,3), new Object[] {" C", "G ",'C', new ItemStack(GlassItems.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE),'G', new ItemStack(Blocks.GLASS)});
				GameRegistry.addRecipe(new ItemStack(GlassBlocks.GLASS_SLAB,2), new Object[] {"CG ", 'G', new ItemStack(Blocks.GLASS),'C', new ItemStack(GlassItems.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE)});
				GameRegistry.addRecipe(new ItemStack(GlassBlocks.GLASS_SLAB,2), new Object[] {"GC", 'G', new ItemStack(Blocks.GLASS),'C', new ItemStack(GlassItems.GLASSCUTTER,1,OreDictionary.WILDCARD_VALUE)});

				
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.LANTERN_ITEM),new ItemStack( GlassItems.LAMP_OIL_BOTTLE),new ItemStack(GlassItems.EMPTY_LANTERN));
				GameRegistry.addShapelessRecipe(new ItemStack(GlassItems.LAMP_ITEM),new ItemStack( GlassItems.LAMP_OIL_BOTTLE),new ItemStack(GlassItems.EMPTY_LAMP));
				//GameRegistry.addRecipe(new ItemStack(GlassItems.EMPTY_LAMP,2), new Object[] {"L","I",'L', new ItemStack(GlassItems.GLASS_LAMP),'I', new ItemStack(Items.GOLD_INGOT)});
				GameRegistry.addRecipe(new ItemStack(GlassItems.EMPTY_LANTERN,2), new Object[] {"III"," L "," I ",'L', new ItemStack(GlassItems.GLASS_LAMP),'I', new ItemStack(Items.IRON_INGOT)});
				//GameRegistry.addRecipe(new ItemStack(GlassItems.LARGE_LANTERN), new Object[] {" I ","ILI"," I ",'L', new ItemStack(GlassItems.GLASS_LAMP),'I', new ItemStack(Items.IRON_INGOT)});
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