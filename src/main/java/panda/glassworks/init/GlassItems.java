package panda.glassworks.init;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






















import panda.glassworks.items.ItemApron;
import panda.glassworks.items.ItemBlockFancyGlass;
import panda.glassworks.items.ItemBlowpipe;
import panda.glassworks.items.ItemBucketTar;
import panda.glassworks.items.ItemCrafting;
import panda.glassworks.items.ItemCrystalFlask;
import panda.glassworks.items.ItemGlassBowl;
import panda.glassworks.items.ItemGlassSlab;
import panda.glassworks.items.ItemGlasscutter;
import panda.glassworks.items.ItemMilkBottle;
import panda.glassworks.items.ItemMoltenGlass;
import panda.glassworks.items.ItemPotionFlask;
import panda.glassworks.items.ItemSeaweed;
//import panda.varietytrees.blocks.BlockDoubleVarietySlab;
//import panda.varietytrees.blocks.BlockHalfVarietySlab;
//import panda.varietytrees.blocks.BlockVarietyDoor;
//import panda.varietytrees.blocks.BlockVarietySlab;
//import panda.varietytrees.blocks.BlockVarietyStairs;
//import panda.varietytrees.items.ItemVarietyDoor;
//import panda.varietytrees.items.ItemVarietySlab;
//import panda.varietytrees.util.IOreDictionaryEntry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public abstract class GlassItems {

	
	public static Item BROKEN_GLASS;
	public static Item SEAWEED;
	public static Item SODA_ASH;
	public static Item GLASS_MIX;
	public static Item CRYSTAL_GLASS_MIX;
	public static Item GLASSCUTTER;
	public static Item MOLTEN_GLASS;
	public static Item MOLTEN_CRYSTAL_GLASS;
	public static Item SEAWEED_BLOCK_ITEM;
	public static Item APRON;
	public static Item MILK_BOTTLE;
	public static Item BLOWPIPE;
	public static Item TAR_BUCKET;
	public static Item LAMP_OIL_BUCKET;
	public static Item LAMP_OIL_BOTTLE;
	public static Item GLASS_BOWL;
	public static Item GLASS_LENS;
	public static Item GLASS_LAMP;
	public static Item POTION_FLASK;
	public static Item LANTERN_GLASS;
	public static Item GLASS_BULB;
	public static Item CRYSTAL_FLASK;
	public static Item FANCY_STAINED_GLASS_ITEM;
	public static Item LANTERN_ITEM;
	public static Item EMPTY_LANTERN;
	public static Item GLASS_SLAB;
	public static Item CRYSTAL_FLASK_UNFINISHED;
	public static Item BOTTLE_UNFINISHED;
	public static Item BLAST_GLASS;
	public static Item OBSIDIAN_GLASS_MIX;
	public static Item LAMP_ITEM;
	public static Item EMPTY_LAMP;
	public static Item STAINED_GLASS_SLAB;
	public static Item SOUL_GLASS;
	
	

	private static boolean initDone = false;

	private static Map<String, Item> itemRegistry = new HashMap<String, Item>();
	private static Map<Item, String> allItems = new HashMap<Item, String>();
	

	public static Item getItemByName(String name) {
		return itemRegistry.get(name);
	}

	public static String getNameOfItem(Item i) {
		return allItems.get(i);
	}

	public static void init() {
		if (initDone)
			return;

		GlassBlocks.init();


		SEAWEED = new ItemSeaweed(GlassBlocks.SEAWEED);
		registerItem(SEAWEED,"seaweed");
		
		SODA_ASH = new ItemCrafting();
		registerItem(SODA_ASH,"soda_ash");
		
		GLASS_MIX = new ItemCrafting();
		registerItem(GLASS_MIX,"glass_mix");
		
		OBSIDIAN_GLASS_MIX = new ItemCrafting();
		registerItem(OBSIDIAN_GLASS_MIX,"obsidian_glass_mix");
		
		CRYSTAL_GLASS_MIX = new ItemCrafting();
		registerItem(CRYSTAL_GLASS_MIX,"crystal_glass_mix");
		
		GLASSCUTTER = new ItemGlasscutter();
		registerItem(GLASSCUTTER,"glasscutter");
		
		BROKEN_GLASS = new ItemCrafting();
		registerItem(BROKEN_GLASS,"broken_glass");
		
		MOLTEN_GLASS = new ItemMoltenGlass();
		registerItem(MOLTEN_GLASS,"molten_glass");
		
		APRON = new ItemApron(ArmorMaterial.LEATHER, 1,EntityEquipmentSlot.CHEST);
		registerItem(APRON,"apron");
		
		SEAWEED_BLOCK_ITEM = new ItemBlock(GlassBlocks.SEAWEED);
		registerItem(SEAWEED_BLOCK_ITEM,"seaweed_itemblock");
		
		FANCY_STAINED_GLASS_ITEM = new ItemBlockFancyGlass(GlassBlocks.FANCY_STAINED_GLASS);
		registerItem(FANCY_STAINED_GLASS_ITEM,"fancy_stained_glass");
		
		MILK_BOTTLE = new ItemMilkBottle();
		
		registerItem(MILK_BOTTLE,"bottle_milk");
		
		BLOWPIPE = new ItemBlowpipe();
		registerItem(BLOWPIPE,"blowpipe");
		
		GLASS_LENS = new ItemCrafting();
		registerItem(GLASS_LENS,"glass_lens");
		
		GLASS_LAMP = new ItemCrafting();
		registerItem(GLASS_LAMP,"glass_lamp");
		
		GLASS_BULB = new ItemCrafting();
		registerItem(GLASS_BULB,"glass_bulb");
		
		TAR_BUCKET = new ItemBucketTar(GlassBlocks.TAR);
		registerItem(TAR_BUCKET,"bucket_tar");
		
		LAMP_OIL_BUCKET = new ItemCrafting();
		LAMP_OIL_BUCKET.setContainerItem(Items.BUCKET);
		registerItem(LAMP_OIL_BUCKET,"bucket_lamp_oil");
		
		LAMP_OIL_BOTTLE = new ItemCrafting();
		LAMP_OIL_BOTTLE.setContainerItem(Items.GLASS_BOTTLE);
		registerItem(LAMP_OIL_BOTTLE,"bottle_lamp_oil");
		
		GLASS_BOWL = new ItemGlassBowl();
		registerItem(GLASS_BOWL,"glass_bowl");
		
		POTION_FLASK = new ItemPotionFlask();
		registerItem(POTION_FLASK,"potion_flask");
		
		CRYSTAL_FLASK = new ItemCrystalFlask();
		registerItem(CRYSTAL_FLASK,"crystal_flask");
		
		LANTERN_ITEM = new ItemBlock(GlassBlocks.LANTERN);
		LANTERN_ITEM.setMaxStackSize(16);
		registerItem(LANTERN_ITEM,"oil_lantern");
		
		EMPTY_LANTERN = new ItemCrafting();
		EMPTY_LANTERN.setMaxStackSize(16);
		registerItem(EMPTY_LANTERN,"empty_lantern");
		
		LAMP_ITEM = new ItemBlock(GlassBlocks.LAMP);
		LAMP_ITEM.setMaxStackSize(16);
		registerItem(LAMP_ITEM,"oil_lamp");
		
		EMPTY_LAMP = new ItemCrafting();
		EMPTY_LAMP.setMaxStackSize(16);
		registerItem(EMPTY_LAMP,"empty_lamp");
		
		GLASS_SLAB = createSlab("glass",GlassBlocks.GLASS_SLAB,GlassBlocks.GLASS_SLAB,GlassBlocks.DOUBLE_GLASS_SLAB);


		CRYSTAL_FLASK_UNFINISHED = new ItemCrafting();
		registerItem(CRYSTAL_FLASK_UNFINISHED,"unfinished_flask");
		
		BOTTLE_UNFINISHED = new ItemCrafting();
		registerItem(BOTTLE_UNFINISHED,"unfinished_bottle");
		
		BLAST_GLASS = new ItemBlock(GlassBlocks.BLAST_GLASS);
		registerItem(BLAST_GLASS,"blast_glass");
		
		SOUL_GLASS = new ItemBlock(GlassBlocks.SOUL_GLASS);
		registerItem(SOUL_GLASS,"soul_glass");
		
		//STAINED_GLASS_SLAB = createSlab("item_stained_glass",GlassBlocks.STAINED_GLASS_SLAB,GlassBlocks.STAINED_GLASS_SLAB,GlassBlocks.DOUBLE_STAINED_GLASS_SLAB);


		
		
		
		//apple_door = createDoor(Materials.apple, Blocks.apple_door);
		//apple_slab = createSlab(Materials.apple, Blocks.apple_slab, Blocks.apple_slab, Blocks.double_apple_slab);



		// TODO: Make this support multiple oredicts
		//for (final Item i : allItems.keySet())
		//	if (i instanceof IOreDictionaryEntry)
		//		OreDictionary.registerOre(((IOreDictionaryEntry) i).getOreDictionaryName(), i);

		initDone = true;
	}

	private static Item registerItem(Item item, String name) {
		item.setRegistryName(name);
		item.setUnlocalizedName(item.getRegistryName().getResourceDomain() + "." + name);
		GameRegistry.register(item);
		itemRegistry.put(name, item);
		allItems.put(item, name);

		//if (item instanceof IOreDictionaryEntry)
		//	OreDictionary.registerOre(((IOreDictionaryEntry) item).getOreDictionaryName(), item);
		return item;
	}

	/*private static Item createSlab(WoodMaterial wood, Block block, BlockSlab slab, BlockSlab doubleslab) {
		return registerItem(new ItemVarietySlab(wood, block, slab, doubleslab), "slab", wood, CreativeTabs.BUILDING_BLOCKS);
	}*/

	/*private static Item createDoor(WoodMaterial metal, BlockDoor door) {
		final Item item = new ItemVarietyDoor(door, metal);
		registerItem(item, "door", metal, CreativeTabs.REDSTONE);
		return item;
	}*/

	private static Item createSlab(String name, Block block, BlockSlab slab, BlockSlab doubleslab) {
		return registerItem(new ItemSlab(block, slab, doubleslab), name+ "_slab");
	}
	
	public static Map<String, Item> getItemRegistry() {
		return itemRegistry;
	}
}