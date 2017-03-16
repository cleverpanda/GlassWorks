package panda.glassworks.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.IFluidBlock;
import panda.glassworks.GlassWorks;
import panda.glassworks.util.registry.BlockList;

public class WorldGenTarPool extends WorldGenerator {

	private static final List<Block> placeables = new ArrayList<Block>();

	public WorldGenTarPool() {
		placeables.add(Blocks.DIRT);
		placeables.add(Blocks.GRASS);
		placeables.add(Blocks.GRAVEL);
		placeables.add(Blocks.STONE);
		placeables.add(Blocks.SAND);
		placeables.add(Blocks.AIR);
	}

	@Override
	public boolean generate(World world, Random random, BlockPos pos) {
		GlassWorks.log.info("################ @" + pos.toString());
		for (int i = 1; i < 3; i++) {
			if (placeables.contains(world.getBlockState(pos.add(0, -i, 0)).getBlock())) {

				if (this.generateRandomShapeAt(world, random, pos.add(0, -i, 0), 6)) {
					GlassWorks.log.info("Generated Tar @" + pos.toString());
					return true;
				}
			} else {
				GlassWorks.log.info("not in list " + world.getBlockState(pos.add(0, -i, 0)));
			}
		}
		return false;
	}

	private boolean generateRandomShapeAt(World world, Random r, BlockPos pos, int max) {
		Block id = BlockList.TAR;

		ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();

		boolean gen = false;
		boolean circular = false;
		if (r.nextDouble() < 0.5) {
			gen = this.generateCircularPool(world, pos, r, id, blocks);
			circular = true;
		} else {
			gen = this.generateEllipticalPool(world, pos, r, id, blocks);
			circular = false;
		}

		if (gen) {
			for (int i = 0; i < blocks.size(); i++) {
				BlockPos c = blocks.get(i);
				if (c.getY() <= pos.getY())
					world.setBlockState(c, id.getDefaultState());
				else {
					world.setBlockToAir(c);
					Block bid = world.getBlockState(c.down()).getBlock();
					if (bid == Blocks.DIRT) {
						world.setBlockState(c.down(), Blocks.GRASS.getDefaultState());
					}
				}
			}
			GlassWorks.log.info("Generator " + (circular ? "Circular" : "elliptical") + " PASSED");
		} else {
			GlassWorks.log.info("Generator " + (circular ? "Circular" : "elliptical") + " failed");
		}

		return gen;
	}

	private boolean generateCircularPool(World world, BlockPos pos, Random rand, Block id, ArrayList<BlockPos> blocks) {
		int d = 2 + rand.nextInt(3);
		int[] r = { d, d - 1, d - 3 };
		for (int i = 0; i < 3; i++) {
			for (int j = -r[i]; j <= r[i]; j++) {
				for (int k = -r[i]; k <= r[i]; k++) {
					double dd = new BlockPos(j, 0, k).getDistance(0, 0, 0);
					if (dd <= r[i] + 0.5) {
						// world.setBlock(x+j, y-i, z+k, id);
						if (this.isValidBlock(world, pos.add(j, -i, k))) {
							blocks.add(pos.add(j, -i, k));
						} else
							return false;
					} else {
						GlassWorks.log.info("distance of " + dd + "is greater than " + (r[i] + 0.5));
					}
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = -r[i] - 1; j <= r[i] + 1; j++) {
				for (int k = -r[i] - 1; k <= r[i] + 1; k++) {
					double dd = new BlockPos(j, 0, k).getDistance(0, 0, 0);
					if (dd <= r[i] + 1.5) {
						// world.setBlock(x+j, y+i+1, z+k, 0);
						if (world.getBlockState(pos.add(j, i + 1, k)).getBlock() instanceof BlockLiquid) {
							GlassWorks.log.info("Block at " + pos.add(j, i + 1, k) + " is liquid");
							return false;
						}
						blocks.add(pos.add(j, i + 1, k));
					} else {
						GlassWorks.log.info("distance of " + dd + "is greater than " + (r[i] + 1.5));
					}
				}
			}
		}
		return true;
	}

	private boolean generateEllipticalPool(World world, BlockPos pos, Random rand, Block id,
			ArrayList<BlockPos> blocks) {
		int d = 2 + rand.nextInt(3);
		int[] r = { d, d - 1, d - 3 };
		double sc = 0.5 + rand.nextDouble() * 0.5;
		for (int i = 0; i < 3; i++) {
			for (int j = -r[i]; j <= r[i]; j++) {
				for (int k = (int) Math.floor(-r[i] * sc); k <= r[i] * sc; k++) {
					double dd = new BlockPos(j, 0, k).getDistance(0, 0, 0);
					if (dd <= r[i] + 0.5) {
						// world.setBlock(x+j, y-i, z+k, id);
						if (this.isValidBlock(world, pos.add(j, -i, k)))
							blocks.add(pos.add(j, -i, k));
						else
							return false;
					} else {
						GlassWorks.log.info("distance of " + dd + "is greater than " + (r[i] + 0.5));
					}
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = -r[i] - 1; j <= r[i] + 1; j++) {
				for (int k = (int) Math.floor(-r[i] * sc - 1); k <= r[i] * sc + 1; k++) {
					double dd = new BlockPos(j, 0, k).getDistance(0, 0, 0);
					if (dd <= r[i] + 1.5) {
						// world.setBlock(x+j, y+i+1, z+k, 0);
						if (world.getBlockState(pos.add(j, i + 1, k)).getBlock() instanceof BlockLiquid) {
							GlassWorks.log.info("Block at " + pos.add(j, i + 1, k) + " is liquid");
							return false;
						}
						blocks.add(pos.add(j, i + 1, k));
					} else {
						GlassWorks.log.info("distance of " + dd + "is greater than " + (r[i] + 1.5));
					}
				}
			}
		}
		return true;
	}

	private boolean isValidBlock(World world, BlockPos pos) {
		Block id = BlockList.TAR;
		Block idx = world.getBlockState(pos).getBlock();
		if (softBlocks(world, pos)) {
			GlassWorks.log.info("Block " + idx.getUnlocalizedName() + " is not soft");
			return false;
		}

		EnumFacing[] dirs = EnumFacing.values();
		for (int i = 0; i < 6; i++) {
			EnumFacing dir = dirs[i];

			BlockPos dp = pos.add(dir.getFrontOffsetX(), dir.getFrontOffsetY(), dir.getFrontOffsetZ());
			Block bid = world.getBlockState(dp).getBlock();
			if (dir == EnumFacing.UP) {

				if (bid != Blocks.AIR && bid instanceof BlockLiquid || bid == id)
					return false;
			} else {
				boolean soft = softBlocks(world, dp);
				boolean ender = world.getBlockState(dp).getBlock() == id;
				if (soft) {
					GlassWorks.log.info("Block " + bid.getUnlocalizedName() + " is not soft");
					return false;
				}
			}
		}
		return true;
	}

	public static boolean softBlocks(World world, BlockPos pos) {
		Block b = world.getBlockState(pos).getBlock();

		if (b == Blocks.PISTON_EXTENSION)
			return false;
		if (b.isReplaceable(world, pos))
			return true;
		if (world.getBlockState(pos).getMaterial() == Material.AIR || b == Blocks.AIR)
			return true;
		if (b == Blocks.VINE)
			return true;
		if (b instanceof BlockLiquid || b instanceof BlockFluidBase || b instanceof IFluidBlock)
			return true;
		if (b == Blocks.DEADBUSH || b == Blocks.SNOW || b == Blocks.TALLGRASS || b == Blocks.SNOW_LAYER) {
			return true;
		}
		return false;

	}
}

// Instantiable.Data.BlockStruct.BlockArray;
// Instantiable.Data.Immutable.Coordinate;
