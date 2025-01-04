package de.notgyros.cloud_mechanics.item;

import de.notgyros.cloud_mechanics.CloudMechanics;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.function.Function;


public class ModItems {

    private static final LinkedHashMap<ResourceLocation, Item> ITEM_LIST = new LinkedHashMap<>();
    private static final LinkedList<Item> NO_GROUP = new LinkedList<>();




    public static final Item DUST = register("dust");
    public static final Item SAND_POWDER = register("sand_powder");




    private static Item register(String id) {
        return register(id, Item::new, new Item.Properties());
    }

    private static Item register(String id, Item.Properties properties) {
        return register(id, Item::new, properties);
    }

    private static Item register(String id, Function<Item.Properties, Item> itemFactory) {
        return register(id, itemFactory, new Item.Properties());
    }

    private static <T extends Item> T register(String id, Function<Item.Properties, T> itemFactory, Item.Properties properties) {
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, id));

        T item = itemFactory.apply(properties.setId(key));
        if (item instanceof BlockItem blockItem)
            blockItem.registerBlocks(Item.BY_BLOCK, item);

        Registry.register(BuiltInRegistries.ITEM, key, item);
        ITEM_LIST.put(ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, id), item);

        return item;
    }

    public static void load() {
    }

    public static Collection<Item> getItemList() {
        return ITEM_LIST.values();
    }

    public static void registerModItems() {
        CloudMechanics.LOGGER.info("Registering Mod Items for " + CloudMechanics.MOD_ID);
    }
}
