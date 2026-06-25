package com.kiber.talismans.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import com.kiber.talismans.Talismans;

/**
 * A dedicated creative-inventory tab that holds every talisman.
 */
public final class ModItemGroups {
	public static final ResourceKey<CreativeModeTab> TALISMANS = ResourceKey.create(
			Registries.CREATIVE_MODE_TAB, Talismans.id("talismans"));

	private ModItemGroups() {
	}

	public static void register() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TALISMANS, FabricItemGroup.builder()
				.title(Component.translatable("itemGroup." + Talismans.MOD_ID + ".talismans"))
				.icon(() -> new ItemStack(ModItems.STRENGTH))
				.displayItems((context, entries) -> {
					for (TalismanItem talisman : ModItems.ALL) {
						entries.accept(talisman);
					}
				})
				.build());
	}
}
