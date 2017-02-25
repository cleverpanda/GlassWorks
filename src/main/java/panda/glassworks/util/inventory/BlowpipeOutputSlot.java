package panda.glassworks.util.inventory;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import panda.glassworks.proxy.CommonProxy;
import panda.glassworks.util.registry.recipe.GlassBlowingRecipes;

public class BlowpipeOutputSlot extends Slot{
	
    /** The craft matrix inventory linked to this result slot. */
    /** The player that is using the GUI where this slot resides. */
    private final Slot inputSlot;
    private int selection = 0;

    public BlowpipeOutputSlot(Slot inputSlotIn, IInventory inventory, int index, int x, int y){
    	super(inventory, index, x, y);
        inputSlot = inputSlotIn;
    }

        /**
         * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
         */
        public boolean isItemValid(@Nullable ItemStack stack)
        {
            return false;
        }

        public void putStack(@Nullable ItemStack stack)
        {
            ((BasicInventory) this.inventory).setInventorySlotContentsNoUpdate(0, stack);
            this.onSlotChanged();
        }
        
        public void setSelection(int k){
        	selection = k;
        }
        
        public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
        {
        	ItemStack input = inputSlot.getStack();
        	if(input != null){
        		int size = input.stackSize;
        		int amount = GlassBlowingRecipes.getBlowingResults(input).get(selection).getAmount();
        		size = size - amount;
        		if(size <= 0){
        			size = 0;
        			this.putStack(null);
        			inputSlot.putStack(null);
        		}
        		else if(size >= 0){
        			ItemStack newInput = new ItemStack(input.getItem(), size, input.getMetadata());
        			inputSlot.putStack(newInput);
        		}
            
        	}
        	else if(input == null){
        		this.putStack(null);
        	}
        }
}
