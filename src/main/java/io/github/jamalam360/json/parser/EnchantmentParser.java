package io.github.jamalam360.json.parser;

import com.google.gson.JsonObject;
import io.github.jamalam360.json.JsonHelper;
import io.github.jamalam360.json.ParsedObject;
import io.github.jamalam360.json.Parser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ConstantConditions")
public class EnchantmentParser implements Parser<Enchantment> {
    @Override
    public ParsedObject<Enchantment> parse(String json) {
        JsonObject obj = JsonHelper.getAsJsonObject(json);

        List<EquipmentSlot> slots = new ArrayList<>();

        obj.get("slots").getAsJsonArray().forEach(element -> slots.add(JsonHelper.GSON.fromJson(element, EquipmentSlot.class)));

        Enchantment entry = new Enchantment(JsonHelper.GSON.fromJson(obj.get("rarity"), Enchantment.Rarity.class), JsonHelper.GSON.fromJson(obj.get("target"), EnchantmentTarget.class), slots.toArray(new EquipmentSlot[0])) {
            @Override
            public int getMinLevel() {
                if (obj.has("minLevel")) {
                    return obj.get("minLevel").getAsInt();
                } else {
                    return super.getMinLevel();
                }
            }

            @Override
            public int getMaxLevel() {
                if (obj.has("maxLevel")) {
                    return obj.get("maxLevel").getAsInt();
                } else {
                    return super.getMinLevel();
                }
            }

            @Override
            public int getMinPower(int level) {
                if (obj.has("minPower")) {
                    try {
                        return (int) JsonHelper.getMethod(obj.get("minPower").getAsString(), Integer.class).invoke(this, level);
                    } catch (Exception ignored) {
                    }
                }

                return super.getMinPower(level);
            }

            @Override
            public int getMaxPower(int level) {
                if (obj.has("maxPower")) {
                    try {
                        return (int) JsonHelper.getMethod(obj.get("maxPower").getAsString(), Integer.class).invoke(this, level);
                    } catch (Exception ignored) {
                    }
                }

                return super.getMaxPower(level);
            }

            @Override
            public int getProtectionAmount(int level, DamageSource source) {
                if (obj.has("protectionAmount")) {
                    try {
                        return (int) JsonHelper.getMethod(obj.get("protectionAmount").getAsString(), Integer.class, DamageSource.class).invoke(this, level, source);
                    } catch (Exception ignored) {
                    }
                }

                return super.getProtectionAmount(level, source);
            }

            @Override
            public float getAttackDamage(int level, EntityGroup group) {
                if (obj.has("attackDamage")) {
                    try {
                        return (float) JsonHelper.getMethod(obj.get("attackDamage").getAsString(), Integer.class, EntityGroup.class).invoke(this, level, group);
                    } catch (Exception ignored) {
                    }
                }

                return super.getAttackDamage(level, group);
            }

            @Override
            protected boolean canAccept(Enchantment other) {
                if (obj.has("canAccept")) {
                    try {
                        return (boolean) JsonHelper.getMethod(obj.get("canAccept").getAsString(), Enchantment.class).invoke(this, other);
                    } catch (Exception ignored) {
                    }
                }

                return super.canAccept(other);
            }

            @Override
            public Text getName(int level) {
                if (obj.has("name")) {
                    try {
                        return (Text) JsonHelper.getMethod(obj.get("name").getAsString(), Integer.class).invoke(this, level);
                    } catch (Exception ignored) {
                    }
                }

                return super.getName(level);
            }

            @Override
            public boolean isAcceptableItem(ItemStack stack) {
                if (obj.has("acceptableItem")) {
                    try {
                        return (boolean) JsonHelper.getMethod(obj.get("acceptableItem").getAsString(), ItemStack.class).invoke(this, stack);
                    } catch (Exception ignored) {
                    }
                }

                return super.isAcceptableItem(stack);
            }

            @Override
            public void onTargetDamaged(LivingEntity user, Entity target, int level) {
                super.onTargetDamaged(user, target, level);

                if (obj.has("targetDamaged")) {
                    try {
                        JsonHelper.getMethod(obj.get("targetDamaged").getAsString(), LivingEntity.class, Entity.class, Integer.class).invoke(this, user, target, level);
                    } catch (Exception ignored) {
                    }
                }
            }

            @Override
            public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
                super.onUserDamaged(user, attacker, level);

                if (obj.has("userDamaged")) {
                    try {
                        JsonHelper.getMethod(obj.get("userDamaged").getAsString(), LivingEntity.class, Entity.class, Integer.class).invoke(this, user, attacker, level);
                    } catch (Exception ignored) {
                    }
                }
            }

            @Override
            public boolean isTreasure() {
                if (obj.has("treasure")) {
                    return obj.get("treasure").getAsBoolean();
                } else {
                    return super.isTreasure();
                }
            }

            @Override
            public boolean isCursed() {
                if (obj.has("cursed")) {
                    return obj.get("cursed").getAsBoolean();
                } else {
                    return super.isCursed();
                }
            }

            @Override
            public boolean isAvailableForEnchantedBookOffer() {
                if (obj.has("availableForEnchantedBookOffer")) {
                    return obj.get("availableForEnchantedBookOffer").getAsBoolean();
                } else {
                    return super.isAvailableForEnchantedBookOffer();
                }
            }

            @Override
            public boolean isAvailableForRandomSelection() {
                if (obj.has("availableForRandomSelection")) {
                    return obj.get("availableForRandomSelection").getAsBoolean();
                } else {
                    return super.isAvailableForRandomSelection();
                }
            }
        };

        return new ParsedObject<>(entry, obj.get("identifier").getAsString());
    }
}
