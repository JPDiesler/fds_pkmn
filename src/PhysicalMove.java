import java.util.Locale;

public class PhysicalMove extends Move {
    
    public PhysicalMove(String name, Type type, int power, int accuracy, int attackPoints) {
        super(name,type, power, accuracy, attackPoints);
    }

    public PhysicalMove(String name, Type type, int power, int accuracy, int attackPoints, StatusEffect effect, float effectChance) {
        super(name,type, power, accuracy, attackPoints, effect, effectChance);
    }

    @Override
    public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
        boolean hit = Math.random() * 100 < this.getAccuracy();
        System.out.println(String.format("%s used %s", user.getName(), getName()));
    
        if (!hit) {
            System.out.println(String.format(Locale.US,"%s missed the attack!", user.getName()));
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
            double attackDefenseRatio = (double) user.getAttack() /  (double) target.getDefense();
            double baseDamage = calculateBaseDamage(user, attackDefenseRatio);
            double effectiveDamage = baseDamage * stabMultiplier * typeMultiplier * randomMultiplier * critMultiplier * weatherMultiplier;
    
            target.setHp((int) (target.getHp() - effectiveDamage));
    
            if (verbose) {
                printVerboseOutput(attackDefenseRatio, randomMultiplier, stabMultiplier, typeMultiplier, critMultiplier,weatherMultiplier, baseDamage, effectiveDamage,target);
            }
        }
    }

private double calculateBaseDamage(Pokemon user, double attackDefenseRatio) {
    return (((((user.level * 2) / 5) + 2) * this.getPower() * attackDefenseRatio) / 50) + 2;
}

private void printEffectivenessAndCrit(double crit, String message) {
    if (crit == 1.5) {
        System.out.println("Critical Hit!");
    }
    System.out.println(message);
}

private void printVerboseOutput(double attackDefenseRatio, double randomMultiplier, double stabMultiplier, double typeMultiplier, double critMultiplier, double weatherMultiplier, double baseDamage, double effectiveDamage, Pokemon defender) {
    String colorReset = "\u001B[0m";
    Type attack = this.getType();
    Type defenderPrimary = defender.getPrimaryType();
    Type defenderSecondary = defender.getSecondaryType();
    if (defenderSecondary == null) {
        System.out.println(attack.getColor()+" "+attack+" "+colorReset+" ---> "+defenderPrimary.getColor()+" "+defenderPrimary+" "+colorReset);
    }else{
        System.out.println(attack.getColor()+" "+attack+" "+colorReset+" ---> "+defenderPrimary.getColor()+" "+defenderPrimary+" "+colorReset+"/"+defenderSecondary.getColor()+" "+defenderSecondary+" "+colorReset);
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
        attackDefenseRatio, randomMultiplier, stabMultiplier, typeMultiplier, critMultiplier, weatherMultiplier,baseDamage, (int) effectiveDamage));
}
}
