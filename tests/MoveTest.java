import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {
    private Move testMove;
    private Pokemon testPokemon;
    private Type fireType;
    private Type waterType;

    @BeforeEach
    public void setUp() {
        // Sample concrete subclass of Move
        testMove = new Move("Test Move", fireType, 50, 100, 15) {
            @Override
            public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
                // Implementation for test purposes
            }
        };
        fireType = Type.FIRE;
        waterType = Type.WATER;

        Move[] moves = { testMove };
        // Sample Pokemon setup
        testPokemon = new Pokemon(0, "TestPKMN", fireType, waterType, 10, StatusEffect.NONE, 50,
                50, 50, 50, 50, 50, moves);
    }

    @Test
    public void testConstructorWithoutEffect() {
        Move move = new Move("Flame Thrower", fireType, 90, 100, 15) {
            @Override
            public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
                // Implementation for test purposes
            }
        };
        assertEquals("Flame Thrower", move.getName());
        assertEquals(fireType, move.getType());
        assertEquals(90, move.getPower());
        assertEquals(100, move.getAccuracy());
        assertEquals(15, move.getAP());
        assertEquals(15, move.getMaxAP());
        assertEquals(Effect.NONE, move.getEffect());
        assertEquals(0.0, move.getEffectChance());
    }

    @Test
    public void testConstructorWithEffect() {
        Effect burnEffect = Effect.BURN;
        Move move = new Move("Flame Thrower", fireType, 90, 100, 15, burnEffect, 0.1) {
            @Override
            public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
                // Implementation for test purposes
            }
        };
        assertEquals("Flame Thrower", move.getName());
        assertEquals(fireType, move.getType());
        assertEquals(90, move.getPower());
        assertEquals(100, move.getAccuracy());
        assertEquals(15, move.getAP());
        assertEquals(15, move.getMaxAP());
        assertEquals(burnEffect, move.getEffect());
        assertEquals(0.1, move.getEffectChance());
    }

    @Test
    public void testCalculateStabMultiplier() {
        Effect burnEffect = Effect.BURN;
        Move testMove = new Move("Flame Thrower", fireType, 90, 100, 15, burnEffect, 0.1) {
            @Override
            public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
                // Implementation for test purposes
            }
        };
        double multiplier = testMove.calculateStabMultiplier(testPokemon);
        System.out.println(multiplier);
        assertEquals(1.5, multiplier);
    }

    @Test
    public void testCalculateStabMultiplier_NoStab() {
        testMove = new Move("Test Move", fireType, 50, 100, 15) {
            @Override
            public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
                // Implementation for test purposes
            }
        };
        fireType = Type.FIRE;
        waterType = Type.WATER;
        Move[] moves = { testMove };
        // Sample Pokemon setup
        Pokemon differentTypePokemon = new Pokemon(0, "TestPKMN", waterType, 10, StatusEffect.NONE, 50,
                50, 50, 50, 50, 50, moves);
        assertEquals(1.0, testMove.calculateStabMultiplier(differentTypePokemon));
    }

    @Test
    public void testGetMoveEffectiveness() {
        Type grassType = Type.GRASS;
        Type rockType = Type.ROCK;
        Type groundType = Type.GROUND;
        Type dragonType = Type.DRAGON;
        Move waterMove = new Move("Water Gun", waterType, 40, 100, 25) {
            @Override
            public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
                // Implementation for test purposes
            }
        };

        double effectiveness = waterMove.getMoveEffectiveness(dragonType, grassType, waterType);
        assertEquals(0.25, effectiveness);
        effectiveness = waterMove.getMoveEffectiveness(grassType, null, waterType);
        assertEquals(0.5, effectiveness);
        effectiveness = waterMove.getMoveEffectiveness(fireType, grassType, waterType);
        assertEquals(1.0, effectiveness);
        effectiveness = waterMove.getMoveEffectiveness(fireType, null, waterType);
        assertEquals(2.0, effectiveness);
        effectiveness = waterMove.getMoveEffectiveness(groundType, rockType, waterType);
        assertEquals(4.0, effectiveness);
    }

    @Test
    public void testGetMoveEffectiveness_NoEffectiveness() {
        Type groundType = Type.GROUND;
        Move fireMove = new Move("Flame Thrower", fireType, 90, 100, 15) {
            @Override
            public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
                // Implementation for test purposes
            }
        };

        double effectiveness = fireMove.getMoveEffectiveness(groundType, waterType, fireType);
        assertEquals(0.5, effectiveness);
    }

    @Test
    public void testGetMoveEffectiveness_Immune() {
        Type groundType = Type.GROUND;
        Move electricMove = new Move("Thunderbolt", Type.ELECTRIC, 90, 100, 15) {
            @Override
            public void use(Pokemon user, Pokemon target, Weather weather, boolean verbose) {
                // Implementation for test purposes
            }
        };

        double effectiveness = electricMove.getMoveEffectiveness(groundType, null, Type.ELECTRIC);
        assertEquals(0.0, effectiveness);
    }

    @Test
    public void testCalculateConfusionDamage() {
        double confusionDamage = testMove.calculateConfusionDamage(testPokemon);
        assertTrue(confusionDamage > 0);
    }

    @Test
    public void testGettersAndSetters() {
        testMove.setName("New Name");
        assertEquals("New Name", testMove.getName());

        testMove.setType(waterType);
        assertEquals(waterType, testMove.getType());

        testMove.setPower(70);
        assertEquals(70, testMove.getPower());

        testMove.setAccuracy(80);
        assertEquals(80, testMove.getAccuracy());

        testMove.setAP(10);
        assertEquals(10, testMove.getAP());

        Effect freezeEffect = Effect.FREEZE;
        testMove.setEffect(freezeEffect);
        assertEquals(freezeEffect, testMove.getEffect());

        testMove.setEffectChance(0.2);
        assertEquals(0.2, testMove.getEffectChance());

        testMove.setMaxAP(20);
        assertEquals(20, testMove.getMaxAP());
    }
}
