package de.notgyros.cloud_mechanics.block;

import com.mojang.serialization.MapCodec;
import de.notgyros.cloud_mechanics.CloudMechanics;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.function.Function;

public class ModBlocks {

    private static final LinkedHashMap<ResourceLocation, Block> BLOCK_LIST = new LinkedHashMap<>();
    private static final LinkedHashMap<ResourceLocation, Item> BLOCK_ITEM_LIST = new LinkedHashMap<>();

    public static final DustBlock DUST_BLOCK = registerDefault("dust_block", DustBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SAND));

    public static final Block SILICON_BLOCK = registerDefault("silicon_block",Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK));



    private static <T extends Block> T registerBlock(String id, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties properties) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, id);
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, location);
        T block = blockFactory.apply(properties.setId(key));
        BLOCK_LIST.put(location, block);
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
    }

    private static <T extends Block> T registerDefault(String id, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties properties) {
        return registerDefault(id, blockFactory, properties, new Item.Properties());
    }

    private static <T extends Block> T registerDefault(String id, Function<BlockBehaviour.Properties, T> blockFactory, BlockBehaviour.Properties properties, Item.Properties itemProperties) {
        T block = registerBlock(id, blockFactory, properties);
        registerBlockItem(id, properties1 -> new BlockItem(block, properties1), itemProperties);
        return block;
    }

    private static void registerBlockItem(String id, Function<Item.Properties, BlockItem> itemFactory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, id));
        BlockItem blockItem = itemFactory.apply(properties.setId(key));

        BLOCK_ITEM_LIST.put(ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, id), blockItem);
        Registry.register(BuiltInRegistries.ITEM, key, blockItem);
    }

    public static void registerModBlocks() {
        CloudMechanics.LOGGER.info("Registering Mod Items for " + CloudMechanics.MOD_ID);
    }

    public static Collection<Block> getBlockList(){
        return BLOCK_LIST.values();
    }
}
