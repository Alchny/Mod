package com.kiber.talismans.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import com.kiber.talismans.TalismansModConstants;

public class TalismanItems {
	public static Item HEALTH_TALISMAN;
	public static Item SPEED_TALISMAN;
	public static Item STRENGTH_TALISMAN;
	public static Item FIRE_RESISTANCE_TALISMAN;

	public static void register() {
		HEALTH_TALISMAN = registerItem("health_talisman", new Item(new Item.Properties()));
		SPEED_TALISMAN = registerItem("speed_talisman", new Item(new Item.Properties()));
		STRENGTH_TALISMAN = registerItem("strength_talisman", new Item(new Item.Properties()));
		FIRE_RESISTANCE_TALISMAN = registerItem("fire_resistance_talisman", new Item(new Item.Properties()));
	}

	private static Item registerItem(String name, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, TalismansModConstants.id(name), item);
	}
}
