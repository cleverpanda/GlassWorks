package panda.glassworks.blocks;

public class BlockDoubleStainedSlabGlass extends BlockStainedGlassSlab
{
	public BlockDoubleStainedSlabGlass(String name) {
		super(name);
		setRegistryName("stained_double_slab");
	}

	@Override
	public boolean isDouble() {
		return true;
	}
}