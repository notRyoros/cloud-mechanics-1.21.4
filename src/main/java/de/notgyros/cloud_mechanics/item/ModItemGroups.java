package de.notgyros.cloud_mechanics.item;

import de.notgyros.cloud_mechanics.CloudMechanics;
import de.notgyros.cloud_mechanics.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.HashMap;


public class ModItemGroups {

    private static final HashMap<ResourceLocation, CreativeModeTab> GROUPS = new HashMap<>();
    public static final ResourceKey<CreativeModeTab> CLOUD_MECHANICS = registerItemGroup("cloud_mechanics", Items.COMMAND_BLOCK);

    private static ResourceKey<CreativeModeTab> registerItemGroup(String id, Item display){
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, id));
        GROUPS.put(
                ResourceLocation.fromNamespaceAndPath(CloudMechanics.MOD_ID, id),
                FabricItemGroup.builder()
                        .icon(() -> new ItemStack(display))
                        .title(Component.translatable(String.format("itemGroup.%s.%s", CloudMechanics.MOD_ID, id)))
                        .build()
        );
        return key;
    }

    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ModItemGroups.CLOUD_MECHANICS).register(entries -> {
            ModItems.getItemList().forEach(entries::accept);
            ModBlocks.getBlockList().stream().filter(block -> block.asItem() != Items.AIR).forEach(entries::accept);
        });
    }

    public static void registerItemGroups() {
        registerModItems();
        GROUPS.forEach((identifier, itemGroup) -> Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, identifier, itemGroup));

        CloudMechanics.LOGGER.info("Registering Item Groups for " + CloudMechanics.MOD_ID);
    }
}
