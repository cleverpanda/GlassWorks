package panda.glassworks.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;
import panda.glassworks.util.registry.IMeta;

public class ItemMoltenGlass extends Item implements IMeta{
	private int ticks = 0;
	private int timeToCool = 600;
	
	public ItemMoltenGlass()
    {
		this.setHasSubtypes(true);
		this.setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("molten_glass");
        
    }
	
	@Override
	public boolean showDurabilityBar(ItemStack stack)
    {
        return false;
    }




	public String getUnlocalizedName(ItemStack stack)
    {
		return "item.glassworks." + getRegistryName().getResourcePath() + "." + stack.getMetadata();
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

	@Override
	public int getMaxMeta() {
		return 2;
	}

	@Override
	public List<ModelResourceLocation> getMetaModelLocations() {
		List<ModelResourceLocation> list = new ArrayList<ModelResourceLocation>();
		ResourceLocation regname = getRegistryName();
		list.add(new ModelResourceLocation(regname, "type=default"));
		list.add(new ModelResourceLocation(regname, "type=crystal"));
		list.add(new ModelResourceLocation(regname, "type=obsidian"));
		return list;
	}
}
