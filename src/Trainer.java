import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import javax.sound.sampled.*;

public class Trainer {

    private int id;
    private String name;
    private String title;
    private Pokemon[] team;
    private List<Pokemon> pc;

    private Clip clip;

    /**
     * Default constructor for the Trainer class.
     * Initializes a Trainer with a predefined set of Pokemon and moves.
     */
    public Trainer() {
        this.id = 0;
        this.name = "Red";
        this.title = "PKMN-Trainer";

        Move CloseCombat = new PhysicalMove("Close Combat", Type.FIGHTING, 120, 100, 5);
        Move BlastBurn = new SpecialMove("Blast Burn", Type.FIRE, 150, 90, 5);
        Move GunkShot = new PhysicalMove("Gunk Shot", Type.POISON, 120, 80, 5);
        Move Earthquake = new PhysicalMove("Earthquake", Type.GROUND, 100, 100, 10);
        Move Outrage = new PhysicalMove("Outrage", Type.DRAGON, 120, 100, 10);
        Move DracoMeteor = new SpecialMove("Draco Meteor", Type.DRAGON, 130, 90, 5);
        Move FireBlast = new SpecialMove("Fire Blast", Type.FIRE, 110, 85, 10);
        Move HyperBeam = new SpecialMove("HyperBeam", Type.NORMAL, 150, 90, 5);
        Move BraveBird = new PhysicalMove("Brave Bird", Type.FLYING, 120, 100, 15);
        Move Hurricane = new SpecialMove("Hurricane", Type.FLYING, 110, 70, 10);
        Move FrenzyPlant = new SpecialMove("Frenzy Plant", Type.GRASS, 150, 90, 5);
        Move FocusPunch = new PhysicalMove("Focus Punch", Type.FIGHTING, 150, 100, 5);
        Move HydroPump = new SpecialMove("Hydro Pump", Type.WATER, 110, 80, 5);
        Move TemperFlare = new PhysicalMove("Temper Flare", Type.FIRE, 75, 100, 10);
        Move IceBeam = new SpecialMove("Ice Beam", Type.ICE, 90, 100, 10);
        Move WildCharge = new PhysicalMove("Wild Charge", Type.ELECTRIC, 90, 100, 15);
        Move Thunder = new SpecialMove("Thunder", Type.ELECTRIC, 110, 70, 10);
        Move TakeDown = new PhysicalMove("Take Down", Type.NORMAL, 90, 85, 20);
        Move Crunch = new PhysicalMove("Crunch", Type.DARK, 80, 100, 15);

        Move[] InfernapeMoves = { CloseCombat, BlastBurn, GunkShot, Earthquake };
        Move[] GarchompMoves = { Earthquake, Outrage, DracoMeteor, FireBlast };
        Move[] LuxrayMoves = { Thunder, WildCharge, TakeDown, Crunch };
        Move[] StaraptorMoves = { BraveBird, HyperBeam, CloseCombat, Hurricane };
        Move[] SceptileMoves = { FrenzyPlant, Earthquake, Outrage, FocusPunch };
        Move[] GyaradosMoves = { HydroPump, Outrage, IceBeam, TemperFlare };

        Pokemon Infernape = new Pokemon(392, "Infernape", Type.FIRE, Type.FIGHTING, 5, StatusEffect.NONE, 269, 254, 200,
                254, 200, 260, InfernapeMoves);
        Pokemon Garchomp = new Pokemon(445, "Garchomp", Type.DRAGON, Type.GROUND, 5, StatusEffect.NONE, 317, 297, 239,
                214, 223, 250, GarchompMoves);
        Pokemon Luxray = new Pokemon(405, "Luxray", Type.ELECTRIC, null, 5, StatusEffect.NONE, 25, 280, 213, 239, 213,
                198, LuxrayMoves);
        Pokemon Staraptor = new Pokemon(398, "Staraptor", Type.NORMAL, Type.FLYING, 5, StatusEffect.NONE, 283, 280, 198,
                165, 181, 247, StaraptorMoves);
        Pokemon Sceptile = new Pokemon(254, "Sceptile", Type.GRASS, null, 5, StatusEffect.NONE, 260, 223, 190, 256, 223,
                2890, SceptileMoves);
        Pokemon Gyarados = new Pokemon(130, "Gyarados", Type.WATER, Type.FLYING, 5, StatusEffect.NONE, 274, 220, 247,
                223, 256, 211, GyaradosMoves);

        this.team = new Pokemon[] { Infernape, Garchomp, Luxray, Staraptor, Sceptile, Gyarados };
        this.pc = new ArrayList<>();
        this.pc.addAll(Arrays.asList(team));
    }

    /**
     * Constructor for the Trainer class.
     * Initializes a Trainer with the provided id, name, title, and team of Pokemon.
     * The team size must be between 1 and 6, inclusive.
     *
     * @param id    The unique identifier for the Trainer.
     * @param name  The name of the Trainer.
     * @param title The title of the Trainer.
     * @param team  The team of Pokemon that the Trainer has. Must contain between 1
     *              and 6 Pokemon.
     * @throws IllegalArgumentException If the team size is not between 1 and 6.
     */
    public Trainer(int id, String name, String title, Pokemon[] team) {
        if (team.length < 1 || team.length > 6) {
            throw new IllegalArgumentException("Team size must be between 1 and 6.");
        }
        this.id = id;
        this.name = name;
        this.title = title;
        this.team = team;
        this.pc = new ArrayList<>();
        this.pc.addAll(Arrays.asList(team));

    }

    // Methods

    /**
     * Prints the trainer's information.
     * 
     * @param verbose If true, prints detailed information about each Pokemon in the
     *                trainer's team.
     */
    public void printTrainerInfo(boolean verbose) {
        System.out.println("Trainer Name: " + this.getName());
        System.out.println("Team:");
        for (Pokemon pokemon : team) {
            if (pokemon != null) {
                System.out.println();
                if (verbose) {
                    pokemon.printInfo();
                } else {
                    System.out.println(" - " + pokemon.getName() + ", HP: " + pokemon.getHp() + ", Type: "
                            + pokemon.getPrimaryType()
                            + (pokemon.getSecondaryType() != null ? "/" + pokemon.getSecondaryType() : ""));
                }
            }
        }
    }

    /**
     * Heals a given Pokemon by setting its HP to its maximum HP and resetting the
     * AP of all its moves.
     * 
     * @param pkmn The Pokemon to heal.
     */
    public void healPokemon(Pokemon pkmn) {
        pkmn.setHp(pkmn.getMaxHp());
        for (Move move : pkmn.getMoves()) {
            move.setAP(move.getMaxAP());
        }
    }

    /**
     * Heals all Pokemon in the trainer's team.
     */
    public void healTeam() {
        for (Pokemon pokemon : team) {
            if (pokemon != null) {
                healPokemon(pokemon);
            }
        }
    }

    /**
     * Makes the trainer sleep for a given number of hours. If the trainer sleeps
     * for 8 hours, they are fully rested and their team is healed.
     * 
     * @param hours The number of hours the trainer should sleep.
     */
    public void sleep(int hours) {
        if (hours > 8) {
            hours = 8;
        }
        System.out.println(this.name + " is sleeping...");
        delay(hours * 1000);
        System.out.println(this.name + " woke up!");
        if (hours == 8) {
            System.out.println(this.name + " is fully rested!");
            healTeam();
        }

    }

    /**
     * Attempts to catch a wild Pokemon from the provided list of available Pokemon.
     * The method simulates a Pokemon battle, where the trainer has a 30% chance to
     * catch the Pokemon.
     * If the Pokemon is caught and the trainer's team has less than 5 Pokemon, the
     * new Pokemon is added to the team.
     * If the team is full, the new Pokemon is transferred to the trainer's PC.
     *
     * @param availablePokemon An array of Pokemon that can be encountered.
     */
    public void catchWildPokemon(Pokemon[] availablePokemon) {
        final int max = 10;
        int randomIndex = (int) (Math.random() * availablePokemon.length);
        Pokemon pkmn = availablePokemon[randomIndex];
        playSoundLoop("sounds\\battle\\Pokemon Wild Battle! Gen4.wav", "00:19.541", "01:11.733");
        System.out.println("A wild " + pkmn.getName() + " appeared!");
        int n = 0;
        while (n < max) {
            System.out.println(this.getName() + " throws a Pokeball...");
            boolean caught = Math.random() < 0.3;
            delay(2000);
            if (caught) {
                System.out.println("Gotcha! " + pkmn.getName() + " was caught!");
                break;
            } else {
                System.out.println(pkmn.getName() + " broke free!");
                delay(2000);
                n++;
            }
        }
        clip.stop();
        if (n == max) {
            System.out.println("Oh no! " + pkmn.getName() + " ran away!");
        } else if (this.getTeam().length <= 5) {
            System.out.println("Congratulations! " + pkmn.getName() + " was added to your team!");
            playSFX("sounds\\battle\\Victory Against Wild Pokémon!.wav");
            Pokemon[] newTeam = Arrays.copyOf(this.getTeam(), this.getTeam().length + 1);
            newTeam[newTeam.length - 1] = pkmn;
            this.setTeam(newTeam);
        } else {
            System.out.println("Congratulations! " + pkmn.getName() + " was added transferred to your PC!");
            playSFX("sounds\\battle\\Victory Against Wild Pokémon!.wav");
            this.pc.add(pkmn);
        }
    }

    /**
     * Initiates a battle between this trainer and an opponent.
     * 
     * @param opponent The opposing trainer.
     * @param verbose  If true, prints detailed information about each turn in the
     *                 battle.
     * @return 1 if this trainer wins the battle, -1 if the opponent wins.
     */
    public int battle(Trainer opponent, boolean verbose) {
        System.out.println("-".repeat(35) + " Pokemon Battle Start " + "-".repeat(35));
        System.out.println();
        String path;
        String loopStartTimestamp = "0:00";
        String loopEndTimestamp = "0:30";
        switch (opponent.getTitle()) {
            case "Champion":
                path = "sounds\\battle\\Pokemon Champion Battle! Gen4.wav";
                loopStartTimestamp = "0:08.917";
                loopEndTimestamp = "01:25.935";
                break;
            case "Elite Four":
                path = "sounds\\battle\\Pokemon Elite Four Battle! Gen4.wav";
                loopStartTimestamp = "0:48.901";
                loopEndTimestamp = "01:23.573";
                break;
            case "Gym Leader":
                path = "sounds\\battle\\Pokemon Gym Leader Battle! Gen4.wav";
                loopStartTimestamp = "0:49.808";
                loopEndTimestamp = "01:24.570";
                break;
            case "Rival":
                path = "sounds\\battle\\Pokemon Rival Battle! Gen4.wav";
                loopStartTimestamp = "0:14.666";
                loopEndTimestamp = "01:07.136";
                break;
            default:
                path = "sounds\\battle\\Pokemon Trainer Battle! Gen4.wav";
                loopStartTimestamp = "0:21.109";
                loopEndTimestamp = "01:39.386";
                break;
        }
        playSoundLoop(path, loopStartTimestamp, loopEndTimestamp);

        delay(1000);
        System.out.println(this.title + " " + this.name + " challenges " + opponent.title + " " + opponent.getName()
                + " to a battle!\n");
        delay(9000);

        Weather weather = Weather.SANDSTORM;
        int turn = 1;
        Pokemon pokemon_1 = this.team[0];
        Pokemon pokemon_2 = opponent.team[0];
        pokemon_1.depoly(this, verbose);
        delay(500);
        pokemon_2.depoly(opponent, verbose);

        while (true) {
            System.out.println("\n" + "-".repeat(35) + " Turn " + turn + " " + "-".repeat(35) + "\n");
            this.printHealth(pokemon_1);
            opponent.printHealth(pokemon_2);
            System.out.println();

            int pokemon_1_move = getMove(pokemon_1);
            int pokemon_2_move = getMove(pokemon_2);

            delay(3000, 6000);

            // Used to determine if a Pokemon has fainted after an attack
            Pokemon initialPokemon1 = pokemon_1;
            Pokemon initialPokemon2 = pokemon_2;

            // Determine which Pokemon attacks first
            if (pokemon_1.getSpeed() >= pokemon_2.getSpeed()) {
                System.out.println(pokemon_1.getName() + " attacks first!");
                pokemon_2 = attack(pokemon_1, pokemon_2, pokemon_1_move, weather, verbose, opponent);
                delay(2000);
                if (pokemon_2 == initialPokemon2) {
                    System.out.println();
                    pokemon_1 = attack(pokemon_2, pokemon_1, pokemon_2_move, weather, verbose, this);
                }
            } else {
                System.out.println(pokemon_2.getName() + " attacks first!");
                pokemon_1 = attack(pokemon_2, pokemon_1, pokemon_2_move, weather, verbose, this);
                delay(2000);
                if (pokemon_1 == initialPokemon1) {
                    System.out.println();
                    pokemon_2 = attack(pokemon_1, pokemon_2, pokemon_1_move, weather, verbose, opponent);
                }
            }

            delay(2000);

            // Apply weather and status effects at the end of the turn
            if (pokemon_1 != null && pokemon_2 != null) {
                weather = weather.applyEffect(pokemon_1, pokemon_2, verbose);
                pokemon_1.resetAfterDuration(verbose);
                pokemon_2.resetAfterDuration(verbose);
            }
            // Check if a Pokemon fainted due to weather or status effects
            if (pokemon_1 != null && pokemon_1.getHp() <= 0) {
                pokemon_1 = this.handleFainting(pokemon_1, pokemon_2, verbose);
            }
            if (pokemon_2 != null && pokemon_2.getHp() <= 0) {
                pokemon_2 = opponent.handleFainting(pokemon_2, pokemon_1, verbose);
            }
            if (pokemon_1 == null) {
                System.out.println(opponent.getTitle() + " " + opponent.getName() + " wins the battle!");
                clip.stop();
                resetPokemonStats(this.team);
                resetPokemonStats(opponent.team);
                return -1; // Opponent wins
            }
            if (pokemon_2 == null) {
                System.out.println(this.title + " " + this.name + " wins the battle!");
                clip.stop();
                playSFX("sounds\\battle\\Victory Against Trainer!.wav");
                resetPokemonStats(this.team);
                resetPokemonStats(opponent.team);
                return 1; // This trainer wins
            }
            turn++;
        }
    }

    /**
     * Selects a move for a given Pokemon.
     * 
     * @param pkmn The Pokemon to select a move for.
     * @return The index of the selected move.
     */
    public int getMove(Pokemon pkmn) {
        if (Arrays.stream(pkmn.getMoves()).allMatch(move -> move.getAP() <= 0)) {
            return -1;
        }
        int move;
        int l = pkmn.getMoves().length;
        do {
            move = (int) (Math.random() * l);
        } while (pkmn.getMoves()[move].getAP() <= 0);
        return move;
    }

    /**
     * Selects a random Pokemon from the trainer's team that is ready for battle
     * (i.e., has HP greater than 0).
     * 
     * @return The selected Pokemon, or null if no Pokemon are ready for battle.
     */
    private Pokemon getRandomBattleReadyPokemon() {
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

    /**
     * Counts the number of Pokemon in the trainer's team that are ready for battle.
     * 
     * @return The number of battle-ready Pokemon.
     */
    private int getRandomBattleReadyPokemonCount() {
        List<Pokemon> battleReadyPokemons = Arrays.stream(team)
                .filter(pokemon -> pokemon.getHp() > 0)
                .collect(Collectors.toList());
        return battleReadyPokemons.size();
    }

    /**
     * Makes a Pokemon attack another Pokemon.
     * 
     * @param attacker The attacking Pokemon.
     * @param defender The defending Pokemon.
     * @param move     The index of the move the attacker should use.
     * @param weather  The current weather, which can affect the attack.
     * @param verbose  If true, prints detailed information about the attack.
     * @param trainer  The trainer of the defending Pokemon.
     * @return The defending Pokemon, or a different Pokemon from the defender's
     *         team if the defender faints.
     */
    private Pokemon attack(Pokemon attacker, Pokemon defender, int move, Weather weather, boolean verbose,
            Trainer trainer) {
        attacker.getMoves()[move].use(attacker, defender, weather, verbose);
        if (!verbose) {
            System.out.println(attacker.getName() + " used " + attacker.getMoves()[move].getName());
        }
        if (defender.getHp() <= 0) {
            defender = trainer.handleFainting(defender, attacker, verbose);
        }
        return defender;
    }

    /**
     * Prints the health of a given Pokemon.
     * 
     * @param pkmn The Pokemon whose health should be printed.
     */
    private void printHealth(Pokemon pkmn) {
        int r = this.getRandomBattleReadyPokemonCount();
        int f = this.team.length - r;
        System.out.println("Trainer " + this.getName() + " " + "*".repeat(r) + "-".repeat(f));
        double hpPercentage = (double) pkmn.getHp() / pkmn.getMaxHp() * 100;
        String colorCode;
        if (hpPercentage >= 50) {
            colorCode = "\u001B[32m"; // Green
        } else if (hpPercentage >= 25) {
            colorCode = "\u001B[33m"; // Yellow
        } else {
            colorCode = "\u001B[31m"; // Red
        }
        System.out.println(pkmn.getName() + " Lvl." + pkmn.getLevel() + " : " + colorCode + pkmn.getHp() + "HP / "
                + pkmn.getMaxHp() + "HP\u001B[0m " + pkmn.getStatus().getTag());
    }

    /**
     * Handles a Pokemon fainting during a battle.
     * 
     * @param fainting The Pokemon that fainted.
     * @param attacker The Pokemon that caused the fainting.
     * @param verbose  If true, prints detailed information about the fainting.
     * @return A different Pokemon from the fainting Pokemon's team that is ready
     *         for battle, or null if no such Pokemon exist.
     */
    private Pokemon handleFainting(Pokemon fainting, Pokemon attacker, boolean verbose) {
        System.out.println(fainting.getName() + " fainted");
        playSFX("sounds\\In-Battle_Faint_No_Health.mp3.wav");
        delay(1000);
        int exp = (int) 1.5 * 250 * fainting.getLevel();
        if (attacker != null) {
            attacker.addEXP(exp);
        }

        Pokemon battleReadyPokemon = getRandomBattleReadyPokemon();
        if (battleReadyPokemon != null) {
            battleReadyPokemon.depoly(this, verbose);
        }
        return battleReadyPokemon;
    }

    /**
     * Plays a sound loop from a given file.
     * 
     * @param path               The path to the sound file.
     * @param loopStartTimestamp The timestamp at which the loop should start.
     * @param loopEndTimestamp   The timestamp at which the loop should end.
     */
    private void playSoundLoop(String path, String loopStartTimestamp, String loopEndTimestamp) {
        clip = null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());
            AudioFormat format = audioInputStream.getFormat();
            float frameRate = format.getFrameRate();

            // Convert timestamps to frames
            int loopStartFrame = timestampToFrames(loopStartTimestamp, frameRate);
            int loopEndFrame = timestampToFrames(loopEndTimestamp, frameRate);

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.setLoopPoints(loopStartFrame, loopEndFrame);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    /**
     * Converts a timestamp to a number of frames.
     * 
     * @param timestamp The timestamp to convert.
     * @param frameRate The frame rate to use for the conversion.
     * @return The number of frames corresponding to the timestamp.
     */
    private int timestampToFrames(String timestamp, float frameRate) {
        String[] parts = timestamp.split(":");
        float minutes = Float.parseFloat(parts[0]);
        float seconds = Float.parseFloat(parts[1]);
        return (int) ((minutes * 60 + seconds) * frameRate);
    }

    /**
     * Plays a sound effect from a given file.
     * 
     * @param path The path to the sound file.
     */
    public void playSFX(String path) {
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

    /**
     * Delays execution for a random amount of time between a minimum and maximum
     * value.
     * 
     * @param min The minimum delay in milliseconds.
     * @param max The maximum delay in milliseconds.
     */
    private void delay(int min, int max) {
        try {
            int delay = min + (int) (Math.random() * ((max - min) + 1));
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delays execution for a given amount of time.
     * 
     * @param delay The delay in milliseconds.
     */
    private void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void resetPokemonStats(Pokemon[] pkmns) {
        for (Pokemon pkmn : pkmns) {
            pkmn.calculateStats();
        }
    }

    // Getters & Setters

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

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Pokemon[] getTeam() {
        return team;
    }

    /**
     * Sets the team of Pokemon for the Trainer.
     * The team size must be between 1 and 6.
     *
     * @param team The team of Pokemon.
     * @throws IllegalArgumentException If the team size is not between 1 and 6.
     */
    public void setTeam(Pokemon[] team) {
        if (team.length < 1 || team.length > 6) {
            throw new IllegalArgumentException("Team size must be between 1 and 6.");
        }
        this.team = team;
    }

}
