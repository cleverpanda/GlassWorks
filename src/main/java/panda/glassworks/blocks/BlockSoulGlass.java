package panda.glassworks.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;
import panda.glassworks.items.itemblocks.ItemBlockSoulGlass;

public class BlockSoulGlass extends BlockGlass {

	public static final PropertyBool isOn = PropertyBool.create("ison");

	public BlockSoulGlass() {
		super(Material.GLASS, false);
		this.setHardness(0.4F);
		this.setSoundType(SoundType.GLASS);
		this.setResistance(3F);
		this.setCreativeTab(GlassWorks.GlassTab);
		this.setDefaultState(this.blockState.getBaseState().withProperty(isOn, false));
		setRegistryName("soul_glass");
		GameRegistry.register(new ItemBlockSoulGlass(this));
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return meta == 0 ? this.blockState.getBaseState().withProperty(isOn, false)
				: this.blockState.getBaseState().withProperty(isOn, true);
	}

	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		if (!worldIn.isRemote && worldIn.getBlockState(pos).getBlock() == this) {
			if (worldIn.getBlockState(pos).getValue(isOn)) {
				worldIn.setBlockState(pos, this.setLightOpacity(3).getDefaultState().withProperty(isOn, true), 2);
			} else {
				worldIn.setBlockState(pos, this.setLightOpacity(255).getDefaultState().withProperty(isOn, false), 2);
			}

		}
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(isOn) ? 1 : 0;
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { isOn });
	}

	@Override // int value is light level blocked out of 15
	public int getLightOpacity(IBlockState state, IBlockAccess world, BlockPos pos) {
		return state == this.getStateFromMeta(0) ? 13 : 2;
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(itemIn, 1, 0));
		list.add(new ItemStack(itemIn, 1, 1));
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		// System.out.println(neighborsAreLit(worldIn, pos));
		if (!worldIn.isRemote) {
			if (worldIn.getBlockState(pos) == this.getStateFromMeta(1) && !worldIn.isBlockPowered(pos)
					&& !neighborsAreLit(worldIn, pos)) {
				// System.out.println("yep");
				worldIn.setBlockState(pos, this.getStateFromMeta(0), 2);
			} else if (worldIn.getBlockState(pos) == this.getStateFromMeta(0) && worldIn.isBlockPowered(pos)) {
				// System.out.println("nop");
				worldIn.setBlockState(pos, this.getStateFromMeta(1), 2);
			}
		}
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public int damageDropped(IBlockState state) {
		return this.getMetaFromState(state);
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		IBlockState iblockstate = worldIn.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		if (block == this || iblockstate.getBlock() == this) {
			return false;
		}

		if (state != iblockstate) {
			return true;
		}

		return false;
	}

	/**
	 * Called when a neighboring block was changed and marks that this state
	 * should perform any checks during a neighbor change. Cases may include
	 * when redstone power is updated, cactus blocks popping off due to a
	 * neighboring solid block, etc.
	 */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn) {
		if (!worldIn.isRemote) {
			if (neighborsAreLit(worldIn, pos) || worldIn.isBlockPowered(pos)) {
				System.out.println(neighborsAreLit(worldIn, pos) + "Yep");
				worldIn.setBlockState(pos, this.getStateFromMeta(1), 3);
			} else {
				// System.out.println(neighborsAreLit(worldIn, pos)+"Nop");
				worldIn.setBlockState(pos, this.getStateFromMeta(0), 3);
			}

			/*
			 * if (worldIn.getBlockState(pos) == this.getStateFromMeta(1) ) //&&
			 * !worldIn.isBlockPowered(pos) { worldIn.scheduleUpdate(pos, this,
			 * 4); } else if (worldIn.getBlockState(pos) ==
			 * this.getStateFromMeta(0) ) //&& worldIn.isBlockPowered(pos) {
			 * worldIn.setBlockState(pos, this.getStateFromMeta(1), 2); }
			 */
		}
	}

	public boolean neighborsAreLit(World worldIn, BlockPos pos) {
		for (EnumFacing enumfacing : EnumFacing.VALUES) {

			if (worldIn.getBlockState(pos.offset(enumfacing)) == this.getStateFromMeta(1)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn) {
		AxisAlignedBB blockBox = state.getCollisionBoundingBox(worldIn, pos);
		AxisAlignedBB axisalignedbb = blockBox.offset(pos);

		if (axisalignedbb != null && entityBox.intersectsWith(axisalignedbb) && entityIn != null
				&& !((entityIn instanceof EntityPlayer) || entityIn.getControllingPassenger() instanceof EntityPlayer
						|| entityIn.hasCustomName())) {
			collidingBoxes.add(axisalignedbb);
		}

	}

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		// TODO Auto-generated method stub
		return super.isPassable(worldIn, pos);
	}

	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		// TODO Auto-generated method stub
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);

	}

	private void notifySameNeighborsOfStateChange(World worldIn, BlockPos pos) {
		if (worldIn.getBlockState(pos).getBlock() == this) {
			worldIn.notifyNeighborsOfStateChange(pos, this);

			for (EnumFacing enumfacing : EnumFacing.values()) {
				worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this);
			}
		}
	}

	@Override
	public void onNeighborChange(IBlockAccess worldIn, BlockPos pos, BlockPos neighbor) {
		if (worldIn.getBlockState(pos).getBlock() == this) {

			for (EnumFacing enumfacing : EnumFacing.values()) {
				if (worldIn.getBlockState(pos.offset(enumfacing)).getValue(isOn) == true) {
					((World) worldIn).setBlockState(pos, getDefaultState().withProperty(isOn, true), 2);
				}
			}
		}
		notifySameNeighborsOfStateChange((World) worldIn, pos);
		super.onNeighborChange(worldIn, pos, neighbor);
	}

	@Override
	public boolean getWeakChanges(IBlockAccess world, BlockPos pos) {
		// TODO Auto-generated method stub

		return true;
	}

	@Override
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
		// TODO Auto-generated method stub
		super.randomDisplayTick(stateIn, worldIn, pos, rand);
	}

	public Vec3d modifyAcceleration(World worldIn, BlockPos pos, Entity entityIn, Vec3d motion) {

		/// this method used only for water, need to do something else
		Double vx = motion.xCoord;
		Double vy = motion.yCoord;
		Double vz = motion.zCoord;
		Double c = 1D;
		return motion.subtract(c * vx * vx * Math.signum(vx), c * vy * vy * Math.signum(vy),
				c * vz * vz * Math.signum(vz));
	}

}