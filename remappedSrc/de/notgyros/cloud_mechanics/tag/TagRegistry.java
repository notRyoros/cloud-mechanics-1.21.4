package de.notgyros.cloud_mechanics.tag;

import de.notgyros.cloud_mechanics.CloudMechanics;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagRegistry {
    public static final TagKey<Item> HAMMERS = TagKey.of(RegistryKey.ofRegistry(RegistryKeys.ITEM.getRegistry()), Identifier.of(CloudMechanics.MOD_ID, "hammers"));
    public static final TagKey<Item> MINEABLE_WITH_HAMMER = TagKey.of(RegistryKey.ofRegistry(RegistryKeys.ITEM.getRegistry()), Identifier.of(CloudMechanics.MOD_ID, "mineable_with_hammer"));
}
