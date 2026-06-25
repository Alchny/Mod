# Talismans Mod (Талисманы)

A Fabric mod for **Minecraft 1.21.11** that adds 18 magical talismans. A talisman
works simply by being **anywhere in your inventory** — no need to hold or equip it.
Talismans glow, are non-stackable, and have a coloured rarity name plus a tooltip
describing what they do. Find them all in their own creative-inventory tab.

Полностью переведён на русский язык (Russian language included).

## Passive talismans (пассивные)

While carried, these keep a status effect active on you:

| Talisman | Эффект |
| --- | --- |
| Health Talisman / Талисман здоровья | Regeneration |
| Speed Talisman / Талисман скорости | Speed |
| Strength Talisman / Талисман силы | Strength |
| Fire Resistance Talisman / Талисман огнестойкости | Fire Resistance |
| Water Breathing Talisman / Талисман водного дыхания | Water Breathing |
| Night Vision Talisman / Талисман ночного зрения | Night Vision |
| Leaping Talisman / Талисман прыжка | Jump Boost II |
| Haste Talisman / Талисман шахтёра | Haste II |
| Resistance Talisman / Талисман стойкости | Resistance |
| Luck Talisman / Талисман удачи | Luck |

## Combat talismans (боевые)

While carried, these trigger a special effect on any mob you hit (melee or ranged):

| Talisman | Эффект против моба |
| --- | --- |
| Poison Talisman / Талисман яда | Poison II |
| Wither Talisman / Талисман иссушения | Wither |
| Weakness Talisman / Талисман слабости | Weakness |
| Frost Talisman / Талисман мороза | Slowness II |
| Flame Talisman / Талисман пламени | Sets the target on fire |
| Levitation Talisman / Талисман левитации | Levitation |
| Vampire Talisman / Талисман вампира | Heals you (lifesteal) |
| Blinding Talisman / Талисман ослепления | Blindness + Nausea |

## How it works

- A server-tick handler refreshes passive effects for every online player who is
  carrying a passive talisman.
- A `ServerLivingEntityEvents.AFTER_DAMAGE` handler applies combat-talisman
  effects whenever a player damages a living entity.

## Building

```bash
./gradlew build
```

The built mod JAR is written to `build/libs/`. A GitHub Actions workflow builds the
mod automatically on every push and uploads the JAR as an artifact.

## Versions

- Minecraft `1.21.11`
- Fabric Loader `0.19.3`
- Fabric API `0.141.4+1.21.11`
- Java `21`
