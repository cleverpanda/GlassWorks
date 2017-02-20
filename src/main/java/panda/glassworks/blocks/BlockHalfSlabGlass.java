package panda.glassworks.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockHalfSlabGlass extends BlockGlassSlab
{
	public BlockHalfSlabGlass(String name) {
		super(name);
	}

	@Override
	public boolean isDouble() {
		return false;
	}
}