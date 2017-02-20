package panda.glassworks.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import panda.glassworks.init.GlassItems;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class GlassBlowingRecipes {
	
	private static Map<ItemStack, ItemStack> glassrecipeList = Maps.<ItemStack, ItemStack>newHashMap();
	private static final GlassBlowingRecipes BLOWING_BASE = new GlassBlowingRecipes();


    public static GlassBlowingRecipes instance()
    {
        return BLOWING_BASE;
    }

    public static void InitGlassBlowingRecipes()
    {

        addGlassBlowingRecipe( new ItemStack(GlassItems.MOLTEN_GLASS,1,0), new ItemStack(GlassItems.BOTTLE_UNFINISHED,1));
        addGlassBlowingRecipe( new ItemStack(GlassItems.MOLTEN_GLASS,1,0), new ItemStack(GlassItems.GLASS_LAMP,1));
        addGlassBlowingRecipe( new ItemStack(GlassItems.MOLTEN_GLASS,2,0), new ItemStack(GlassItems.GLASS_LENS,1));
        addGlassBlowingRecipe( new ItemStack(GlassItems.MOLTEN_GLASS,2,0), new ItemStack(GlassItems.GLASS_BOWL,1));
        addGlassBlowingRecipe( new ItemStack(GlassItems.MOLTEN_GLASS,1,0), new ItemStack(GlassItems.GLASS_BULB,1));
        addGlassBlowingRecipe( new ItemStack(GlassItems.MOLTEN_GLASS,2,1), new ItemStack(GlassItems.CRYSTAL_FLASK_UNFINISHED,1));
    }


    /**
     * Adds a smelting recipe, where the input item is an instance of Block.
     */
    public void addSmeltingRecipeForBlock(Block input, ItemStack stack)
    {
        this.addGlassBlowing(Item.getItemFromBlock(input), stack);
    }

    /**
     * Adds a smelting recipe using an Item as the input item.
     */
    public void addGlassBlowing(Item input, ItemStack stack)
    {
        this.addGlassBlowingRecipe(new ItemStack(input, 1, 32767), stack);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public static void addGlassBlowingRecipe(ItemStack input, ItemStack stack)
    {
        //if (getGlassBlowingResult(input) != null){
        //		net.minecraftforge.fml.common.FMLLog.info("Ignored glass blowing recipe with conflicting input: " + input + " = " + stack);
        //	return;
        //}
        
        glassrecipeList.put(input, stack);
    }

    /**
     * Returns the smelting result of an item.
     */
    @Nullable
    public ItemStack getGlassBlowingResult(ItemStack stack)
    {
        for (Entry<ItemStack, ItemStack> entry : this.glassrecipeList.entrySet())
        {
            if (this.compareItemStacks(stack, (ItemStack)entry.getKey()))
            {
                return (ItemStack)entry.getValue();
            }
        }

        return null;
    }
    
    @Nullable
    public static ArrayList<ItemStack> getGlassBlowingResultList(ItemStack stack)
    {
    	
    	ArrayList<ItemStack> resultList = new ArrayList<ItemStack>();
    	if(stack != null){
    	
    	for (Entry<ItemStack, ItemStack> entry : glassrecipeList.entrySet())
        {
    		
    			
    		if(stack.stackSize > 0){
        			if (compareItemStacks(stack, (ItemStack)entry.getKey()))
        			{
        				resultList.add((ItemStack)entry.getValue());
        			}
        		}

        }
    	}

        return resultList;
    }

    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private static boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
    	//System.out.println(stack1.stackSize +", " +stack2.stackSize);
    	return (stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata()) && stack1.stackSize >= stack2.stackSize);
        
    }

    public static Map<ItemStack, ItemStack> getGlassBlowingList()
    {
        return glassrecipeList;
    }
}
