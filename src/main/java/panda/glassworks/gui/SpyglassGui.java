package panda.glassworks.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class SpyglassGui extends Gui {

	private final static ResourceLocation overlay = new ModelResourceLocation("glassworks:textures/gui/spyglass.png");
	private final static int OVERLAY_WIDTH = 256;
	private final static int OVERLAY_HEIGHT = 256;

	private Minecraft mc;

	public SpyglassGui(Minecraft mc) {
		this.mc = mc;
	}

	public void renderOverlay(int screenWidth, int screenHeight) {
		ScaledResolution scaledresolution = new ScaledResolution(mc);
		int width = scaledresolution.getScaledWidth();
		int height = scaledresolution.getScaledHeight();

		float scalefact1 = (float) screenHeight / (float) OVERLAY_HEIGHT;
		float scalefact2 = (float) screenWidth / (float) OVERLAY_WIDTH;
		float scalefact = scalefact1 > scalefact2 ? scalefact2 : scalefact1;

		int xPos = screenWidth / 2 - (int) ((scalefact * OVERLAY_WIDTH) / 2);
		int yPos = screenHeight / 2 - (int) ((scalefact * OVERLAY_HEIGHT) / 2);
		// xPos /= scalefact;
		// yPos /= scalefact;
		// System.out.println(scalefact*OVERLAY_HEIGHT+", "+screenHeight);
		// System.out.println(scalefact*OVERLAY_WIDTH+", "+screenWidth);
		
		GlStateManager.pushAttrib();
		GL11.glPushMatrix();

		mc.renderEngine.bindTexture(overlay);
		GL11.glScalef(scalefact, scalefact, 1);
		
		//GlStateManager.colorMask(true, true, true, false);

		GlStateManager.disableDepth();
		
		if (scalefact1 > scalefact2) {
			System.out.println("allign left");
			this.drawTexturedModalRect(0, yPos, 0, 0, 256, 256);
			this.drawRect(0, 0,screenWidth , yPos);
			this.drawRect(0, yPos+OVERLAY_HEIGHT, screenWidth, screenHeight);
			
		} else {
			System.out.println("allign top");
			this.drawTexturedModalRect(xPos, 0, 0, 0, 256, 256);
			this.drawRect(0, 0, xPos, screenHeight);
			this.drawRect(xPos+OVERLAY_WIDTH, 0, screenWidth, screenHeight);
		}
		
		// System.out.println(mc.displayWidth +"X"+mc.displayHeight+" , "+width
		// +"X"+height);

		GlStateManager.enableDepth();
		GL11.glPopMatrix();
		GlStateManager.popAttrib();
	}

	public static void drawRect(int left, int top, int right, int bottom) {
		System.out.println("Doot");
		if (left < right) {
			int i = left;
			left = right;
			right = i;
		}

		if (top < bottom) {
			int j = top;
			top = bottom;
			bottom = j;
		}

		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);
		GlStateManager.color(0, 0, 0, 1f);
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
		vertexbuffer.pos((double) left, (double) bottom, 0.0D).endVertex();
		vertexbuffer.pos((double) right, (double) bottom, 0.0D).endVertex();
		vertexbuffer.pos((double) right, (double) top, 0.0D).endVertex();
		vertexbuffer.pos((double) left, (double) top, 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}
}
