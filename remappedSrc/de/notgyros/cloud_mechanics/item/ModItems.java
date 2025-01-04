package de.notgyros.cloud_mechanics.item;

import com.ibm.icu.impl.UResource;
import de.notgyros.cloud_mechanics.CloudMechanics;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item DUST = registerItem("dust", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CloudMechanics.MOD_ID, "dust")))
    ));
    public static final Item SAND_POWDER = registerItem("sand_powder", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CloudMechanics.MOD_ID, "sand_powder")))
    ));

    //public static final Item DUST_BLOCK = registerItem("dust_block", new Item(new Item.Settings()
    //        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(CloudMechanics.MOD_ID, "dust_block")))
    //));



    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CloudMechanics.MOD_ID, name), item);
    }

    public static void registerModItems() {
        CloudMechanics.LOGGER.info("Registering Mod Items for " + CloudMechanics.MOD_ID);
    }
}
