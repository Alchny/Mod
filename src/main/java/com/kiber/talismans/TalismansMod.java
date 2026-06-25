package com.kiber.talismans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

import com.kiber.talismans.handler.TalismanHandlers;
import com.kiber.talismans.item.ModItemGroups;
import com.kiber.talismans.item.ModItems;

public class TalismansMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(Talismans.MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Talismans Mod for Minecraft 1.21.11");

		ModItems.register();
		ModItemGroups.register();
		TalismanHandlers.register();
	}
}
