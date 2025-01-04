package de.notgyros.cloud_mechanics.data;

import com.google.common.base.Equivalence;
import de.notgyros.cloud_mechanics.block.ModBlocks;

import de.notgyros.cloud_mechanics.item.ModItems;
import de.notgyros.cloud_mechanics.tag.TagRegistry;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.nbt.TagType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.Wrapper;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Stream;


public class CloudMechanicsBlockLootTableProvider extends BlockLootSubProvider {

    public CloudMechanicsBlockLootTableProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    public void generate() {
        HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        //this.dropSelf(ModBlocks.DUST_BLOCK);

        this.add(ModBlocks.DUST_BLOCK, block -> this.createDustLootTable(block, registryLookup));

    }

    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
        this.generate();

        Set<ResourceKey<LootTable>> set = new HashSet<>();

        for (Block block : ModBlocks.getBlockList()) {
            if (block.isEnabled(this.enabledFeatures)) {
                block.getLootTable()
                        .ifPresent(
                                resourceKey -> {
                                    if (set.add(resourceKey)) {
                                        LootTable.Builder builder = this.map.remove(resourceKey);
                                        if (builder == null) {
                                            throw new IllegalStateException(
                                                    String.format(Locale.ROOT, "Missing loottable '%s' for '%s'", resourceKey.location(), BuiltInRegistries.BLOCK.getKey(block))
                                            );
                                        }

                                        biConsumer.accept(resourceKey, builder);
                                    }
                                }
                        );
            }

        }
    }

    private LootTable.Builder createDustLootTable(Block block, HolderLookup.RegistryLookup<Enchantment> registryLookup) {
        return LootTable.lootTable()
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1F))
                                .add(
                                        LootItem.lootTableItem(Items.REDSTONE)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                                .when(LootItemRandomChanceCondition.randomChance(0.5F))
                                                    .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.registries.lookupOrThrow(Registries.ITEM), ItemTags.SHOVELS)))
                                )
                )

                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1F))
                                .add(
                                        LootItem.lootTableItem(Items.GOLD_INGOT)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                                                .when(LootItemRandomChanceCondition.randomChance(0.5F))
                                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.registries.lookupOrThrow(Registries.ITEM), ItemTags.SHOVELS)))
                                )
                )

                .withPool(
                        LootPool.lootPool()

                                .add(LootItem.lootTableItem(ModBlocks.DUST_BLOCK)
                                        .when(InvertedLootItemCondition.invert(MatchTool.toolMatches(ItemPredicate.Builder.item().of(this.registries.lookupOrThrow(Registries.ITEM), ItemTags.SHOVELS))))
                                )
                );
    }
}
