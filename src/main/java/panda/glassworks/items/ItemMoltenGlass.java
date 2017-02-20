package panda.glassworks.items;

import java.util.List;

import panda.glassworks.GlassWorks;
import panda.glassworks.init.GlassItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMoltenGlass extends Item{
	private int ticks =0;
	private int timeToCool = 600;
	
	public ItemMoltenGlass()
    {
		this.setHasSubtypes(true);
		this.setCreativeTab(GlassWorks.GlassTab);
        
    }
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
    {
        return false;
    }




	public String getUnlocalizedName(ItemStack stack)
    {
		switch(stack.getMetadata()){
		case 0:
			return "panda.glassworks.item.molten_glass";
		case 1:
			return "panda.glassworks.item.molten_crystal_glass";
		case 2:
			return "panda.glassworks.item.molten_obsidian_glass";
    }	
		return "panda.glassworks.item.molten_glass";
    }
	
	
/*case 2:
			return "panda.glassworks.item.molten_crystal_glass";
		case 3:
			return "panda.glassworks.item.molten_crystal_glass_cool";*/

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn,int itemSlot, boolean isSelected) { 
		super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);
		
		/*if(ticks >= timeToCool ){
			if(stack.getMetadata() == 0){
				((EntityPlayer)entityIn).inventory.setInventorySlotContents(itemSlot, new ItemStack(GlassItems.MOLTEN_GLASS,stack.stackSize,1));
				//entityIn.getArmorInventoryList()
				this.ticks =0;
			}else
				((EntityPlayer)entityIn).inventory.setInventorySlotContents(itemSlot, new ItemStack(Blocks.GLASS,stack.stackSize));
		}
		this.ticks++;*/
	}
	
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        subItems.add(new ItemStack(itemIn, 1, 0));//Molten Glass
        subItems.add(new ItemStack(itemIn, 1, 1));//Molten Crystal Glass
        subItems.add(new ItemStack(itemIn, 1, 2));
        //subItems.add(new ItemStack(itemIn, 1, 2));//Molten Crystal Glass
        //subItems.add(new ItemStack(itemIn, 1, 3));//Molten Crystal Glass Cool
    }

    
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player,Entity entity) {
		return super.onLeftClickEntity(stack, player, entity);
	}

    @Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target,EntityLivingBase attacker) {
		return super.hitEntity(stack, target, attacker);
	}
}
