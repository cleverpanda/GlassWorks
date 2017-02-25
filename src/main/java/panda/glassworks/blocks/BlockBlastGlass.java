package panda.glassworks.blocks;

import panda.glassworks.GlassWorks;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockBlastGlass extends BlockGlass{

	public BlockBlastGlass() {
		super(Material.GLASS, false);
		this.setHardness(0.1F);
		this.setSoundType(SoundType.GLASS);
		this.setResistance(10F);
		this.setCreativeTab(GlassWorks.GlassTab);
		setRegistryName("blast_glass");
	}
	
	

}
