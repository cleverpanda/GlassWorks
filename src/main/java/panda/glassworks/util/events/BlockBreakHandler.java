package panda.glassworks.util.events;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import panda.glassworks.blocks.BlockGlassSlabBase;
import panda.glassworks.util.registry.BlockList;
import panda.glassworks.util.registry.ItemList;

public class BlockBreakHandler {
	// TODO: fix this.
	@SubscribeEvent
	public void onBlockDrops(HarvestDropsEvent event) {
		if (event.getState().getMaterial() == Material.GLASS) {

			/*
			 * if(event.getHarvester() != null && event.getState() != null &&
			 * event.getDrops() != null && event.getDrops().isEmpty() &&
			 * event.getHarvester().getHeldItemMainhand() != null &&
			 * event.getHarvester().getHeldItemMainhand().getItem() ==
			 * GlassItems.GLASSCUTTER &&
			 * event.getState().getBlock().canSilkHarvest(event.getWorld(),
			 * event.getPos(), event.getState(), event.getHarvester())) {
			 * if((event.getState().getBlock() == BlockList.DOUBLE_GLASS_SLAB
			 * )){ event.getDrops().add(new ItemStack(BlockList.GLASS_SLAB,2));
			 * }else if((event.getState().getBlock() ==
			 * BlockList.DOUBLE_STAINED_GLASS_SLAB )){ event.getDrops().add(new
			 * ItemStack(BlockList.STAINED_GLASS_SLAB,2)); }else
			 * event.getDrops().add(new ItemStack(event.getState().getBlock(),
			 * 1,
			 * event.getState().getBlock().getMetaFromState(event.getState())));
			 * } else
			 */
			if (!(event.getState().getBlock() == BlockList.GLASS_SLAB)
					&& !(event.getState().getBlock() instanceof BlockGlassSlabBase)) {
				event.getDrops().add(new ItemStack(ItemList.BROKEN_GLASS));
			}

		}

	}
}
