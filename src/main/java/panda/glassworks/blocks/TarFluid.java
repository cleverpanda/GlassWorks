package panda.glassworks.blocks;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class TarFluid extends Fluid {
    
    public static final String name = "tar";
    public static final TarFluid instance = new TarFluid();

    public TarFluid()
    {
        super(name, new ResourceLocation("glassworks:blocks/Tar_Still"), new ResourceLocation("glassworks:blocks/Tar_Flowing"));
    }

}