package panda.glassworks.util.inventory;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import panda.glassworks.gui.ContainerBlowpipe;

public class BasicInventory implements IInventory{

	private final String name;
	private ItemStack stack;
	private ContainerBlowpipe blowpipe;
	
    public BasicInventory(ContainerBlowpipe blowpipeIn, String nameIn)
    {
        name = nameIn;
        blowpipe = blowpipeIn;
    }
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasCustomName() {
		return true;
	}

	@Override
	public ITextComponent getDisplayName() {
		return new TextComponentString(I18n.format(name));
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int index) {
		return stack;
	}

    @Nullable
    public ItemStack decrStackSize(int index, int count)
    {
        ItemStack itemstack = stack.splitStack(count);

        if (itemstack != null)
        {
        	blowpipe.onCraftMatrixChanged(this);
        }

        return itemstack;
    }

	@Override
	public ItemStack removeStackFromSlot(int index) {
		ItemStack taken = null;
		if(stack != null){ taken = stack.copy();}
		stack = null;
		return taken;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stackIn) {
		stack = stackIn;
		blowpipe.onCraftMatrixChanged(this);
	}
	
	public void setInventorySlotContentsNoUpdate(int index, ItemStack stackIn) {
		stack = stackIn;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void markDirty() {
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return true;
	}

	@Override
	public void openInventory(EntityPlayer player) {
	}

	@Override
	public void closeInventory(EntityPlayer player) {
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {	
	}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		stack = null;	
	}

}
