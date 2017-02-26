package panda.glassworks.items.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import panda.glassworks.blocks.BlockGlassSlabBase;
import panda.glassworks.blocks.BlockGlassSlabBase.SlabVariant;

public class ItemGlassSlabBlock extends ItemBlock{

	public ItemGlassSlabBlock(Block block) {
		super(block);
		this.setMaxDamage(0);
		setRegistryName(block.getRegistryName());
	}
	
	@Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        IBlockState state = worldIn.getBlockState(pos);
        Block block = state.getBlock();

        if (!block.isReplaceable(worldIn, pos) && !(block == this.block))
        {
            pos = pos.offset(facing);
        }
        
        block = worldIn.getBlockState(pos).getBlock();
        int i = stack.getMetadata();
        if(stack.stackSize != 0 && playerIn.canPlayerEdit(pos, facing, stack) && block == this.block && isStateValidForDoubling(state, facing)){
            IBlockState state2 = this.block.getDefaultState().withProperty(BlockGlassSlabBase.VARIANT, BlockGlassSlabBase.SlabVariant.DOUBLE);
        	
            if (worldIn.setBlockState(pos, state2))
            {
                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
                worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                --stack.stackSize;
            }

            return EnumActionResult.SUCCESS;
        }
        
        if (!block.isReplaceable(worldIn, pos))
        {
            pos = pos.offset(facing);
        }
        
        if (stack.stackSize != 0 && playerIn.canPlayerEdit(pos, facing, stack) && worldIn.canBlockBePlaced(this.block, pos, false, facing, (Entity)null, stack))
        {
            IBlockState state2 = this.block.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, i, playerIn, stack);

            if (placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, state2))
            {
                SoundType soundtype = worldIn.getBlockState(pos).getBlock().getSoundType(worldIn.getBlockState(pos), worldIn, pos, playerIn);
                worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
                --stack.stackSize;
            }

            return EnumActionResult.SUCCESS;
        }
        else
        {
            return EnumActionResult.FAIL;
        }
    }
	
	private boolean isStateValidForDoubling(IBlockState state, EnumFacing facing){
		SlabVariant value = state.getValue(BlockGlassSlabBase.VARIANT);
		return((value == SlabVariant.LOWER && facing == EnumFacing.UP) || (value == SlabVariant.UPPER && facing == EnumFacing.DOWN));
	}
	
	

}
