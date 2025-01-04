package de.notgyros.cloud_mechanics.tag;

import de.notgyros.cloud_mechanics.CloudMechanics;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class TagRegistry {
    public static final TagKey<Item> HAMMERS = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, "hammers"));
    public static final TagKey<Item> MINEABLE_WITH_HAMMER = TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, "mineable_with_hammer"));
}
