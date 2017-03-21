package plasmacraft.worldgen;

import java.util.Random;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import plasmacraft.PlasmaCraft;

public class WorldGeneratorImpl implements IWorldGenerator
{
    private final WorldGenerator gen_ore_lead = new WorldGenMinable(PlasmaCraft.BLOCK_ORE_LEAD.getDefaultState(), 5);
    private final WorldGenerator gen_ore_neptunium = new WorldGenMinable(PlasmaCraft.BLOCK_ORE_NEPTUNIUM.getDefaultState(), 7, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenerator gen_ore_obsidium = new WorldGenMinable(PlasmaCraft.BLOCK_ORE_OBSIDIUM.getDefaultState(), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
    private final WorldGenerator gen_ore_plutonium = new WorldGenMinable(PlasmaCraft.BLOCK_ORE_PLUTONIUM.getDefaultState(), 5);
    private final WorldGenerator gen_ore_radionite = new WorldGenMinable(PlasmaCraft.BLOCK_ORE_RADIONITE.getDefaultState(), 4);
    private final WorldGenerator gen_ore_uranium = new WorldGenMinable(PlasmaCraft.BLOCK_ORE_URANIUM.getDefaultState(), 4);
    
    private final WorldGenerator gen_fluid_netherflow = new WorldGenHellLava(PlasmaCraft.BLOCK_NETHERFLOW, false);
    private final WorldGenerator gen_fluid_neptunium = new WorldGenHellLava(PlasmaCraft.BLOCK_NEPTUNIUM, false);
    private final WorldGenerator gen_fluid_acid = new WorldGenLiquids(PlasmaCraft.BLOCK_ACID);
    
    private final WorldGenLakesCaustic gen_lake_acid = new WorldGenLakesCaustic(PlasmaCraft.BLOCK_ACID);
    private final WorldGenLakesCaustic gen_lake_cryonite = new WorldGenLakesCaustic(PlasmaCraft.BLOCK_CRYONITE);
    private final WorldGenLakesCaustic gen_lake_netherflow = new WorldGenLakesCaustic(PlasmaCraft.BLOCK_NETHERFLOW, Blocks.NETHERRACK);
    
    private final WorldGenerator gen_spike_cryonite = new WorldGenFrozenCryoniteSpike(PlasmaCraft.BLOCK_FROZEN_CRYONITE);
    
    @Override
    public void generate(
            Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
            IChunkProvider chunkProvider
    )
    {
        switch (world.provider.getDimension()) {
            case 0:
                runGenerator(gen_ore_lead, world, random, chunkX, chunkZ, 6, 0, 40);
                runGenerator(gen_ore_plutonium, world, random, chunkX, chunkZ, 4, 16, 48);
                runGenerator(gen_ore_radionite, world, random, chunkX, chunkZ, 6, 4, 24);
                runGenerator(gen_ore_uranium, world, random, chunkX, chunkZ, 6, 4, 32);
                runGenerator(gen_fluid_acid, world, random, chunkX, chunkZ, 2, 8, 32);
                
                float temperature = world.getBiome(new BlockPos(chunkX, 50, chunkZ)).getTemperature();
                
                if (temperature <= 0.2)
                {
                    runGenerator(gen_lake_cryonite, world, random, chunkX, chunkZ, 6, 50);
                    
                    if (world.getBiome(new BlockPos(chunkX, 50, chunkZ)) == Biomes.ICE_PLAINS)
                    {
                        runGenerator(gen_spike_cryonite, world, random, chunkX, chunkZ, 1, 96);
                    }
                }
                else if (temperature <= 1.5)
                {
                    runGenerator(gen_lake_acid, world, random, chunkX, chunkZ, 6, 50);
                }
                break;
            case -1:
                runGenerator(gen_ore_neptunium, world, random, chunkX, chunkZ, 6, 16, 64);
                runGenerator(gen_ore_obsidium, world, random, chunkX, chunkZ, 4, 16, 64);
                runGenerator(gen_fluid_netherflow, world, random, chunkX, chunkZ, 3, 8, 64);
                runGenerator(gen_fluid_neptunium, world, random, chunkX, chunkZ, 2, 8, 64);
                runGenerator(gen_lake_netherflow, world, random, chunkX, chunkZ, 6, 96);
                break;
            default:
                break;
        }
    }

    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) 
    {
        int heightDiff = maxHeight - minHeight + 1;
        for (int i = 0; i < chancesToSpawn; i ++) 
        {
            int x = chunk_X * 16 + rand.nextInt(16);
            int y = minHeight + rand.nextInt(heightDiff);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, y, z));
        }
    }
    
    private void runGenerator(WorldGenLakesCaustic generator, World world, Random random, int chunkX, int chunkZ, int chancesToSpawn, int maxHeight) 
    {
        if (random.nextInt(chancesToSpawn) == 0)
        {
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            int top = Math.max(1, world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY() - 10);
            if(top > 0) {
                top = Math.min(top, maxHeight);
                int y = random.nextInt(top);
                generator.generate(world, random, new BlockPos(x, y, z));
            }
        }
    }
    
    private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int Height) 
    {
        for (int i = 0; i < chancesToSpawn; i ++) 
        {
            int x = chunk_X * 16 + rand.nextInt(16);
            int z = chunk_Z * 16 + rand.nextInt(16);
            generator.generate(world, rand, new BlockPos(x, Height, z));
        }
    }
    
}
