package panda.glassworks.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import panda.glassworks.init.Recipes;
import panda.glassworks.util.GlassBlowingRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ContainerBlowpipe extends Container{

	/** The crafting matrix inventory (3x3). */
	private IInventory outputSlot;
	/** The 2slots where you put your items in that you want to merge and/or rename. */
	public InventoryCrafting inputSlot;
	private World worldObj;
	/** Position of the workbench */
	private BlockPos pos;

	private int selection;

	public ContainerBlowpipe(InventoryPlayer playerInventory, World worldIn, BlockPos posIn)
	{
		System.out.println("Called on " + FMLCommonHandler.instance().getEffectiveSide());
		this.inputSlot = new InventoryCrafting(this, 1, 1);
		this.outputSlot = new InventoryCraftResult();
		this.selection = 0;

		this.addSlotToContainer(new Slot(this.inputSlot, 0, 110, 22));
		//this.addSlotToContainer(new Slot(this.outputSlot, 1, 138, 22));
		this.addSlotToContainer(new SlotCrafting(playerInventory.player, inputSlot, outputSlot, 1, 138, 22));

		this.worldObj = worldIn;
		this.pos = posIn;


		for (int i = 0; i < 3; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18-26));
			}
		}

		for (int k = 0; k < 9; ++k)
		{
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142-26));
		}

		this.onCraftMatrixChanged();
	}

	@Override
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn,EntityPlayer player) {
		//System.out.println("DOOT");
		//System.out.println(selection);
		//this.onCraftMatrixChanged();
		System.out.println(player.inventory.getItemStack());
		
		return super.slotClick(slotId, dragType, clickTypeIn, player);
	}

	@Override
	public void putStackInSlot(int slotID, ItemStack stack) {
		// TODO Auto-generated method stub
		super.putStackInSlot(slotID, stack);
	}

	@Override
	public void setCanCraft(EntityPlayer player, boolean canCraft) {
		// TODO Auto-generated method stub
		super.setCanCraft(player, canCraft);
	}

	public void onCraftMatrixChanged()
	{	
		if(hasInput()){
			List<ItemStack> recipes = GlassBlowingRecipes.getGlassBlowingResultList(inputStack());
			if(!recipes.isEmpty()){
				if(selection < recipes.size()){
					if(recipes.get(selection)  != null){
						System.out.println(selection);
						//System.out.println(recipes.get(selection));
						this.putStackInSlot(1, recipes.get(selection).copy());
					}else{
						this.outputSlot.clear();
					}
				}else{
					this.outputSlot.clear();
					this.selection = recipes.size()-1;
				}
				
			}else{
				this.outputSlot.clear();
			}
		}else{
			this.outputSlot.clear();
		}
		this.detectAndSendChanges();
	}
	
	boolean hasInput(){
		return (this.inputSlot.getStackInSlot(0) != null);
	}
	
	ItemStack inputStack(){
		return (this.inputSlot.getStackInSlot(0));
	}

	public void setCurrentRecipeIndex(int currentRecipeIndexIn)
	{

		if (hasInput())
		{
				List<ItemStack> recipes = GlassBlowingRecipes.getGlassBlowingResultList(inputStack());
				if(!recipes.isEmpty()){
					if(recipes.get(selection)  != null){
						if(currentRecipeIndexIn > GlassBlowingRecipes.getGlassBlowingResultList(inputStack()).size()-1){
							this.selection = GlassBlowingRecipes.getGlassBlowingResultList(inputStack()).size();
						}else{
							this.selection = currentRecipeIndexIn;
						}
						if (this.selection < 0)
						{
							this.selection = 0;
						}else{
							this.selection = currentRecipeIndexIn;
						}
					}else{
						this.outputSlot.clear();
						this.selection =0;
					}
				}else{
					this.outputSlot.clear();
					this.selection =0;
				}
		}else{
			this.outputSlot.clear();
			this.selection =0;
		}
		this.detectAndSendChanges();
		onCraftMatrixChanged();
	}

	@Override
	public void detectAndSendChanges() {

		super.detectAndSendChanges();
	}

	@Override
	protected void retrySlotClick(int slotId, int clickedButton, boolean mode,EntityPlayer playerIn) {


		super.retrySlotClick(slotId, clickedButton, mode, playerIn);
	}

	/**
	 * Called when the container is closed.
	 */
	 public void onContainerClosed(EntityPlayer playerIn)
	{
		super.onContainerClosed(playerIn);

		if (!this.worldObj.isRemote)
		{

			ItemStack itemstack = this.inputSlot.removeStackFromSlot(0);

			if (itemstack != null)
			{
				playerIn.dropItem(itemstack, false);
			}
		}
		this.detectAndSendChanges();
	}

	 public boolean canInteractWith(EntityPlayer playerIn)
	 {
		 return true;
	 }

	 /**
	  * Take a stack from the specified inventory slot.
	  */
	 @Nullable
	 public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
	 {
		 ItemStack itemstack = null;
		 Slot slot = (Slot)this.inventorySlots.get(index);

		 if (slot != null && slot.getHasStack())
		 {
			 ItemStack itemstack1 = slot.getStack();
			 itemstack = itemstack1.copy();

			 if (index == 0)
			 {
				 if (!this.mergeItemStack(itemstack1, 2, 38, true))
				 {
					 return null;
				 }

				 slot.onSlotChange(itemstack1, itemstack);
			 }
			 else if (index >= 2 && index < 29)
			 {
				 if (!this.mergeItemStack(itemstack1, 29, 38, false))
				 {
					 return null;
				 }
			 }
			 else if (index >= 29 && index < 38)
			 {
				 if (!this.mergeItemStack(itemstack1, 2, 29, false))
				 {
					 return null;
				 }
			 }
			 else if (!this.mergeItemStack(itemstack1, 2, 38, false))
			 {
				 return null;
			 }

			 if (itemstack1.stackSize == 0)
			 {
				 slot.putStack((ItemStack)null);
			 }
			 else
			 {
				 slot.onSlotChanged();
			 }

			 if (itemstack1.stackSize == itemstack.stackSize)
			 {
				 return null;
			 }

			 slot.onPickupFromSlot(playerIn, itemstack1);
		 }
		 this.detectAndSendChanges();

		 return itemstack;
	 }
	 
	 

	 /**
	  * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in
	  * is null for the initial slot that was double-clicked.
	  */
	 public boolean canMergeSlot(ItemStack stack, Slot slotIn)
	 {
		 return slotIn.inventory != this.outputSlot && super.canMergeSlot(stack, slotIn);
	 }

	 
	 static class MySlotCrafting extends Slot
		 {
		     /** The craft matrix inventory linked to this result slot. */
		     private final InventoryCrafting craftMatrix;
		     /** The player that is using the GUI where this slot resides. */
		     private final EntityPlayer thePlayer;
		     /** The number of items that have been crafted so far. Gets passed to ItemStack.onCrafting before being reset. */
		     private int amountCrafted;

		     public MySlotCrafting(EntityPlayer player, InventoryCrafting craftingInventory, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
		     {
		         super(inventoryIn, slotIndex, xPosition, yPosition);
		         this.thePlayer = player;
		         this.craftMatrix = craftingInventory;
		     }

		     /**
		      * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
		      */
		     public boolean isItemValid(@Nullable ItemStack stack)
		     {
		         return false;
		     }

		     /**
		      * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
		      * stack.
		      */
		     public ItemStack decrStackSize(int amount)
		     {
		         if (this.getHasStack())
		         {
		             this.amountCrafted += Math.min(amount, this.getStack().stackSize);
		         }

		         return super.decrStackSize(amount);
		     }

		     /**
		      * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
		      * internal count then calls onCrafting(item).
		      */
		     protected void onCrafting(ItemStack stack, int amount)
		     {
		         this.amountCrafted += amount;
		         this.onCrafting(stack);
		     }

		     /**
		      * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
		      */
		     protected void onCrafting(ItemStack stack)
		     {
		         if (this.amountCrafted > 0)
		         {
		             stack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.amountCrafted);
		         }

		         this.amountCrafted = 0;

		         

		         /*if (stack.getItem() instanceof ItemSword)
		         {
		             this.thePlayer.addStat(AchievementList.BUILD_SWORD);
		         }*/

		     }

		     public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack)
		     {
		         this.onCrafting(stack);
		         ArrayList<ItemStack> aitemstack = GlassBlowingRecipes.instance().getGlassBlowingResultList(this.craftMatrix.getStackInSlot(0));
		         System.out.println(aitemstack);

		         for (int i = 0; i < aitemstack.size()-1; ++i)
		         {
		             ItemStack itemstack = this.craftMatrix.getStackInSlot(0);
		             ItemStack itemstack1 = aitemstack.get(i);

		             if (itemstack != null)
		             {
		                 this.craftMatrix.decrStackSize(0, 1);
		                 itemstack = this.craftMatrix.getStackInSlot(0);
		             }

		             if (itemstack1 != null)
		             {
		                 if (itemstack == null)
		                 {
		                     this.craftMatrix.setInventorySlotContents(0, itemstack1);
		                 }
		                 else if (ItemStack.areItemsEqual(itemstack, itemstack1) && ItemStack.areItemStackTagsEqual(itemstack, itemstack1))
		                 {
		                     itemstack1.stackSize += itemstack.stackSize;
		                     this.craftMatrix.setInventorySlotContents(0, itemstack1);
		                 }
		                 else if (!this.thePlayer.inventory.addItemStackToInventory(itemstack1))
		                 {
		                     this.thePlayer.dropItem(itemstack1, false);
		                 }
		             }
		         }
		     }
	 }
}