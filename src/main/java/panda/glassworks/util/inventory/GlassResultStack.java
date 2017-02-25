package panda.glassworks.util.inventory;

import net.minecraft.item.ItemStack;

public class GlassResultStack {
	
	private int reagentMeta;
	private int reagentAmount;
	private ItemStack resultStack;
	
	
	public GlassResultStack(ItemStack stack, int requiredAmount, int requiredMeta){
		reagentMeta = requiredMeta;
		reagentAmount = requiredAmount;
		resultStack = stack;
	}
	
	public GlassResultStack(ItemStack stack, int requiredAmount){
		this(stack, requiredAmount, 0);
	}

	public GlassResultStack(ItemStack stack){
		this(stack, 0, 0);
	}
	
	public int getMeta(){
		return reagentMeta;
	}
	
	public int getAmount(){
		return reagentAmount;
	}
	
	public ItemStack getStack(){
		return resultStack;
	}
	
}
