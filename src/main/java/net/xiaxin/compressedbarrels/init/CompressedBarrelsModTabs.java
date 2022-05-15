
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xiaxin.compressedbarrels.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class CompressedBarrelsModTabs {
	public static CreativeModeTab TAB_COMPRESSED_BARRELSA_2;

	public static void load() {
		TAB_COMPRESSED_BARRELSA_2 = new CreativeModeTab("tabcompressed_barrelsa_2") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(CompressedBarrelsModBlocks.COMPRESSED_BARRELS.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
}
