package panda.glassworks.proxy;

import panda.glassworks.GlassWorks;
import panda.glassworks.gui.BlowpipeGuiHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;


public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    	
    }

    public void init(FMLInitializationEvent e) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(GlassWorks.INSTANCE, new BlowpipeGuiHandler());
    	
    }

    public void postInit(FMLPostInitializationEvent e) {
    	
    }

	public void replaceBOPBucketTexture() {}
	public void registerFluidBlockRendering(Block block, String name) {}
}