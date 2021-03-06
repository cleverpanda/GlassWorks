package panda.glassworks.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;
import panda.glassworks.items.itemblocks.ItemBlockFancyGlass;

public class BlockFancyStainedGlass extends BlockGlass {
	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color",
			EnumDyeColor.class);

	public BlockFancyStainedGlass() {
		super(Material.GLASS, false);
		this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
		this.setCreativeTab(GlassWorks.GlassTab);
		this.setHardness(0.3F);
		this.setSoundType(SoundType.GLASS);
		setRegistryName("fancy_stained_glass");
		GameRegistry.register(new ItemBlockFancyGlass(this));
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public float[] getBeaconColorMultiplier(IBlockState state, World world, BlockPos pos, BlockPos beaconPos) {

		return EntitySheep.getDyeRgb(((EnumDyeColor) state.getValue(COLOR)));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return !(world.getBlockState(pos.offset(side)).getBlock() == this);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		for (EnumDyeColor enumdyecolor : EnumDyeColor.values()) {
			list.add(new ItemStack(itemIn, 1, enumdyecolor.getMetadata()));
		}

	}

	@Override
	public MapColor getMapColor(IBlockState state) {
		return ((EnumDyeColor) state.getValue(COLOR)).getMapColor();
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public int quantityDropped(Random random) {
		return 0;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(meta));
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			BlockBeacon.updateColorAsync(worldIn, pos);
		}
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		if (!worldIn.isRemote) {
			BlockBeacon.updateColorAsync(worldIn, pos);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, COLOR);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(COLOR).getMetadata();
	}

}
