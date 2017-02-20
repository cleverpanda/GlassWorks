package panda.glassworks.proxy;

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelDynBucket;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import panda.glassworks.GlassWorks;
import panda.glassworks.blocks.BlockGlassSlab;
import panda.glassworks.blocks.BlockStainedGlassSlab;
import panda.glassworks.blocks.TarFluid;
import panda.glassworks.gui.BlowpipeGuiHandler;
import panda.glassworks.init.GlassBlocks;
import panda.glassworks.init.GlassItems;

public class ProxyClient extends CommonProxy{

	
	public void init(FMLInitializationEvent e) {
		super.init(e);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor()
        {
            public int getColorFromItemstack(ItemStack stack, int tintIndex)
            {
                return tintIndex > 0 ? -1 : PotionUtils.getPotionColorFromEffectList(PotionUtils.getEffectsFromStack(stack));
            }
        }, new Item[] {GlassItems.POTION_FLASK});
		
		for (final String blockName : GlassBlocks.getBlockRegistry().keySet()) {
			final Block block = GlassBlocks.getBlockByName(blockName);

			if(block instanceof BlockGlassSlab || block instanceof BlockStainedGlassSlab){
				Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, blockName), "inventory"));
			}
		}
		
	}
	
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        for (final String itemName : GlassItems.getItemRegistry().keySet()) {
			final Item item = GlassItems.getItemByName(itemName);
			
			if(item == GlassItems.MOLTEN_GLASS){
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "type=glassmolten"));
				ModelLoader.setCustomModelResourceLocation(item, 1, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "type=crystalglassmolten"));
				ModelLoader.setCustomModelResourceLocation(item, 2, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "type=obsidianglassmolten"));
				//ModelLoader.setCustomModelResourceLocation(item, 3, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "type=crystalglassmoltencool"));
			}else
			if(item == GlassItems.FANCY_STAINED_GLASS_ITEM){
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(GlassBlocks.FANCY_STAINED_GLASS), 0, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, "fancy_stained_glass"), "color=white"));
				//ModelLoader.setCustomModelResourceLocation(GlassBlocks.FANCY_STAINED_GLASS,  0, "color=white");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  1, "color=orange");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  2, "color=magenta");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  3, "color=light_blue");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  4, "color=yellow");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  5, "color=lime");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  6, "color=pink");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  7, "color=gray");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  8, "color=silver");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  9, "color=cyan");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  10, "color=purple");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  11, "color=blue");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  12, "color=brown");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  13, "color=green");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  14, "color=red");
				this.registerItemBlockModel(GlassBlocks.FANCY_STAINED_GLASS,  15, "color=black");
				
			}else
			if(item == GlassItems.STAINED_GLASS_SLAB){
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "variant=white"));
				ModelLoader.setCustomModelResourceLocation(item, 1, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "variant=orange"));
				ModelLoader.setCustomModelResourceLocation(item, 2, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "variant=magenta"));
				ModelLoader.setCustomModelResourceLocation(item, 3, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "variant=light_blue"));
				ModelLoader.setCustomModelResourceLocation(item, 4, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "variant=yellow"));
				ModelLoader.setCustomModelResourceLocation(item, 5, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "variant=lime"));
				ModelLoader.setCustomModelResourceLocation(item, 6, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "variant=pink"));
				ModelLoader.setCustomModelResourceLocation(item, 7, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "variant=gray"));
			}else
			if(item == GlassItems.POTION_FLASK){
				ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "dose=4"));
				ModelLoader.setCustomModelResourceLocation(item, 1, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "dose=3"));
				ModelLoader.setCustomModelResourceLocation(item, 2, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "dose=2"));
				ModelLoader.setCustomModelResourceLocation(item, 3, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "dose=1"));
			}else
				if(item != GlassItems.FANCY_STAINED_GLASS_ITEM){
					ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, itemName), "inventory"));
				}
			

		}
		

		for (final String blockName : GlassBlocks.getBlockRegistry().keySet()) {
			final Block block = GlassBlocks.getBlockByName(blockName);
			if (block instanceof BlockDoor)
				continue; // do not add door blocks
			if(!(block instanceof BlockGlassSlab)&&!(block instanceof BlockStainedGlassSlab)){
				ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(GlassWorks.MODID, blockName), "inventory"));
			}
				
			
		}
		
		
		
		
    }
	
	private void registerItemBlockModel(Block blockIn, int meta, String fullVariant)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(blockIn), meta,new ModelResourceLocation(blockIn.getRegistryName(), fullVariant));
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);

        
    }
    
    @Override
    public void registerFluidBlockRendering(Block block, String name) 
    {
        final ModelResourceLocation fluidLocation = new ModelResourceLocation(GlassWorks.MODID + ":fluids", name);

        // use a custom state mapper which will ignore the LEVEL property
        ModelLoader.setCustomStateMapper(block, new StateMapperBase()
        {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state)
            {
                return fluidLocation;
            }
        });
    }

}
