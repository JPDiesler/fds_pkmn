public enum StatusEffect {
    NONE(0, 0),
    BURN(0.0625, 2),
    FREEZE(0, 2),
    PARALYSIS(0, 3),
    POISON(0.0625, -1),
    SLEEP(0, 3),
    CONFUSION(0, 3);

    private final double damage;
    private final int duration;

    StatusEffect(double damage, int duration) {
        this.damage = damage;
        this.duration = duration;
    }

    public double getDamage() {
        return damage;
    }

    public int getDuration() {
        return duration;
    }
}