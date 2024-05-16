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
    private int statusDuration = 0;

    private int expirience;
    private int hp_AS;
    private int attack_AS;
    private int defense_AS;
    private int special_attack_AS;
    private int special_defense_AS;
    private int speed_AS;

    private int max_hp;
    private int current_hp;
    private int attack;
    private int defense;
    private int special_attack;
    private int special_defense;
    private int speed;

    private final int iv = 31;
    private final int ev = 252;
    
    private Move[] moves;
    
    public Pokemon(int id, String name, Type primary_type, int level, StatusEffect status, int hp, int attack, int defense, int special_attack, int special_defense, int speed, Move[] moves) {
        this.id = id;
        this.name = name;
        this.primary_type = primary_type;
        this.secondary_type = null;
        this.level = level;
        this.expirience = (int) Math.pow(level,3);
        this.status = status;
        this.hp_AS = hp;
        this.attack_AS = attack;
        this.defense_AS = defense;
        this.special_attack_AS = special_attack;
        this.special_defense_AS = special_defense;
        this.speed_AS = speed;
        this.moves = moves;

        calculateHP();
        calculateStats();
        this.setHp(max_hp);
    }

    public Pokemon(int id, String name, Type primary_type, Type secondary_type, int level, StatusEffect status, int hp, int attack, int defense, int special_attack, int special_defense, int speed, Move[] moves) {
        this.id = id;
        this.name = name;
        this.primary_type = primary_type;
        this.secondary_type = secondary_type;
        this.level = level;
        this.status = status;
        this.hp_AS = hp;
        this.attack_AS = attack;
        this.defense_AS = defense;
        this.special_attack_AS = special_attack;
        this.special_defense_AS = special_defense;
        this.speed_AS = speed;
        this.moves = moves;

        calculateHP();
        calculateStats();
        this.setHp(max_hp);
    }

    private void calculateHP(){
        int hp = (int) ((((2.0 * hp_AS + iv + (ev / 4.0)) * level) / 100.0) + level + 10);
        this.max_hp = hp;
    }

    private int calculateStat(int base){
        int stat = (int) ((((2.0 * base + iv + (ev / 4.0)) * level) / 100.0) + 5);
        return stat;
    }

    private void calculateStats(){
        this.attack = calculateStat(this.attack_AS);
        this.defense = calculateStat(this.defense_AS);
        this.special_attack = calculateStat(this.special_attack_AS);
        this.special_defense = calculateStat(this.special_defense_AS);
        this.speed = calculateStat(this.speed_AS);
    }

    public void applyStatusEffect(StatusEffect newStatus) {
        this.status = newStatus;
        this.statusDuration = 0;
    }

    public void applyStatusDamage(boolean verbose) {
        int damage = (int)(status.getDamage() * this.max_hp);
        this.setHp(this.current_hp-damage);
        if(this.status == StatusEffect.BURN || this.status == StatusEffect.POISON){
            System.out.println(name + " took damage from " + status + ".");
            if (verbose) {
                System.out.println(name + " took " + damage + " damage from " + status + ".");
            }
        }else{
            switch(this.status){
                case SLEEP:
                    System.out.println(name + " is fast asleep.");
                    break;
                case FREEZE:
                    System.out.println(name + " is frozen solid.");
                    break;
                case PARALYSIS:
                    System.out.println(name + " is paralyzed.");
                    break;
                case CONFUSION:
                    System.out.println(name + " is confused.");
                    break;
                default:
                    break;
            }
        }
        
    }

    public void resetAfterDuration(boolean verbose) {
        if (this.status != StatusEffect.NONE) {
            this.statusDuration++;
            applyStatusDamage(verbose);
            if (this.statusDuration == this.status.getDuration()) {
                System.out.println(name + " is no longer " + status.toString() + ".");
                this.status = StatusEffect.NONE;
                this.statusDuration = 0;
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
        int oldMaxHp = this.max_hp;
        int oldAttack = this.attack;
        int oldDefense = this.defense;
        int oldSpecialAttack = this.special_attack;
        int oldSpecialDefense = this.special_defense;
        int oldSpeed = this.speed;
    
        this.level = level;
        this.expirience = (int) Math.pow(level,3);
        calculateHP();
        calculateStats();
        
        System.out.println(name + " leveled up to " + level + "!\n");
        System.out.println("HP: " + oldMaxHp + " -> " + this.max_hp + " (+" + (this.max_hp - oldMaxHp) + ")");
        System.out.println("Attack: " + oldAttack + " -> " + this.attack + " (+" + (this.attack - oldAttack) + ")");
        System.out.println("Defense: " + oldDefense + " -> " + this.defense + " (+" + (this.defense - oldDefense) + ")");
        System.out.println("Special Attack: " + oldSpecialAttack + " -> " + this.special_attack + " (+" + (this.special_attack - oldSpecialAttack) + ")");
        System.out.println("Special Defense: " + oldSpecialDefense + " -> " + this.special_defense + " (+" + (this.special_defense - oldSpecialDefense) + ")");
        System.out.println("Speed: " + oldSpeed + " -> " + this.speed + " (+" + (this.speed - oldSpeed) + ")");
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
   
    public void addEXP(int exp){
        System.out.println(name + " gained " + exp + " exp.");
        this.expirience += exp;
        if(this.expirience >= Math.pow(level+1,3)){
            this.setLevel(level+1);
        }
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

    public void printInfo() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Primary Type: " + primary_type);
        System.out.println("Secondary Type: " + (secondary_type != null ? secondary_type : "None"));
        System.out.println("Level: " + level);
        System.out.println("Status: " + status);
        System.out.println("Max HP: " + max_hp);
        System.out.println("Current HP: " + current_hp);
        System.out.println("Attack: " + attack);
        System.out.println("Defense: " + defense);
        System.out.println("Special Attack: " + special_attack);
        System.out.println("Special Defense: " + special_defense);
        System.out.println("Speed: " + speed);
        System.out.println("Moves: ");
        for (Move move : moves) {
            System.out.println(" - " + move.getName());
        }
    }

}
