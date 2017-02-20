package panda.glassworks.blocks;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

public class BlockDoubleSlabGlass extends BlockGlassSlab
{
	public BlockDoubleSlabGlass(String name) {
		super(name);
	}

	@Override
	public boolean isDouble() {
		return true;
	}
}