package getfluxed.minemagicka.common.world;

import getfluxed.minemagicka.common.blocks.MMBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class GenerationHandler implements IWorldGenerator {
    public boolean gen = true;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimensionId()) {
            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
                break;
            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
                break;
        }
    }

    private void generateEnd(World world, Random rand, int chunkX, int chunkZ) {
    }

    private void generateSurface(World world, Random rand, int chunkX, int chunkZ) {
        int Xcoord1 = chunkX + rand.nextInt(16);
        int Ycoord1 = rand.nextInt(40) + 60;
        int Zcoord1 = chunkZ + rand.nextInt(16);
        BlockPos pos = new BlockPos(Xcoord1, Ycoord1, Zcoord1);
        String biome = world.getBiomeGenForCoords(pos).biomeName;
        if (rand.nextInt(30) == 0)
            if (!biome.toLowerCase().startsWith("ocean") && !biome.toLowerCase().startsWith("river")) {
                while (world.isAirBlock(pos) || !world.isSideSolid(pos, EnumFacing.UP) || world.getBlockState(pos).getBlock().isReplaceable(world, pos)) {
                    pos = pos.down();
                }
                while (!world.isAirBlock(pos.up())) {
                    pos = pos.up();
                }
                if (!world.isAirBlock(pos)) {
                    pos = pos.up();
                }

                if (world.getBlockState(pos.down()) == Blocks.water.getDefaultState() || world.getBlockState(pos.down()) == Blocks.flowing_water.getDefaultState()) {
                    return;
                }

                EnumFacing[] dirs = new EnumFacing[]{EnumFacing.NORTH, EnumFacing.EAST, EnumFacing.SOUTH, EnumFacing.WEST};
                for (EnumFacing dir : dirs) {
                    world.setBlockState(pos.up(2).offset(dir), MMBlocks.leavesMagick.getDefaultState());
                }
                for (EnumFacing dir : dirs) {
                    for (EnumFacing dire : dirs) {
                        world.setBlockState(pos.up(3).offset(dir).offset(dire), MMBlocks.leavesMagick.getDefaultState());
                    }
                    world.setBlockState(pos.up(3).offset(dir), MMBlocks.leavesMagick.getDefaultState());
                }

                for (EnumFacing dir : dirs) {
                    world.setBlockState(pos.up(3).offset(dir, 2), MMBlocks.leavesMagick.getDefaultState());
                }
                for (EnumFacing dir : dirs) {
                    world.setBlockState(pos.up(4).offset(dir), MMBlocks.leavesMagick.getDefaultState());
                }
                for (EnumFacing dir : dirs) {
                    world.setBlockState(pos.up(6).offset(dir), MMBlocks.leavesMagick.getDefaultState());
                }
                for (int i = 0; i < 3; i++)
                    for (EnumFacing dir : dirs) {
                        for (EnumFacing dire : dirs) {
                            world.setBlockState(pos.up(7 + i).offset(dir).offset(dire), MMBlocks.leavesMagick.getDefaultState());
                        }
                        world.setBlockState(pos.up(7 + i).offset(dir), MMBlocks.leavesMagick.getDefaultState());
                    }
                for (EnumFacing dir : dirs) {
                    world.setBlockState(pos.up(10).offset(dir), MMBlocks.leavesMagick.getDefaultState());
                }
                world.setBlockState(pos.up(11), MMBlocks.leavesMagick.getDefaultState());
                for (int i = 0; i < 11; i++) {
                    world.setBlockState(pos.up(i), MMBlocks.logMagick.getDefaultState());
                }
            }
    }

    private void generateNether(World world, Random rand, int chunkX, int chunkZ) {
    }
}