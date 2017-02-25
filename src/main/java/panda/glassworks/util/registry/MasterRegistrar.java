package panda.glassworks.util.registry;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;
import panda.glassworks.util.registry.recipe.RecipeRegistry;

public final class MasterRegistrar {

	
	
	public static void register(FMLPreInitializationEvent e, List<?> list){
		Iterator<?> iterator = list.iterator();
		
		while(iterator.hasNext()){
			Object k = iterator.next();
			if(k instanceof Block) {
				Block block = (Block) k;
				GameRegistry.register(block);
				block.setUnlocalizedName(GlassWorks.MODID + "." + block.getRegistryName().getResourcePath());
				if(Item.getItemFromBlock(block) == null) GameRegistry.register(new ItemBlock(block), block.getRegistryName());
			}
			else if (k instanceof Item){
				GameRegistry.register((Item) k);
				((Item) k).setUnlocalizedName(GlassWorks.MODID + "." + ((Item) k).getRegistryName().getResourcePath());
			}
			
		}
		if(e.getSide() == Side.CLIENT) registerModels(list);
	}
	
	public static void registerModels(List<?> list){
		Iterator<?> iterator = list.iterator();
		
		while(iterator.hasNext()){
			Object k = iterator.next();
			Item item = null;
			if (k instanceof Block){
				item = Item.getItemFromBlock((Block) k);
			}
			else if (k instanceof Item){
				item = (Item) k;
			}
			if(item instanceof IMeta){
				for(int i = 0; i <= ((IMeta) item).getMaxMeta(); i++){
					Map<Integer, ModelResourceLocation> map = ((IMeta) item).getMetaModelLocations();
					ModelLoader.setCustomModelResourceLocation(item, i, map.get(i));
				}
			}
			else if(item != null) ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
			if(k instanceof BlockFluidBase){
				handleFluidState((Block) k, ((Block) k).getRegistryName().getResourcePath());
			}
		}

	}
	
	public static void callRegistry(FMLPreInitializationEvent e){
		register(e, BlockList.getList());
		register(e, ItemList.getList());
		RecipeRegistry.register();
	}
	
	
	@SideOnly(Side.CLIENT)
	public static void registerColorHandlers(FMLInitializationEvent e){
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(new IItemColor(){
            public int getColorFromItemstack(ItemStack stack, int tintIndex){
                return tintIndex > 0 ? -1 : PotionUtils.getPotionColorFromEffectList(PotionUtils.getEffectsFromStack(stack));
            }}, ItemList.POTION_FLASK);}
	
    @SideOnly(Side.CLIENT)
    public static void handleFluidState(Block block, String name){
        ModelLoader.setCustomStateMapper(block, new StateMapperBase(){
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state){
                return new ModelResourceLocation(GlassWorks.MODID + ":fluids", name);
            }});}
	
}
