package de.notgyros.cloud_mechanics.tag;

import de.notgyros.cloud_mechanics.CloudMechanics;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagRegistry {
    public static final TagKey<Item> HAMMERS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, "hammers"));
    public static final TagKey<Block> MINEABLE_WITH_HAMMER = TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, "mineable_with_hammer"));
}
