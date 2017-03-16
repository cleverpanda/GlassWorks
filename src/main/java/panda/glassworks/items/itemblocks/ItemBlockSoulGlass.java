package panda.glassworks.items.itemblocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import panda.glassworks.blocks.BlockSoulGlass;
import panda.glassworks.util.registry.BlockList;
import panda.glassworks.util.registry.IMeta;

public class ItemBlockSoulGlass extends ItemBlock implements IMeta {

	public ItemBlockSoulGlass(Block block) {
		super(block);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		setRegistryName(block.getRegistryName());
	}

	@Override
	public int getMetadata(int metadata) {
		return metadata;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		list.add(new ItemStack(itemIn, 1, 0));
		list.add(new ItemStack(itemIn, 1, 1));
	}

	public String getUnlocalizedName(ItemStack stack) {
		return stack.getMetadata() == 0 ? BlockList.SOUL_GLASS.getUnlocalizedName()
				: BlockList.SOUL_GLASS.getUnlocalizedName() + "_on";
	}

	@Override
	public int getMaxMeta() {
		return 1;
	}

	@Override
	public List<ModelResourceLocation> getMetaModelLocations() {
		List<ModelResourceLocation> list = new ArrayList<ModelResourceLocation>();
		Block block = this.getBlock();

		list.add(new ModelResourceLocation(block.getRegistryName(), "ison=false"));
		list.add(new ModelResourceLocation(block.getRegistryName(), "ison=true"));

		return list;
	}

	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		IBlockState state = world.getBlockState(pos);
		Block block = state.getBlock();

		if (!block.isReplaceable(world, pos)) {
			pos = pos.offset(facing);
		}

		if (stack.stackSize != 0 && player.canPlayerEdit(pos, facing, stack)
				&& world.canBlockBePlaced(this.block, pos, false, facing, null, stack)) {
			BlockSoulGlass block2 = (BlockSoulGlass) Block.getBlockFromItem(stack.getItem());
			IBlockState state2 = block2.getStateFromMeta(stack.getMetadata());
			if (placeBlockAt(stack, player, world, pos, facing, hitX, hitY, hitZ, state2)) {
				// SoundType soundtype = SoundType.WOOD;
				// world.playSound(player, pos, soundtype.getPlaceSound(),
				// SoundCategory.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F,
				// soundtype.getPitch() * 0.8F);
				--stack.stackSize;
			}

			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}
}
