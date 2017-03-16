package panda.glassworks.blocks;

import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.GlassWorks;

public class BlockBlastGlass extends BlockGlass {

	public BlockBlastGlass() {
		super(Material.GLASS, false);
		this.setHardness(0.3F);
		this.setSoundType(SoundType.GLASS);
		this.setResistance(10F);
		this.setCreativeTab(GlassWorks.GlassTab);
		this.setLightOpacity(40);
		this.useNeighborBrightness = true;
		setRegistryName("blast_glass");
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

}
