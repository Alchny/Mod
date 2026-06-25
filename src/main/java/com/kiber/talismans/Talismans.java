package com.kiber.talismans;

import net.minecraft.resources.Identifier;

/**
 * Shared constants and helpers for the Talismans mod.
 */
public final class Talismans {
	public static final String MOD_ID = "talismans";

	private Talismans() {
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
