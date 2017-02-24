package panda.glassworks.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import panda.glassworks.init.GlassItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GlassBlowingRecipes {
	
	private Map<Item, List<GlassResultStack>> glassMultiList = new HashMap<Item, List<GlassResultStack>>();
	private static final GlassBlowingRecipes INSTANCE = new GlassBlowingRecipes();


    public static GlassBlowingRecipes instance()
    {
        return INSTANCE;
    }
    
    public void InitGlassBlowingRecipes()
    {
    	
    	addBlowingRecipe(GlassItems.MOLTEN_GLASS, GlassItems.BOTTLE_UNFINISHED);
    	addBlowingRecipe(GlassItems.MOLTEN_GLASS, GlassItems.GLASS_LAMP);
    	addBlowingRecipe(GlassItems.MOLTEN_GLASS, GlassItems.GLASS_BULB);
    	
    	addBlowingRecipe(Items.NETHER_STAR, new ItemStack(Blocks.BEACON));
        
        addBlowingRecipe(new ItemStack(GlassItems.MOLTEN_GLASS,2), GlassItems.GLASS_LENS);
        addBlowingRecipe(new ItemStack(GlassItems.MOLTEN_GLASS,2), GlassItems.GLASS_BOWL);
        
        addBlowingRecipe(new ItemStack(GlassItems.MOLTEN_GLASS,2,1), GlassItems.CRYSTAL_FLASK_UNFINISHED);
    }

    
    
    public void addBlowingRecipe(ItemStack input, ItemStack output){
    	
    	Item item = input.getItem();
    	int meta = input.getMetadata();
    	int amount = input.stackSize;
		GlassResultStack stack = new GlassResultStack(output, amount, meta);
		
    	if(getAllBlowingResults(item) == null){
    		List<GlassResultStack> list = new ArrayList<GlassResultStack>();
    		list.add(stack);
    		glassMultiList.put(item, list);
    		return;
    	}
    	
    	List<GlassResultStack> list = getAllBlowingResults(item);
    	if(list != null && !list.contains(stack)){
    		list.add(stack);
    		glassMultiList.replace(item, list);
    	}
    	
    }
    
    public void addBlowingRecipe(Item input, ItemStack output){
    	addBlowingRecipe(new ItemStack(input), output);
    }
    
    public void addBlowingRecipe(ItemStack input, Item output){
    	addBlowingRecipe(input, new ItemStack(output));
    }
    
    public void addBlowingRecipe(Item input, Item output){
    	addBlowingRecipe(new ItemStack(input), new ItemStack(output));
    }
    
    private List<GlassResultStack> getAllBlowingResults(Item input){
    	return glassMultiList.get(input);
    }
    
    //getAllBlowingResults is internal for THIS CLASS only! DO NOT USE IT!
    
    private List<GlassResultStack> getAllBlowingResults(ItemStack input){
    	return getAllBlowingResults(input.getItem());
    }
    
    @Nullable
	public List<GlassResultStack> getBlowingResults(ItemStack input){
		List<GlassResultStack> results = getAllBlowingResults(input);
		List<GlassResultStack> returned = new ArrayList<GlassResultStack>();
		if(results != null){
		for(GlassResultStack theResult : results){
			if(theResult.getMeta() == input.getMetadata() && theResult.getAmount() <= input.stackSize){
				returned.add(theResult);
			}
		}
		}
		if(returned.size() == 0) return null;
		return returned;
	}

}
