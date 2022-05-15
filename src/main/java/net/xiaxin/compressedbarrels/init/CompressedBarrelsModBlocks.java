
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xiaxin.compressedbarrels.init;

import net.xiaxin.compressedbarrels.block.CompressedBarrelsBlock;
import net.xiaxin.compressedbarrels.CompressedBarrelsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class CompressedBarrelsModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, CompressedBarrelsMod.MODID);
	public static final RegistryObject<Block> COMPRESSED_BARRELS = REGISTRY.register("compressed_barrels", () -> new CompressedBarrelsBlock());
}
