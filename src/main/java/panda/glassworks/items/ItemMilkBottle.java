package panda.glassworks.items;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import panda.glassworks.GlassWorks;

public class ItemMilkBottle extends Item{
	
	public ItemMilkBottle()
    {
		setMaxStackSize(1);
		setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("milk_bottle");
        
    }
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack)
    {
        return 24;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
	@Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        playerIn.setActiveHand(hand);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
    }
    @Nullable
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }

        if (!worldIn.isRemote)
        {
            entityLiving.curePotionEffects(new ItemStack(Items.MILK_BUCKET));
        }

        if (entityLiving instanceof EntityPlayer)
        {
            ((EntityPlayer)entityLiving).addStat(StatList.getObjectUseStats(this));
        }

        return stack.stackSize <= 0 ? new ItemStack(Items.GLASS_BOTTLE) : stack;
    }
}
