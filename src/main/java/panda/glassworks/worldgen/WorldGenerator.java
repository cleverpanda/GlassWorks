package panda.glassworks.worldgen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.fml.common.IWorldGenerator;
import panda.glassworks.GlassWorks;
import panda.glassworks.blocks.BlockSeaweed;
import panda.glassworks.util.registry.BlockList;

public class WorldGenerator implements IWorldGenerator {
	private int MIN = -1, MAX = -1, numTrees = -1, randX = -1, randZ = -1;

	private void makeTarpool(int chunkX, int chunkZ, Random random, World world, int min, int max) {
		int randX, randZ;
		int num;
		if (min == max) {
			num = min;
		} else {
			num = min + random.nextInt(max - min);
		}

		for (int i = 0; i < num; i++) {
			randX = chunkX * 16 + random.nextInt(16);
			randZ = chunkZ * 16 + random.nextInt(16);
			new WorldGenLakes(BlockList.TAR).generate(world, random, world.getHeight(new BlockPos(randX, 0, randZ)));

			if (world.getBlockState(world.getHeight(new BlockPos(randX, 0, randZ))).getBlock() == BlockList.TAR) {
				GlassWorks.log.info("generated tar at " + world.getHeight(new BlockPos(randX, 0, randZ)));
			}

		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {

		if (world.provider.getDimension() == 0) {

			// get the biome
			Biome biome = world.getBiome(new BlockPos(chunkX * 16, 64, chunkZ * 16));

			if (biome instanceof BiomeSwamp) {
				makeTarpool(chunkX, chunkZ, random, world, 0, 1);
			}

			if (GlassWorks.isBOPInstalled) {
				if (biome.getBiomeName() == "") {
					makeTarpool(chunkX, chunkZ, random, world, 0, 1);
				}
			}

			if (biome instanceof BiomeOcean) {
				makeSeaweedCluster(chunkX, chunkZ, random, world, 0, 2);
			}
		}

		if (world.provider.getDimension() == -1) {

			for (int i = 0; i < 16; ++i) {
				BlockPos pos = new BlockPos(chunkX * 16, 0, chunkZ * 16).add(random.nextInt(16), random.nextInt(108),
						random.nextInt(16));
				new WorldGenLakes(BlockList.TAR).generate(world, random, pos);
			}
		}

	}

	private void makeSeaweedCluster(int chunkX, int chunkZ, Random rand, World world, int min, int max) {

		int randX, randZ;
		int num;
		if (min == max) {
			num = min;
		} else {
			num = MathHelper.getRandomIntegerInRange(rand, min, max);
		}

		randX = chunkX * 16 + rand.nextInt(16);
		randZ = chunkZ * 16 + rand.nextInt(16);

		for (int i = 0; i < num; i++) {
			BlockPos pos = new BlockPos(randX, 0, randZ);
			makeSeaweed(world, rand, scatter(pos, rand, 1, 6));
		}
	}

	private void makeSeaweed(World world, Random rand, BlockPos pos) {
		BlockPos seaBedPos = new BlockPos(pos.getX(), 47, pos.getZ());
		while (world.getBlockState(seaBedPos).getMaterial().isLiquid()) {
			seaBedPos = seaBedPos.down();
		}
		int height = MathHelper.clamp_int(world.getSeaLevel() - seaBedPos.getY() - rand.nextInt(40), 1, 10);
		if (height == 1)
			height = rand.nextInt(6) + 1;

		if (BlockList.SEAWEED.canPlaceBlockAt(world, seaBedPos.up())) {
			// System.out.println("Trying seaweed gen at pos " +
			// seaBedPos.toString() + " which is a block known as " +
			// world.getBlockState(seaBedPos).getBlock().getRegistryName() + "
			// with height " + height);
			if (height == 1)
				world.setBlockState(seaBedPos.up(1), BlockList.SEAWEED.getDefaultState());
			else if (height > 1) {
				world.setBlockState(seaBedPos.up(1),
						BlockList.SEAWEED.getDefaultState().withProperty(BlockSeaweed.STAGE, 1));
				int k = 2;
				while (k < height && k > 1) {
					world.setBlockState(seaBedPos.up(k),
							BlockList.SEAWEED.getDefaultState().withProperty(BlockSeaweed.STAGE, 3));
					++k;
				}
				world.setBlockState(seaBedPos.up(height),
						BlockList.SEAWEED.getDefaultState().withProperty(BlockSeaweed.STAGE, 2));
			}
		}
	}

	private BlockPos scatter(BlockPos in, Random random, int minDist, int maxDist) {
		int scatterx, scatterz;
		if (minDist == maxDist) {
			scatterx = minDist;
			scatterz = minDist;
		} else {
			scatterx = minDist + random.nextInt(maxDist - minDist);
			scatterz = minDist + random.nextInt(maxDist - minDist);
		}

		int mx = random.nextBoolean() ? -1 : 1;
		int mz = random.nextBoolean() ? -1 : 1;

		return in.add(scatterx * mx, 0, scatterz * mz);
	}

}
