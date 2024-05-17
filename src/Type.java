import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Enum representing the types of Pokemon.
 * Each type has a list of types it is immune to, ineffective against, and
 * effective against.
 */
public enum Type {
    NORMAL(Arrays.asList(), Arrays.asList("ROCK", "STEEL"), Arrays.asList("GHOST")),
    FIRE(Arrays.asList(), Arrays.asList("FIRE", "WATER", "ROCK", "DRAGON"),
            Arrays.asList("GRASS", "ICE", "BUG", "STEEL")),
    WATER(Arrays.asList(), Arrays.asList("WATER", "GRASS", "DRAGON"), Arrays.asList("FIRE", "GROUND", "ROCK")),
    ELECTRIC(Arrays.asList("GROUND"), Arrays.asList("ELECTRIC", "GRASS", "DRAGON"), Arrays.asList("WATER", "FLYING")),
    GRASS(Arrays.asList(), Arrays.asList("FIRE", "GRASS", "POISON", "FLYING", "BUG", "DRAGON", "STEEL"),
            Arrays.asList("WATER", "GROUND", "ROCK")),
    ICE(Arrays.asList(), Arrays.asList("FIRE", "WATER", "ICE", "STEEL"),
            Arrays.asList("GRASS", "GROUND", "FLYING", "DRAGON")),
    FIGHTING(Arrays.asList("GHOST"), Arrays.asList("POISON", "FLYING", "PSYCHIC", "BUG", "FAIRY"),
            Arrays.asList("NORMAL", "ICE", "ROCK", "DARK", "STEEL")),
    POISON(Arrays.asList("STEEL"), Arrays.asList("POISON", "GROUND", "ROCK", "GHOST"), Arrays.asList("GRASS", "FAIRY")),
    GROUND(Arrays.asList("FLYING"), Arrays.asList("GRASS", "BUG"),
            Arrays.asList("FIRE", "ELECTRIC", "POISON", "ROCK", "STEEL")),
    FLYING(Arrays.asList(), Arrays.asList("ELECTRIC", "ROCK", "STEEL"), Arrays.asList("GRASS", "FIGHTING", "BUG")),
    PSYCHIC(Arrays.asList("DARK"), Arrays.asList("STEEL", "PSYCHIC"), Arrays.asList("FIGHTING", "POISON")),
    BUG(Arrays.asList(), Arrays.asList("FIRE", "FIGHTING", "POISON", "FLYING", "GHOST", "STEEL", "FAIRY"),
            Arrays.asList("GRASS", "PSYCHIC", "DARK")),
    ROCK(Arrays.asList(), Arrays.asList("FIGHTING", "GROUND", "STEEL"), Arrays.asList("FIRE", "ICE", "FLYING", "BUG")),
    GHOST(Arrays.asList("NORMAL"), Arrays.asList("DARK"), Arrays.asList("PSYCHIC", "GHOST")),
    DRAGON(Arrays.asList("FAIRY"), Arrays.asList("STEEL"), Arrays.asList("DRAGON")),
    DARK(Arrays.asList(), Arrays.asList("FIGHTING", "DARK", "FAIRY"), Arrays.asList("PSYCHIC", "GHOST")),
    STEEL(Arrays.asList("POISON"), Arrays.asList("FIRE", "WATER", "ELECTRIC", "STEEL"),
            Arrays.asList("ICE", "ROCK", "FAIRY")),
    FAIRY(Arrays.asList(), Arrays.asList("FIRE", "POISON", "STEEL"), Arrays.asList("FIGHTING", "DRAGON", "DARK"));

    /**
     * Map to convert string values to their corresponding Type.
     */
    private static final Map<String, Type> stringToType = Arrays.stream(Type.values())
            .collect(Collectors.toMap(Type::name, Function.identity()));

    private final List<String> immuneStrings;
    private final List<String> ineffectiveStrings;
    private final List<String> effectiveStrings;

    private List<Type> immune;
    private List<Type> ineffective;
    private List<Type> effective;

    /**
     * Constructor for the Type enum.
     *
     * @param immuneStrings      The list of types this type is immune to.
     * @param ineffectiveStrings The list of types this type is ineffective against.
     * @param effectiveStrings   The list of types this type is effective against.
     */
    Type(List<String> immuneStrings, List<String> ineffectiveStrings, List<String> effectiveStrings) {
        this.immuneStrings = immuneStrings;
        this.ineffectiveStrings = ineffectiveStrings;
        this.effectiveStrings = effectiveStrings;
    }

    /**
     * Returns the list of types this type is immune to.
     *
     * @return The list of types this type is immune to.
     */
    public List<Type> getImmune() {
        if (immune == null) {
            immune = immuneStrings.stream().map(stringToType::get).collect(Collectors.toList());
        }
        return immune;
    }

    /**
     * Returns the list of types this type is ineffective against.
     *
     * @return The list of types this type is ineffective against.
     */
    public List<Type> getIneffective() {
        if (ineffective == null) {
            ineffective = ineffectiveStrings.stream().map(stringToType::get).collect(Collectors.toList());
        }
        return ineffective;
    }

    /**
     * Returns the list of types this type is effective against.
     *
     * @return The list of types this type is effective against.
     */
    public List<Type> getEffective() {
        if (effective == null) {
            effective = effectiveStrings.stream().map(stringToType::get).collect(Collectors.toList());
        }
        return effective;
    }

    /**
     * Returns the color associated with this type.
     *
     * @return The color associated with this type.
     */
    public String getColor() {
        switch (this) {
            case NORMAL:
                return "\u001B[47m\u001B[30m"; // Bright White background with Black text
            case FIRE:
                return "\u001B[101m\u001B[30m"; // Bright Red background with Black text
            case WATER:
                return "\u001B[44m\u001B[37m"; // Dark Blue background with White text
            case ELECTRIC:
                return "\u001B[103m\u001B[30m"; // Bright Yellow background with Black text
            case GRASS:
                return "\u001B[42m\u001B[30m"; // Dark Green background with Black text
            case ICE:
                return "\u001B[106m\u001B[30m"; // Bright Cyan background with Black text
            case FIGHTING:
                return "\u001B[43m\u001B[30m"; // Dark Yellow (Brown) background with Black text
            case POISON:
                return "\u001B[105m\u001B[30m"; // Bright Purple background with Black text
            case GROUND:
                return "\u001B[43m\u001B[30m"; // Dark Yellow (Brown) background with Black text
            case FLYING:
                return "\u001B[46m\u001B[30m"; // Dark Cyan background with Black text
            case PSYCHIC:
                return "\u001B[105m\u001B[30m"; // Bright Purple background with Black text
            case BUG:
                return "\u001B[42m\u001B[30m"; // Dark Green background with Black text
            case ROCK:
                return "\u001B[43m\u001B[30m"; // Dark Yellow (Brown) background with Black text
            case GHOST:
                return "\u001B[45m\u001B[37m"; // Dark Purple background with White text
            case DRAGON:
                return "\u001B[105m\u001B[30m"; // Bright Purple background with Black text
            case DARK:
                return "\u001B[40m\u001B[37m"; // Dark Black background with White text
            case STEEL:
                return "\u001B[47m\u001B[30m"; // Bright White background with Black text
            case FAIRY:
                return "\u001B[105m\u001B[30m"; // Bright Purple background with Black text
            default:
                return "\u001B[0m"; // Reset
        }
    }

    /**
     * Returns the tag associated with this type.
     *
     * @return The tag associated with this type.
     */
    public String getTag() {
        switch (this) {
            case NORMAL:
                return this.getColor() + " NORMAL " + "\u001B[0m";
            case FIRE:
                return this.getColor() + " FIRE " + "\u001B[0m";
            case WATER:
                return this.getColor() + " WATER " + "\u001B[0m";
            case ELECTRIC:
                return this.getColor() + " ELECTRIC " + "\u001B[0m";
            case GRASS:
                return this.getColor() + " GRASS " + "\u001B[0m";
            case ICE:
                return this.getColor() + " ICE " + "\u001B[0m";
            case FIGHTING:
                return this.getColor() + " FIGHTING " + "\u001B[0m";
            case POISON:
                return this.getColor() + " POISON " + "\u001B[0m";
            case GROUND:
                return this.getColor() + " GROUND " + "\u001B[0m";
            case FLYING:
                return this.getColor() + " FLYING " + "\u001B[0m";
            case PSYCHIC:
                return this.getColor() + " PSYCHIC " + "\u001B[0m";
            case BUG:
                return this.getColor() + " BUG " + "\u001B[0m";
            case ROCK:
                return this.getColor() + " ROCK " + "\u001B[0m";
            case GHOST:
                return this.getColor() + " GHOST " + "\u001B[0m";
            case DRAGON:
                return this.getColor() + " DRAGON " + "\u001B[0m";
            case DARK:
                return this.getColor() + " DARK " + "\u001B[0m";
            case STEEL:
                return this.getColor() + " STEEL " + "\u001B[0m";
            case FAIRY:
                return this.getColor() + " FAIRY " + "\u001B[0m";
            default:
                return "";
        }
    }
}