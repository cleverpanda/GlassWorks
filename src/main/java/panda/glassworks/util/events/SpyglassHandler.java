package panda.glassworks.util.events;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import panda.glassworks.gui.SpyglassGui;
import panda.glassworks.items.ItemSpyglass;
import panda.glassworks.util.registry.ItemList;

public class SpyglassHandler {


	  public SpyglassHandler(SpyglassGui overlay) {
		  spyglassOverlay = overlay;
	}

	private SpyglassGui spyglassOverlay;

	
	/*@SubscribeEvent
	public void preRenderOverlay(RenderGameOverlayEvent.Pre event) 
	{
		if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET)
		{
			if(Minecraft.getMinecraft().thePlayer != null)
			{
				EntityPlayer player = Minecraft.getMinecraft().thePlayer;
				
				if(Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
				{
					if(player.getHeldItem(player.getActiveHand()) != null && player.getHeldItem(player.getActiveHand()).getItem() == ItemList.SPYGLASS)
					{

						
						float factor = ((ItemSpyglass)player.getHeldItem(player.getActiveHand()).getItem()).getZoom(player.getHeldItem(player.getActiveHand()));
						
						if(factor > 0.1F)
						{
							spyglassOverlay.renderOverlay(event.getResolution().getScaledWidth(), event.getResolution().getScaledHeight());
						}
					}
				}
			}
		}
	}*/




		


}