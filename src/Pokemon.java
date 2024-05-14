import javax.sound.sampled.*;
import java.io.File;
import java.util.concurrent.CountDownLatch;

public class Pokemon {

    public int id;
    public String name;
    public Type primary_type;
    public Type secondary_type;
    public int level;
    public StatusEffect status;
    private int statusDuration;

    private int max_hp;
    private int current_hp;
    private int attack;
    private int defense;
    private int special_attack;
    private int special_defense;
    private int speed;
    
    private Move[] moves;
    
    public Pokemon(int id, String name, Type primary_type, Type secondary_type, int level, StatusEffect status, int max_hp, int current_hp, int attack, int defense, int special_attack, int special_defense, int speed, Move[] moves) {
        this.id = id;
        this.name = name;
        this.primary_type = primary_type;
        this.secondary_type = secondary_type;
        this.level = level;
        this.status = status;
        this.max_hp = max_hp;
        this.current_hp = current_hp;
        this.attack = attack;
        this.defense = defense;
        this.special_attack = special_attack;
        this.special_defense = special_defense;
        this.speed = speed;
        this.moves = moves;
    }

    public void applyStatusEffect(StatusEffect newStatus) {
        this.status = newStatus;
        this.statusDuration = newStatus.getDuration();
        System.out.println(name + " is now " + newStatus);
    }

    private void applyStatusDamage() {
        int damage = (int)(status.getDamage() * this.max_hp);
        this.setHp(this.current_hp-damage);
        System.out.println(name + " took " + damage + " damage from " + status + ".");
    }

    public void resetAfterDuration() {
        if (statusDuration > 0) {
            statusDuration--;
            applyStatusDamage();
            if (statusDuration == 0) {
                System.out.println(name + "'s " + status + " wore off.");
                status = StatusEffect.NONE;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getPrimaryType() {
        return primary_type;
    }

    public void setPrimaryType(Type primary_type) {
        this.primary_type = primary_type;
    }

    public Type getSecondaryType() {
        return secondary_type;
    }

    public void setSecondaryType(Type secondary_type) {
        this.secondary_type = secondary_type;
    }

    public Type[] getTyping() {
        return new Type[] { primary_type, secondary_type };
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public StatusEffect getStatus() {
        return status;
    }

    public void setStatus(StatusEffect status) {
        this.status = status;
    }

    public int getMaxHp() {
        return max_hp;
    }

    public void setMaxHp(int max_hp) {
        this.max_hp = max_hp;
    }

    public int getHp() {
        return current_hp;
    }

    public void setHp(int current_hp) {
        if (current_hp > max_hp) {
            this.current_hp = max_hp;
        } else if (current_hp < 0) {
            this.current_hp = 0;
        }else{
            this.current_hp = current_hp;
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return special_attack;
    }

    public void setSpecialAttack(int special_attack) {
        this.special_attack = special_attack;
    }

    public int getSpecialDefense() {
        return special_defense;
    }

    public void setSpecialDefense(int special_defense) {
        this.special_defense = special_defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Move[] getMoves() {
        return moves;
    }

    public void setMoves(Move[] moves) {
        this.moves = moves;
    }
    
    public void crie() {
        try {
            String formattedId = String.format("%03d", this.id);
            File dir = new File("sounds/pokemon/");
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().contains(formattedId)) {
                        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
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
                        break; // Only play the first matching file
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}
