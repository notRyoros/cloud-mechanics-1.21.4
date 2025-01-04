package de.notgyros.cloud_mechanics.item;

import de.notgyros.cloud_mechanics.CloudMechanics;
import de.notgyros.cloud_mechanics.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup CLOUD_MECHANICS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CloudMechanics.MOD_ID, "cloud_mechanics_tab"),
            FabricItemGroup.builder().icon(() -> new ItemStack(Items.COMMAND_BLOCK))
                    .displayName(Text.translatable("itemgroup.cloud_mechanics.cloud_mechanics_tab"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.SAND_POWDER);
                        entries.add(ModItems.DUST);

                        entries.add(ModBlocks.DUST_BLOCK);
                    })
                    .build());



    public static void registerItemGroups() {
        CloudMechanics.LOGGER.info("Registering Item Groups for " + CloudMechanics.MOD_ID);
    }
}
