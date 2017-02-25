package panda.glassworks.blocks;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import panda.glassworks.util.registry.BlockList;

public class TarFluid extends Fluid {

    public TarFluid()
    {
        super("tar", new ResourceLocation("glassworks:blocks/tar_still"), new ResourceLocation("glassworks:blocks/tar_flowing"));
        setViscosity(6000);
        setDensity(50000);
        setBlock(BlockList.TAR);
        FluidRegistry.addBucketForFluid(this);
    }

}