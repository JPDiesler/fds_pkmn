import java.util.Locale;

public class SpecialMove extends Move {

    /**
     * Default constructor for the SpecialMove class.
     * Creates a new instance of SpecialMove with predefined parameters.
     * The move created is "Blast Burn" with type FIRE, power 150, accuracy 90,
     * attack points 15, effect BURN, and effect chance 30%.
     */
    public SpecialMove() {
        super("Blast Burn", Type.FIRE, 150, 90, 15, Effect.BURN, 0.3);
    }

    /**
     * Constructor for the SpecialMove class.
     * Creates a new instance of SpecialMove without an effect.
     *
     * @param name         The name of the move.
     * @param type         The type of the move.
     * @param power        The power of the move.
     * @param accuracy     The accuracy of the move.
     * @param attackPoints The number of attack points for the move.
     */
    public SpecialMove(String name, Type type, int power, int accuracy, int attackPoints) {
        super(name, type, power, accuracy, attackPoints);
    }

    /**
     * Constructor for the SpecialMove class.
     * Creates a new instance of SpecialMove with an effect
     *
     * @param name         The name of the move.
     * @param type         The type of the move.
     * @param power        The power of the move.
     * @param accuracy     The accuracy of the move.
     * @param attackPoints The number of attack points for the move.
     * @param effect       The effect of the move.
     * @param effectChance The chance of the effect occurring.
     */
    public SpecialMove(String name, Type type, int power, int accuracy, int attackPoints, Effect effect,
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
            double attackDefenseRatio = (double) user.getSpecialAttack() / (double) target.getSpecialDefense();
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
                this.getEffect().apply(user, target, verbose);
            }
        }
    }

    /**
     * Calculates the base damage of a special move.
     *
     * @param user               The Pokemon using the move.
     * @param attackDefenseRatio The ratio of the user's special attack to the
     *                           defender's special defense.
     * @return The base damage of the move.
     */
    private double calculateBaseDamage(Pokemon user, double attackDefenseRatio) {
        return (((((user.level * 2) / 5) + 2) * this.getPower() * attackDefenseRatio) / 50) + 2;
    }

    /**
     * Prints a message about the effectiveness of a move and whether it was a
     * critical hit.
     *
     * @param crit    The critical hit multiplier. If it's 1.5, a message about a
     *                critical hit is printed.
     * @param message The message about the effectiveness of the move.
     */
    private void printEffectivenessAndCrit(double crit, String message) {
        if (crit == 1.5) {
            System.out.println("Critical Hit!");
        }
        System.out.println(message);
    }

    /**
     * Prints verbose output about the calculation of a move's damage.
     *
     * @param attackDefenseRatio The ratio of the user's special attack to the
     *                           defender's special defense.
     * @param randomMultiplier   The random multiplier applied to the damage.
     * @param stabMultiplier     The same-type attack bonus multiplier.
     * @param typeMultiplier     The type effectiveness multiplier.
     * @param critMultiplier     The critical hit multiplier.
     * @param weatherMultiplier  The weather multiplier.
     * @param baseDamage         The base damage of the move.
     * @param effectiveDamage    The final damage dealt by the move.
     * @param defender           The Pokemon defending against the move.
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
