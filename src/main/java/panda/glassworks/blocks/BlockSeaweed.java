package panda.glassworks.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import panda.glassworks.GlassWorks;
import panda.glassworks.items.itemblocks.ItemSeaweed;

public class BlockSeaweed extends Block {

	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 3);

	public BlockSeaweed() {
		super(Material.WATER);
		this.setHardness(0.05F);
		this.setSoundType(SoundType.PLANT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, 15));
		setTickRandomly(true);
		setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("seaweed");
		GameRegistry.register(new ItemSeaweed(this));
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return isSurroundedByWaterOrSeaweed(world, pos) && hasOkAbove(world, pos) && hasOkBelow(world, pos);
	}

	boolean isSurroundedByWaterOrSeaweed(World world, BlockPos pos) {
		Iterable<MutableBlockPos> list = BlockPos.getAllInBoxMutable(pos.add(-1, 0, -1), pos.add(1, 0, 1));
		for (MutableBlockPos blockpos : list) {
			Block block = world.getBlockState(blockpos).getBlock();
			if (world.getBlockState(blockpos).getMaterial() != Material.WATER && block != Blocks.PRISMARINE
					&& block != Blocks.SEA_LANTERN) {
				return false;
			}
		}
		return true;
	}

	boolean hasOkAbove(World world, BlockPos pos) {
		Block block = world.getBlockState(pos.up()).getBlock();
		return block == Blocks.WATER || block == Blocks.FLOWING_WATER || block == this || block == Blocks.PRISMARINE
				|| block == Blocks.SEA_LANTERN;
	}

	boolean hasOkBelow(World world, BlockPos pos) {
		Block block = world.getBlockState(pos.down()).getBlock();
		return block == Blocks.SAND || block == Blocks.GRAVEL || block == this;
	}

	public boolean canBlockStay(World worldIn, BlockPos pos) {
		return this.canPlaceBlockAt(worldIn, pos);
	}

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn) {
		checkAndDropBlock(world, pos, state);
	}

	protected final boolean checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
		if (this.canBlockStay(world, pos)) {
			return true;
		} else {
			world.destroyBlock(pos, true);
			return false;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return null;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {

	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(LEVEL, 15).withProperty(STAGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(STAGE);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LEVEL, STAGE });
	}
}
