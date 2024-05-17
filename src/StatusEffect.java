/**
 * Enum representing the possible status effects a Pokemon can have.
 * Each status effect has a damage rate and a duration.
 */
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

    /**
     * Constructor for the StatusEffect enum.
     *
     * @param damage   The damage rate of the status effect.
     * @param duration The duration of the status effect.
     */
    StatusEffect(double damage, int duration) {
        this.damage = damage;
        this.duration = duration;
    }

    /**
     * Gets the damage rate of the status effect.
     *
     * @return The damage rate.
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Gets the duration of the status effect.
     *
     * @return The duration.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Gets the color associated with the status effect.
     *
     * @return The color.
     */
    private String getColor() {
        switch (this) {
            case BURN:
                return "\u001B[41m\u001B[30m"; // Red background, black text
            case PARALYSIS:
                return "\u001B[43m\u001B[30m"; // Yellow background, black text
            case FREEZE:
                return "\u001B[44m\u001B[37m"; // Blue background, white text
            case POISON:
                return "\u001B[45m\u001B[37m"; // Purple background, white text
            case SLEEP:
                return "\u001B[47m\u001B[30m"; // Grey background, black text
            case CONFUSION:
                return "\u001B[47m\u001B[30m"; // Grey background, black text
            default:
                return "";
        }
    }

    /**
     * Gets the tag associated with the status effect.
     *
     * @return The tag.
     */
    public String getTag() {
        switch (this) {
            case BURN:
                return this.getColor() + " BRN \u001B[0m";
            case PARALYSIS:
                return this.getColor() + " PAR \u001B[0m";
            case FREEZE:
                return this.getColor() + " FRZ \u001B[0m";
            case POISON:
                return this.getColor() + " PSN \u001B[0m";
            case SLEEP:
                return this.getColor() + " SLP \u001B[0m";
            case CONFUSION:
                return this.getColor() + " CNF \u001B[0m";
            default:
                return "";
        }

    }

    /**
     * Converts the status effect to a string.
     *
     * @return The string representation of the status effect.
     */
    public String toString() {
        switch (this) {
            case BURN:
                return "burning";
            case PARALYSIS:
                return "paralyzed";
            case FREEZE:
                return "frozen";
            case POISON:
                return "poisoned";
            case SLEEP:
                return "asleep";
            case CONFUSION:
                return "confused";
            default:
                return "";
        }
    }
}