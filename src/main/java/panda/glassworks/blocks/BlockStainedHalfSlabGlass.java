package panda.glassworks.blocks;

public class BlockStainedHalfSlabGlass extends BlockStainedGlassSlab
{
	public BlockStainedHalfSlabGlass(String name) {
		super(name);
		setRegistryName("stained_half_slab");
	}

	@Override
	public boolean isDouble() {
		return false;
	}
}