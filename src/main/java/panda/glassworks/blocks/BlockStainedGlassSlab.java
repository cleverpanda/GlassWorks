package panda.glassworks.blocks;

import java.util.List;
import java.util.Random;

import panda.glassworks.GlassWorks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStainedGlassSlab extends BlockSlab{

	public static final PropertyEnum<BlockStainedGlassSlab.EnumType> VARIANT = PropertyEnum.<BlockStainedGlassSlab.EnumType>create("variant", BlockStainedGlassSlab.EnumType.class);
	
	private Item slabItem;
	private String slabitemname;

	public BlockStainedGlassSlab(String si) {
		super(Material.GLASS);
		this.setSoundType(SoundType.GLASS);
		this.slabitemname = si;
		this.setHardness(0.3F);
		this.setLightOpacity(0);
		this.setCreativeTab(GlassWorks.GlassTab);
		this.useNeighborBrightness = true;

		IBlockState iblockstate = this.blockState.getBaseState();

		if (!this.isDouble()){
			iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
		}
			

		this.setDefaultState(iblockstate.withProperty(VARIANT, BlockStainedGlassSlab.EnumType.WHITE));
		setRegistryName("stained_glass_slab");
	}
	
	
	@Override
	public int damageDropped (IBlockState state){
		return ((BlockStainedGlassSlab.EnumType) state.getValue(VARIANT)).getID();
		
	}

	
	@Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
	@Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

	@Override
    protected boolean canSilkHarvest()
    {
        return true;
    }

	
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	
	@Override
	public final IBlockState getStateFromMeta (int meta){
		IBlockState blockstate = this.getDefaultState();
		blockstate = blockstate.withProperty(VARIANT,BlockStainedGlassSlab.EnumType.byMetadata(meta &7));//replace to 7
		
		if(!this.isDouble()){
			blockstate = blockstate.withProperty(HALF, (meta & 8 ) == 0 ? EnumBlockHalf.BOTTOM : EnumBlockHalf.TOP);
			}
		//GlassWorks.log.info(blockstate);
		return blockstate;
	}
	
	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
		for (BlockStainedGlassSlab.EnumType t : BlockStainedGlassSlab.EnumType.values())
	    list.add(new ItemStack(itemIn, 1, t.ordinal()));  
	}
	
	
	
	
	@Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face)
    {
        return false;
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = blockAccess.getBlockState(pos.offset(side));
        Block block = iblockstate.getBlock();

            if (blockState != iblockstate)
            {
                return true;
            }

            if (block == this)
            {
                return false;
            }

        return block == this ? false : super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }

	
	@Override
	public final int getMetaFromState(IBlockState state){
		int meta = ((BlockStainedGlassSlab.EnumType) state.getValue(VARIANT)).getID();
		if (!this.isDouble() && state.getValue(HALF) == EnumBlockHalf.TOP){
			meta |=8;
		}
		return meta;
	}
	

	@Override
	protected BlockStateContainer createBlockState() {
		return this.isDouble() ? new BlockStateContainer(this, new IProperty[] { VARIANT }) : new BlockStateContainer(this, new IProperty[] { HALF, VARIANT });
	}

	@Override
	public boolean isDouble() {
		return false;
	}

	@Override
	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	private Item getSlabItem() {
		if (this.slabItem == null) {
			FMLLog.severe("getting item for slab: %s, %s", this.getRegistryName().getResourceDomain(), this.slabitemname + "_slab");
			this.slabItem = Item.REGISTRY.getObject(new ResourceLocation(this.getRegistryName().getResourceDomain(), this.slabitemname + "_slab"));
		}

		return this.slabItem;
	}

	/**
	 * Get the Item that this Block should drop when harvested.
	 */
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return this.getSlabItem();
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return new ItemStack(this.getSlabItem());
	}
	
	
	
	@Override
	public String getUnlocalizedName(int meta){
		return this.getUnlocalizedName() + "_" + BlockStainedGlassSlab.EnumType.values()[meta];
	}
	
	@Override
	public Comparable<?> getTypeForItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return BlockStainedGlassSlab.EnumType.byMetadata(stack.getMetadata() & 7);//replace to 7
	}
	
	
	public enum EnumType implements IStringSerializable{

		WHITE(0, "white"), ORANGE(1, "orange"), MAGENTA(2, "magenta"), LIGHTBLUE(3, "light_blue"), YELLOW(4, "yellow"), LIME(5, "lime"), PINK(6, "pink"), GRAY(7, "gray");//,SILVER(8, "silver"); 
		//SILVER(8, "silver"), CYAN(9, "cyan"), PURPLE(10, "purple"), BLUE(11, "blue"), BROWN(12, "brown"), GREEN(13, "green"), RED(14, "red"), BLACK(15, "black");
		private static final BlockStainedGlassSlab.EnumType[] META_LOOKUP = new BlockStainedGlassSlab.EnumType[values().length];

	    private int ID;
	    private String name;
	   
	    private EnumType(int ID, String name) {
	        this.ID = ID;
	        this.name = name;
	            
	    }
	    @Override
	    public String getName() {
	        return name;
	    }
	    public int getID() {
	        return ID;
	    }
	    @Override
	    public String toString() {
	        return getName();
	    }
	    public static BlockStainedGlassSlab.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }
	    static
        {
            for (BlockStainedGlassSlab.EnumType types : values())
            {
                META_LOOKUP[types.getID()] = types;
            }
        }
	}


}
