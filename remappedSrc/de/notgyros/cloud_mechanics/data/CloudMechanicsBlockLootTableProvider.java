package de.notgyros.cloud_mechanics.data;

import de.notgyros.cloud_mechanics.block.ModBlocks;
import de.notgyros.cloud_mechanics.item.ModItems;
import de.notgyros.cloud_mechanics.tag.TagRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.RegistryWrapper;
import java.util.concurrent.CompletableFuture;

public class CloudMechanicsBlockLootTableProvider extends FabricBlockLootTableProvider {

    public CloudMechanicsBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        // Additional loot table logic can be added here if necessary
        registerLootTables();
    }

    public static void registerLootTables() {
        registerNewLootTable(Blocks.OAK_LOG, ItemTags.AXES, ModItems.SAND_POWDER, ModBlocks.DUST_BLOCK);
        registerNewLootTable(Blocks.SAND, TagRegistry.HAMMERS, ModItems.DUST, Items.SAND);
    }

    public static <T> T registerNewLootTable(Block BLOCK, TagKey TOOL, T ONE, T TWO) {
        LootTableEvents.REPLACE.register((registryKey, builder, lootTableSource, wrapperLookup) -> {
            if (BLOCK.getLootTableKey().isPresent() && BLOCK.getLootTableKey().get().equals(registryKey)) {

                LootPool.Builder lootPoolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                                ItemEntry.builder((ItemConvertible) ONE)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create()
                                                .tag(wrapperLookup.getOrThrow(RegistryKeys.ITEM), TOOL))
                                        ).alternatively(ItemEntry.builder((ItemConvertible) TWO).conditionally(SurvivesExplosionLootCondition.builder()))
                        );
                return LootTable.builder().pool(lootPoolBuilder).build();

            }
            return builder;
        });

        return ONE;
    }
}
