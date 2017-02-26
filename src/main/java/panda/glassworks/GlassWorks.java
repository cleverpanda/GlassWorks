package panda.glassworks;

import panda.glassworks.worldgen.WorldGenerator;
import panda.glassworks.gui.BlowpipeGuiHandler;
import panda.glassworks.proxy.CommonProxy;
import panda.glassworks.proxy.ClientProxy;
import panda.glassworks.util.events.BlockBreakHandler;
import panda.glassworks.util.events.BucketHandler;
import panda.glassworks.util.events.ViewRenderHandler;
import panda.glassworks.util.registry.ItemList;
import panda.glassworks.util.registry.recipe.RecipeRegistry;
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

@Mod(modid = GlassWorks.MODID, name = GlassWorks.NAME, version = GlassWorks.VERSION, dependencies = "after:biomesoplenty")


public class GlassWorks
{
	public static final String MODID = "glassworks";
	public static final String VERSION = "0.1";
	public static final String NAME = "Glass Works";
	
	@SidedProxy(serverSide = "panda.glassworks.proxy.ServerProxy", clientSide = "panda.glassworks.proxy.ClientProxy")
	public static CommonProxy proxy;
	
	@Mod.Instance(MODID)
	public static GlassWorks INSTANCE;
	
	public static Logger log = LogManager.getLogger(NAME);
	
	public static boolean isBOPInstalled = Loader.isModLoaded("biomesoplenty");
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{
		proxy.preInit(event);
		MinecraftForge.EVENT_BUS.register(new BlockBreakHandler());
		MinecraftForge.EVENT_BUS.register(new BucketHandler());
		MinecraftForge.EVENT_BUS.register(new ViewRenderHandler());

	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{ 
		proxy.init(event);
		GameRegistry.registerWorldGenerator(new WorldGenerator(), 0);
	}  
	
	public static final CreativeTabs GlassTab = new CreativeTabs("glassworks") {
	    @Override public Item getTabIconItem() {
	        return ItemList.MOLTEN_GLASS;
	    }
	    
	};


}