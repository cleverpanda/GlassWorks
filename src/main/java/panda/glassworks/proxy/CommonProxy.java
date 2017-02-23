package panda.glassworks.proxy;

import panda.glassworks.GlassWorks;
import panda.glassworks.gui.BlowpipeGuiHandler;
import panda.glassworks.util.Message;
import panda.glassworks.util.MessageHandler;
import panda.glassworks.util.Network;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;


public class CommonProxy {

	
    public void preInit(FMLPreInitializationEvent e) {
    	
    }

    public void init(FMLInitializationEvent e) {
    	Network.INSTANCE.registerMessage(MessageHandler.class, Message.class, 0, Side.SERVER);
    	NetworkRegistry.INSTANCE.registerGuiHandler(GlassWorks.INSTANCE, new BlowpipeGuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    	
    }

	public void replaceBOPBucketTexture() {}
	public void registerFluidBlockRendering(Block block, String name) {}
}