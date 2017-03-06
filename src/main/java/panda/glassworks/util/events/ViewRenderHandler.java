package panda.glassworks.util.events;



import org.lwjgl.opengl.GL11;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import panda.glassworks.gui.SpyglassGui;
import panda.glassworks.items.ItemSpyglass;
import panda.glassworks.util.registry.ItemList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

public class ViewRenderHandler {

	  
	  
	@SubscribeEvent
	public void fogRenderEvent(EntityViewRenderEvent.FogDensity event)
	{
		if(event.getEntity() instanceof EntityPlayer){
			if(((EntityPlayer)event.getEntity()).inventory.armorInventory[3] != null){
				if((  ((EntityPlayer)event.getEntity()).inventory.armorInventory[3].getItem() == ItemList.GLASS_BOWL)){
					if(event.getEntity().isInsideOfMaterial(Material.WATER)){
						event.setDensity(0.005F);
						event.setCanceled(true);
					}
				}
			}
		}
	}
/*
	@SubscribeEvent
	public void onBowFOV(FOVUpdateEvent event)
	{
		if(event.getEntity() == null || event.getEntity().getActiveHand() == null) return;
		
		ItemStack stack = event.getEntity().getHeldItem(event.getEntity().getActiveHand());

		ItemSpyglass spy = null;
		if (stack != null && stack.getItem() == ItemList.SPYGLASS){
			spy = (ItemSpyglass)stack.getItem();
			event.setNewfov(event.getNewfov() * (1.0F - spy.getZoom(stack)));
		}

	}
*/

		


}







/*
		if (((EntityLivingBase)event.getPlayer()).isPotionActive(MobEffects.WATER_BREATHING))
        {
            GlStateManager.setFogDensity(0.01F);
        }
        else
        {
            GlStateManager.setFogDensity(0.1F - (float)EnchantmentHelper.getRespirationModifier((EntityLivingBase)entity) * 0.03F);
        }

		float f12 = 0.0F;

        if (entity instanceof EntityLivingBase)
        {
            f12 = (float)EnchantmentHelper.getRespirationModifier((EntityLivingBase)entity) * 0.2F;

            if (((EntityLivingBase)entity).isPotionActive(MobEffects.WATER_BREATHING))
            {
                f12 = f12 * 0.3F + 0.6F;
            }
        }

        this.fogColorRed = 0.02F + f12;
        this.fogColorGreen = 0.02F + f12;
        this.fogColorBlue = 0.2F + f12;





		/*
		System.out.println("POOP");
		if(event.getEntity() instanceof EntityPlayer && event.getEntity().worldObj.getBlockState(event.getEntity().getPosition().add(0, 1.6, 0)).getBlock() == GlassBlocks.TAR ){
	    	System.out.println("DOOT");
	    	//GlStateManager.setFogStart(0);
		    //GlStateManager.setFogEnd(2);
		    GlStateManager.color(0, 0, 0);
		    GlStateManager.setActiveTexture(texture);.setFog(GlStateManager.FogMode.EXP);
            GlStateManager.setFogDensity(20.0F);

	    }*/


//.FogColors
