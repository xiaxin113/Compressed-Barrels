
package net.mcreator.compressedbarrels.gui;

import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.ScreenManager;

import net.mcreator.compressedbarrels.CompressedBarrelsModElements;
import net.mcreator.compressedbarrels.CompressedBarrelsMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

@CompressedBarrelsModElements.ModElement.Tag
public class CompressedBarrels2Gui extends CompressedBarrelsModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;

	public CompressedBarrels2Gui(CompressedBarrelsModElements instance) {
		super(instance, 1);
		elements.addNetworkMessage(ButtonPressedMessage.class, ButtonPressedMessage::buffer, ButtonPressedMessage::new,
				ButtonPressedMessage::handler);
		elements.addNetworkMessage(GUISlotChangedMessage.class, GUISlotChangedMessage::buffer, GUISlotChangedMessage::new,
				GUISlotChangedMessage::handler);
		containerType = new ContainerType<>(new GuiContainerModFactory());
		FMLJavaModLoadingContext.get().getModEventBus().register(new ContainerRegisterHandler());
	}

	private static class ContainerRegisterHandler {
		@SubscribeEvent
		public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
			event.getRegistry().register(containerType.setRegistryName("compressed_barrels_2"));
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, CompressedBarrels2GuiWindow::new));
	}

	public static class GuiContainerModFactory implements IContainerFactory {
		public GuiContainerMod create(int id, PlayerInventory inv, PacketBuffer extraData) {
			return new GuiContainerMod(id, inv, extraData);
		}
	}

	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {
		World world;
		PlayerEntity entity;
		int x, y, z;
		private IItemHandler internal;
		private Map<Integer, Slot> customSlots = new HashMap<>();
		private boolean bound = false;

		public GuiContainerMod(int id, PlayerInventory inv, PacketBuffer extraData) {
			super(containerType, id);
			this.entity = inv.player;
			this.world = inv.player.world;
			this.internal = new ItemStackHandler(187);
			BlockPos pos = null;
			if (extraData != null) {
				pos = extraData.readBlockPos();
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
			}
			if (pos != null) {
				if (extraData.readableBytes() == 1) { // bound to item
					byte hand = extraData.readByte();
					ItemStack itemstack;
					if (hand == 0)
						itemstack = this.entity.getHeldItemMainhand();
					else
						itemstack = this.entity.getHeldItemOffhand();
					itemstack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						this.internal = capability;
						this.bound = true;
					});
				} else if (extraData.readableBytes() > 1) {
					extraData.readByte(); // drop padding
					Entity entity = world.getEntityByID(extraData.readVarInt());
					if (entity != null)
						entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							this.internal = capability;
							this.bound = true;
						});
				} else { // might be bound to block
					TileEntity ent = inv.player != null ? inv.player.world.getTileEntity(pos) : null;
					if (ent != null) {
						ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							this.internal = capability;
							this.bound = true;
						});
					}
				}
			}
			this.customSlots.put(1, this.addSlot(new SlotItemHandler(internal, 1, 8, 21) {
			}));
			this.customSlots.put(2, this.addSlot(new SlotItemHandler(internal, 2, 26, 21) {
			}));
			this.customSlots.put(3, this.addSlot(new SlotItemHandler(internal, 3, 44, 21) {
			}));
			this.customSlots.put(4, this.addSlot(new SlotItemHandler(internal, 4, 62, 21) {
			}));
			this.customSlots.put(5, this.addSlot(new SlotItemHandler(internal, 5, 80, 21) {
			}));
			this.customSlots.put(6, this.addSlot(new SlotItemHandler(internal, 6, 98, 21) {
			}));
			this.customSlots.put(7, this.addSlot(new SlotItemHandler(internal, 7, 116, 21) {
			}));
			this.customSlots.put(8, this.addSlot(new SlotItemHandler(internal, 8, 134, 21) {
			}));
			this.customSlots.put(9, this.addSlot(new SlotItemHandler(internal, 9, 152, 21) {
			}));
			this.customSlots.put(10, this.addSlot(new SlotItemHandler(internal, 10, 170, 21) {
			}));
			this.customSlots.put(11, this.addSlot(new SlotItemHandler(internal, 11, 188, 21) {
			}));
			this.customSlots.put(12, this.addSlot(new SlotItemHandler(internal, 12, 206, 21) {
			}));
			this.customSlots.put(13, this.addSlot(new SlotItemHandler(internal, 13, 224, 21) {
			}));
			this.customSlots.put(14, this.addSlot(new SlotItemHandler(internal, 14, 242, 21) {
			}));
			this.customSlots.put(15, this.addSlot(new SlotItemHandler(internal, 15, 260, 21) {
			}));
			this.customSlots.put(16, this.addSlot(new SlotItemHandler(internal, 16, 278, 21) {
			}));
			this.customSlots.put(17, this.addSlot(new SlotItemHandler(internal, 17, 296, 21) {
			}));
			this.customSlots.put(18, this.addSlot(new SlotItemHandler(internal, 18, 314, 21) {
			}));
			this.customSlots.put(19, this.addSlot(new SlotItemHandler(internal, 19, 332, 21) {
			}));
			this.customSlots.put(20, this.addSlot(new SlotItemHandler(internal, 20, 350, 21) {
			}));
			this.customSlots.put(21, this.addSlot(new SlotItemHandler(internal, 21, 368, 21) {
			}));
			this.customSlots.put(22, this.addSlot(new SlotItemHandler(internal, 22, 386, 21) {
			}));
			this.customSlots.put(23, this.addSlot(new SlotItemHandler(internal, 23, 404, 21) {
			}));
			this.customSlots.put(24, this.addSlot(new SlotItemHandler(internal, 24, 8, 39) {
			}));
			this.customSlots.put(25, this.addSlot(new SlotItemHandler(internal, 25, 26, 39) {
			}));
			this.customSlots.put(26, this.addSlot(new SlotItemHandler(internal, 26, 44, 39) {
			}));
			this.customSlots.put(27, this.addSlot(new SlotItemHandler(internal, 27, 62, 39) {
			}));
			this.customSlots.put(28, this.addSlot(new SlotItemHandler(internal, 28, 80, 39) {
			}));
			this.customSlots.put(29, this.addSlot(new SlotItemHandler(internal, 29, 98, 39) {
			}));
			this.customSlots.put(30, this.addSlot(new SlotItemHandler(internal, 30, 116, 39) {
			}));
			this.customSlots.put(31, this.addSlot(new SlotItemHandler(internal, 31, 134, 39) {
			}));
			this.customSlots.put(32, this.addSlot(new SlotItemHandler(internal, 32, 152, 39) {
			}));
			this.customSlots.put(33, this.addSlot(new SlotItemHandler(internal, 33, 170, 39) {
			}));
			this.customSlots.put(34, this.addSlot(new SlotItemHandler(internal, 34, 188, 39) {
			}));
			this.customSlots.put(35, this.addSlot(new SlotItemHandler(internal, 35, 206, 39) {
			}));
			this.customSlots.put(36, this.addSlot(new SlotItemHandler(internal, 36, 224, 39) {
			}));
			this.customSlots.put(37, this.addSlot(new SlotItemHandler(internal, 37, 242, 39) {
			}));
			this.customSlots.put(38, this.addSlot(new SlotItemHandler(internal, 38, 260, 39) {
			}));
			this.customSlots.put(39, this.addSlot(new SlotItemHandler(internal, 39, 278, 39) {
			}));
			this.customSlots.put(40, this.addSlot(new SlotItemHandler(internal, 40, 296, 39) {
			}));
			this.customSlots.put(41, this.addSlot(new SlotItemHandler(internal, 41, 314, 39) {
			}));
			this.customSlots.put(42, this.addSlot(new SlotItemHandler(internal, 42, 332, 39) {
			}));
			this.customSlots.put(43, this.addSlot(new SlotItemHandler(internal, 43, 350, 39) {
			}));
			this.customSlots.put(44, this.addSlot(new SlotItemHandler(internal, 44, 368, 39) {
			}));
			this.customSlots.put(45, this.addSlot(new SlotItemHandler(internal, 45, 386, 39) {
			}));
			this.customSlots.put(46, this.addSlot(new SlotItemHandler(internal, 46, 404, 39) {
			}));
			this.customSlots.put(47, this.addSlot(new SlotItemHandler(internal, 47, 8, 57) {
			}));
			this.customSlots.put(48, this.addSlot(new SlotItemHandler(internal, 48, 26, 57) {
			}));
			this.customSlots.put(49, this.addSlot(new SlotItemHandler(internal, 49, 44, 57) {
			}));
			this.customSlots.put(50, this.addSlot(new SlotItemHandler(internal, 50, 62, 57) {
			}));
			this.customSlots.put(51, this.addSlot(new SlotItemHandler(internal, 51, 80, 57) {
			}));
			this.customSlots.put(52, this.addSlot(new SlotItemHandler(internal, 52, 98, 57) {
			}));
			this.customSlots.put(53, this.addSlot(new SlotItemHandler(internal, 53, 116, 57) {
			}));
			this.customSlots.put(54, this.addSlot(new SlotItemHandler(internal, 54, 134, 57) {
			}));
			this.customSlots.put(55, this.addSlot(new SlotItemHandler(internal, 55, 152, 57) {
			}));
			this.customSlots.put(56, this.addSlot(new SlotItemHandler(internal, 56, 170, 57) {
			}));
			this.customSlots.put(57, this.addSlot(new SlotItemHandler(internal, 57, 188, 57) {
			}));
			this.customSlots.put(58, this.addSlot(new SlotItemHandler(internal, 58, 206, 57) {
			}));
			this.customSlots.put(59, this.addSlot(new SlotItemHandler(internal, 59, 224, 57) {
			}));
			this.customSlots.put(60, this.addSlot(new SlotItemHandler(internal, 60, 242, 57) {
			}));
			this.customSlots.put(61, this.addSlot(new SlotItemHandler(internal, 61, 260, 57) {
			}));
			this.customSlots.put(62, this.addSlot(new SlotItemHandler(internal, 62, 278, 57) {
			}));
			this.customSlots.put(63, this.addSlot(new SlotItemHandler(internal, 63, 296, 57) {
			}));
			this.customSlots.put(64, this.addSlot(new SlotItemHandler(internal, 64, 314, 57) {
			}));
			this.customSlots.put(65, this.addSlot(new SlotItemHandler(internal, 65, 332, 57) {
			}));
			this.customSlots.put(66, this.addSlot(new SlotItemHandler(internal, 66, 350, 57) {
			}));
			this.customSlots.put(67, this.addSlot(new SlotItemHandler(internal, 67, 368, 57) {
			}));
			this.customSlots.put(68, this.addSlot(new SlotItemHandler(internal, 68, 386, 57) {
			}));
			this.customSlots.put(69, this.addSlot(new SlotItemHandler(internal, 69, 404, 57) {
			}));
			this.customSlots.put(70, this.addSlot(new SlotItemHandler(internal, 70, 8, 75) {
			}));
			this.customSlots.put(71, this.addSlot(new SlotItemHandler(internal, 71, 26, 75) {
			}));
			this.customSlots.put(72, this.addSlot(new SlotItemHandler(internal, 72, 44, 75) {
			}));
			this.customSlots.put(73, this.addSlot(new SlotItemHandler(internal, 73, 62, 75) {
			}));
			this.customSlots.put(74, this.addSlot(new SlotItemHandler(internal, 74, 80, 75) {
			}));
			this.customSlots.put(75, this.addSlot(new SlotItemHandler(internal, 75, 98, 75) {
			}));
			this.customSlots.put(76, this.addSlot(new SlotItemHandler(internal, 76, 116, 75) {
			}));
			this.customSlots.put(77, this.addSlot(new SlotItemHandler(internal, 77, 134, 75) {
			}));
			this.customSlots.put(78, this.addSlot(new SlotItemHandler(internal, 78, 152, 75) {
			}));
			this.customSlots.put(79, this.addSlot(new SlotItemHandler(internal, 79, 170, 75) {
			}));
			this.customSlots.put(80, this.addSlot(new SlotItemHandler(internal, 80, 188, 75) {
			}));
			this.customSlots.put(81, this.addSlot(new SlotItemHandler(internal, 81, 206, 75) {
			}));
			this.customSlots.put(82, this.addSlot(new SlotItemHandler(internal, 82, 224, 75) {
			}));
			this.customSlots.put(83, this.addSlot(new SlotItemHandler(internal, 83, 242, 75) {
			}));
			this.customSlots.put(84, this.addSlot(new SlotItemHandler(internal, 84, 260, 75) {
			}));
			this.customSlots.put(85, this.addSlot(new SlotItemHandler(internal, 85, 278, 75) {
			}));
			this.customSlots.put(86, this.addSlot(new SlotItemHandler(internal, 86, 296, 75) {
			}));
			this.customSlots.put(87, this.addSlot(new SlotItemHandler(internal, 87, 314, 75) {
			}));
			this.customSlots.put(88, this.addSlot(new SlotItemHandler(internal, 88, 332, 75) {
			}));
			this.customSlots.put(89, this.addSlot(new SlotItemHandler(internal, 89, 350, 75) {
			}));
			this.customSlots.put(90, this.addSlot(new SlotItemHandler(internal, 90, 368, 75) {
			}));
			this.customSlots.put(91, this.addSlot(new SlotItemHandler(internal, 91, 386, 75) {
			}));
			this.customSlots.put(92, this.addSlot(new SlotItemHandler(internal, 92, 404, 75) {
			}));
			this.customSlots.put(93, this.addSlot(new SlotItemHandler(internal, 93, 8, 93) {
			}));
			this.customSlots.put(94, this.addSlot(new SlotItemHandler(internal, 94, 26, 93) {
			}));
			this.customSlots.put(95, this.addSlot(new SlotItemHandler(internal, 95, 44, 93) {
			}));
			this.customSlots.put(96, this.addSlot(new SlotItemHandler(internal, 96, 62, 93) {
			}));
			this.customSlots.put(97, this.addSlot(new SlotItemHandler(internal, 97, 80, 93) {
			}));
			this.customSlots.put(98, this.addSlot(new SlotItemHandler(internal, 98, 98, 93) {
			}));
			this.customSlots.put(99, this.addSlot(new SlotItemHandler(internal, 99, 116, 93) {
			}));
			this.customSlots.put(100, this.addSlot(new SlotItemHandler(internal, 100, 134, 93) {
			}));
			this.customSlots.put(101, this.addSlot(new SlotItemHandler(internal, 101, 152, 93) {
			}));
			this.customSlots.put(102, this.addSlot(new SlotItemHandler(internal, 102, 170, 93) {
			}));
			this.customSlots.put(103, this.addSlot(new SlotItemHandler(internal, 103, 188, 93) {
			}));
			this.customSlots.put(104, this.addSlot(new SlotItemHandler(internal, 104, 206, 93) {
			}));
			this.customSlots.put(105, this.addSlot(new SlotItemHandler(internal, 105, 224, 93) {
			}));
			this.customSlots.put(106, this.addSlot(new SlotItemHandler(internal, 106, 242, 93) {
			}));
			this.customSlots.put(107, this.addSlot(new SlotItemHandler(internal, 107, 260, 93) {
			}));
			this.customSlots.put(108, this.addSlot(new SlotItemHandler(internal, 108, 278, 93) {
			}));
			this.customSlots.put(109, this.addSlot(new SlotItemHandler(internal, 109, 296, 93) {
			}));
			this.customSlots.put(110, this.addSlot(new SlotItemHandler(internal, 110, 314, 93) {
			}));
			this.customSlots.put(111, this.addSlot(new SlotItemHandler(internal, 111, 332, 93) {
			}));
			this.customSlots.put(112, this.addSlot(new SlotItemHandler(internal, 112, 350, 93) {
			}));
			this.customSlots.put(113, this.addSlot(new SlotItemHandler(internal, 113, 368, 93) {
			}));
			this.customSlots.put(114, this.addSlot(new SlotItemHandler(internal, 114, 386, 93) {
			}));
			this.customSlots.put(115, this.addSlot(new SlotItemHandler(internal, 115, 404, 93) {
			}));
			this.customSlots.put(116, this.addSlot(new SlotItemHandler(internal, 116, 8, 111) {
			}));
			this.customSlots.put(117, this.addSlot(new SlotItemHandler(internal, 117, 26, 111) {
			}));
			this.customSlots.put(118, this.addSlot(new SlotItemHandler(internal, 118, 44, 111) {
			}));
			this.customSlots.put(119, this.addSlot(new SlotItemHandler(internal, 119, 62, 111) {
			}));
			this.customSlots.put(120, this.addSlot(new SlotItemHandler(internal, 120, 80, 111) {
			}));
			this.customSlots.put(121, this.addSlot(new SlotItemHandler(internal, 121, 98, 111) {
			}));
			this.customSlots.put(122, this.addSlot(new SlotItemHandler(internal, 122, 116, 111) {
			}));
			this.customSlots.put(123, this.addSlot(new SlotItemHandler(internal, 123, 134, 111) {
			}));
			this.customSlots.put(124, this.addSlot(new SlotItemHandler(internal, 124, 152, 111) {
			}));
			this.customSlots.put(125, this.addSlot(new SlotItemHandler(internal, 125, 170, 111) {
			}));
			this.customSlots.put(126, this.addSlot(new SlotItemHandler(internal, 126, 188, 111) {
			}));
			this.customSlots.put(127, this.addSlot(new SlotItemHandler(internal, 127, 206, 111) {
			}));
			this.customSlots.put(128, this.addSlot(new SlotItemHandler(internal, 128, 224, 111) {
			}));
			this.customSlots.put(129, this.addSlot(new SlotItemHandler(internal, 129, 242, 111) {
			}));
			this.customSlots.put(130, this.addSlot(new SlotItemHandler(internal, 130, 260, 111) {
			}));
			this.customSlots.put(131, this.addSlot(new SlotItemHandler(internal, 131, 278, 111) {
			}));
			this.customSlots.put(132, this.addSlot(new SlotItemHandler(internal, 132, 296, 111) {
			}));
			this.customSlots.put(133, this.addSlot(new SlotItemHandler(internal, 133, 314, 111) {
			}));
			this.customSlots.put(134, this.addSlot(new SlotItemHandler(internal, 134, 332, 111) {
			}));
			this.customSlots.put(135, this.addSlot(new SlotItemHandler(internal, 135, 350, 111) {
			}));
			this.customSlots.put(136, this.addSlot(new SlotItemHandler(internal, 136, 368, 111) {
			}));
			this.customSlots.put(137, this.addSlot(new SlotItemHandler(internal, 137, 386, 111) {
			}));
			this.customSlots.put(138, this.addSlot(new SlotItemHandler(internal, 138, 404, 111) {
			}));
			this.customSlots.put(139, this.addSlot(new SlotItemHandler(internal, 139, 8, 129) {
			}));
			this.customSlots.put(140, this.addSlot(new SlotItemHandler(internal, 140, 26, 129) {
			}));
			this.customSlots.put(141, this.addSlot(new SlotItemHandler(internal, 141, 44, 129) {
			}));
			this.customSlots.put(142, this.addSlot(new SlotItemHandler(internal, 142, 62, 129) {
			}));
			this.customSlots.put(143, this.addSlot(new SlotItemHandler(internal, 143, 80, 129) {
			}));
			this.customSlots.put(144, this.addSlot(new SlotItemHandler(internal, 144, 98, 129) {
			}));
			this.customSlots.put(145, this.addSlot(new SlotItemHandler(internal, 145, 314, 129) {
			}));
			this.customSlots.put(146, this.addSlot(new SlotItemHandler(internal, 146, 332, 129) {
			}));
			this.customSlots.put(147, this.addSlot(new SlotItemHandler(internal, 147, 350, 129) {
			}));
			this.customSlots.put(148, this.addSlot(new SlotItemHandler(internal, 148, 368, 129) {
			}));
			this.customSlots.put(149, this.addSlot(new SlotItemHandler(internal, 149, 386, 129) {
			}));
			this.customSlots.put(150, this.addSlot(new SlotItemHandler(internal, 150, 404, 129) {
			}));
			this.customSlots.put(151, this.addSlot(new SlotItemHandler(internal, 151, 8, 147) {
			}));
			this.customSlots.put(152, this.addSlot(new SlotItemHandler(internal, 152, 26, 147) {
			}));
			this.customSlots.put(153, this.addSlot(new SlotItemHandler(internal, 153, 44, 147) {
			}));
			this.customSlots.put(154, this.addSlot(new SlotItemHandler(internal, 154, 62, 147) {
			}));
			this.customSlots.put(155, this.addSlot(new SlotItemHandler(internal, 155, 80, 147) {
			}));
			this.customSlots.put(156, this.addSlot(new SlotItemHandler(internal, 156, 98, 147) {
			}));
			this.customSlots.put(157, this.addSlot(new SlotItemHandler(internal, 157, 314, 147) {
			}));
			this.customSlots.put(158, this.addSlot(new SlotItemHandler(internal, 158, 332, 147) {
			}));
			this.customSlots.put(159, this.addSlot(new SlotItemHandler(internal, 159, 350, 147) {
			}));
			this.customSlots.put(160, this.addSlot(new SlotItemHandler(internal, 160, 368, 147) {
			}));
			this.customSlots.put(161, this.addSlot(new SlotItemHandler(internal, 161, 386, 147) {
			}));
			this.customSlots.put(162, this.addSlot(new SlotItemHandler(internal, 162, 404, 147) {
			}));
			this.customSlots.put(163, this.addSlot(new SlotItemHandler(internal, 163, 8, 165) {
			}));
			this.customSlots.put(164, this.addSlot(new SlotItemHandler(internal, 164, 26, 165) {
			}));
			this.customSlots.put(165, this.addSlot(new SlotItemHandler(internal, 165, 44, 165) {
			}));
			this.customSlots.put(166, this.addSlot(new SlotItemHandler(internal, 166, 62, 165) {
			}));
			this.customSlots.put(167, this.addSlot(new SlotItemHandler(internal, 167, 80, 165) {
			}));
			this.customSlots.put(168, this.addSlot(new SlotItemHandler(internal, 168, 98, 165) {
			}));
			this.customSlots.put(169, this.addSlot(new SlotItemHandler(internal, 169, 314, 165) {
			}));
			this.customSlots.put(170, this.addSlot(new SlotItemHandler(internal, 170, 332, 165) {
			}));
			this.customSlots.put(171, this.addSlot(new SlotItemHandler(internal, 171, 350, 165) {
			}));
			this.customSlots.put(172, this.addSlot(new SlotItemHandler(internal, 172, 368, 165) {
			}));
			this.customSlots.put(173, this.addSlot(new SlotItemHandler(internal, 173, 386, 165) {
			}));
			this.customSlots.put(174, this.addSlot(new SlotItemHandler(internal, 174, 404, 165) {
			}));
			this.customSlots.put(175, this.addSlot(new SlotItemHandler(internal, 175, 8, 183) {
			}));
			this.customSlots.put(176, this.addSlot(new SlotItemHandler(internal, 176, 26, 183) {
			}));
			this.customSlots.put(177, this.addSlot(new SlotItemHandler(internal, 177, 44, 183) {
			}));
			this.customSlots.put(178, this.addSlot(new SlotItemHandler(internal, 178, 62, 183) {
			}));
			this.customSlots.put(179, this.addSlot(new SlotItemHandler(internal, 179, 80, 183) {
			}));
			this.customSlots.put(180, this.addSlot(new SlotItemHandler(internal, 180, 98, 183) {
			}));
			this.customSlots.put(181, this.addSlot(new SlotItemHandler(internal, 181, 314, 183) {
			}));
			this.customSlots.put(182, this.addSlot(new SlotItemHandler(internal, 182, 332, 183) {
			}));
			this.customSlots.put(183, this.addSlot(new SlotItemHandler(internal, 183, 350, 183) {
			}));
			this.customSlots.put(184, this.addSlot(new SlotItemHandler(internal, 184, 368, 183) {
			}));
			this.customSlots.put(185, this.addSlot(new SlotItemHandler(internal, 185, 386, 183) {
			}));
			this.customSlots.put(186, this.addSlot(new SlotItemHandler(internal, 186, 404, 183) {
			}));
			int si;
			int sj;
			for (si = 0; si < 3; ++si)
				for (sj = 0; sj < 9; ++sj)
					this.addSlot(new Slot(inv, sj + (si + 1) * 9, 127 + 8 + sj * 18, 47 + 84 + si * 18));
			for (si = 0; si < 9; ++si)
				this.addSlot(new Slot(inv, si, 127 + 8 + si * 18, 47 + 142));
		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(PlayerEntity player) {
			return true;
		}

		@Override
		public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
			ItemStack itemstack = ItemStack.EMPTY;
			Slot slot = (Slot) this.inventorySlots.get(index);
			if (slot != null && slot.getHasStack()) {
				ItemStack itemstack1 = slot.getStack();
				itemstack = itemstack1.copy();
				if (index < 186) {
					if (!this.mergeItemStack(itemstack1, 186, this.inventorySlots.size(), true)) {
						return ItemStack.EMPTY;
					}
					slot.onSlotChange(itemstack1, itemstack);
				} else if (!this.mergeItemStack(itemstack1, 0, 186, false)) {
					if (index < 186 + 27) {
						if (!this.mergeItemStack(itemstack1, 186 + 27, this.inventorySlots.size(), true)) {
							return ItemStack.EMPTY;
						}
					} else {
						if (!this.mergeItemStack(itemstack1, 186, 186 + 27, false)) {
							return ItemStack.EMPTY;
						}
					}
					return ItemStack.EMPTY;
				}
				if (itemstack1.getCount() == 0) {
					slot.putStack(ItemStack.EMPTY);
				} else {
					slot.onSlotChanged();
				}
				if (itemstack1.getCount() == itemstack.getCount()) {
					return ItemStack.EMPTY;
				}
				slot.onTake(playerIn, itemstack1);
			}
			return itemstack;
		}

		@Override /** 
					* Merges provided ItemStack with the first avaliable one in the container/player inventor between minIndex (included) and maxIndex (excluded). Args : stack, minIndex, maxIndex, negativDirection. /!\ the Container implementation do not check if the item is valid for the slot
					*/
		protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
			boolean flag = false;
			int i = startIndex;
			if (reverseDirection) {
				i = endIndex - 1;
			}
			if (stack.isStackable()) {
				while (!stack.isEmpty()) {
					if (reverseDirection) {
						if (i < startIndex) {
							break;
						}
					} else if (i >= endIndex) {
						break;
					}
					Slot slot = this.inventorySlots.get(i);
					ItemStack itemstack = slot.getStack();
					if (slot.isItemValid(itemstack) && !itemstack.isEmpty() && areItemsAndTagsEqual(stack, itemstack)) {
						int j = itemstack.getCount() + stack.getCount();
						int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
						if (j <= maxSize) {
							stack.setCount(0);
							itemstack.setCount(j);
							slot.putStack(itemstack);
							flag = true;
						} else if (itemstack.getCount() < maxSize) {
							stack.shrink(maxSize - itemstack.getCount());
							itemstack.setCount(maxSize);
							slot.putStack(itemstack);
							flag = true;
						}
					}
					if (reverseDirection) {
						--i;
					} else {
						++i;
					}
				}
			}
			if (!stack.isEmpty()) {
				if (reverseDirection) {
					i = endIndex - 1;
				} else {
					i = startIndex;
				}
				while (true) {
					if (reverseDirection) {
						if (i < startIndex) {
							break;
						}
					} else if (i >= endIndex) {
						break;
					}
					Slot slot1 = this.inventorySlots.get(i);
					ItemStack itemstack1 = slot1.getStack();
					if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
						if (stack.getCount() > slot1.getSlotStackLimit()) {
							slot1.putStack(stack.split(slot1.getSlotStackLimit()));
						} else {
							slot1.putStack(stack.split(stack.getCount()));
						}
						slot1.onSlotChanged();
						flag = true;
						break;
					}
					if (reverseDirection) {
						--i;
					} else {
						++i;
					}
				}
			}
			return flag;
		}

		@Override
		public void onContainerClosed(PlayerEntity playerIn) {
			super.onContainerClosed(playerIn);
			if (!bound && (playerIn instanceof ServerPlayerEntity)) {
				if (!playerIn.isAlive() || playerIn instanceof ServerPlayerEntity && ((ServerPlayerEntity) playerIn).hasDisconnected()) {
					for (int j = 0; j < internal.getSlots(); ++j) {
						playerIn.dropItem(internal.extractItem(j, internal.getStackInSlot(j).getCount(), false), false);
					}
				} else {
					for (int i = 0; i < internal.getSlots(); ++i) {
						playerIn.inventory.placeItemBackInInventory(playerIn.world,
								internal.extractItem(i, internal.getStackInSlot(i).getCount(), false));
					}
				}
			}
		}

		private void slotChanged(int slotid, int ctype, int meta) {
			if (this.world != null && this.world.isRemote()) {
				CompressedBarrelsMod.PACKET_HANDLER.sendToServer(new GUISlotChangedMessage(slotid, x, y, z, ctype, meta));
				handleSlotAction(entity, slotid, ctype, meta, x, y, z);
			}
		}
	}

	public static class ButtonPressedMessage {
		int buttonID, x, y, z;

		public ButtonPressedMessage(PacketBuffer buffer) {
			this.buttonID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
		}

		public ButtonPressedMessage(int buttonID, int x, int y, int z) {
			this.buttonID = buttonID;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public static void buffer(ButtonPressedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.buttonID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
		}

		public static void handler(ButtonPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}

	public static class GUISlotChangedMessage {
		int slotID, x, y, z, changeType, meta;

		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		public GUISlotChangedMessage(PacketBuffer buffer) {
			this.slotID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.changeType = buffer.readInt();
			this.meta = buffer.readInt();
		}

		public static void buffer(GUISlotChangedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.slotID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			buffer.writeInt(message.changeType);
			buffer.writeInt(message.meta);
		}

		public static void handler(GUISlotChangedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}

	static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}

	private static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}
}
