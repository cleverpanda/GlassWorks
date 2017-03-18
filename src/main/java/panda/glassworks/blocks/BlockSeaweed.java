package panda.glassworks.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import panda.glassworks.GlassWorks;

public class BlockSeaweed extends Block {
	
	public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 3);
	public static final PropertyInteger LEVEL = PropertyInteger.create("level", 0, 15);
	
	public BlockSeaweed() {
		super(Material.WATER);
		this.setHardness(0.05F);
		this.setSoundType(SoundType.PLANT);
		this.setDefaultState(blockState.getBaseState().withProperty(STAGE, 0).withProperty(LEVEL, 15));
		this.setLightLevel(3);
		setTickRandomly(true);
		setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("seaweed");
		GameRegistry.register(new ItemBlock(this){
			@Override
		    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
				if(world.getBlockState(pos).getBlock() == this.block) return super.onItemUse(stack, player, world, pos.up(), hand, facing, hitX, hitY, hitZ);
				return super.onItemUse(stack, player, world, pos, hand, facing, hitX, hitY, hitZ);
			}
		}, getRegistryName());
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		return isSurroundedByWater(world, pos) && hasOkAbove(world, pos) && hasOkBelow(world, pos);
	}

	boolean isSurroundedByWater(World world, BlockPos pos) {
		Iterable<MutableBlockPos> list = BlockPos.getAllInBoxMutable(pos.add(-1, 0, -1), pos.add(1, 0, 1));

		for (MutableBlockPos blockpos : list) {
			if (world.getBlockState(blockpos).getMaterial() != Material.WATER) return false;
		}
		return true;
	}

	boolean hasOkAbove(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.up());
		return state.getMaterial().isLiquid() || state.getBlock() == this;
	}
	
	boolean hasOkAboveSpecial(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.up());
		return state.getMaterial().isLiquid() || state.getBlock() == this || state.getBlock() == Blocks.AIR;
	}

	boolean hasOkBelow(World world, BlockPos pos) {
		IBlockState state = world.getBlockState(pos.down());
		return state.getMaterial().isSolid() || state.getBlock() == this;
	}
	
	@Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block) {
		this.checkAndDropBlock(world, pos, state); 
	}
	
	@Override
    public void breakBlock(World world, BlockPos pos, IBlockState state){
		super.breakBlock(world, pos, state);
		if(world.getBlockState(pos.down()).getBlock() == this) world.setBlockState(pos.down(), this.getStateForPlacement(world, pos.down(), null, 0, 0, 0, 0, null, null), 3);
	}
	
	@Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack){
		if(world.getBlockState(pos.down()).getBlock() == this && world.getBlockState(pos.down(2)).getBlock() == this) world.setBlockState(pos.down(), this.getDefaultState().withProperty(STAGE, 3));
		else if(world.getBlockState(pos.down()).getBlock() == this) world.setBlockState(pos.down(), this.getDefaultState().withProperty(STAGE, 1));
    }

	protected final void checkAndDropBlock(World world, BlockPos pos, IBlockState state) {
		boolean flag = true;
		if (this.hasOkAboveSpecial(world, pos) && this.hasOkBelow(world, pos)) flag = false;
		else if(flag && !world.isRemote) world.destroyBlock(pos, true);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
		return NULL_AABB;
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
		int k = state.getValue(STAGE);
		if(k == 0 || k == 2){
		if(rand.nextInt(6) == 0 && world.getBlockState(pos.up(2)).getBlock() == Blocks.WATER && world.getBlockState(pos.down(3)).getBlock() != this){
			world.setBlockState(pos, this.getDefaultState().withProperty(STAGE, k == 0 ? 1 : 3));
			world.setBlockState(pos.up(), this.getDefaultState().withProperty(STAGE, 2));
		}
		}
		
	}

	@Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, ItemStack stack){
		IBlockState up = world.getBlockState(pos.up());
		IBlockState down = world.getBlockState(pos.down());
		if(up.getBlock() != this){
			if(down.getBlock() == this)return getDefaultState().withProperty(STAGE, 2);
			else return getDefaultState();
		}
		else{
			if(down.getBlock() == this) return getDefaultState().withProperty(STAGE, 3);
			else return getDefaultState().withProperty(STAGE, 1);
		}
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(STAGE, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(STAGE);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {STAGE, LEVEL});
	}
}
