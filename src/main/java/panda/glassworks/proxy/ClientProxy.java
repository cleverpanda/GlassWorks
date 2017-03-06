package panda.glassworks.proxy;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import panda.glassworks.gui.SpyglassGui;
import panda.glassworks.util.events.SpyglassHandler;
import panda.glassworks.util.registry.MasterRegistrar;

public class ClientProxy extends CommonProxy{

	@Override
    public void preInit(FMLPreInitializationEvent e) {	
		super.preInit(e);
    }
	
	public void init(FMLInitializationEvent e) {
		super.init(e);
		MasterRegistrar.registerColorHandlers(e);
	}

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
        
        MinecraftForge.EVENT_BUS.register(new SpyglassHandler(new SpyglassGui(Minecraft.getMinecraft())));
    }
    


}
