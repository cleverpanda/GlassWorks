package panda.glassworks.items;

import panda.glassworks.GlassWorks;
import panda.glassworks.util.registry.BlockList;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemSeaweed extends ItemBlock{
	private Block theblock;

    public ItemSeaweed(Block b)
    {
    	super(b);
        this.theblock = b;
        this.setCreativeTab(GlassWorks.GlassTab);
        setRegistryName("seaweed");
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player,World world, BlockPos pos, EnumHand hand, EnumFacing side,float hitX, float hitY, float hitZ)
    {
        Block block = world.getBlockState(pos).getBlock();

        if (block == theblock || block == Blocks.SAND)
        {
            side = EnumFacing.UP;
        }

        if (!player.canPlayerEdit(pos.up(), side, stack))
        {
            return EnumActionResult.FAIL;
        }
        else if (stack.stackSize == 0)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
        	if((block == theblock || block == Blocks.SAND) && world.getBlockState(pos.up()).getBlock() == Blocks.WATER && world.getBlockState(pos.up(2)).getBlock() != Blocks.AIR){
                if (world.setBlockState(pos.up(), this.theblock.getDefaultState(), 2))
                {
                    if (world.getBlockState(pos.up()).getBlock() == this.theblock)
                    {
                        this.theblock.onBlockPlacedBy(world, pos, this.theblock.getDefaultState(), player, stack);
                    }

                    
                    --stack.stackSize;
                }
            }
        }

            return EnumActionResult.SUCCESS;
        }
}
