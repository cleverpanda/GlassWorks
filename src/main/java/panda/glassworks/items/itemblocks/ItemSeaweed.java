package panda.glassworks.items.itemblocks;

import java.math.BigInteger;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import panda.glassworks.GlassWorks;

public class ItemSeaweed extends ItemBlock {

	public ItemSeaweed(Block b) {
		super(b);
		this.setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("seaweed");
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		Block block = world.getBlockState(pos).getBlock();
		
			if (player.canPlayerEdit(pos, side, stack) 
					&& (block == this.block || block == Blocks.SAND || block == Blocks.GRAVEL) 
					&& world.getBlockState(pos.up()).getBlock() == Blocks.WATER
					&& world.getBlockState(pos.up(2)).getBlock() == Blocks.WATER
					&& world.setBlockState(pos.up(), this.block.getDefaultState())) {
					--stack.stackSize;
					return EnumActionResult.SUCCESS;
			}
			System.out.println(world.getBlockState(pos).getBlock().toString() + " " + world.getBlockState(pos.up()).getBlock().toString() + " " + world.getBlockState(pos.down()).getBlock().toString());
			
			
			
		return EnumActionResult.PASS;
	}
}
