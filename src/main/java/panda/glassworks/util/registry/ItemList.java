package panda.glassworks.util.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import panda.glassworks.items.ItemApron;
import panda.glassworks.items.ItemBase;
import panda.glassworks.items.ItemBlowpipe;
import panda.glassworks.items.ItemBucketTar;
import panda.glassworks.items.ItemCrystalFlask;
import panda.glassworks.items.ItemGlassBowl;
import panda.glassworks.items.ItemGlasscutter;
import panda.glassworks.items.ItemMilkBottle;
import panda.glassworks.items.ItemMoltenGlass;
import panda.glassworks.items.ItemPotionFlask;

public final class ItemList {
	
	/*
	 * Declare items here and then subsequently add them to getList(); They will be automatically moved to registration and model loading.
	 */
	
	public static final Item BROKEN_GLASS = new ItemBase("broken_glass");
	public static final Item SODA_ASH = new ItemBase("soda_ash");
	public static final Item GLASS_MIX = new ItemBase("glass_mix");
	public static final Item CRYSTAL_GLASS_MIX = new ItemBase("crystal_glass_mix");
	public static final Item GLASSCUTTER = new ItemGlasscutter();
	public static final Item MOLTEN_GLASS = new ItemMoltenGlass();
	public static final Item APRON = new ItemApron();
	public static final Item MILK_BOTTLE = new ItemMilkBottle();
	public static final Item BLOWPIPE = new ItemBlowpipe();
	public static final Item TAR_BUCKET = new ItemBucketTar();
	public static final Item LAMP_OIL_BUCKET = new ItemBase("lamp_oil_bucket").setContainerItem(Items.BUCKET);
	public static final Item LAMP_OIL_BOTTLE = new ItemBase("lamp_oil_bottle").setContainerItem(Items.GLASS_BOTTLE);
	public static final Item GLASS_BOWL = new ItemGlassBowl();
	public static final Item GLASS_LENS = new ItemBase("glass_lens");
	public static final Item GLASS_LAMP = new ItemBase("glass_lamp");
	public static final Item POTION_FLASK = new ItemPotionFlask();
	public static final Item GLASS_BULB = new ItemBase("glass_bulb");
	public static final Item CRYSTAL_FLASK = new ItemCrystalFlask();
	public static final Item EMPTY_LANTERN = new ItemBase("empty_lantern");
	public static final Item CRYSTAL_FLASK_UNFINISHED = new ItemBase("crystal_flask_unf");
	public static final Item BOTTLE_UNFINISHED = new ItemBase("bottle_unf");
	public static final Item OBSIDIAN_GLASS_MIX = new ItemBase("obsidian_glass_mix");
	public static final Item EMPTY_LAMP = new ItemBase("empty_lamp");
	
	
	
	
	
	public static List<Item> getList() {
		List<Item> list = new ArrayList<Item>();
		list.add(BROKEN_GLASS);
		list.add(SODA_ASH);
		list.add(GLASS_MIX);
		list.add(CRYSTAL_GLASS_MIX);
		list.add(GLASSCUTTER);
		list.add(MOLTEN_GLASS);
		list.add(APRON);
		list.add(MILK_BOTTLE);
		list.add(BLOWPIPE);
		list.add(TAR_BUCKET);
		list.add(LAMP_OIL_BUCKET);
		list.add(LAMP_OIL_BOTTLE);
		list.add(GLASS_BOWL);
		list.add(GLASS_LENS);
		list.add(GLASS_LAMP);
		list.add(POTION_FLASK);
		list.add(GLASS_BULB);
		list.add(CRYSTAL_FLASK);
		list.add(EMPTY_LANTERN);
		list.add(CRYSTAL_FLASK_UNFINISHED);
		list.add(BOTTLE_UNFINISHED);
		list.add(OBSIDIAN_GLASS_MIX);
		list.add(EMPTY_LAMP);
		return list;
	}
}
