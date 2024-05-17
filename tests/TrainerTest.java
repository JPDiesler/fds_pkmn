import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TrainerTest {

    private Trainer trainer;
    private Pokemon[] team;

    @BeforeEach
    public void setUp() {
        Move[] moves = { new PhysicalMove("Tackle", Type.NORMAL, 40, 100, 35) };
        Pokemon pkmn = new Pokemon(1, "Bulbasaur", Type.GRASS, Type.POISON, 5, StatusEffect.NONE, 45, 49, 49, 65, 65,
                45, moves);
        team = new Pokemon[] { pkmn };
        trainer = new Trainer(1, "Ash", "PKMN-Trainer", team);
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(1, trainer.getId());
        assertEquals("Ash", trainer.getName());
        assertEquals("PKMN-Trainer", trainer.getTitle());
        assertNotNull(trainer.getTeam());
        assertEquals(1, trainer.getTeam().length);
    }

    @Test
    public void testParameterizedConstructorValidTeam() {
        Trainer customTrainer = new Trainer(1, "Ash", "Pokemon Master", team);
        assertEquals(1, customTrainer.getId());
        assertEquals("Ash", customTrainer.getName());
        assertEquals("Pokemon Master", customTrainer.getTitle());
        assertNotNull(customTrainer.getTeam());
        assertEquals(1, customTrainer.getTeam().length);
    }

    @Test
    public void testParameterizedConstructorInvalidTeam() {
        Pokemon[] invalidTeam = new Pokemon[] {};
        assertThrows(IllegalArgumentException.class, () -> new Trainer(1, "Ash", "Pokemon Master", invalidTeam));
    }

    @Test
    public void testSetTeamValid() {
        trainer.setTeam(team);
        int teamSize = trainer.getTeam().length;
        assertTrue(teamSize >= 1 && teamSize <= 6);
    }

    @Test
    public void testSetTeamInvalid() {
        Pokemon[] underSizedTeam = new Pokemon[] {};
        assertThrows(IllegalArgumentException.class, () -> trainer.setTeam(underSizedTeam));
        Pokemon[] OversizedTeam = new Pokemon[] { new Pokemon(), new Pokemon(), new Pokemon(), new Pokemon(),
                new Pokemon(), new Pokemon(), new Pokemon() };
        assertThrows(IllegalArgumentException.class, () -> trainer.setTeam(OversizedTeam));
    }

    @Test
    public void testHealPokemon() {
        team[0].setHp(50); // Set HP to a known value
        trainer.healPokemon(team[0]);
        assertEquals(team[0].getMaxHp(), team[0].getHp()); // Check that HP is now 100
        // You'll need to add a way to check the AP of a Move to test that
    }

    @Test
    public void testHealTeam() {
        for (Pokemon pokemon : team) {
            pokemon.setHp(50); // Set HP to a known value
        }
        trainer.setTeam(team);
        trainer.healTeam();
        for (Pokemon pokemon : team) {
            assertEquals(pokemon.getMaxHp(), pokemon.getHp()); // Check that HP is now 100
            // You'll need to add a way to check the AP of a Move to test that
        }
    }

    @Test
    public void testSleep() {
        for (Pokemon pokemon : team) {
            pokemon.setHp(0); // Set HP to a known value
            for (Move move : pokemon.getMoves()) {
                move.setAP(0); // Set AP to a known value
            }
        }
        trainer.setTeam(team);
        trainer.sleep(8);
        for (Pokemon pokemon : team) {
            assertEquals(pokemon.getMaxHp(), pokemon.getHp()); // Check that HP is now 100
            for (Move move : pokemon.getMoves()) {
                assertEquals(move.getMaxAP(), move.getAP()); // Check that AP is now max AP
            }
        }
    }

    @Test
    public void testCatchWildPokemon() {
        // Simplified test: just ensure methods are called
        Pokemon wildPokemon = new Pokemon(/* parameters */);
        wildPokemon.setName("Pikachu");
        Pokemon[] wildPokemons = new Pokemon[] { wildPokemon };

        trainer.catchWildPokemon(wildPokemons);
    }

    @Test
    public void testGetMove() {
        // Assuming that the Pokemon class and Move class are defined in your project
        Move move1 = new SpecialMove(); // Assuming Move constructor takes name and AP
        Move move2 = new SpecialMove();
        Move move3 = new PhysicalMove();
        Move move4 = new PhysicalMove();

        move1.setAP(0); // Set AP to 0 to ensure it's not selected
        move2.setAP(10); // Set AP to a known value
        move3.setAP(20); // Set AP to a known value
        move4.setAP(0); // Set AP to 0 to ensure it's not selected

        Pokemon pkmn = new Pokemon();
        pkmn.setMoves(new Move[] { move1, move2, move3, move4 });

        int moveIndex = trainer.getMove(pkmn);

        // Check that the returned move index is valid and the move has AP > 0
        assertTrue(moveIndex >= 0 && moveIndex < 4);
        assertTrue(pkmn.getMoves()[moveIndex].getAP() > 0);

        move1.setAP(0); // Set AP to 0 to ensure it's not selected
        move2.setAP(0); // Set AP to 0 to ensure it's not selected
        move3.setAP(0); // Set AP to 0 to ensure it's not selected
        move4.setAP(0); // Set AP to 0 to ensure it's not selected

        moveIndex = trainer.getMove(pkmn);

        assertTrue(moveIndex == -1); // Ensure that -1 is returned when no moves are available

        for (int i = 1; i <= 4; i++) {
            Move[] moves = new Move[i];
            for (int j = 0; j < i; j++) {
                moves[j] = new SpecialMove();
                moves[j].setAP(10); // Set AP to a known value
            }

            pkmn = new Pokemon();
            pkmn.setMoves(moves);

            moveIndex = trainer.getMove(pkmn);

            // Check that the returned move index is valid and the move has AP > 0
            assertTrue(moveIndex >= 0 && moveIndex < i);
            assertTrue(pkmn.getMoves()[moveIndex].getAP() > 0);
        }

    }

    @Test
    public void testBattle() {
        Trainer opponent = new Trainer(2, "Blue", "Rival", team);
        int result = trainer.battle(opponent, false);
        assertTrue(result == 1 || result == -1); // Ensure it returns a valid result
    }
}