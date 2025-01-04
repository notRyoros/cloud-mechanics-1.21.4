package de.notgyros.cloud_mechanics.data.loot_table;

import de.notgyros.cloud_mechanics.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class OverrideLootTables {

    public static void registerLootTables() {

        LootTableEvents.REPLACE.register((registryKey, builder, lootTableSource, wrapperLookup) -> {
            if (Blocks.SAND.getLootTable().isPresent() && Blocks.SAND.getLootTable().get().equals(registryKey)) {

                LootPool.Builder lootPoolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(
                                LootItem.lootTableItem(ModItems.DUST)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item()
                                                .of(wrapperLookup.lookupOrThrow(Registries.ITEM), ItemTags.SHOVELS))
                                        ).otherwise(LootItem.lootTableItem(Items.SAND).when(ExplosionCondition.survivesExplosion()))
                        );

                return LootTable.lootTable().withPool(lootPoolBuilder).build();
            }

            if (Blocks.DIRT.getLootTable().isPresent() && Blocks.DIRT.getLootTable().get().equals(registryKey)) {

                LootPool.Builder lootPoolBuilder = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(
                                LootItem.lootTableItem(ModItems.SAND_POWDER)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item()
                                                .of(wrapperLookup.lookupOrThrow(Registries.ITEM), ItemTags.SHOVELS))
                                        ).otherwise(LootItem.lootTableItem(Items.DIRT).when(ExplosionCondition.survivesExplosion()))
                        );

                return LootTable.lootTable().withPool(lootPoolBuilder).build();
            }

            return builder;
        });
    }
}
