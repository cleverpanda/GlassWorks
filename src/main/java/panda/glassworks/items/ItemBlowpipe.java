package panda.glassworks.items;

import panda.glassworks.GlassWorks;
import panda.glassworks.gui.BlowpipeGuiHandler;

import com.google.common.collect.ImmutableMap;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.animation.ITimeValue;
import net.minecraftforge.fml.common.Mod;

public class ItemBlowpipe extends Item{

	public ItemBlowpipe(){
		super();
		this.setCreativeTab(GlassWorks.GlassTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn,World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			player.openGui(GlassWorks.INSTANCE, BlowpipeGuiHandler.BLOWPIPE_GUI, world, (int)player.posX, (int)player.posY, (int)player.posZ);
	    }
		return super.onItemRightClick(itemStackIn, world, player, hand);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.DRINK;
	}

	@Override
	public boolean canItemEditBlocks() {
		return false;
	}

	@Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
		return super.onDroppedByPlayer(item, player);
	}

}