
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.xiaxin.compressedbarrels.init;

import net.xiaxin.compressedbarrels.client.gui.CompressedBarrelsa3Screen;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CompressedBarrelsModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(CompressedBarrelsModMenus.COMPRESSED_BARRELSA_3, CompressedBarrelsa3Screen::new);
		});
	}
}
