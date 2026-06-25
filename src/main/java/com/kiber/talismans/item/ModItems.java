package com.kiber.talismans.item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemLore;

import com.kiber.talismans.Talismans;

/**
 * Registers every talisman in the mod. Each talisman is a glowing, rare item
 * whose tooltip (lore) is pulled from the language files.
 */
public final class ModItems {
	/** Registration-ordered list of all talismans, used to fill the creative tab. */
	public static final List<TalismanItem> ALL = new ArrayList<>();

	// --- Passive talismans (effect while carried) ---
	public static final TalismanItem HEALTH = effect("health_talisman", MobEffects.REGENERATION, 0, Rarity.RARE);
	public static final TalismanItem SPEED = effect("speed_talisman", MobEffects.SPEED, 0, Rarity.RARE);
	public static final TalismanItem STRENGTH = effect("strength_talisman", MobEffects.STRENGTH, 0, Rarity.RARE);
	public static final TalismanItem FIRE_RESISTANCE = effect("fire_resistance_talisman", MobEffects.FIRE_RESISTANCE, 0, Rarity.RARE);
	public static final TalismanItem WATER_BREATHING = effect("water_breathing_talisman", MobEffects.WATER_BREATHING, 0, Rarity.RARE);
	public static final TalismanItem NIGHT_VISION = effect("night_vision_talisman", MobEffects.NIGHT_VISION, 0, Rarity.RARE);
	public static final TalismanItem LEAPING = effect("leaping_talisman", MobEffects.JUMP_BOOST, 1, Rarity.RARE);
	public static final TalismanItem HASTE = effect("haste_talisman", MobEffects.HASTE, 1, Rarity.RARE);
	public static final TalismanItem RESISTANCE = effect("resistance_talisman", MobEffects.RESISTANCE, 0, Rarity.EPIC);
	public static final TalismanItem LUCK = effect("luck_talisman", MobEffects.LUCK, 0, Rarity.RARE);

	// --- Combat talismans (effect on the mob you hit) ---
	public static final TalismanItem POISON = combat("poison_talisman", Rarity.RARE,
			(attacker, target) -> target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 1)));
	public static final TalismanItem WITHER = combat("wither_talisman", Rarity.EPIC,
			(attacker, target) -> target.addEffect(new MobEffectInstance(MobEffects.WITHER, 80, 0)));
	public static final TalismanItem WEAKNESS = combat("weakness_talisman", Rarity.RARE,
			(attacker, target) -> target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 140, 0)));
	public static final TalismanItem FROST = combat("frost_talisman", Rarity.RARE,
			(attacker, target) -> target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 120, 1)));
	public static final TalismanItem FLAME = combat("flame_talisman", Rarity.RARE,
			(attacker, target) -> target.setRemainingFireTicks(Math.max(target.getRemainingFireTicks(), 100)));
	public static final TalismanItem LEVITATION = combat("levitation_talisman", Rarity.RARE,
			(attacker, target) -> target.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 50, 0)));
	public static final TalismanItem VAMPIRE = combat("vampire_talisman", Rarity.EPIC,
			(attacker, target) -> attacker.heal(2.0F));
	public static final TalismanItem BLINDING = combat("blinding_talisman", Rarity.RARE,
			(attacker, target) -> {
				target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 80, 0));
				target.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 80, 0));
			});

	private ModItems() {
	}

	/** Forces class loading so all static fields are registered. */
	public static void register() {
	}

	private static TalismanItem effect(String name, Holder<MobEffect> effect, int amplifier, Rarity rarity) {
		return register(name, rarity, props -> new EffectTalismanItem(props, effect, amplifier));
	}

	private static TalismanItem combat(String name, Rarity rarity, CombatTalismanItem.CombatEffect effect) {
		return register(name, rarity, props -> new CombatTalismanItem(props, effect));
	}

	private static TalismanItem register(String name, Rarity rarity, Function<Item.Properties, TalismanItem> factory) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, Talismans.id(name));

		Item.Properties properties = new Item.Properties()
				.setId(key)
				.stacksTo(1)
				.rarity(rarity)
				.component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)
				.component(DataComponents.LORE, new ItemLore(List.of(
						Component.translatable("lore." + Talismans.MOD_ID + "." + name)
								.withStyle(ChatFormatting.GRAY))));

		TalismanItem item = factory.apply(properties);
		Registry.register(BuiltInRegistries.ITEM, key, item);
		ALL.add(item);
		return item;
	}
}
