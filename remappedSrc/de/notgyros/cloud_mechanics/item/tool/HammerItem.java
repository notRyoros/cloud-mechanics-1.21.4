package de.notgyros.cloud_mechanics.item.tool;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;

public class HammerItem extends MiningToolItem {

    public HammerItem(ToolMaterial toolMaterial, TagKey<Block> effectiveBlocks, float attackDamage, float attackSpeed, net.minecraft.item.Item.Settings settings, ToolMaterial material1, ToolMaterial material) {
        super(toolMaterial, effectiveBlocks, attackDamage, attackSpeed, settings);

        this.material = toolMaterial;
    }

    private final ToolMaterial material;

    public ToolMaterial getMaterial() {
        return this.material;
    }

}
