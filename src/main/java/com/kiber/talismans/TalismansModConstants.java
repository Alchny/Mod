package com.kiber.talismans;

import net.minecraft.resources.ResourceLocation;

public class TalismansModConstants {
	public static final String MOD_ID = "talismans";

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
	}
}
