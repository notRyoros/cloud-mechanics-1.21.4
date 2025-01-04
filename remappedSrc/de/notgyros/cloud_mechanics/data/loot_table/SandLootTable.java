package de.notgyros.cloud_mechanics.data.loot_table;

import de.notgyros.cloud_mechanics.item.ModItems;
import de.notgyros.cloud_mechanics.tag.TagRegistry;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;
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

public class SandLootTable {

    public static void registerSandLootTable() {
        LootTableEvents.REPLACE.register((registryKey, builder, lootTableSource, wrapperLookup) -> {
            if (Blocks.SAND.getLootTableKey().isPresent() && Blocks.SAND.getLootTableKey().get().equals(registryKey)) {

                LootPool.Builder lootPoolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(
                                ItemEntry.builder(ModItems.DUST)
                                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 4.0f)))
                                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create()
                                                .tag(wrapperLookup.getOrThrow(RegistryKeys.ITEM), TagRegistry.HAMMERS))
                                        ).alternatively(ItemEntry.builder(Items.SAND).conditionally(SurvivesExplosionLootCondition.builder()))
                        );
                return LootTable.builder().pool(lootPoolBuilder).build();

            }
            return builder;
        });
    }
}
