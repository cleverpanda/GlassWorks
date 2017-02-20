package panda.glassworks.blocks;

public class BlockStainedHalfSlabGlass extends BlockStainedGlassSlab
{
	public BlockStainedHalfSlabGlass(String name) {
		super(name);
	}

	@Override
	public boolean isDouble() {
		return false;
	}
}