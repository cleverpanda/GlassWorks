package panda.glassworks.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import panda.glassworks.GlassWorks;
import panda.glassworks.gui.BlowpipeGuiHandler;
import panda.glassworks.util.network.Message;
import panda.glassworks.util.network.MessageHandler;
import panda.glassworks.util.network.Network;
import panda.glassworks.util.registry.MasterRegistrar;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		MasterRegistrar.callRegistry(e);
	}

	public void init(FMLInitializationEvent e) {
		Network.INSTANCE.registerMessage(MessageHandler.class, Message.class, 0, Side.SERVER);
		NetworkRegistry.INSTANCE.registerGuiHandler(GlassWorks.INSTANCE, new BlowpipeGuiHandler());
	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}