package panda.glassworks.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;
import panda.glassworks.items.itemblocks.ItemSeaweed;

public class BlockSeaweed extends Block {

	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);// So
																						// the
																						// game
																						// doesnt
																						// crash

	/*
	 * META 0 > Stalk META 1 > connected stalk META 2 > midsection Meta 3 > Top
	 * Meta 4 > Terminating cap, does not generate higher
	 */

	public BlockSeaweed() {
		super(Material.WATER);
		this.setHardness(0.05F);
		this.setSoundType(SoundType.PLANT);
		this.setDefaultState(this.blockState.getBaseState().withProperty(LEVEL, Integer.valueOf(2)));
		// float f = 0.375f;
		// this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F +
		// f);
		this.setLightLevel(3);
		setTickRandomly(true);
		setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("seaweed");
		GameRegistry.register(new ItemSeaweed(this));
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos) {
		return false;
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player,
			boolean willHarvest) {
		this.onBlockHarvested(world, pos, state, player);
		return world.setBlockState(pos, Blocks.WATER.getDefaultState(), world.isRemote ? 11 : 3);
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos.down());
		Block block = state.getBlock();
		if (isSurroundedByWaterOrSeaweed(worldIn, pos)) {
			// System.out.println("isSurrounded by water");
			// System.out.println(worldIn.getBlockState(pos.up()).getBlock());
			if (hasOkAbove(worldIn, pos)) {
				// System.out.println("isOkAbove");
				// System.out.println(worldIn.getBlockState(pos.down()).getBlock());
				if (hasOkBelow(worldIn, pos)) {
					// System.out.println("isOkBelow");
					return true;
				}
			}
		}

		return false;
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

		if (block == Blocks.WATER || block == Blocks.FLOWING_WATER || block == this || block == Blocks.PRISMARINE
				|| block == Blocks.SEA_LANTERN) {
			return true;
		}
		return false;
	}

	boolean hasOkBelow(World world, BlockPos pos) {
		Block block = world.getBlockState(pos.down()).getBlock();

		if (block == Blocks.SAND || block == Blocks.GRAVEL || block == this) {
			return true;
		}
		return false;
	}

	/*
	 * return (world.getBlockState(pos.north()).getBlock() == Blocks.WATER ||
	 * world.getBlockState(pos.north()).getBlock() == this) &&
	 * (world.getBlockState(pos.west()).getBlock() == Blocks.WATER ||
	 * world.getBlockState(pos.west()).getBlock() == this) &&
	 * (world.getBlockState(pos.south()).getBlock() == Blocks.WATER ||
	 * world.getBlockState(pos.south()).getBlock() == this) &&
	 * (world.getBlockState(pos.east()).getBlock() == Blocks.WATER ||
	 * world.getBlockState(pos.east()).getBlock() == this) &&
	 * (world.getBlockState(pos.down()).getBlock() == Blocks.SAND ||
	 * world.getBlockState(pos.down()).getBlock() == this) &&
	 * (world.getBlockState(pos.up()).getBlock() == Blocks.WATER ||
	 * world.getBlockState(pos.up()).getBlock() == this);
	 */

	public boolean canBlockStay(World worldIn, BlockPos pos) {
		return this.canPlaceBlockAt(worldIn, pos);
	}

	/*
	 * @Override public void randomTick(World worldIn, BlockPos pos, IBlockState
	 * state,Random random) { super.randomTick(worldIn, pos, state, random);
	 * //if(random.nextInt(2)==0){ if(isTopBlock(worldIn,pos) &&
	 * canGrowHigher(worldIn,pos) &&
	 * worldIn.getBlockState(pos.up(2))==Blocks.WATER){
	 * worldIn.setBlockState(pos.up(), state); } //} }
	 */

	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn) {
		// world.scheduleUpdate(pos, blockIn, 1);
		this.checkForDrop(world, pos, state); // +
		super.neighborChanged(state, world, pos, blockIn);
	}

	protected final boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
		if (this.canBlockStay(worldIn, pos)) {
			return true;
		} else {
			this.dropBlockAsItem(worldIn, pos, state, 0);
			worldIn.setBlockState(pos, Blocks.WATER.getDefaultState());
			return false;
		}
	}

	@Override
	public boolean canCreatureSpawn(IBlockState state, IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
		return true;
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(this);
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

	@SideOnly(Side.CLIENT)
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (worldIn.getBlockState(pos.down()).getBlock() == this || this.checkForDrop(worldIn, pos, state)
				&& worldIn.getBlockState(pos.down()) != this.getStateFromMeta(4)) {
			if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.WATER) {
				int i;

				for (i = 1; worldIn.getBlockState(pos.down(i)).getBlock() == this; ++i) {
					;
				}

				if (i < 5 && worldIn.getBlockState(pos.up(2)).getBlock() == Blocks.WATER) {
					worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(LEVEL, Integer.valueOf(2)));
				}
			}
			worldIn.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, Integer.valueOf(1)));
		}
	}

	/**
	 * Called by ItemBlocks after a block is set in the world, to allow
	 * post-place logic
	 */
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (worldIn.getBlockState(pos.down()) != this && worldIn.getBlockState(pos) == this) {
			if (worldIn.getBlockState(pos.up()) == this) {
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, 1));
			} else {
				worldIn.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, 0));
			}

		}
		if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.WATER) {
			worldIn.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, 2));
		}

		if (worldIn.getBlockState(pos.up()).getBlock() == this
				&& worldIn.getBlockState(pos.down()).getBlock() == this) {
			worldIn.setBlockState(pos, this.getDefaultState().withProperty(LEVEL, 3));
		}

	}

	/*
	 * @Override public void updateTick(World world, BlockPos pos, IBlockState
	 * state, Random rand){ if(this.canBlockStay(world, pos) &&
	 * this.canBlockStay(world, pos.up()) && world.getBlockState(pos.down(10))
	 * != this) world.setBlockState(pos.up(), this.getDefaultState());
	 * if(!this.canBlockStay(world, pos)){ world.setBlockState(pos,
	 * Blocks.WATER.getDefaultState()); this.dropBlockAsItem(world, pos,
	 * this.getDefaultState(), 0); } }
	 */

	/*
	 * @Override public void breakBlock(World world, BlockPos pos, IBlockState
	 * state) { world.scheduleUpdate(pos.up(), this, 1);
	 * world.scheduleUpdate(pos.down(), this, 1); }
	 */

	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int par3) {
		return Item.getItemFromBlock(this);
	}

	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(LEVEL, Integer.valueOf(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((Integer) state.getValue(LEVEL)).intValue();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { LEVEL });
	}
}
