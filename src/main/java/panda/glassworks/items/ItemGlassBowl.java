package panda.glassworks.items;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import panda.glassworks.GlassWorks;
import net.minecraftforge.common.util.EnumHelper;

public class ItemGlassBowl extends ItemArmor{
	static ArmorMaterial GLASS = EnumHelper.addArmorMaterial("glass", "glassworks:textures/models/armor/glass_bowl_layer_1.png", 10, new int[]{0,0,0,0}, 0, SoundEvents.BLOCK_GLASS_PLACE, 0);
	private boolean underwater;
	boolean hassetair = false;
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn,
			World worldIn, EntityPlayer playerIn, EnumHand hand) {
		// TODO Auto-generated method stub
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}

	public ItemGlassBowl() {
		super(GLASS, 1, EntityEquipmentSlot.HEAD);
		this.setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("glass_bowl");
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		//&& !haveSetAir
		
		//if underwater
	
		if(player.isInsideOfMaterial(Material.WATER)){
			underwater = true;	
		}else{
			underwater = false;
			hassetair = false;
		}
			
		if (itemStack.getItem() == this && underwater && !hassetair) {

			player.setAir(390);
			hassetair = true;
	    }
		
	} 

	private void effectPlayer(EntityPlayer player, Potion potion, int amplifier) {
	    //Always effect for 8 seconds, then refresh
	    if (player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1)
	        player.addPotionEffect(new PotionEffect(Potion.getPotionById(13), 40, 1, false, false));
	}
	
	
	@Override
	public boolean hasOverlay(ItemStack stack) {
		// TODO Auto-generated method stub
		return super.hasOverlay(stack);
	}
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type){
		return GlassWorks.MODID +":textures/models/armor/glass_bowl_layer_1.png";
	
	}

}
