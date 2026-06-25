package com.kiber.talismans.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;

/**
 * A combat talisman that runs a custom action against any living entity the
 * owner damages (in melee or with projectiles). The action is supplied as a
 * small strategy so each talisman can apply a debuff, set the target alight,
 * heal the attacker, and so on.
 */
public class CombatTalismanItem extends TalismanItem {
	@FunctionalInterface
	public interface CombatEffect {
		void apply(ServerPlayer attacker, LivingEntity target);
	}

	private final CombatEffect effect;

	public CombatTalismanItem(Properties properties, CombatEffect effect) {
		super(properties);
		this.effect = effect;
	}

	@Override
	public void onHitTarget(ServerPlayer attacker, LivingEntity target) {
		effect.apply(attacker, target);
	}
}
