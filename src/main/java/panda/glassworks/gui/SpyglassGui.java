package panda.glassworks.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import java.text.DecimalFormat;


public class SpyglassGui extends Gui {

  private final static ResourceLocation overlay = new ModelResourceLocation("glassworks:textures/gui/spyglass.png");

  private final static int OVERLAY_WIDTH = 256;
  private final static int OVERLAY_HEIGHT = 256;


  private Minecraft mc;

  public SpyglassGui(Minecraft mc) {
    this.mc = mc;
  }


  public void renderOverlay(int screenWidth, int screenHeight) {

    World world = mc.theWorld;
    EntityPlayer player = mc.thePlayer;

    /* Saving the current state of OpenGL so that I can restore it when I'm done */
    GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
    GL11.glPushMatrix();

      /* Set the rendering color to white */
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

      /* This method tells OpenGL to draw with the custom texture */
    mc.renderEngine.bindTexture(overlay);

    // we will draw the status bar just above the hotbar.
    //  obtained by inspecting the vanilla hotbar rendering code
    final int vanillaExpLeftX = screenWidth / 2 - OVERLAY_WIDTH; // leftmost edge of the experience bar
    final int vanillaExpTopY = screenHeight /2 - OVERLAY_HEIGHT;  // top of the experience bar

      /* Shift our rendering origin to just above the experience bar
       * The top left corner of the screen is x=0, y=0
       */
    GL11.glTranslatef(vanillaExpLeftX-OVERLAY_WIDTH, vanillaExpTopY - OVERLAY_HEIGHT, 0);

      /* Draw a part of the image file at the current position
       *
       * The first two arguments are the x,y position that you want to draw the texture at
       * (with respect to the current transformations).
       *
       * The next four arguments specify what part of the image file to draw, in the order below:
       *
       *   1. Left-most side
       *   2. Top-most side
       *   3. Right-most side
       *   4. Bottom-most side
       *
       * The units of these four arguments are pixels in the image file. These arguments will form a
       * rectangle, which is then "cut" from your image and used as the texture
       *
       * This line draws the background of the custom bar
       */
    drawTexturedModalRect(0, 0, 0, 0, OVERLAY_WIDTH, OVERLAY_HEIGHT);


     
    GL11.glPopMatrix();
    GL11.glPopAttrib();
  }
}