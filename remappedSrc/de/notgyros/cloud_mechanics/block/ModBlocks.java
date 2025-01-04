package de.notgyros.cloud_mechanics.block;

import com.mojang.serialization.MapCodec;
import de.notgyros.cloud_mechanics.CloudMechanics;
import de.notgyros.cloud_mechanics.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block DUST_BLOCK = register(
            new FallingBlock(AbstractBlock.Settings.create().sounds(BlockSoundGroup.SAND).registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(CloudMechanics.MOD_ID, "dust_block")))
                    .hardness(0.5f)
                    .resistance(0.5f)
                    ) {
                @Override
                protected MapCodec<? extends FallingBlock> getCodec() {
                    return null;
                }
            },
            "dust_block",
            true
    );


    public static Block register(Block block, String name, boolean shouldRegisterItem) {
        // Register the block and its item.
        Identifier id = Identifier.of(CloudMechanics.MOD_ID, name);

        // Sometimes, you may not want to register an item for the block.
        // Eg: if it's a technical block like `minecraft:air` or `minecraft:end_gateway`
        if (shouldRegisterItem) {
            BlockItem blockItem = new BlockItem(block, new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CloudMechanics.MOD_ID, "dust_block"))));
            Registry.register(Registries.ITEM, id, blockItem);
        }

        return Registry.register(Registries.BLOCK, id, block);
    }

    public static void registerModBlocks() {
        CloudMechanics.LOGGER.info("Registering Mod Items for " + CloudMechanics.MOD_ID);
    }
}
