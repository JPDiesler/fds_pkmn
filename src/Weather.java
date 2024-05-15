import java.io.File;
import java.util.concurrent.CountDownLatch;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;

public enum Weather {
    CLEAR,
    SUNNY,
    RAIN,
    SANDSTORM,
    HAIL,
    HARSH_SUNLIGHT,
    HEAVY_RAIN,
    EXTREMELY_HARSH_SUNLIGHT,
    FOG,
    SNOW;

    public Weather applyEffect(Pokemon pokemon1, Pokemon pokemon2,Boolean verbose) {
        switch (this) {
            case SANDSTORM:
                System.out.println("The sandstorm rages!");
                applyWeatherDamage(pokemon1,verbose);
                applyWeatherDamage(pokemon2,verbose);
                break;
            case HAIL:
                System.out.println("The hailstorm rages!");
                applyWeatherDamage(pokemon1,verbose);
                applyWeatherDamage(pokemon2,verbose);
                break;
            case CLEAR:
            case SUNNY:
                System.out.println("The sunlight is strong!");
            case RAIN:
                System.out.println("It's raining!");
            case HARSH_SUNLIGHT:
                System.out.println("The sunlight is harsh!");
            case HEAVY_RAIN:
                System.out.println("It's raining heavily!");
            case EXTREMELY_HARSH_SUNLIGHT:
                System.out.println("The sunlight is extremely harsh!");
            case FOG:
                System.out.println("It's foggy!");
            case SNOW:
                System.out.println("It's snowing!");
            default:
                // No effects
                break;
        }
        if (Math.random() < 0.05 && (this == Weather.RAIN || this == Weather.HEAVY_RAIN || this == Weather.SANDSTORM || this == Weather.HAIL)) {
            System.out.println("The weather cleared up!");
            return Weather.CLEAR;
        } else {
            return this;
        }

    }

    public double getMultiplier(Type type) {
        switch (this) {
            case SUNNY:
                if (type == Type.FIRE) {
                    return 1.5;
                } else if (type == Type.WATER) {
                    return 0.5;
                }
                break;
            case RAIN:
                if (type == Type.WATER) {
                    return 1.5;
                } else if (type == Type.FIRE) {
                    return 0.5;
                }
                break;
            case HARSH_SUNLIGHT:
                if (type == Type.FIRE) {
                    return 1.5;
                } else if (type == Type.WATER) {
                    return 0.5;
                }
                break;
            case HEAVY_RAIN:
                if (type == Type.WATER) {
                    return 1.5;
                } else if (type == Type.FIRE) {
                    return 0.5;
                }
                break;
            case EXTREMELY_HARSH_SUNLIGHT:
                if (type == Type.FIRE) {
                    return 1.5;
                } else if (type == Type.WATER) {
                    return 0.5;
                }
                break;
            case FOG:
                if (type == Type.FAIRY) {
                    return 1.5;
                } else if (type == Type.DARK) {
                    return 0.5;
                }
                break;
            case SNOW:
                if (type == Type.ICE) {
                    return 1.5;
                } else if (type == Type.FIRE) {
                    return 0.5;
                }
                break;
            case SANDSTORM:
            case HAIL:
            case CLEAR:
            default:
                return 1.0;
        }
        return 1.0;
    }

    private void applyWeatherDamage(Pokemon target,boolean verbose){
        String sound = "sounds/hit/Hit_Not_Very_Effective.wav";
        if (target != null) {
                    Type[] types1 = target.getTyping();
                    if (!(types1[0] == Type.ROCK || types1[0] == Type.GROUND || types1[0] == Type.STEEL || 
                          types1[1] == Type.ROCK || types1[1] == Type.GROUND || types1[1] == Type.STEEL)) {
                        target.setHp(target.getHp() - target.getMaxHp() / 16);
                        System.out.println(target.getName() + " took damage from the "+this.name().toLowerCase()+"!");
                        if (verbose){
                            System.out.println("Weather damage: "+ target.getMaxHp() / 16);
                        }
                        playSFX(sound);
                        delay(1000);
                    }
                }
    }

    private void playSFX(String path){
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

    private void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
