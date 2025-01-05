package de.notgyros.cloud_mechanics.item.tool;

import de.notgyros.cloud_mechanics.tag.TagRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.block.Block;

public class HammerItem extends DiggerItem {

    public HammerItem(ToolMaterial toolMaterial, float f, float g, Properties properties) {
        super(toolMaterial, TagRegistry.MINEABLE_WITH_HAMMER, f, g, properties);

        this.material = toolMaterial;
    }

    private final ToolMaterial material;

    public ToolMaterial getMaterial() {
        return this.material;
    }

}
