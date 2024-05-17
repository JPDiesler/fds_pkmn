import org.junit.Test;
import static org.junit.Assert.*;

public class SpecialMoveTest {

    @Test
    public void testConstructorWithoutEffect() {
        SpecialMove move = new SpecialMove("Thunderbolt", Type.ELECTRIC, 90, 100, 10);

        // Assert that the move is initialized correctly
        assertEquals("Thunderbolt", move.getName());
        assertEquals(Type.ELECTRIC, move.getType());
        assertEquals(90, move.getPower());
        assertEquals(100, move.getAccuracy());
        assertEquals(10, move.getAP());
        assertEquals(Effect.NONE, move.getEffect());
        assertEquals(0.0, move.getEffectChance(), 0.01);
    }

    @Test
    public void testConstructorWithEffect() {
        SpecialMove move = new SpecialMove("Fire Blast", Type.FIRE, 110, 85, 5, Effect.BURN, 0.1);

        // Assert that the move is initialized correctly
        assertEquals("Fire Blast", move.getName());
        assertEquals(Type.FIRE, move.getType());
        assertEquals(110, move.getPower());
        assertEquals(85, move.getAccuracy());
        assertEquals(5, move.getAP());
        assertEquals(Effect.BURN, move.getEffect());
        assertEquals(0.1, move.getEffectChance(), 0.01);
    }

    @Test
    public void testUse_MoveMissed() {
        SpecialMove move = new SpecialMove("Thunderbolt", Type.ELECTRIC, 90, 100, 10);
        Move[] moves = { move };
        Pokemon user = new Pokemon(25, "Pikachu", Type.ELECTRIC, 25, StatusEffect.NONE, 100, 100, 100, 100, 100, 100,
                moves);
        Pokemon target = new Pokemon(1, "Bulbasaur", Type.GRASS, 10, StatusEffect.NONE, 100, 100, 100, 100, 100, 100,
                moves);
        Weather weather = Weather.CLEAR;

        // Setting hit to false to simulate the move missing
        boolean hit = false;
        move.use(user, target, weather, false, hit, false, false, false, false);

        // Assert that the target's HP remains unchanged
        assertEquals(target.getMaxHp(), target.getHp());
    }

    @Test
    public void testUse_MoveHit_NoEffect() {
        SpecialMove move = new SpecialMove("Thunderbolt", Type.ELECTRIC, 90, 100, 10);
        Move[] moves = { move };
        Pokemon user = new Pokemon(25, "Pikachu", Type.ELECTRIC, 25, StatusEffect.NONE, 100, 100, 100, 100, 100, 100,
                moves);
        Pokemon target = new Pokemon(1, "Bulbasaur", Type.GRASS, 10, StatusEffect.NONE, 100, 100, 100, 100, 100, 100,
                moves);
        Weather weather = Weather.CLEAR;

        // Setting hit to true to simulate the move hitting
        boolean hit = true;
        move.use(user, target, weather, false, hit, false, false, false, false);

        // Assert that the target's HP is reduced due to the move's damage
        assertTrue(target.getHp() < 100);
    }

    @Test
    public void testUse_MoveHit_WithEffect() {
        // Testing a move with an effect
        SpecialMove move = new SpecialMove("Fire Blast", Type.FIRE, 110, 85, 5, Effect.BURN, 0.1);
        Move[] moves = { move };
        Pokemon user = new Pokemon(6, "Charizard", Type.FIRE, 36, StatusEffect.NONE, 100, 100, 100, 100, 100, 100,
                moves);
        Pokemon target = new Pokemon(7, "Squirtle", Type.WATER, 10, StatusEffect.NONE, 100, 100, 100, 100, 100, 100,
                moves);
        Weather weather = Weather.CLEAR;

        // Setting hit and effect to true to simulate the move hitting and applying its
        // effect
        boolean hit = true;
        boolean effect = true;
        move.use(user, target, weather, false, hit, false, effect, false, false);

        // Assert that the target's HP is reduced due to the move's damage and it
        // possibly got burned
        assertTrue(target.getHp() < 100);
        assertTrue(target.getStatus() == StatusEffect.BURN);
    }

    @Test
    public void testCalculateBaseDamage() {
        SpecialMove move = new SpecialMove("Fire Blast", Type.FIRE, 110, 85, 5);
        Move[] moves = { move };
        Pokemon user = new Pokemon(6, "Charizard", Type.FIRE, 36, StatusEffect.NONE, 100, 100, 100, 100, 100, 100,
                moves);
        double attackDefenseRatio = 1.5; // Example ratio
        double baseDamage = move.calculateBaseDamage(user, attackDefenseRatio);

        // Expected base damage calculation
        double expectedBaseDamage = (((((user.level * 2) / 5) + 2) * move.getPower() * attackDefenseRatio) / 50) + 2;

        // Assert that the calculated base damage matches the expected value
        assertEquals(expectedBaseDamage, baseDamage, 0.01);
    }
}