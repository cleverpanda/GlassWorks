package panda.glassworks.items;

	import java.util.Set;

import panda.glassworks.GlassWorks;
import panda.glassworks.blocks.BlockGlassSlab;
import panda.glassworks.init.GlassBlocks;
import panda.glassworks.init.GlassItems;
import panda.glassworks.worldgen.WorldGenTarPool;

	import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

	import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

	public class ItemGlasscutter extends Item{
		private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.GLASS,Blocks.GLASS_PANE,Blocks.GLOWSTONE,Blocks.STAINED_GLASS_PANE,Blocks.STAINED_GLASS});
		
		public ItemGlasscutter() {
			this.setMaxStackSize(1);
	        this.setMaxDamage(1562);
	        this.setCreativeTab(GlassWorks.GlassTab);
	        this.setContainerItem(this);
	        setRegistryName("glasscutter");
		}
		
		@Override
		public ItemStack getContainerItem(ItemStack itemStack) {
			return new ItemStack(this,1,itemStack.getItemDamage() +1);
		}
		
		@SideOnly(Side.CLIENT)
		@Override
	    public boolean shouldRotateAroundWhenRendering()
	    {
	        return false;
	    }

		public float getStrVsBlock(ItemStack stack, IBlockState state)
	    {
	        Material material = state.getMaterial();
	        return material != Material.GLASS ? super.getStrVsBlock(stack, state) : 8.0F;
	    }
		


		@Override
		public boolean onBlockDestroyed(ItemStack stack, World worldIn,IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
			if(EFFECTIVE_ON.contains(state.getBlock()) || state.getMaterial() == Material.GLASS){
				stack.damageItem(1, entityLiving);
			}else{
				stack.damageItem(300, entityLiving);
			}
			
			return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
		}

		@Override
		public EnumActionResult onItemUse(ItemStack stack,EntityPlayer playerIn, World worldIn, BlockPos pos,EnumHand hand, EnumFacing facing, float hitX, float hitY,float hitZ) {
			
			return super.onItemUse(stack, playerIn, worldIn, pos, hand, facing, hitX, hitY,
					hitZ);
		}



	}
