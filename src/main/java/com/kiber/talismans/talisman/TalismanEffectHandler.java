package com.kiber.talismans.talisman;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import com.kiber.talismans.item.TalismanItems;

public class TalismanEffectHandler {
	private static final int EFFECT_DURATION = 10; // 10 ticks = 0.5 seconds, renewed each tick
	private static final int EFFECT_AMPLIFIER = 0; // Effect level (0 = Level 1)

	public static void register() {
		// Effects registration happens via tick events
	}

	public static void onServerTick(net.minecraft.server.MinecraftServer server) {
		for (ServerPlayer player : server.getPlayerList().getPlayers()) {
			applyTalismanEffects(player);
		}
	}

	private static void applyTalismanEffects(ServerPlayer player) {
		if (player.level().isClientSide()) {
			return;
		}

		Inventory inventory = player.getInventory();

		// Check all inventory slots
		for (int i = 0; i < inventory.getContainerSize(); i++) {
			ItemStack itemStack = inventory.getItem(i);

			if (itemStack.isEmpty()) {
				continue;
			}

			Item item = itemStack.getItem();

			if (item == TalismanItems.HEALTH_TALISMAN) {
				applyEffect(player, MobEffects.REGENERATION, 1);
			} else if (item == TalismanItems.SPEED_TALISMAN) {
				applyEffect(player, MobEffects.MOVEMENT_SPEED, 1);
			} else if (item == TalismanItems.STRENGTH_TALISMAN) {
				applyEffect(player, MobEffects.DAMAGE_BOOST, 1);
			} else if (item == TalismanItems.FIRE_RESISTANCE_TALISMAN) {
				applyEffect(player, MobEffects.FIRE_RESISTANCE, 0);
			}
		}
	}

	private static void applyEffect(ServerPlayer player, net.minecraft.core.Holder<MobEffect> effect, int amplifier) {
		if (!player.hasEffect(effect)) {
			player.addEffect(new MobEffectInstance(effect, EFFECT_DURATION + 1, amplifier, false, false));
		} else {
			MobEffectInstance instance = player.getEffect(effect);
			if (instance != null && instance.getDuration() <= 1) {
				player.removeEffectNoUpdate(effect);
				player.addEffect(new MobEffectInstance(effect, EFFECT_DURATION + 1, amplifier, false, false));
			}
		}
	}
}
