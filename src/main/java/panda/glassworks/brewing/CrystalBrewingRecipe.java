package panda.glassworks.brewing;

import panda.glassworks.init.GlassItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class CrystalBrewingRecipe implements IBrewingRecipe {


	    /**
	     * Code adapted from TileEntityBrewingStand.isItemValidForSlot(int index, ItemStack stack)
	     */
	    @Override
	    public boolean isInput(ItemStack stack)
	    {
	        Item item = stack.getItem();
	        return item == GlassItems.POTION_FLASK || item == GlassItems.CRYSTAL_FLASK;
	    }

	    /**
	     * Code adapted from TileEntityBrewingStand.isItemValidForSlot(int index, ItemStack stack)
	     */
	    @Override
	    public boolean isIngredient(ItemStack stack)
	    {
	        return PotionHelper.isReagent(stack);
	    }

	    /**
	     * Code copied from TileEntityBrewingStand.brewPotions()
	     * It brews the potion by doing the bit-shifting magic and then checking if the new PotionEffect list is different to the old one,
	     * or if the new potion is a splash potion when the old one wasn't.
	     */
	    @Override
	    public ItemStack getOutput(ItemStack input, ItemStack ingredient)
	    {
	        if (ingredient != null && input != null && isIngredient(ingredient))
	        {
	            ItemStack result = PotionHelper.doReaction(ingredient, input);
	            if (result != input)
	            {
	                return result;
	            }
	            return null;
	        }

	        return null;
	    }
	}