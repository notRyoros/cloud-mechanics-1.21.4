package de.notgyros.cloud_mechanics.item.tool;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public class HammerItem extends DiggerItem {

    public HammerItem(ToolMaterial toolMaterial, TagKey<Block> tagKey, float f, float g, Properties properties, ToolMaterial material) {
        super(toolMaterial, tagKey, f, g, properties);

        this.material = material;
    }

    private final ToolMaterial material;

    public ToolMaterial getMaterial() {
        return this.material;
    }

}
