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
    private StatusEffect effect;
    private float effectChance;

    // Constructor
    public Move(String name, Type type, int power, int accuracy, int maxAttackPoints) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.attackPoints = maxAttackPoints;
        this.maxAttackPoints = attackPoints;
    }

    public Move(String name, Type type, int power, int accuracy, int maxAttackPoints, StatusEffect effect, float effectChance) {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.attackPoints = maxAttackPoints;
        this.maxAttackPoints = maxAttackPoints;
        this.effect = effect;
        this.effectChance = effectChance;
    }

    public double calculateStabMultiplier(Pokemon user) {
        if (this.getType() == user.getPrimaryType() || this.getType() == user.getSecondaryType()) {
            return 1.5;
        } else {
            return 1.0;
        }
    }

    public double getMoveEffectiveness(Type primaryType, Type secondaryType, Type moveType) {
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

    public abstract void use(Pokemon user, Pokemon target, Weather weather,boolean verbose);

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

    public StatusEffect getEffect() {
        return this.effect;
    }

    public void setEffect(StatusEffect effect) {
        this.effect = effect;
    }   

    public float getEffectChance() {
        return this.effectChance;
    }

    public void setEffectChance(float effectChance) {
        this.effectChance = effectChance;
    }

    public int getMaxAP() {
        return this.maxAttackPoints;
    }

    public void setMaxAP(int maxAttackPoints) {
        this.maxAttackPoints = maxAttackPoints;
    }

    public void hitSFX() {
        playSFX("sounds/hit/Hit_Normal_Damage.wav");
    }

    public void notVeryEffectiveHitSFX() {
        playSFX("sounds/hit/Hit_Not_Very_Effective.wav");
    }

    public void superEffectiveHitSFX() {
        playSFX("sounds/hit/Hit_Super_Effective.wav");
    }

    public void playMoveSFX() {
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

    public void playSFX(String path){
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
}
