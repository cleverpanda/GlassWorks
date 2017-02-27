package panda.glassworks.blocks;

import java.util.Random;

import panda.glassworks.GlassWorks;
import panda.glassworks.init.GlassBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockTarFluid extends BlockFluidClassic
{
    public BlockTarFluid(Fluid fluid)
    {
        super(fluid, Material.WATER);
        this.quantaPerBlock = 3;
        this.renderLayer = BlockRenderLayer.SOLID;
        this.blockParticleGravity =0.5F;
        this.temperature = 500;
        this.setSoundType(SoundType.SLIME);
        setRegistryName("tar");
    }
    
	@Override
	public Boolean isEntityInsideMaterial(IBlockAccess world,
			BlockPos blockpos, IBlockState iblockstate, Entity entity,
			double yToTest, Material materialIn, boolean testingHead) {
		// TODO Auto-generated method stub
		return super.isEntityInsideMaterial(world, blockpos, iblockstate, entity,
				yToTest, materialIn, testingHead);
	}
    
    @Override
    public boolean isVisuallyOpaque(){
		return true;
    	
    }

	@Override
	public boolean shouldSideBeRendered(IBlockState state, IBlockAccess world,
			BlockPos pos, EnumFacing side) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return FULL_BLOCK_AABB;
    }
    
	@Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
    {
		//entity.motionX *= 0.1D;
		//entity.motionY *= 0.0000001D;
		//entity.motionZ *= 0.1D;
		entity.setInWeb();
    }
    
    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
    	super.onBlockAdded(worldIn, pos, state);
        this.checkForMixing(worldIn, pos, state);
    }


	@Override
	public boolean canReplace(World worldIn, BlockPos pos, EnumFacing side,
			ItemStack stack) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
    	super.neighborChanged(state, worldIn, pos, blockIn);
        this.checkForMixing(worldIn, pos, state);
    }
    
    public boolean checkForMixing(World worldIn, BlockPos pos, IBlockState state)
    {
    	 boolean flag = false;
    	 
         for (EnumFacing enumfacing : EnumFacing.values())
         {
             if (enumfacing != EnumFacing.DOWN && (worldIn.getBlockState(pos.offset(enumfacing)).getMaterial().isLiquid() == true))
             {
                 if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() != this.getBlockState().getBlock())
                 {
                     flag = true;
                     break;
                 }
             }
         }
  
         if (flag)
         {

            return true;

         }
  
         return false;
     }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        double d0 = (double)pos.getX();
        double d1 = (double)pos.getY();
        double d2 = (double)pos.getZ();

        if (worldIn.getBlockState(pos.up()).getMaterial() == Material.AIR && !worldIn.getBlockState(pos.up()).isOpaqueCube())
        {
            if (rand.nextInt(200) == 0)
            {
                double d8 = d0 + (double)rand.nextFloat();
                double d4 = d1 + stateIn.getBoundingBox(worldIn, pos).maxY;
                double d6 = d2 + (double)rand.nextFloat();
                worldIn.spawnParticle(EnumParticleTypes.WATER_BUBBLE, d8, d4, d6, 0.0D, 0.0D, 0.0D, new int[0]);
                worldIn.playSound(d8, d4, d6, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.1F + rand.nextFloat() * 0.2F, 0.4F + rand.nextFloat() * 0.15F, false);
            }
        }
    }
}