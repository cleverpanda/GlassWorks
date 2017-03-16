package panda.glassworks.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;
import panda.glassworks.util.registry.IMeta;

public class ItemSpyglass extends Item implements IMeta {

	public static boolean isUsing = false;

	public ItemSpyglass() {
		super();
		this.setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("spyglass");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, List list) {
		list.add(new ItemStack(itemIn, 1, 0));
		list.add(new ItemStack(itemIn, 1, 1));
	}

	@Override
	// Make a unique name for each contents type (lime, orange, etc) so we can
	// name them individually
	// The fullness information is added separately in getItemStackDisplayName()
	public String getUnlocalizedName(ItemStack stack) {
		int metadata = stack.getMetadata();

		return super.getUnlocalizedName() + "." + (metadata == 0 ? "short" : "long");
	}

	@Override
	public EnumAction getItemUseAction(ItemStack item) {
		return EnumAction.BOW;
	}

	private void toggleMode(ItemStack item) {
		int dam = item.getItemDamage();
		item.setItemDamage(dam == 1 ? 0 : 1);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack item) {
		return Integer.MAX_VALUE;
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
		isUsing = false;
		super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack item, World world, EntityPlayer user, EnumHand hand) {

		if (user.isSneaking()) {
			isUsing = false;
			world.playSound(user, user.posX, user.posY + 1.5D, user.posZ, SoundEvents.BLOCK_COMPARATOR_CLICK,
					SoundCategory.BLOCKS, 1.0F, 1.5F);
			toggleMode(item);
			if (user.getActiveHand() != null) {
				user.swingArm(user.getActiveHand());
			}

		} else {
			isUsing = true;
			ItemStack heldItem = null;
			if (user.getActiveHand() != null) {
				heldItem = user.getHeldItem(user.getActiveHand());
			}
			if (heldItem != null) {
				ReflectionHelper.setPrivateValue(EntityLivingBase.class, user, heldItem, "activeItemStack",
						"field_184627_bm");
				ReflectionHelper.setPrivateValue(EntityLivingBase.class, user, heldItem.getMaxItemUseDuration(),
						"activeItemStackUseCount", "field_184628_bn");
				user.getDataManager().set(ReflectionHelper.getPrivateValue(EntityLivingBase.class, user, "HAND_STATES",
						"field_184621_as"), (byte) 1);
			}

			// return ActionResult.newResult(EnumActionResult.SUCCESS,
			// heldItem);
			// user.setActiveHand(hand);//..get.setItemStackToSlot(slotIn,
			// stack);.setItemInUse(item, getMaxItemUseDuration(item));
		}
		return ActionResult.newResult(EnumActionResult.SUCCESS, item);
	}

	public float getZoom(ItemStack item) {
		int id = item.getItemDamage();
		return .75F + (2F * id);
	}

	@Override
	public int getMaxMeta() {
		return 1;
	}

	@Override
	public List<ModelResourceLocation> getMetaModelLocations() {
		List<ModelResourceLocation> map = new ArrayList<ModelResourceLocation>();
		map.add(new ModelResourceLocation(getRegistryName(), "use=0"));
		map.add(new ModelResourceLocation(getRegistryName(), "use=1"));
		return map;
	}
}
