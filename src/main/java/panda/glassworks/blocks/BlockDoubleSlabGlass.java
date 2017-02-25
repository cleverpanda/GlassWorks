package panda.glassworks.blocks;

public class BlockDoubleSlabGlass extends BlockGlassSlab
{
	public BlockDoubleSlabGlass(String name) {
		super(name);
		setRegistryName("glass_double_slab");
	}

	@Override
	public boolean isDouble() {
		return true;
	}
}
//TODO 
//Rewrite this