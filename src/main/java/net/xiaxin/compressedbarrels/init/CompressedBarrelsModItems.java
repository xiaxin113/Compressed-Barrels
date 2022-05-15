
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xiaxin.compressedbarrels.init;

import net.xiaxin.compressedbarrels.CompressedBarrelsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

public class CompressedBarrelsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, CompressedBarrelsMod.MODID);
	public static final RegistryObject<Item> COMPRESSED_BARRELS = block(CompressedBarrelsModBlocks.COMPRESSED_BARRELS,
			CompressedBarrelsModTabs.TAB_COMPRESSED_BARRELSA_2);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
