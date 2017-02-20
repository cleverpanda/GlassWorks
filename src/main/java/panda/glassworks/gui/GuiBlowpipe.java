package panda.glassworks.gui;

import io.netty.buffer.Unpooled;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import panda.glassworks.init.Recipes;
import panda.glassworks.util.GlassBlowingRecipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBlowpipe extends GuiContainer
{
    private static final ResourceLocation BLOWPIPE_GUI_TEXTURES = new ResourceLocation("glassworks:textures/gui/blowpipe.png");

    /** The button which proceeds to the next available merchant recipe. */
    private GuiBlowpipe.Button nextButton;
    /** Returns to the previous Merchant recipe if one is applicable. */
    private GuiBlowpipe.Button previousButton;
    
    private int selectedRecipe;
    
    public GuiBlowpipe(InventoryPlayer playerInv, World worldIn)
    {
        this(playerInv, worldIn, BlockPos.ORIGIN);
        
    }

    @Override
	public void initGui() {
		super.initGui();
		this.nextButton = new GuiBlowpipe.Button(1, guiLeft + 136, guiTop +4, true);
        this.previousButton = new GuiBlowpipe.Button(2, guiLeft + 136, guiTop +44, false);
        
        
		this.buttonList.add(nextButton);
        this.buttonList.add(previousButton);
	}

	public GuiBlowpipe(InventoryPlayer playerInv, World worldIn, BlockPos blockPosition)
    {
    	super(new ContainerBlowpipe(playerInv, worldIn, blockPosition));
        this.xSize = 176;
        this.ySize = 140;
        this.guiLeft = Math.abs((this.width - this.xSize) / 2);
        this.guiTop = Math.abs((this.height - this.ySize) / 2);
        this.selectedRecipe = 0;
            
    }

	@Override
	public void updateScreen() {
		super.updateScreen();
		
		if(hasInput()){
			List<ItemStack> recipelist = GlassBlowingRecipes.getGlassBlowingResultList(inputStack());
			if (recipelist != null)
	        {
				this.nextButton.enabled = this.selectedRecipe < recipelist.size()-1;
		        this.previousButton.enabled = this.selectedRecipe > 0;
	        }
		}else{
			this.nextButton.enabled = false;
        	this.previousButton.enabled = false;
        	this.selectedRecipe = 0;
			
		}
		
        
        ((ContainerBlowpipe)this.inventorySlots).onCraftMatrixChanged();
	}
	
	boolean hasInput(){
		return ((ContainerBlowpipe)this.inventorySlots).getInventory().get(0) != null;
	}
	
	ItemStack inputStack(){
		return ((ContainerBlowpipe)this.inventorySlots).getInventory().get(0);
	}
	
	
	
	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		//System.out.println(this.selectedRecipe);
		super.mouseClicked(mouseX, mouseY, mouseButton);

	}
	
	
	//Button Calls
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
    {
        //System.out.println("TOP BUTTON PRESSED");
        if (button.id ==1)
        {
 
            if(hasInput()){
            	
            	++this.selectedRecipe;
    			
            	List<ItemStack> recipelist = GlassBlowingRecipes.getGlassBlowingResultList(inputStack());
    			if (recipelist != null )
    	        {
    				if(!recipelist.isEmpty()){
    					
    					if(this.selectedRecipe > GlassBlowingRecipes.getGlassBlowingResultList(inputStack()).size()-1){
    						this.selectedRecipe = GlassBlowingRecipes.getGlassBlowingResultList(inputStack()).size();
    					}
    				}				
    	        }
    		}else{
    			this.selectedRecipe = 0;
    		}

        }
        else if (button.id ==2)
        {
        	if(hasInput()){
        		--this.selectedRecipe;
        		if (this.selectedRecipe < 0)
                {
                    this.selectedRecipe = 0;
                }
        	}else{
        		this.selectedRecipe = 0;
        	}
            //System.out.println("BOTTOM BUTTON PRESSED");

        }
        System.out.println(selectedRecipe);

        ((ContainerBlowpipe)this.inventorySlots).setCurrentRecipeIndex(this.selectedRecipe);
       
    }

	/**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRendererObj.drawString(I18n.format("container.crafting", new Object[0]), 9, 9, 4210752);
        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize -95, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BLOWPIPE_GUI_TEXTURES);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
    }

    @SideOnly(Side.CLIENT)
    static class Button extends GuiButton
        {
            private final boolean up;

            public Button(int buttonID, int x, int y, boolean isUp)
            {
                super(buttonID, x, y, 13, 9, "");
                this.width = 19;
                this.height = 12;
                this.up = isUp;
            }
            
            @Override
			public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {

				return super.mousePressed(mc, mouseX, mouseY);
			}

			@Override
			public void playPressSound(SoundHandler soundHandlerIn) {
				// TODO Auto-generated method stub
				super.playPressSound(soundHandlerIn);
			}

			/**
             * Draws this button to the screen.
             */
            public void drawButton(Minecraft mc, int mouseX, int mouseY)
            {

            	if (this.visible)
                {
            		mc.getTextureManager().bindTexture(BLOWPIPE_GUI_TEXTURES);
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    boolean flag = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
                    int j = 0;
                    int i = 176;
                    int hOffset = this.xPosition; //136;
                    int vOffset = this.yPosition;//4;
                    
                    
                    if (!this.enabled) //disabled button
                    {
                        j += this.height * 2;
                        //System.out.println((mouseX >= this.xPosition )+","+(mouseY >= this.yPosition)+","+(mouseX < this.xPosition + this.width)+","+(mouseY < this.yPosition + this.height));
                    }
                    else 
                    if (flag)//mouse hover
                    {
                        j += this.height;
                        
                        
                    }

                    if (!this.up)
                    {
                        i += this.width;
                    }

                    if(this.up){
                    	this.drawTexturedModalRect(hOffset, vOffset, i, j, this.width, this.height);
                    }else{
                    	this.drawTexturedModalRect(hOffset, vOffset, i, j, this.width, this.height);
                    }
                }
            }
        }
}