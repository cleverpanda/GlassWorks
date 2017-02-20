package panda.glassworks.blocks;

public class BlockDoubleStainedSlabGlass extends BlockStainedGlassSlab
{
	public BlockDoubleStainedSlabGlass(String name) {
		super(name);
	}

	@Override
	public boolean isDouble() {
		return true;
	}
}