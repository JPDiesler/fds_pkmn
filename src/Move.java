import javax.sound.sampled.*;
import java.io.File;
import java.util.concurrent.CountDownLatch;

public abstract class Move {
    private String name;
    private Type type;
    private int power;
    private int accuracy;
    private int attackPoints;
    private int maxAttackPoints;
    private Effect effect;
    private double effectChance;

    // Constructor

    /**
     * No standard constructor for a Move. Since Move is an abstract class, it
     * cannot be instantiated.
     * Use physical, special, or status move instead.
     */

    /**
     * Constructor for a Move without an effect.
     *
     * @param name            The name of the move.
     * @param type            The type of the move.
     * @param power           The power of the move.
     * @param accuracy        The accuracy of the move.
     * @param maxAttackPoints The maximum number of attack points for the move.
     */
    public Move(String name, Type type, int power, int accuracy, int maxAttackPoints) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.attackPoints = maxAttackPoints;
        this.maxAttackPoints = attackPoints;
        this.effect = Effect.NONE;
        this.effectChance = 0.0;
    }

    /**
     * Constructor for a Move with an effect.
     *
     * @param name            The name of the move.
     * @param type            The type of the move.
     * @param power           The power of the move.
     * @param accuracy        The accuracy of the move.
     * @param maxAttackPoints The maximum number of attack points for the move.
     * @param effect          The effect of the move.
     * @param effectChance    The chance of the effect occurring.
     */
    public Move(String name, Type type, int power, int accuracy, int maxAttackPoints, Effect effect,
            double effectChance) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.attackPoints = maxAttackPoints;
        this.maxAttackPoints = maxAttackPoints;
        this.effect = effect;
        this.effectChance = effectChance;
    }

    /**
     * Calculates the STAB (Same Type Attack Bonus) multiplier for the move.
     *
     * @param user The Pokemon using the move.
     * @return The STAB multiplier.
     */
    protected double calculateStabMultiplier(Pokemon user) {
        System.out.println(this.getType());
        System.out.println(user.getPrimaryType());
        System.out.println(user.getSecondaryType());
        if (this.getType() == user.getPrimaryType() || this.getType() == user.getSecondaryType()) {
            return 1.5;
        } else {
            return 1.0;
        }
    }

    /**
     * Calculates the effectiveness of the move against a Pokemon with a given
     * primary and secondary type.
     *
     * @param primaryType   The primary type of the target Pokemon.
     * @param secondaryType The secondary type of the target Pokemon.
     * @param moveType      The type of the move.
     * @return The effectiveness multiplier.
     */
    protected double getMoveEffectiveness(Type primaryType, Type secondaryType, Type moveType) {
        double primaryEffectiveness = 1.0;
        double secondaryEffectiveness = 1.0;

        if (moveType.getEffective().contains(primaryType)) {
            primaryEffectiveness *= 2.0;
        }
        if (secondaryType != null && moveType.getEffective().contains(secondaryType)) {
            secondaryEffectiveness *= 2.0;
        }

        if (moveType.getIneffective().contains(primaryType)) {
            primaryEffectiveness /= 2.0;
        }
        if (secondaryType != null && moveType.getIneffective().contains(secondaryType)) {
            secondaryEffectiveness /= 2.0;
        }

        if (moveType.getImmune().contains(primaryType)) {
            primaryEffectiveness = 0.0;
        }
        if (secondaryType != null && moveType.getImmune().contains(secondaryType)) {
            secondaryEffectiveness = 0.0;
        }

        return primaryEffectiveness * secondaryEffectiveness;
    }

    /**
     * Uses the move on a target Pokemon.
     *
     * @param user    The Pokemon using the move.
     * @param target  The Pokemon that the move is being used on.
     * @param weather The current weather.
     * @param verbose If true, additional information will be printed.
     */
    public abstract void use(Pokemon user, Pokemon target, Weather weather, boolean verbose);

    /**
     * Calculates the damage done by confusion.
     *
     * @param user The Pokemon that is confused.
     * @return The damage done by confusion.
     */
    protected double calculateConfusionDamage(Pokemon user) {
        double ad_ratio = (double) user.getAttack() / (double) user.getDefense();
        return (((((user.getLevel() * 2) / 5) + 2) * 40 * ad_ratio) / 50) + 2;
    }

    /**
     * Plays the sound effect for a normal hit.
     */
    protected void hitSFX() {
        playSFX("sounds/hit/Hit_Normal_Damage.wav");
    }

    /**
     * Plays the sound effect for a not very effective hit.
     */
    protected void notVeryEffectiveHitSFX() {
        playSFX("sounds/hit/Hit_Not_Very_Effective.wav");
    }

    /**
     * Plays the sound effect for a super effective hit.
     */
    protected void superEffectiveHitSFX() {
        playSFX("sounds/hit/Hit_Super_Effective.wav");
    }

    /**
     * Plays the sound effect for the move.
     */
    protected void playMoveSFX() {
        try {
            File dir = new File("sounds/moves/");
            File[] files = dir.listFiles();
            if (files != null) {
                File bestMatch = null;
                int bestWordCount = Integer.MAX_VALUE;
                for (File file : files) {
                    if (file.getName().contains(this.name)) {
                        int wordCount = file.getName().split("\\s+").length;
                        if (wordCount < bestWordCount) {
                            bestMatch = file;
                            bestWordCount = wordCount;
                        }
                    }
                }
                if (bestMatch != null) {
                    playSFX(bestMatch.getPath());
                }
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * Plays a sound effect from a given file path.
     *
     * @param path The path to the sound effect file.
     */
    protected void playSFX(String path) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            CountDownLatch latch = new CountDownLatch(1);
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    latch.countDown();
                }
            });
            clip.open(audioInputStream);
            clip.start();
            latch.await(); // Wait for the sound to finish
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // Getters
    public String getName() {
        return this.name;
    }

    public Type getType() {
        return this.type;
    }

    public int getPower() {
        return this.power;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public int getAP() {
        return this.attackPoints;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setAP(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public Effect getEffect() {
        return this.effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public double getEffectChance() {
        return this.effectChance;
    }

    public void setEffectChance(double effectChance) {
        this.effectChance = effectChance;
    }

    public int getMaxAP() {
        return this.maxAttackPoints;
    }

    public void setMaxAP(int maxAttackPoints) {
        this.maxAttackPoints = maxAttackPoints;
    }
}
