import java.util.Locale;

/**
 * The PhysicalMove class extends the Move class and represents a physical move
 * that a Pokemon can use in a battle.
 * A physical move is a move that uses the user's Attack stat and the target's
 * Defense stat to calculate damage.
 * This class provides methods to execute a physical move and calculate its
 * damage.
 */
public class PhysicalMove extends Move {

    /**
     * Default constructor for the PhysicalMove class.
     * Creates a new instance of PhysicalMove with predefined parameters.
     * The move's name is "Close Combat", its type is Fighting, its power is 120,
     * its accuracy is 100, its attack points are 5, and it has a 100% chance to
     * lower the users's Defense and Special Defense stats by 1.
     */
    public PhysicalMove() {
        super("Close Combat", Type.FIGHTING, 120, 100, 5, Effect.LOWERDEFENCES, 1);
    }

    /**
     * Constructor for the PhysicalMove class.
     * Creates a new instance of PhysicalMove without an effect.
     *
     * @param name         The name of the move.
     * @param type         The type of the move.
     * @param power        The power of the move.
     * @param accuracy     The accuracy of the move.
     * @param attackPoints The maximum number of attack points for the move.
     */
    public PhysicalMove(String name, Type type, int power, int accuracy, int attackPoints) {
        super(name, type, power, accuracy, attackPoints);
    }

    /**
     * Constructor for the PhysicalMove class.
     * Creates a new instance of PhysicalMove with an effect.
     *
     * @param name         The name of the move.
     * @param type         The type of the move.
     * @param power        The power of the move.
     * @param accuracy     The accuracy of the move.
     * @param attackPoints The maximum number of attack points for the move.
     * @param effect       The effect of the move.
     * @param effectChance The chance of the effect occurring.
     */
    public PhysicalMove(String name, Type type, int power, int accuracy, int attackPoints, Effect effect,
            double effectChance) {
        super(name, type, power, accuracy, attackPoints, effect, effectChance);
    }

    /**
     * Executes a special move from one Pokemon to another.
     *
     * @param user    The Pokemon that is using the move.
     * @param target  The Pokemon that the move is being used on.
     * @param weather The current weather, which can affect the move's accuracy and
     *                damage.
     * @param verbose If true, additional information about the move's execution
     *                will be printed.
     *
     *                This method first checks if the move hits, which is determined
     *                by the move's accuracy and potentially affected by the
     *                weather.
     *                If the move hits, it calculates the damage based on several
     *                factors:
     *                - A random multiplier to add some variability to the damage.
     *                - A STAB (Same-Type Attack Bonus) multiplier, which increases
     *                the damage if the user's type matches the move's type.
     *                - A type multiplier, which is based on the effectiveness of
     *                the move's type against the target's type(s).
     *                - A weather multiplier, which can increase or decrease the
     *                damage based on the weather.
     *                - A critical hit multiplier, which can increase the damage if
     *                a critical hit occurs.
     *
     *                The method then applies the damage to the target's HP.
     *                If the verbose parameter is true, it also prints detailed
     *                information about the damage calculation.
     */
    @Override
    public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
        this.setAP(this.getAP() - 1);
        StatusEffect userStatus = user.getStatus();
        if (userStatus == StatusEffect.PARALYSIS || userStatus == StatusEffect.FREEZE
                || userStatus == StatusEffect.SLEEP) {
            if (userStatus == StatusEffect.PARALYSIS && Math.random() < 0.90) {
                System.out.println(String.format(Locale.US, "%s is paralyzed and can't move!", user.getName()));
                return;
            }
            if (userStatus == StatusEffect.FREEZE) {
                System.out.println(String.format(Locale.US, "%s is frozen solid!", user.getName()));
                return;
            }
            if (userStatus == StatusEffect.SLEEP) {
                System.out.println(String.format(Locale.US, "%s is fast asleep!", user.getName()));
                return;
            }
        }
        if (userStatus == StatusEffect.CONFUSION && Math.random() < 0.50) {
            System.out.println(
                    String.format(Locale.US, "%s is confused and hurt itself in its confusion!", user.getName()));
            user.setHp((int) (user.getHp() - this.calculateConfusionDamage(user)));
            if (verbose) {
                System.out.println("Damage: " + (int) this.calculateConfusionDamage(user));
            }
            return;
        }

        double accuracy = (weather == Weather.FOG) ? this.getAccuracy() * 0.75 : this.getAccuracy();
        boolean hit = Math.random() * 100 < accuracy;
        System.out.println(String.format("%s used %s", user.getName(), getName()));
        if (!hit) {
            System.out.println(String.format(Locale.US, "%s missed the attack!", user.getName()));
            return;
        }

        double randomMultiplier = Math.random() * 0.16 + 0.85;
        double stabMultiplier = calculateStabMultiplier(user);
        double typeMultiplier = getMoveEffectiveness(target.getPrimaryType(), target.getSecondaryType(), getType());
        double weatherMultiplier = weather.getMultiplier(this.getType());
        double critMultiplier = Math.random() < 0.1 ? 1.5 : 1.0;

        switch (String.valueOf(typeMultiplier)) {
            case "0.0":
                System.out.println(String.format("It doesn't affect %s...", target.getName()));
                break;
            case "1.0":
                playMoveSFX();
                printEffectivenessAndCrit(critMultiplier, "Normal effectiveness.");
                hitSFX();
                break;
            case "2.0":
                printEffectivenessAndCrit(critMultiplier, "It's super effective!");
                playMoveSFX();
                superEffectiveHitSFX();
                break;
            default:
                playMoveSFX();
                printEffectivenessAndCrit(critMultiplier, "It's not very effective...");
                notVeryEffectiveHitSFX();
                break;
        }

        if (typeMultiplier > 0) {
            double attackDefenseRatio = (double) user.getAttack() / (double) target.getDefense();
            double baseDamage = calculateBaseDamage(user, attackDefenseRatio);
            double effectiveDamage = baseDamage * stabMultiplier * typeMultiplier * randomMultiplier * critMultiplier
                    * weatherMultiplier;

            target.setHp((int) (target.getHp() - effectiveDamage));

            if (verbose) {
                printVerboseOutput(attackDefenseRatio, randomMultiplier, stabMultiplier, typeMultiplier, critMultiplier,
                        weatherMultiplier, baseDamage, effectiveDamage, target);
                System.out.println();
            }

            if (this.getEffect() != Effect.NONE && Math.random() < this.getEffectChance()) {
                System.out.println();
                this.getEffect().apply(user, target, verbose);
            }

        }
    }

    /**
     * Calculates the base damage of a physical move.
     *
     * @param user               The Pokemon that is using the move.
     * @param attackDefenseRatio The ratio of the user's Attack stat to the target's
     *                           Defense stat.
     * @return The base damage of the move.
     *
     *         This method calculates the base damage of a physical move using the
     *         following formula:
     *         (((((user's level * 2) / 5) + 2) * move's power * attack/defense
     *         ratio) / 50) + 2
     *         This formula is based on the one used in the Pokemon games.
     */
    private double calculateBaseDamage(Pokemon user, double attackDefenseRatio) {
        return (((((user.level * 2) / 5) + 2) * this.getPower() * attackDefenseRatio) / 50) + 2;
    }

    /**
     * Prints a message about the effectiveness of a move and whether it was a
     * critical hit.
     *
     * @param crit    The critical hit multiplier. If this is 1.5, a message about a
     *                critical hit will be printed.
     * @param message A message about the effectiveness of the move.
     *
     *                This method first checks if the move was a critical hit by
     *                checking if the crit parameter is 1.5.
     *                If it was a critical hit, it prints a message about this.
     *                It then prints the message about the effectiveness of the
     *                move.
     */
    private void printEffectivenessAndCrit(double crit, String message) {
        if (crit == 1.5) {
            System.out.println("Critical Hit!");
        }
        System.out.println(message);
    }

    /**
     * Prints detailed information about the execution of a move.
     *
     * @param attackDefenseRatio The ratio of the user's Attack stat to the target's
     *                           Defense stat.
     * @param randomMultiplier   The random multiplier that was applied to the
     *                           damage.
     * @param stabMultiplier     The STAB (Same-Type Attack Bonus) multiplier that
     *                           was applied to the damage.
     * @param typeMultiplier     The type multiplier that was applied to the damage.
     * @param critMultiplier     The critical hit multiplier that was applied to the
     *                           damage.
     * @param weatherMultiplier  The weather multiplier that was applied to the
     *                           damage.
     * @param baseDamage         The base damage of the move.
     * @param effectiveDamage    The effective damage of the move, after all
     *                           multipliers were applied.
     * @param defender           The Pokemon that the move was used on.
     *
     *                           This method prints detailed information about the
     *                           execution of a move, including the attack/defense
     *                           ratio, the various multipliers that were applied to
     *                           the damage, the base damage, the effective damage,
     *                           and the types of the user and the defender.
     */
    private void printVerboseOutput(double attackDefenseRatio, double randomMultiplier, double stabMultiplier,
            double typeMultiplier, double critMultiplier, double weatherMultiplier, double baseDamage,
            double effectiveDamage, Pokemon defender) {
        Type attack = this.getType();
        Type defenderPrimary = defender.getPrimaryType();
        Type defenderSecondary = defender.getSecondaryType();
        if (defenderSecondary == null) {
            System.out.println(attack.getTag() + " ---> " + defenderPrimary.getTag());
        } else {
            System.out.println(
                    attack.getTag() + " ---> " + defenderPrimary.getTag() + " / " + defenderSecondary.getTag());
        }
        System.out.println(String.format(Locale.US,
                "A/D_ratio:          %.2f\n" +
                        "Random_multiplier:  %.2f\n" +
                        "Stab_multiplier:    %.2f\n" +
                        "Type_multiplier:    %.2f\n" +
                        "Crit_multiplier:    %.2f\n" +
                        "Weather_multiplier: %.2f\n" +
                        "Base Damage:        %.2f\n" +
                        "Effective Damage:   %d",
                attackDefenseRatio, randomMultiplier, stabMultiplier, typeMultiplier, critMultiplier, weatherMultiplier,
                baseDamage, (int) effectiveDamage));
    }
}
