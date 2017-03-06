package panda.glassworks.items;

import panda.glassworks.GlassWorks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ItemSpyglass extends Item{

	
	public ItemSpyglass()
    {
    	super();
    	this.setCreativeTab(GlassWorks.GlassTab);
    	setRegistryName("spyglass");
    	this.setMaxDamage(1);
    	this.setMaxStackSize(1);
    }
	
	@Override
    public EnumAction getItemUseAction(ItemStack item)
    {
        return EnumAction.BOW;
    }
	
    private void toggleMode(ItemStack item)
    {
    	int dam = item.getItemDamage();
    	item.setItemDamage(dam == 1 ? 0 : 1);
	}
    
	@Override
    public int getMaxItemUseDuration(ItemStack item)
    {
        return Integer.MAX_VALUE;
    }

    @Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack item,World world, EntityPlayer user, EnumHand hand) {

    if(user.isSneaking())
	{
		world.playSound(user,user.posX, user.posY + 1.5D, user.posZ, SoundEvents.BLOCK_COMPARATOR_CLICK ,SoundCategory.BLOCKS, 1.0F, 1.5F);
		toggleMode(item);
		user.swingArm(user.getActiveHand());
	}
	else
	{
		ItemStack heldItem = user.getHeldItemMainhand();
		ReflectionHelper.setPrivateValue(EntityLivingBase.class, user, heldItem, "activeItemStack", "field_184627_bm");
		ReflectionHelper.setPrivateValue(EntityLivingBase.class, user, heldItem.getMaxItemUseDuration(), "activeItemStackUseCount", "field_184628_bn");
		user.getDataManager().set(ReflectionHelper.getPrivateValue(EntityLivingBase.class, user, "HAND_STATES", "field_184621_as"), (byte) 1);
		
		//return ActionResult.newResult(EnumActionResult.SUCCESS, heldItem);		
		//user.setActiveHand(hand);//..get.setItemStackToSlot(slotIn, stack);.setItemInUse(item, getMaxItemUseDuration(item));
	}
    return ActionResult.newResult(EnumActionResult.SUCCESS, item);
    }
    
	public float getZoom(ItemStack item)
    {
    	int id = item.getItemDamage();
    	return 0.5F +  (0.25F* id);
    }
}
