package de.notgyros.cloud_mechanics;

import de.notgyros.cloud_mechanics.block.ModBlocks;
import de.notgyros.cloud_mechanics.data.CloudMechanicsBlockLootTableProvider;
import de.notgyros.cloud_mechanics.data.loot_table.SandLootTable;
import de.notgyros.cloud_mechanics.item.ModItemGroups;
import de.notgyros.cloud_mechanics.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloudMechanics implements ModInitializer {
	public static final String MOD_ID = "cloud_mechanics";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		//CloudMechanicsBlockLootTableProvider.registerLootTables();
	}
}