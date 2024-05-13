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
        Pokemon selectedPokemon;

        do {
            int index = (int) (Math.random() * team.length);
            selectedPokemon = team[index];
        } while (selectedPokemon.getHp() <= 0);

        return selectedPokemon;
    }

    public void printHealth(Pokemon p1,Pokemon p2){
        System.out.println("-".repeat(35)+" Health Status "+"-".repeat(35));
        System.out.println(p1.getName()+": "+p1.getHp()+"HP / "+p1.getMaxHp()+"HP");
        System.out.println(p2.getName()+": "+p2.getHp()+"HP / "+p2.getMaxHp()+"HP");
    }

    public Pokemon handleFainting(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " fainted");

        Pokemon battleReadyPokemon = getRandomBattleReadyPokemon();
        if (battleReadyPokemon != null) {
            System.out.println("Trainer " + this.name + " sends out " + battleReadyPokemon.getName() + "!");
        }

        return battleReadyPokemon;
    }

    public int battle(Trainer opponent, boolean verbose) {
        System.out.println("Trainer " + this.name + " challenges Trainer " + opponent.getName() + " to a battle!");
        Weather weather = Weather.CLEAR;
        int turn = 1;
        Pokemon pokemon_1 = this.getRandomBattleReadyPokemon();
        Pokemon pokemon_2 = opponent.getRandomBattleReadyPokemon();
        System.out.println("Trainer " + this.name + " sends out " + pokemon_1.getName());
        System.out.println("Trainer " + opponent.getName() + " sends out " + pokemon_2.getName());
        printHealth(pokemon_1, pokemon_2);
        while (true){
            System.out.println();
            System.out.println("-".repeat(35)+" Turn " + turn + " "+"-".repeat(35));
            System.out.println();
            int pokemon_1_move;
            do {
                pokemon_1_move = (int)(Math.random() * 4);
            } while (pokemon_1.getMoves()[pokemon_1_move].getAP() <= 0);

            int pokemon_2_move;
            do {
                pokemon_2_move = (int)(Math.random() * 4);
            } while (pokemon_2.getMoves()[pokemon_2_move].getAP() <= 0);
            try {
                int delay = 3000 + (int)(Math.random() * ((6000-3000) + 1));
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Determine which Pokemon attacks first
            if (pokemon_1.getSpeed() >= pokemon_2.getSpeed()) {
                // Pokemon 1 attacks first
                System.out.println(pokemon_1.getName() + " attacks first!");
                pokemon_1.getMoves()[pokemon_1_move].use(pokemon_1, pokemon_2, weather,verbose);
                pokemon_1.getMoves()[pokemon_1_move].setAP(pokemon_1.getMoves()[pokemon_1_move].getAP() - 1);
                if(!verbose){
                    System.out.println(pokemon_1.getName()+" used "+pokemon_1.getMoves()[pokemon_1_move].getName());
                }
                if(pokemon_2.getHp() <= 0){
                    pokemon_2 = opponent.handleFainting(pokemon_2);
                    if (pokemon_2 == null) {
                        System.out.println("Trainer " + this.name + " wins the battle!");
                        return 1; // This trainer wins
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // Pokemon 2 attacks first
                System.out.println(pokemon_2.getName() + " attacks first!");
                pokemon_2.getMoves()[pokemon_2_move].use(pokemon_2, pokemon_1, weather,verbose);
                pokemon_2.getMoves()[pokemon_2_move].setAP(pokemon_2.getMoves()[pokemon_2_move].getAP() - 1);
                if(!verbose){
                    System.out.println(pokemon_2.getName()+" used "+pokemon_2.getMoves()[pokemon_2_move].getName());
                }
                if(pokemon_1.getHp() <= 0){
                    pokemon_1 = this.handleFainting(pokemon_1);
                    if (pokemon_1 == null) {
                        System.out.println("Trainer " + opponent.getName() + " wins the battle!");
                        return -1; // Opponent wins
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
             
            try {
                int delay = 2000;
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             // Determine which Pokemon attacks second
             if (pokemon_1.getSpeed() < pokemon_2.getSpeed()) {
                // Pokemon 1 attacks seocond
                pokemon_1.getMoves()[pokemon_1_move].use(pokemon_1, pokemon_2, weather,verbose);
                pokemon_1.getMoves()[pokemon_1_move].setAP(pokemon_1.getMoves()[pokemon_1_move].getAP() - 1);
                if(!verbose){
                    System.out.println(pokemon_1.getName()+" used "+pokemon_1.getMoves()[pokemon_1_move].getName());
                }
                if(pokemon_2.getHp() <= 0){
                    pokemon_2 = opponent.handleFainting(pokemon_2);
                    if (pokemon_2 == null) {
                        System.out.println("Trainer " + this.name + " wins the battle!");
                        return 1; // This trainer wins
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                // Pokemon 2 attacks second
                pokemon_2.getMoves()[pokemon_2_move].use(pokemon_2, pokemon_1, weather, verbose);
                pokemon_2.getMoves()[pokemon_2_move].setAP(pokemon_2.getMoves()[pokemon_2_move].getAP() - 1);
                if(!verbose){
                    System.out.println(pokemon_2.getName()+" used "+pokemon_2.getMoves()[pokemon_2_move].getName());
                }
                if(pokemon_1.getHp() <= 0){
                    pokemon_1 = this.handleFainting(pokemon_1);
                    if (pokemon_1 == null) {
                        System.out.println("Trainer " + opponent.getName() + " wins the battle!");
                        return -1; // Opponent wins
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    
    
            // Apply weather and status effects at the end of the turn
            weather.applyEffect(pokemon_1, pokemon_2);
            pokemon_1.resetAfterDuration();
            pokemon_2.resetAfterDuration();
    
            // Check if a Pokemon fainted due to weather or status effects
            if(pokemon_1.getHp() <= 0){
                pokemon_1 = this.handleFainting(pokemon_1);
                if (pokemon_1 == null) {
                    System.out.println("Trainer " + opponent.getName() + " wins the battle!");
                    return -1; // Opponent wins
                }
            }
            if(pokemon_2.getHp() <= 0){
                pokemon_2 = opponent.handleFainting(pokemon_2);
                if (pokemon_2 == null) {
                    System.out.println("Trainer " + this.name + " wins the battle!");
                    return 1; // This trainer wins
                }
            }
            System.out.println();
            printHealth(pokemon_1, pokemon_2);
            turn++;
        }
    }
}

