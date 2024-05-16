public enum Effect {
    NONE,
    BURN,
    PARALYSIS,
    FREEZE,
    POISON,
    SLEEP,
    CONFUSE,
    LOWERATTACKS,
    LOWERDEFENCES;

    public void apply(Pokemon user, Pokemon target, boolean verbose) {
        switch (this) {
            case CONFUSE:
                target.applyStatusEffect(StatusEffect.CONFUSION);
                System.out.println(target.getName() + " became confused! " + StatusEffect.CONFUSION.getTag());
                break;
            case SLEEP:
                target.applyStatusEffect(StatusEffect.SLEEP);
                System.out.println(target.getName() + " fell asleep! " + StatusEffect.SLEEP.getTag());
                break;
            case FREEZE:
                target.applyStatusEffect(StatusEffect.FREEZE);
                System.out.println(target.getName() + " was frozen solid! " + StatusEffect.FREEZE.getTag());
                break;
            case POISON:
                target.applyStatusEffect(StatusEffect.POISON);
                System.out.println(target.getName() + " was poisoned! " + StatusEffect.POISON.getTag());
                break;
            case BURN:
                target.applyStatusEffect(StatusEffect.BURN);
                System.out.println(target.getName() + " was burned! " + StatusEffect.BURN.getTag());
                break;
            case PARALYSIS:
                target.applyStatusEffect(StatusEffect.PARALYSIS);
                System.out.println(target.getName() + " was paralyzed! " + StatusEffect.PARALYSIS.getTag());
                break;
            case LOWERATTACKS:
                user.setAttack(user.getAttack() - 1);
                user.setSpecialAttack(user.getSpecialAttack() - 1);
                System.out.println(user.getName() + "'s Attack fell!");
                delay(500);
                System.out.println(user.getName() + "'s Sp. Attack fell!");
                if (verbose) {
                    System.out.println("Attack: -1 Sp. Attack: -1");
                }
                break;
            case LOWERDEFENCES:
                user.setDefense(user.getDefense() - 1);
                user.setSpecialDefense(user.getSpecialDefense() - 1);
                System.out.println(user.getName() + "'s Defence fell!");
                delay(500);
                System.out.println(user.getName() + "'s Sp. Defence fell!");
                if (verbose) {
                    System.out.println("Defence: -1 Sp. Defence: -1");
                }
                break;
            default:
                break;
        }
    }

    private void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
