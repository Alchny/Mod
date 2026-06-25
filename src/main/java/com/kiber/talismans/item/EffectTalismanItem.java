package com.kiber.talismans.item;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

/**
 * A passive talisman that keeps a status effect active on its owner while it is
 * carried. The effect is refreshed shortly before it would run out so it never
 * visibly flickers, and it never overrides a longer effect from another source.
 */
public class EffectTalismanItem extends TalismanItem {
	private static final int REFRESH_DURATION = 60; // ticks (3 seconds)

	private final Holder<MobEffect> effect;
	private final int amplifier;

	public EffectTalismanItem(Properties properties, Holder<MobEffect> effect, int amplifier) {
		super(properties);
		this.effect = effect;
		this.amplifier = amplifier;
	}

	@Override
	public void onHeldTick(ServerPlayer player) {
		MobEffectInstance current = player.getEffect(effect);

		if (current == null || current.getDuration() <= 1) {
			player.addEffect(new MobEffectInstance(effect, REFRESH_DURATION, amplifier, true, false, true));
		}
	}
}
