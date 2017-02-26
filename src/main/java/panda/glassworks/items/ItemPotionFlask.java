package panda.glassworks.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import panda.glassworks.GlassWorks;
import panda.glassworks.init.GlassItems;
import panda.glassworks.util.registry.IMeta;
import panda.glassworks.util.registry.ItemList;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPotionFlask extends Item implements IMeta{
    public ItemPotionFlask()
    {
        this.setMaxStackSize(1);
        this.setCreativeTab(GlassWorks.GlassTab);
        this.setHasSubtypes(true); // This allows the item to be marked as a metadata item.
        this.setMaxDamage(0);
        setRegistryName("potion_flask");
    }

    /**
     * Called when the player finishes using this Item (E.g. finishes eating.). Not called when the player stops using
     * the Item before the action is complete.
     */
    @Nullable
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        EntityPlayer entityplayer = entityLiving instanceof EntityPlayer ? (EntityPlayer)entityLiving : null;

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode && this.getMetadata(stack) == 3)
        {
            --stack.stackSize;
        }else if(!entityplayer.capabilities.isCreativeMode){
        	stack.setItemDamage(getMetadata(stack)+1);
        }

        if (!worldIn.isRemote)
        {
            for (PotionEffect potioneffect : PotionUtils.getEffectsFromStack(stack))
            {
                entityLiving.addPotionEffect(new PotionEffect(potioneffect));
            }
        }

        if (entityplayer != null)
        {
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }

        if (entityplayer == null || !entityplayer.capabilities.isCreativeMode)
        {
            if (stack.stackSize <= 0)
            {
                return new ItemStack(ItemList.CRYSTAL_FLASK);
            }

            if (entityplayer != null && stack.stackSize <= 0)
            {
                entityplayer.inventory.addItemStackToInventory(new ItemStack(ItemList.CRYSTAL_FLASK));
            }
        }

        return stack;
    }

	/**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        playerIn.setActiveHand(hand);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
    }

    public String getItemStackDisplayName(ItemStack stack)
    {
    	
    	return I18n.format("text.glassworks.potion_flask.name")+" "+I18n.format(PotionUtils.getPotionFromItem(stack).getNamePrefixed("potion.effect."));
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        PotionUtils.addPotionTooltip(stack, tooltip, 1.0F);
        tooltip.add(I18n.format("text.glassworks.potion_flask_remaining.name")+" "+(4-stack.getMetadata()));
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return !PotionUtils.getEffectsFromStack(stack).isEmpty();
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (PotionType potiontype : PotionType.REGISTRY)
        {
            subItems.add(PotionUtils.addPotionToItemStack(new ItemStack(itemIn), potiontype));
        }
    }

	@Override
	public int getMaxMeta() {
		return 3;
	}
	
	@Override
	public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.RARE ;
    }

	@Override
	public List<ModelResourceLocation> getMetaModelLocations() {
		List<ModelResourceLocation> map = new ArrayList<ModelResourceLocation>();
		map.add(new ModelResourceLocation(getRegistryName(), "dose=4"));
		map.add(new ModelResourceLocation(getRegistryName(), "dose=3"));
		map.add(new ModelResourceLocation(getRegistryName(), "dose=2"));
		map.add(new ModelResourceLocation(getRegistryName(), "dose=1"));
		return map;
	}
}