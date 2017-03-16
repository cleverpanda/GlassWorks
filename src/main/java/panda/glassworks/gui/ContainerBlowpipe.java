package panda.glassworks.gui;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import panda.glassworks.util.inventory.BasicInventory;
import panda.glassworks.util.inventory.BlowpipeOutputSlot;
import panda.glassworks.util.inventory.GlassResultStack;
import panda.glassworks.util.registry.recipe.GlassBlowingRecipes;

public class ContainerBlowpipe extends Container {

	private World worldObj;
	private Slot input_slot;
	private BlowpipeOutputSlot output_slot;
	public int currentSelection;
	EntityPlayer player;

	public ContainerBlowpipe(InventoryPlayer playerInventory, World worldIn, BlockPos posIn) {
		System.out.println("Called on " + FMLCommonHandler.instance().getEffectiveSide());
		IInventory inputInv = new BasicInventory(this, "glassworks.blowpipe.input");
		IInventory outputInv = new BasicInventory(this, "glassworks.blowpipe.output");
		input_slot = new Slot(inputInv, 0, 110, 22);
		output_slot = new BlowpipeOutputSlot(input_slot, outputInv, 1, 138, 22);

		this.addSlotToContainer(input_slot);
		this.addSlotToContainer(output_slot);

		this.worldObj = worldIn;

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 - 26));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142 - 26));
		}
		player = playerInventory.player;
	}

	public int getSelection() {
		return currentSelection;
	}

	public int setSelection(int k) {
		currentSelection = k;
		return currentSelection;
	}

	public int forceUpdateSelector(int k) {
		setSelection(k);
		forceUpdate();
		return k;
	}

	/*
	 * PICKUP, Left/Right normal click QUICK_MOVE, Shift-Click SWAP, Pushing a
	 * key to move a stack (1-9) CLONE, THROW, Pushing q QUICK_CRAFT, Touching
	 * the input slot? This breaks the entire thing for some reason. Also fires
	 * on random times when not touching the input slot. PICKUP_ALL;
	 * Double-Click
	 */

	@Override
	public void onCraftMatrixChanged(IInventory inventory) {
		int theSelection = getSelection();
		System.out.println("MATRIX UPDATED WITH SELECTION " + theSelection + " AT SIDE "
				+ FMLCommonHandler.instance().getEffectiveSide());
		output_slot.inventory.clear();
		if (hasInput() && GlassBlowingRecipes.getBlowingResults(input_slot.getStack()) != null) {
			List<GlassResultStack> list = GlassBlowingRecipes.getBlowingResults(input_slot.getStack());
			int g = list.size();
			if (theSelection >= g) {
				theSelection = g - 1;
				output_slot.setSelection(theSelection);
			}
			GlassResultStack stack = list.get(theSelection);
			int sizeInput = input_slot.getStack().stackSize;
			int metaInput = input_slot.getStack().getMetadata();
			if (sizeInput >= stack.getAmount() && metaInput == stack.getMeta()) {
				System.out.println("Selection index is " + getSelection());
				System.out.println("Setting output to " + stack.getStack().toString());
				output_slot.putStack(stack.getStack().copy());
			}
		} else if (!hasInput()) {
			output_slot.putStack(null);
			;
		}
		this.detectAndSendChanges();
	}

	public void forceUpdate() {
		if (output_slot.getHasStack())
			this.onCraftMatrixChanged(output_slot.inventory);
	}

	boolean hasInput() {
		return input_slot.getHasStack();
	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);

		if (!this.worldObj.isRemote) {

			ItemStack itemstack = this.input_slot.getStack();
			input_slot.putStack(null);

			if (itemstack != null) {
				playerIn.dropItem(itemstack, false);
			}
		}
		this.detectAndSendChanges();
	}

	public boolean canInteractWith(EntityPlayer playerIn) {
		return true;
	}

	/**
	 * Take a stack from the specified inventory slot.
	 */
	@Nullable
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = null;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 0)// Input Slot
			{
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if (index == 1)// Output Slot
			{
				if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
					return null;
				}
				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= 2 && index < 29)// Player Main Inv
			{
				if (!this.mergeItemStack(itemstack1, 29, 38, false)) {
					return null;
				}
			} else if (index >= 29 && index < 38)// Hotbar
			{
				if (!this.mergeItemStack(itemstack1, 2, 29, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 2, 38, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(playerIn, itemstack1);
		}
		this.detectAndSendChanges();

		return itemstack;
	}

}