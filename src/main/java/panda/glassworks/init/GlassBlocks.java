package panda.glassworks.init;

@Deprecated
public class GlassBlocks {
/*
	private static boolean initDone = false;

	private static Map<Block, String> allBlocks = new HashMap<Block, String>();
	private static final Map<String, Block> blockRegistry = new HashMap<String, Block>();

	public static Block SEAWEED;
	public static Block TAR;
	public static Block FANCY_STAINED_GLASS;
	public static Block LANTERN;
	public static Block LAMP;
	public static BlockSlab GLASS_SLAB;
	public static BlockSlab DOUBLE_GLASS_SLAB;
	public static BlockSlab STAINED_GLASS_SLAB;
	public static BlockSlab DOUBLE_STAINED_GLASS_SLAB;
	public static Block SOUL_GLASS;
	
	
	
	public static Block BLAST_GLASS;



	/**
	 * Gets an block by its name. The name is the name as it is registered in
	 * the GameRegistry, not its unlocalized name (the unlocalized name is the
	 * registered name plus the prefix "varietytrees.")
	 * 
	 * @param name The name of the block in question
	 * @return The block matching that name, or null if there isn't one
	 /
	public static Block getBlockByName(String name) {
		return blockRegistry.get(name);
	}

	/**
	 * This is the reverse of the getBlockByName(...) method, returning the
	 * registered name of an block instance (Variety Trees blocks only).
	 * 
	 * @param b The item in question
	 * @return The name of the item, or null if the item is not a Variety Trees
	 * block.
	 /
	public static String getNameOfItem(Block b) {
		return allBlocks.get(b);
	}

	/**
	 *
	 /
	public static void init() {
		if (initDone)
			return;
		
		SEAWEED = new BlockSeaweed();
		addBlock(SEAWEED, "seaweed");
		
		FANCY_STAINED_GLASS = new BlockFancyStainedGlass();
		addBlock(FANCY_STAINED_GLASS, "fancy_stained_glass");
		
		LANTERN = new BlockLantern(false);
		addBlock(LANTERN, "oil_lantern");
		
		BLAST_GLASS = new BlockBlastGlass();
		addBlock(BLAST_GLASS, "blast_glass");
		
		LAMP = new BlockLamp(false);
		addBlock(LAMP, "oil_lamp");
		

		GLASS_SLAB = createSlab("glass");
		DOUBLE_GLASS_SLAB = createDoubleSlab("glass");
		
		//STAINED_GLASS_SLAB = createColoredSlab("stained_glass");
		//DOUBLE_STAINED_GLASS_SLAB = createColoredDoubleSlab("stained_glass");
		
		SOUL_GLASS = new BlockSoulGlass();
		addBlock(SOUL_GLASS, "soul_glass");
		
		TarFluid tar_fluid = TarFluid.instance;
        tar_fluid.setViscosity(6000).setDensity(50000);
        FluidRegistry.addBucketForFluid(tar_fluid);
        TAR = registerFluidBlock(tar_fluid, new BlockTarFluid(tar_fluid), "tar");

        
        
		initDone = true;
	}
	
	private static BlockSlab createSlab(String name) {
		return (BlockSlab) addBlock(new BlockHalfSlabGlass(name), name + "_slab");
	}
	

	private static BlockSlab createDoubleSlab(String name) {
		return (BlockSlab) addBlock(new BlockDoubleSlabGlass(name), "double_"+name + "_slab");
	}
	
	private static BlockSlab createColoredSlab(String name) {
		return (BlockSlab) addBlock(new BlockStainedHalfSlabGlass(name), name + "_slab");
	}
	

	private static BlockSlab createColoredDoubleSlab(String name) {
		return (BlockSlab) addBlock(new BlockDoubleStainedSlabGlass(name), "double_"+name + "_slab");
	}

	private static Block addBlock(Block block, String name) {
		String fullName = name;

		block.setRegistryName(fullName);
		block.setUnlocalizedName(block.getRegistryName().getResourceDomain() + "." + fullName);
		GameRegistry.register(block);
		blockRegistry.put(fullName, block);
		allBlocks.put(block, fullName);	

		return block;
	}
	
	public static Block registerFluidBlock(Fluid fluid, Block fluidBlock, String name)
    {
        Block block = GameRegistry.register(fluidBlock, new ResourceLocation(GlassWorks.MODID, name));
        GlassWorks.proxy.registerFluidBlockRendering(block, name);
        fluid.setBlock(fluidBlock);
        return block;
    }

	/
	private static Block createStairs(WoodMaterial wood) {
		return addBlock(new BlockVarietyStairs(wood, Blocks.getBlockByName(wood.getName() + "_planks")), "stairs", wood);
	}


	private static BlockDoor createDoor(WoodMaterial wood) {
		return (BlockDoor) addBlock(new BlockVarietyDoor(wood), "door", wood);
	}

	public static Map<String, Block> getBlockRegistry() {
		return blockRegistry;
	}
	*/
}