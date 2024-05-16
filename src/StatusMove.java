import java.util.Locale;

public class StatusMove extends Move {

    public StatusMove(String name, Type type, int accuracy, Effect effect, int attackPoints) {
        super(name, type, 0, accuracy, attackPoints, effect, 1);
    }

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

        double typeMultiplier = getMoveEffectiveness(target.getPrimaryType(), target.getSecondaryType(), getType());
        switch (String.valueOf(typeMultiplier)) {
            case "0.0":
                System.out.println(String.format("It doesn't affect %s...", target.getName()));
                break;
            case "1.0":
                playMoveSFX();
                printEffectivenessAndCrit(1, "Normal effectiveness.");
                hitSFX();
                break;
            case "2.0":
                printEffectivenessAndCrit(1, "It's super effective!");
                playMoveSFX();
                superEffectiveHitSFX();
                break;
            default:
                playMoveSFX();
                printEffectivenessAndCrit(1, "It's not very effective...");
                notVeryEffectiveHitSFX();
                break;
        }

        if (typeMultiplier > 0) {
            this.getEffect().apply(user, target, verbose);
            if (verbose) {
                printVerboseOutput(typeMultiplier, target);
            }
        }
    }

    private void printEffectivenessAndCrit(double crit, String message) {
        if (crit == 1.5) {
            System.out.println("Critical Hit!");
        }
        System.out.println(message);
    }

    private void printVerboseOutput(double typeMultiplier, Pokemon defender) {
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
                "Type_multiplier:    %.2f\n",
                typeMultiplier));
    }

}
