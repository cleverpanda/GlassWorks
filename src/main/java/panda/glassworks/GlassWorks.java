package panda.glassworks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import panda.glassworks.proxy.CommonProxy;
import panda.glassworks.util.events.BlockBreakHandler;
import panda.glassworks.util.events.BucketHandler;
import panda.glassworks.util.events.ItemModelHandler;
import panda.glassworks.util.events.ViewRenderHandler;
import panda.glassworks.util.registry.ItemList;
import panda.glassworks.worldgen.WorldGenerator;

@Mod(modid = GlassWorks.MODID, name = GlassWorks.NAME, version = GlassWorks.VERSION, dependencies = "after:biomesoplenty")

public class GlassWorks {
	public static final String MODID = "glassworks";
	public static final String VERSION = "0.20.2";
	public static final String NAME = "Glass Works";

	@SidedProxy(serverSide = "panda.glassworks.proxy.ServerProxy", clientSide = "panda.glassworks.proxy.ClientProxy")
	public static CommonProxy proxy;

	@Mod.Instance(MODID)
	public static GlassWorks INSTANCE;

	public static Logger log = LogManager.getLogger(NAME);

	public static boolean isBOPInstalled = Loader.isModLoaded("biomesoplenty");

	@EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
		MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
		MinecraftForge.EVENT_BUS.register(new BucketHandler());
		MinecraftForge.EVENT_BUS.register(new ViewRenderHandler());
		MinecraftForge.EVENT_BUS.register(new ItemModelHandler());

	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
	}

	public static final CreativeTabs GlassTab = new CreativeTabs(GlassWorks.MODID) {
		@Override
		public Item getTabIconItem() {
			return ItemList.MOLTEN_GLASS;
		}

	};

}