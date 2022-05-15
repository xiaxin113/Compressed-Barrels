
package net.mcreator.compressedbarrels.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.compressedbarrels.block.CompressedBarrelsBlock;
import net.mcreator.compressedbarrels.CompressedBarrelsModElements;

@CompressedBarrelsModElements.ModElement.Tag
public class CompressedBarrels3ItemGroup extends CompressedBarrelsModElements.ModElement {
	public CompressedBarrels3ItemGroup(CompressedBarrelsModElements instance) {
		super(instance, 3);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabcompressed_barrels_3") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(CompressedBarrelsBlock.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
