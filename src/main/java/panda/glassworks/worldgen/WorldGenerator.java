package panda.glassworks.worldgen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeOcean;
import net.minecraft.world.biome.BiomeSwamp;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraftforge.fml.common.IWorldGenerator;
import panda.glassworks.GlassWorks;
import panda.glassworks.util.registry.BlockList;

public class WorldGenerator implements IWorldGenerator{
	private int MIN=-1,MAX=-1,numTrees=-1,randX=-1,randZ=-1;

	private void makeTarpool(int chunkX, int chunkZ,Random random, World world,int min, int max){
		int randX,randZ;
		int num;
		if(min==max){
			num = min;
		}else{
			num = min + random.nextInt(max - min);
		}
	
		
		for(int i = 0; i < num; i++)
		{
			randX = chunkX*16 + random.nextInt(16);
			randZ = chunkZ*16 + random.nextInt(16);
			new WorldGenLakes(BlockList.TAR).generate(world,random,world.getHeight(new BlockPos(randX,0,randZ)));

			if(world.getBlockState(world.getHeight(new BlockPos(randX,0,randZ))).getBlock()==BlockList.TAR){
				GlassWorks.log.info("generated tar at "+world.getHeight(new BlockPos(randX,0,randZ)));
			}
			
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
		if(world.provider.getDimension() == 0){

			// get the biome
			Biome biome = world.getBiome(new BlockPos(chunkX*16, 64, chunkZ*16));

			
			if(biome instanceof BiomeSwamp)
			{		
				makeTarpool(chunkX, chunkZ, random, world, 0, 1);
			}
			
			if(GlassWorks.isBOPInstalled){
				if(biome.getBiomeName() == "")
				{		
					makeTarpool(chunkX, chunkZ, random, world, 0, 1);
				}
			}
			
			
			if(biome instanceof BiomeOcean)
			{		
				makeSeaweedCluster(chunkX, chunkZ, random, world, 0, 2);
			}
		}
		
		if(world.provider.getDimension() == -1){
			
			for (int i = 0; i < 16; ++i)
	        {
				BlockPos pos = new BlockPos(chunkX*16, 0, chunkZ*16).add(random.nextInt(16), random.nextInt(108), random.nextInt(16));
				new WorldGenLakes(BlockList.TAR).generate(world, random,pos );
	        }
		}
		
		
	}

	private void makeSeaweedCluster(int chunkX, int chunkZ, Random random,World world, int min, int max) {
		
		int randX,randZ,numShoots;
		int num;
		if(min==max){
			num = min;
		}else{
			num = min + random.nextInt(max - min);
		}
		
		randX = chunkX*16 + random.nextInt(16);
		randZ = chunkZ*16 + random.nextInt(16);
		numShoots = 5 + random.nextInt(7);
	
		
		for(int i = 0; i < num; i++)
		{
			for(int s = 0; s < numShoots; s++)
			{
				BlockPos pos = world.getHeight(new BlockPos(randX,0,randZ));
				makeSeaweed(world,random,scatter(pos,random,1,6),3,6);
			}
		}	
	}
	
	private void makeSeaweed(World world, Random random, BlockPos position,int minheight, int maxheight) {
		BlockPos seaBedPos;
		int height = 0;
		for ( seaBedPos = position.down(); world.getBlockState(seaBedPos).getMaterial() == Material.WATER; seaBedPos = seaBedPos.down()){;}
		//GlassWorks.log.info("Seabed @"+seaBedPos);
		
		if(minheight == maxheight){
			height = minheight;
		}else{
			height = minheight + random.nextInt(maxheight - minheight);
		}
		
		for(int i = 1; i < height; i++)
		{
			//if(((BlockSeaweed)GlassBlocks.SEAWEED).canPlaceBlockAt(world,position)){
				world.setBlockState(seaBedPos.up(i), BlockList.SEAWEED.getDefaultState());
			//}
		}
	}

	private BlockPos scatter(BlockPos in,Random random,int minDist, int maxDist){
		int scatterx,scatterz;
		if(minDist==maxDist){
			scatterx = minDist;
			scatterz = minDist;
		}else{
			scatterx = minDist + random.nextInt(maxDist - minDist);
			scatterz = minDist + random.nextInt(maxDist - minDist);
		}
		
		int mx = random.nextBoolean()?-1:1;
		int mz = random.nextBoolean()?-1:1;
	
		return in.add(scatterx*mx, 0, scatterz*mz);
	}

}
