package com.kiber.talismans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import com.kiber.talismans.item.TalismanItems;
import com.kiber.talismans.talisman.TalismanEffectHandler;

public class TalismansMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger(TalismansModConstants.MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Talismans Mod for Minecraft 1.21.11");

		TalismanItems.register();
		TalismanEffectHandler.register();

		ServerTickEvents.END_SERVER_TICK.register(TalismanEffectHandler::onServerTick);
	}
}
