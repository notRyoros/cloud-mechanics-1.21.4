package de.notgyros.cloud_mechanics.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.FallingBlock;

public class DustBlock extends FallingBlock {
    public static final MapCodec<DustBlock> CODEC = simpleCodec(DustBlock::new);

    public DustBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return CODEC;
    }
}
