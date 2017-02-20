package panda.glassworks;

import panda.glassworks.worldgen.WorldGenerator;
import panda.glassworks.gui.BlowpipeGuiHandler;
import panda.glassworks.init.GlassBlocks;
import panda.glassworks.init.GlassItems;
import panda.glassworks.init.Recipes;
import panda.glassworks.proxy.CommonProxy;
import panda.glassworks.proxy.ProxyClient;
import panda.glassworks.util.BlockBreakHandler;
import panda.glassworks.util.BucketHandler;
import panda.glassworks.util.ViewRenderHandler;
//import panda.glassworks.util.ConfigurationHandler;
import panda.glassworks.versionchecker.VersionChecker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = GlassWorks.MODID, name = GlassWorks.NAME, version = GlassWorks.VERSION)


public class GlassWorks
{
	public static final String MODID = "glassworks";
	public static final String VERSION = "0.1";
	//fixed issue regarding other mod's tree drops
	//fixed tree grid generation
	//cleaned up
	public static final String NAME = "Glass Works";
	@SidedProxy(serverSide = "panda.glassworks.proxy.ProxyServer", clientSide = "panda.glassworks.proxy.ProxyClient")
	public static CommonProxy proxy;

	public static boolean haveWarnedVersionOutOfDate;
	public static VersionChecker versionChecker;

	public static ProxyClient PROXY = null;
	
	@Mod.Instance(MODID)
	public static GlassWorks INSTANCE;
	
	public static Logger log;
	
	public static boolean isBOPInstalled = false;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		INSTANCE = this;
		log = LogManager.getLogger(this.NAME);
		
		MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
		MinecraftForge.EVENT_BUS.register(new BucketHandler());
		MinecraftForge.EVENT_BUS.register(new ViewRenderHandler());
		//MinecraftForge.EVENT_BUS.register(new OnJoinWorldHandler());
		
		GlassItems.init();
		GlassBlocks.init();
		
		proxy.preInit(event);
		//ConfigurationHandler.init(event);

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{ 
		Recipes.init();
		proxy.init(event);
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
		
		

		isBOPInstalled = Loader.isModLoaded("biomesoplenty");


	}  
	
	public static final CreativeTabs GlassTab = new CreativeTabs("glassworks") {
	    @Override public Item getTabIconItem() {
	        return GlassItems.MOLTEN_GLASS;
	    }
	};


}