package com.kiber.talismans.handler;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import com.kiber.talismans.item.TalismanItem;

/**
 * Wires up the two ways talismans take effect:
 * <ul>
 *     <li>Passive talismans tick every server tick for each online player.</li>
 *     <li>Combat talismans trigger whenever the player damages a living entity.</li>
 * </ul>
 */
public final class TalismanHandlers {
	private TalismanHandlers() {
	}

	public static void register() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayer player : server.getPlayerList().getPlayers()) {
				tickPlayer(player);
			}
		});

		ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamage, damageTaken, blocked) -> {
			if (source.getEntity() instanceof ServerPlayer player && player != entity) {
				onPlayerHit(player, entity);
			}
		});
	}

	private static void tickPlayer(ServerPlayer player) {
		Inventory inventory = player.getInventory();

		for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
			ItemStack stack = inventory.getItem(slot);

			if (stack.getItem() instanceof TalismanItem talisman) {
				talisman.onHeldTick(player);
			}
		}
	}

	private static void onPlayerHit(ServerPlayer attacker, LivingEntity target) {
		Inventory inventory = attacker.getInventory();

		for (int slot = 0; slot < inventory.getContainerSize(); slot++) {
			ItemStack stack = inventory.getItem(slot);

			if (stack.getItem() instanceof TalismanItem talisman) {
				talisman.onHitTarget(attacker, target);
			}
		}
	}
}
