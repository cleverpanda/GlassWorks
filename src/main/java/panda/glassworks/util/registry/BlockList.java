package panda.glassworks.util.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraftforge.fluids.Fluid;
import panda.glassworks.blocks.BlockFancyStainedGlass;
import panda.glassworks.blocks.BlockLamp;
import panda.glassworks.blocks.BlockLantern;
import panda.glassworks.blocks.BlockSeaweed;
import panda.glassworks.blocks.BlockSoulGlass;
import panda.glassworks.blocks.BlockStainedGlassSlab;
import panda.glassworks.blocks.BlockTarFluid;
import panda.glassworks.blocks.TarFluid;

public final class BlockList{

	/*
	 * Declare blocks here and then subsequently add them to getList(); They will be automatically moved to registration and model loading.
	 */
	
	public static final Block SEAWEED = new BlockSeaweed();
	public static final Fluid TAR_FLUID = new TarFluid();
	public static final Block TAR = new BlockTarFluid(TAR_FLUID);
	public static final Block FANCY_STAINED_GLASS = new BlockFancyStainedGlass();
	public static final Block LANTERN = new BlockLantern(false);
	public static final Block LAMP = new BlockLamp(false);
	/*public static final BlockSlab GLASS_SLAB = new
	public static final BlockSlab DOUBLE_GLASS_SLAB = new
	public static final BlockSlab STAINED_GLASS_SLAB = new
	public static final BlockSlab DOUBLE_STAINED_GLASS_SLAB = new*/
	public static final Block SOUL_GLASS = new BlockSoulGlass();
	public static final Block FANCY_WHITE_SLAB = new BlockStainedGlassSlab("fancy_white_slab");
	
	
	
	
	public static List<Block> getList() {
		List<Block> list = new ArrayList<Block>();
		list.add(SEAWEED);
		list.add(TAR);
		list.add(FANCY_STAINED_GLASS);
		list.add(LANTERN);
		list.add(LAMP);
		list.add(FANCY_WHITE_SLAB);
		return list;
	}

	
	
}
