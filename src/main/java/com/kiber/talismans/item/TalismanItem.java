package com.kiber.talismans.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;

/**
 * Base class for all talismans. A talisman grants its benefit simply by being
 * present anywhere in the player's inventory.
 *
 * <p>There are two flavours:
 * <ul>
 *     <li>{@link EffectTalismanItem} – a passive talisman that keeps a status
 *     effect active on its owner.</li>
 *     <li>{@link CombatTalismanItem} – a combat talisman that triggers an effect
 *     on a mob the owner hits.</li>
 * </ul>
 */
public abstract class TalismanItem extends Item {
	protected TalismanItem(Properties properties) {
		super(properties);
	}

	/**
	 * Called every server tick for each player carrying this talisman.
	 */
	public void onHeldTick(ServerPlayer player) {
	}

	/**
	 * Called when a player carrying this talisman damages a living entity.
	 */
	public void onHitTarget(ServerPlayer attacker, LivingEntity target) {
	}
}
