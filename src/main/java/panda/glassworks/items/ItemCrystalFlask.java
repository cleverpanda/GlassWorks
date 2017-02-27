package panda.glassworks.items;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;
import panda.glassworks.util.registry.ItemList;

public class ItemCrystalFlask extends Item
{
	
	    public ItemCrystalFlask()
	    {
	    	super();
	    	this.setCreativeTab(GlassWorks.GlassTab);
	    	setRegistryName("crystal_flask");
	    }
	    
	    
	    @SideOnly(Side.CLIENT)
	    public boolean hasEffect(ItemStack stack)
	    {
	        return true;
	    }
	    
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        List<EntityAreaEffectCloud> list = worldIn.<EntityAreaEffectCloud>getEntitiesWithinAABB(EntityAreaEffectCloud.class, playerIn.getEntityBoundingBox().expandXyz(2.0D), new Predicate<EntityAreaEffectCloud>()
        {
            public boolean apply(@Nullable EntityAreaEffectCloud p_apply_1_)
            {
                return p_apply_1_ != null && p_apply_1_.isEntityAlive() && p_apply_1_.getOwner() instanceof EntityDragon;
            }
        });

        
            RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);

            if (raytraceresult == null)
            {
                return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
            }
            else
            {
                if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK)
                {
                    BlockPos blockpos = raytraceresult.getBlockPos();

                    if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemStackIn))
                    {
                        return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
                    }

                    if (worldIn.getBlockState(blockpos).getMaterial() == Material.WATER)
                    {
                        worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, this.turnBottleIntoItem(itemStackIn, playerIn, new ItemStack(ItemList.POTION_FLASK)));
                    }
                }

                return new ActionResult<ItemStack>(EnumActionResult.PASS, itemStackIn);
            }
        
    }

    protected ItemStack turnBottleIntoItem(ItemStack p_185061_1_, EntityPlayer player, ItemStack stack)
    {
        --p_185061_1_.stackSize;
        player.addStat(StatList.getObjectUseStats(this));

        if (p_185061_1_.stackSize <= 0)
        {
            return stack;
        }
        else
        {
            if (!player.inventory.addItemStackToInventory(stack))
            {
                player.dropItem(stack, false);
            }

            return p_185061_1_;
        }
    }
}
