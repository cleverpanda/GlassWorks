package panda.glassworks.blocks;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;
import panda.glassworks.items.itemblocks.ItemGlassSlabBlock;
import panda.glassworks.util.registry.ItemList;

public class BlockGlassSlabBase extends Block{

	public static final PropertyEnum<SlabVariant> VARIANT = PropertyEnum.<SlabVariant>create("slab_variant", SlabVariant.class);

	
    /**
     * @param name - The registry name.
     * @param block - Has no use, but don't be making slabs for nonexistent blocks.
     */
	public BlockGlassSlabBase(String name, Block block) {
		super(Material.GLASS);
		this.setSoundType(SoundType.GLASS);
		this.setHardness(0.3F);
		this.setLightOpacity(0);
		this.setCreativeTab(GlassWorks.GlassTab);
		this.useNeighborBrightness = true;
		setRegistryName(name);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, SlabVariant.LOWER));
		GameRegistry.register(new ItemGlassSlabBlock(this));
	}
	
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return (state.getValue(VARIANT) == SlabVariant.DOUBLE);
    }
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        return 1;
    }

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
		return state.getValue(VARIANT).getAABB();
	}
	
	@Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos){
		return state.getBoundingBox(world, pos);
	}
	
	@Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        List<ItemStack> ret = new java.util.ArrayList<ItemStack>();

        ret.add(new ItemStack(ItemList.BROKEN_GLASS));
        if(state.getValue(VARIANT) == SlabVariant.DOUBLE) ret.add(new ItemStack(ItemList.BROKEN_GLASS));
        return ret;
    }
	
	
	@Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing facing)
    {	IBlockState state2 = blockAccess.getBlockState(pos.offset(facing));
    	if(!facing.getAxis().isVertical()) return !(state2.getBlock() == this && (state2 == state || getDouble() == state2));
    	else if (facing == EnumFacing.DOWN && state2 == getDouble()) return !(state == getLower() || state == state2);
    	else if (facing == EnumFacing.UP && state2 == getDouble()) return !(state.getBlock() == this);
    	else if (facing == EnumFacing.UP && state == getDouble()) return !(state2 == getLower());
    	return !(state2.getBlock() == this && state2 == getOpposite(state));
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public IBlockState getStateFromMeta (int meta){
		SlabVariant variant = SlabVariant.LOWER;
		if(meta == 1) variant = SlabVariant.UPPER;
		else if(meta == 2) variant = SlabVariant.DOUBLE;
		return this.getDefaultState().withProperty(VARIANT, variant);
	}
	
	@Override
	public int getMetaFromState(IBlockState state){
		return state.getValue(VARIANT).getMeta();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}
	
	@Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return false;
    }
	
	public IBlockState getUpper(){
		return this.getDefaultState().withProperty(VARIANT, SlabVariant.UPPER);
	}
	
	public IBlockState getLower(){
		return this.getDefaultState();
	}
	
	public IBlockState getDouble(){
		return this.getDefaultState().withProperty(VARIANT, SlabVariant.DOUBLE);
	}
	
	public IBlockState getOpposite(IBlockState state){
		if(state == getUpper()) return getLower();
		return getUpper();
	}
	
	public static enum SlabVariant implements IStringSerializable{
		LOWER("lower", new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.5D, 1.0D), 0),
		UPPER("upper", new AxisAlignedBB(0.0D, 0.5D, 0.0D, 1.0D, 1.0D, 1.0D), 1),
		DOUBLE("double", FULL_BLOCK_AABB, 2);

		private String name;
		private AxisAlignedBB aabb;
		private int meta;
		
		private SlabVariant(String name_, AxisAlignedBB AABB_, int meta_){
			name = name_;
			aabb = AABB_;
			meta = meta_;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		public AxisAlignedBB getAABB() {
			return aabb;
		}
		
		public int getMeta(){
			return meta;
		}
	}

}
