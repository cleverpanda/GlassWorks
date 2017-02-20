package panda.glassworks.util;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import panda.glassworks.blocks.BlockGlassSlab;
import panda.glassworks.init.GlassBlocks;
import panda.glassworks.init.GlassItems;

public class BlockBreakHandler {
	@SubscribeEvent
	public void onBlockDrops(HarvestDropsEvent event) {
		if(event.getState().getMaterial() == Material.GLASS){

			if(event.getHarvester() != null && event.getState() != null && event.getDrops() != null && event.getDrops().isEmpty() && event.getHarvester().getHeldItemMainhand() != null && event.getHarvester().getHeldItemMainhand().getItem() == GlassItems.GLASSCUTTER && event.getState().getBlock().canSilkHarvest(event.getWorld(), event.getPos(), event.getState(), event.getHarvester()))
			{	if((event.getState().getBlock() == GlassBlocks.DOUBLE_GLASS_SLAB )){
					event.getDrops().add(new ItemStack(GlassBlocks.GLASS_SLAB,2));
				}else
					if((event.getState().getBlock() == GlassBlocks.DOUBLE_STAINED_GLASS_SLAB )){
						event.getDrops().add(new ItemStack(GlassBlocks.STAINED_GLASS_SLAB,2));
					}else
				event.getDrops().add(new ItemStack(event.getState().getBlock(), 1, event.getState().getBlock().getMetaFromState(event.getState())));
			}
			else
				if(!(event.getState().getBlock() == GlassBlocks.GLASS_SLAB) && !(event.getState().getBlock() == GlassBlocks.STAINED_GLASS_SLAB)){
					event.getDrops().add(new ItemStack(GlassItems.BROKEN_GLASS));
				}
				
		}
		
	}
}
