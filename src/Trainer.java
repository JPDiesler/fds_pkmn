import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import javax.sound.sampled.*;
public class Trainer {

    private int id;
    private String name;
    private Pokemon[] team;
    
    public Trainer(int id, String name, Pokemon[] team) {
        this.id = id;
        this.name = name;
        this.team = team;
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
    
    public Pokemon[] getTeam() {
        return team;
    }
    
    public void setTeam(Pokemon[] team) {
        this.team = team;
    }

    public Pokemon getRandomBattleReadyPokemon() {
        List<Pokemon> battleReadyPokemons = Arrays.stream(team)
                                                  .filter(pokemon -> pokemon.getHp() > 0)
                                                  .collect(Collectors.toList());

        if (battleReadyPokemons.isEmpty()) {
            System.out.println(this.getName() + " has no battle-ready Pokemon left!");
            return null;
        }

        int index = (int) (Math.random() * battleReadyPokemons.size());
        return battleReadyPokemons.get(index);
    }

    public int getRandomBattleReadyPokemonCount() {
        List<Pokemon> battleReadyPokemons = Arrays.stream(team)
                                                  .filter(pokemon -> pokemon.getHp() > 0)
                                                  .collect(Collectors.toList());

        return battleReadyPokemons.size();
    }

    public void printTrainerInfo() {
        System.out.println("Trainer Name: " + this.getName());
        System.out.println("Team:");
        for (Pokemon pokemon : team) {
            if (pokemon != null) {
                System.out.println(" - " + pokemon.getName() + ", HP: " + pokemon.getHp() + ", Type: " + pokemon.getPrimaryType() + (pokemon.getSecondaryType() != null ? "/" + pokemon.getSecondaryType() : ""));
            }
        }
    }

    public void printHealth(Pokemon p1){
        int r = this.getRandomBattleReadyPokemonCount();
        int f = this.team.length - r;
        System.out.println("Trainer " + this.getName()+ " " + "*".repeat(r)+"-".repeat(f));
        double hpPercentage = (double) p1.getHp() / p1.getMaxHp() * 100;
        String colorCode;
        if (hpPercentage >= 50) {
            colorCode = "\u001B[32m"; // Green
        } else if (hpPercentage >= 25) {
            colorCode = "\u001B[33m"; // Yellow
        } else {
            colorCode = "\u001B[31m"; // Red
        }
        System.out.println(colorCode + p1.getName() + ": " + p1.getHp() + "HP / " + p1.getMaxHp() + "HP\u001B[0m");
    }

    public Pokemon handleFainting(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " fainted");
        delay(1000);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\In-Battle_Faint_No_Health.mp3.wav").getAbsoluteFile());
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

        Pokemon battleReadyPokemon = getRandomBattleReadyPokemon();
        if (battleReadyPokemon != null) {
            System.out.println("Trainer " + this.name + " sends out " + battleReadyPokemon.getName() + "!");
            battleReadyPokemon.crie();
        }

        return battleReadyPokemon;
    }

    public int battle(Trainer opponent, boolean verbose) {
        System.out.println("-".repeat(35) + " Pokemon Battle Start " + "-".repeat(35));
        System.out.println();
        Clip clip = null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds\\Pokémon_Diamond_Pearl_Platinum_Trainer_Battle_Music.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
        delay(4000);
        System.out.println("Trainer " + this.name + " challenges Trainer " + opponent.getName() + " to a battle!");
        Weather weather = Weather.CLEAR;
        int turn = 1;
        Pokemon pokemon_1 = this.team[0];
        Pokemon pokemon_2 = opponent.team[0];
        System.out.println("Trainer " + this.name + " sends out " + pokemon_1.getName());
        pokemon_1.crie();
        delay(1250);
        System.out.println("Trainer " + opponent.getName() + " sends out " + pokemon_2.getName());
        pokemon_2.crie();
    
        while (true) {
            System.out.println("\n" + "-".repeat(35) + " Turn " + turn + " " + "-".repeat(35) + "\n");
            System.out.println("-".repeat(35)+" Health Status "+"-".repeat(35));
            this.printHealth(pokemon_1);
            opponent.printHealth(pokemon_2);
            System.out.println();

            int pokemon_1_move = getMove(pokemon_1);
            int pokemon_2_move = getMove(pokemon_2);
    
            delay(3000, 6000);
    
            
            Pokemon initialPokemon1 = pokemon_1;
            Pokemon initialPokemon2 = pokemon_2;
            // Determine which Pokemon attacks first
            if (pokemon_1.getSpeed() >= pokemon_2.getSpeed()) {
                pokemon_2 = attack(pokemon_1, pokemon_2, pokemon_1_move, weather, verbose, opponent);
                delay(2000);
                if (pokemon_2 == initialPokemon2) {
                    pokemon_1 = attack(pokemon_2, pokemon_1, pokemon_2_move, weather, verbose, this);

                }
            } else {
                pokemon_1 = attack(pokemon_2, pokemon_1, pokemon_2_move, weather, verbose, this);
                delay(2000);
                if (pokemon_1 == initialPokemon1) {
                    pokemon_2 = attack(pokemon_1, pokemon_2, pokemon_1_move, weather, verbose, opponent);
                }
            }
    
            delay(2000);
    
            // Apply weather and status effects at the end of the turn
            if(pokemon_1 != null && pokemon_2 != null){
                weather.applyEffect(pokemon_1, pokemon_2);
            pokemon_1.resetAfterDuration();
            pokemon_2.resetAfterDuration();
            }
            
    
            // Check if a Pokemon fainted due to weather or status effects
            if (pokemon_1 != null && pokemon_1.getHp() <= 0) {
                pokemon_1 = this.handleFainting(pokemon_1);
                
            }
            if (pokemon_2 != null && pokemon_2.getHp() <= 0) {
                pokemon_2 = opponent.handleFainting(pokemon_2);
                
            }
            if (pokemon_1 == null) {
                    System.out.println("Trainer " + opponent.getName() + " wins the battle!");
                    clip.stop();
                    return -1; // Opponent wins
                }
            if (pokemon_2 == null) {
                    System.out.println("Trainer " + this.name + " wins the battle!");
                    clip.stop();
                    return 1; // This trainer wins
                }
            turn++;
        }
    }
    
    private int getMove(Pokemon pokemon) {
        int move;
        do {
            move = (int) (Math.random() * 4);
        } while (pokemon.getMoves()[move].getAP() <= 0);
        return move;
    }
    
    private void delay(int min, int max) {
        try {
            int delay = min + (int) (Math.random() * ((max - min) + 1));
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private Pokemon attack(Pokemon attacker, Pokemon defender, int move, Weather weather, boolean verbose, Trainer trainer) {
        System.out.println(attacker.getName() + " attacks first!");
        attacker.getMoves()[move].use(attacker, defender, weather, verbose);
        attacker.getMoves()[move].setAP(attacker.getMoves()[move].getAP() - 1);
        if (!verbose) {
            System.out.println(attacker.getName() + " used " + attacker.getMoves()[move].getName());
        }
        if (defender.getHp() <= 0) {
            defender = trainer.handleFainting(defender);
        }
        return defender;
    }
    
    public void healPokemon(Pokemon pokemon) {
        pokemon.setHp(pokemon.getMaxHp());
        for (Move move : pokemon.getMoves()) {
            move.setAP(move.getMaxAP());
        }
    }

    public void healTeam() {
        for (Pokemon pokemon : team) {
            if (pokemon != null) {
                healPokemon(pokemon);
            }
        }
    }
}

