package io.github.real_septicake.hexxyplanes.forge;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

// Based on Fantasy's `VoidChunkGenerator` since infiniverse doesn't supply one

public class VoidChunkGenerator extends ChunkGenerator {
    private final Holder<Biome> biome;

    public static final Codec<VoidChunkGenerator> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Biome.CODEC.stable().fieldOf("biome").forGetter((v) -> v.biome)
    ).apply(instance, instance.stable(VoidChunkGenerator::new)));

    public VoidChunkGenerator(MinecraftServer server) {
        this(server.registryAccess().lookupOrThrow(Registries.BIOME)
                .getOrThrow(Biomes.THE_VOID));
    }

    public VoidChunkGenerator(Holder<Biome> biome) {
        super(new FixedBiomeSource(biome));
        this.biome = biome;
    }

    @Override
    protected @NotNull Codec<? extends ChunkGenerator> codec() {
        return CODEC;
    }

    @Override
    public void applyCarvers(@NotNull WorldGenRegion level, long seed, @NotNull RandomState random, @NotNull BiomeManager biomeManager, @NotNull StructureManager structureManager, @NotNull ChunkAccess chunk, @NotNull GenerationStep.Carving step) {

    }

    @Override
    public void buildSurface(@NotNull WorldGenRegion level, @NotNull StructureManager structureManager, @NotNull RandomState random, @NotNull ChunkAccess chunk) {

    }

    @Override
    public void spawnOriginalMobs(@NotNull WorldGenRegion level) {

    }

    @Override
    public int getGenDepth() {
        return 0;
    }

    @Override
    public @NotNull CompletableFuture<ChunkAccess> fillFromNoise(@NotNull Executor executor, @NotNull Blender blender, @NotNull RandomState random, @NotNull StructureManager structureManager, @NotNull ChunkAccess chunk) {
        return CompletableFuture.completedFuture(chunk);
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getMinY() {
        return 0;
    }

    @Override
    public int getBaseHeight(int x, int z, @NotNull Heightmap.Types type, @NotNull LevelHeightAccessor level, @NotNull RandomState random) {
        return 0;
    }

    private static final NoiseColumn EMPTY_COLUMN = new NoiseColumn(0, new BlockState[0]);

    @Override
    public @NotNull NoiseColumn getBaseColumn(int x, int z, @NotNull LevelHeightAccessor height, @NotNull RandomState random) {
        return EMPTY_COLUMN;
    }

    @Override
    public void addDebugScreenInfo(@NotNull List<String> info, @NotNull RandomState random, @NotNull BlockPos pos) {

    }

    @Nullable
    @Override
    public Pair<BlockPos, Holder<Structure>> findNearestMapStructure(@NotNull ServerLevel level, @NotNull HolderSet<Structure> structure, @NotNull BlockPos pos, int searchRadius, boolean skipKnownStructures) {
        return null;
    }

    @Override
    public @NotNull WeightedRandomList<MobSpawnSettings.SpawnerData> getMobsAt(@NotNull Holder<Biome> biome, @NotNull StructureManager structureManager, @NotNull MobCategory category, @NotNull BlockPos pos) {
        return WeightedRandomList.create();
    }

    @Override
    public @NotNull ChunkGeneratorStructureState createState(@NotNull HolderLookup<StructureSet> structureSetLookup, @NotNull RandomState randomState, long seed) {
        return ChunkGeneratorStructureState.createForNormal(randomState, seed, biomeSource, structureSetLookup);
    }

    @Override
    public void createStructures(@NotNull RegistryAccess registryAccess, @NotNull ChunkGeneratorStructureState structureState, @NotNull StructureManager structureManager, @NotNull ChunkAccess chunk, @NotNull StructureTemplateManager structureTemplateManager) {

    }
}
