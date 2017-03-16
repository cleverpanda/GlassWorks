package panda.glassworks.util.registry;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;
import panda.glassworks.blocks.BlockBlastGlass;
import panda.glassworks.blocks.BlockFancyStainedGlass;
import panda.glassworks.blocks.BlockFancyStainedGlassPane;
import panda.glassworks.blocks.BlockGlassSlabBase;
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
	public static final Block FANCY_STAINED_GLASS_PANE = new BlockFancyStainedGlassPane();
	public static final Block LANTERN = new BlockLantern(false);
	public static final Block LAMP = new BlockLamp(false);
	public static final Block BLAST_GLASS = new BlockBlastGlass();
	public static final Block BLAST_GLASS_SLAB = new BlockGlassSlabBase("blast_glass_slab",BLAST_GLASS).setLightOpacity(40);
	public static final Block GLASS_SLAB = new BlockGlassSlabBase("glass_slab",Blocks.GLASS);
	public static final Block SOUL_GLASS = new BlockSoulGlass();
	public static final Block FANCY_WHITE_SLAB = new BlockStainedGlassSlab("fancy_white_slab");
	//public static final Block BLAST_GLASS_PANE = new BlockPane(Material.GLASS, false);
	
	
	
	
	public static List<Block> getList() {
		List<Block> list = new ArrayList<Block>();
		list.add(SEAWEED);
		list.add(TAR);
		list.add(FANCY_STAINED_GLASS);
		list.add(FANCY_STAINED_GLASS_PANE);
		list.add(LANTERN);
		list.add(LAMP);
		list.add(FANCY_WHITE_SLAB);
		list.add(GLASS_SLAB);
		list.add(BLAST_GLASS);
		list.add(BLAST_GLASS_SLAB);
		list.add(SOUL_GLASS);
		
		return list;
	}

	
	
}
