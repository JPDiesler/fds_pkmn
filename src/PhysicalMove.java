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
    int weatherMultiplier = 1;
    double randomMultiplier = Math.random() * 0.16 + 0.85;
    double stabMultiplier = this.calculateStabMultiplier(user);
    double typeMultiplier = this.getMoveEffectiveness(target.getPrimaryType(), target.getSecondaryType(), this.getType());
    boolean hit = Math.random() * 100 < this.getAccuracy();
    double crit = Math.random() < 0.1 ? 1.5 : 1.0;
    System.out.println(String.format("%s used %s", user.getName(), this.getName()));

    if (hit){
        if (typeMultiplier == 0) {
    System.out.println(String.format("It doesn't affect %s...", target.getName()));
} else if (typeMultiplier == 0.5) {
    this.play_move_SFX();
    printEffectivenessAndCrit(crit, "It's not very effective...");
    this.hit_SFX();
} else if (typeMultiplier == 1) {
    this.play_move_SFX();
    if (verbose){
        printEffectivenessAndCrit(crit, "Normal effectiveness.");
    }else{
        if(crit == 1.5){
            System.out.println("Critical Hit!");
        }
    }
    this.hit_SFX();
    
} else if (typeMultiplier == 2) {
    printEffectivenessAndCrit(crit, "It's super effective!");
    this.play_move_SFX();
    this.super_effective_hit_SFX();
}

    if (typeMultiplier > 0) {
        
        double attackDefenseRatio = (double) user.getAttack() /  (double) target.getDefense();
        double baseDamage = calculateBaseDamage(user, attackDefenseRatio);
        double effectiveDamage = calculateEffectiveDamage(baseDamage, stabMultiplier, typeMultiplier, randomMultiplier, crit, weatherMultiplier);
        target.setHp((int) (target.getHp() - effectiveDamage));

        if (verbose) {
            printVerboseOutput(attackDefenseRatio, randomMultiplier, stabMultiplier, typeMultiplier, crit, baseDamage, effectiveDamage);
        }
    }
    }else{
        System.out.println(String.format(Locale.US,"%s missed the attack!", user.getName()));
    }

    
}

private double calculateBaseDamage(Pokemon user, double attackDefenseRatio) {
    return (((((user.level * 2) / 5) + 2) * this.getPower() * attackDefenseRatio) / 50) + 2;
}

private double calculateEffectiveDamage(double baseDamage, double stabMultiplier, double typeMultiplier, double randomMultiplier, double crit, int weatherMultiplier) {
    return baseDamage * stabMultiplier * typeMultiplier * randomMultiplier * crit * weatherMultiplier;
}

private void printEffectivenessAndCrit(double crit, String message) {
    if (crit == 1.5) {
        System.out.println("Critical Hit!");
    }
    System.out.println(message);
}

private void printVerboseOutput(double attackDefenseRatio, double randomMultiplier, double stabMultiplier, double typeMultiplier, double crit, double baseDamage, double effectiveDamage) {
    System.out.println(String.format(Locale.US, "A/D_ratio: %.2f\nRandom_multiplier: %.2f\nStab_multiplier: %.2f\nType_multiplier: %.2f\nCrit: %.2f\nBase Damage: %.2f\nEffective Damage: %d",
                attackDefenseRatio, randomMultiplier, stabMultiplier, typeMultiplier, crit, baseDamage, (int) effectiveDamage));
}
}
