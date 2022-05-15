
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xiaxin.compressedbarrels.init;

import net.xiaxin.compressedbarrels.block.entity.CompressedBarrelsBlockEntity;
import net.xiaxin.compressedbarrels.CompressedBarrelsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

public class CompressedBarrelsModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,
			CompressedBarrelsMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> COMPRESSED_BARRELS = register("compressed_barrels",
			CompressedBarrelsModBlocks.COMPRESSED_BARRELS, CompressedBarrelsBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block,
			BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
