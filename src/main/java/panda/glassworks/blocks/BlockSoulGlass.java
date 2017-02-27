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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;

public class BlockSoulGlass extends BlockGlass{


	public static final PropertyBool isOn = PropertyBool.create("ison");
	
	public BlockSoulGlass() {
		super(Material.GLASS, false);
		this.setHardness(0.4F);
		this.setSoundType(SoundType.GLASS);
		this.setResistance(3F);
		this.setCreativeTab(GlassWorks.GlassTab);
		this.setLightOpacity(255);
		this.setDefaultState(this.blockState.getBaseState().withProperty(isOn, false));
		setRegistryName("soul_glass");
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		return meta == 0? this.blockState.getBaseState().withProperty(isOn, false):this.blockState.getBaseState().withProperty(isOn, true);
	}
	
	 public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	    {
	        if (!worldIn.isRemote && worldIn.getBlockState(pos).getBlock() == this)
	        {
	        	if(worldIn.getBlockState(pos).getValue(isOn)){
	        		worldIn.setBlockState(pos,this.setLightOpacity(3).getDefaultState().withProperty(isOn, true),2);
	        	}else{
	        		worldIn.setBlockState(pos,this.setLightOpacity(255).getDefaultState().withProperty(isOn, false),2);
	        	}
	        	
	        	

	        }
	    }
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(isOn)? 1:0;
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {isOn});
	}

	
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn)
	{
		AxisAlignedBB blockBox = state.getCollisionBoundingBox(worldIn, pos);
		AxisAlignedBB axisalignedbb = blockBox.offset(pos);

		if (axisalignedbb != null && entityBox.intersectsWith(axisalignedbb) && entityIn != null && !((entityIn instanceof EntityPlayer)||entityIn.getControllingPassenger() instanceof EntityPlayer  || entityIn.hasCustomName()))
		{
			collidingBoxes.add(axisalignedbb);
		}
		
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            if (worldIn.isBlockPowered(pos))
            {
                worldIn.setBlockState(pos, getDefaultState().withProperty(isOn, true), 2);
                this.setLightOpacity(3);
            }
            else 
            {
                worldIn.setBlockState(pos, getDefaultState(), 2);
            }
        }
    }
	
	//entityIn.isOnSameTeam(entityIn)
	//entityIn.isOnScoreboardTeam(teamIn)

	@Override
	public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
		// TODO Auto-generated method stub
		return super.isPassable(worldIn, pos);
	}
	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }


	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,IBlockState state, EntityPlayer playerIn, EnumHand hand,ItemStack heldItem, EnumFacing side, float hitX, float hitY,float hitZ) {
		if(worldIn.isBlockPowered(pos)){
			worldIn.setBlockState(pos, getDefaultState().withProperty(isOn, true), 2);
			notifySameNeighborsOfStateChange(worldIn, pos);
			return true;
		}
		return false;
	}


	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos,
			IBlockState state, Entity entityIn) {
		// TODO Auto-generated method stub
		super.onEntityCollidedWithBlock(worldIn, pos, state, entityIn);
	}
	
	private void notifySameNeighborsOfStateChange(World worldIn, BlockPos pos)
    {
        if (worldIn.getBlockState(pos).getBlock() == this)
        {
            worldIn.notifyNeighborsOfStateChange(pos, this);

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this);
            }
        }
    }

	@Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess,
			BlockPos pos, EnumFacing side) {
		// TODO Auto-generated method stub
		return super.getStrongPower(blockState, blockAccess, pos, side);
	}


	@Override
	public void onNeighborChange(IBlockAccess worldIn, BlockPos pos,BlockPos neighbor) {
		if (worldIn.getBlockState(pos).getBlock() == this)
        {

            for (EnumFacing enumfacing : EnumFacing.values())
            {
                if(worldIn.getBlockState(pos.offset(enumfacing)).getValue(isOn) == true ){
                	((World) worldIn).setBlockState(pos, getDefaultState().withProperty(isOn, true), 2);
                }
            }
        }
		notifySameNeighborsOfStateChange((World)worldIn,pos);
		super.onNeighborChange(worldIn, pos, neighbor);
	}

	@Override
	public boolean getWeakChanges(IBlockAccess world, BlockPos pos) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess worldIn, BlockPos pos, EnumFacing side)
	{
		IBlockState iblockstate = worldIn.getBlockState(pos.offset(side));
		Block block = iblockstate.getBlock();

		if (block == this || iblockstate.getBlock() == this)
		{
			return false;
		}
		
		if (state != iblockstate)
		{
			return true;
		}

		return false;
	}


}