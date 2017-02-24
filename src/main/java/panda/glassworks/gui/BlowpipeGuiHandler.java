package panda.glassworks.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


	public class BlowpipeGuiHandler implements IGuiHandler {
		public static final int BLOWPIPE_GUI = 0;
		
		@Override
		public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
			  //NOT BEING CALLED
			if (ID == BLOWPIPE_GUI){
				
				return new ContainerBlowpipe(player.inventory, world, player.getPosition());
				
			}
			return null;
			
		}

		@Override
		public Object getClientGuiElement(int ID, EntityPlayer player,World world, int x, int y, int z) {
			System.out.println(BLOWPIPE_GUI);
			if (ID == BLOWPIPE_GUI){
				
				return new GuiBlowpipe(player.inventory, world);
			}

	        return null;
		}
	}
