package panda.glassworks.blocks;

public class BlockHalfSlabGlass extends BlockGlassSlab
{
	public BlockHalfSlabGlass(String name) {
		super(name);
		setRegistryName("glass_half_slab");
	}

	@Override
	public boolean isDouble() {
		return false;
	}
}