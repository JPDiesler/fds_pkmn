import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PhysicalMoveTest {

    private PhysicalMove defaultMove;
    private Pokemon user;
    private Pokemon target;
    private Weather weather;

    @BeforeEach
    void setUp() {
        defaultMove = new PhysicalMove();
        Move[] moves = { defaultMove, defaultMove, defaultMove, defaultMove };
        user = new Pokemon(6, "Charizard", Type.FIRE, Type.FLYING, 36, StatusEffect.NONE, 78, 84, 78, 109,
                85, 100, moves);
        target = new Pokemon(9, "Blastoise", Type.WATER, 36, StatusEffect.NONE, 79, 83, 100, 85, 105, 78, moves);
        weather = Weather.CLEAR;
    }

    @Test
    void testDefaultConstructor() {
        assertEquals("Close Combat", defaultMove.getName());
        assertEquals(Type.FIGHTING, defaultMove.getType());
        assertEquals(120, defaultMove.getPower());
        assertEquals(100, defaultMove.getAccuracy());
        assertEquals(5, defaultMove.getAP());
        assertEquals(Effect.LOWERDEFENCES, defaultMove.getEffect());
        assertEquals(1.0, defaultMove.getEffectChance());
    }

    @Test
    void testCustomConstructorWithoutEffect() {
        PhysicalMove customMove = new PhysicalMove("Tackle", Type.NORMAL, 40, 100, 35);
        assertEquals("Tackle", customMove.getName());
        assertEquals(Type.NORMAL, customMove.getType());
        assertEquals(40, customMove.getPower());
        assertEquals(100, customMove.getAccuracy());
        assertEquals(35, customMove.getAP());
        assertEquals(Effect.NONE, customMove.getEffect());
    }

    @Test
    void testCustomConstructorWithEffect() {
        PhysicalMove customMove = new PhysicalMove("Thunder Punch", Type.ELECTRIC, 75, 100, 15, Effect.PARALYSIS, 0.1);
        assertEquals("Thunder Punch", customMove.getName());
        assertEquals(Type.ELECTRIC, customMove.getType());
        assertEquals(75, customMove.getPower());
        assertEquals(100, customMove.getAccuracy());
        assertEquals(15, customMove.getAP());
        assertEquals(Effect.PARALYSIS, customMove.getEffect());
        assertEquals(0.1, customMove.getEffectChance());
    }

    void testUseMoveHits() {
        int initialHP = target.getHp();
        defaultMove.use(user, target, weather, true, true, false, false, false, false);
        assertTrue(target.getHp() < initialHP);
        assertEquals(4, defaultMove.getAP());
    }

    @Test
    void testUseMoveMisses() {
        int initialHP = target.getHp();
        defaultMove.use(user, target, weather, true, false, false, false, false, false);
        assertEquals(initialHP, target.getHp());
        assertEquals(4, defaultMove.getAP());
    }

    @Test
    void testUseMoveWithParalysis() {
        user.setStatus(StatusEffect.PARALYSIS);
        int initialHP = target.getHp();
        defaultMove.use(user, target, weather, true, true, true, false, false, false);
        assertEquals(initialHP, target.getHp());
    }

    @Test
    void testUseMoveWithFreeze() {
        user.setStatus(StatusEffect.FREEZE);
        int initialHP = target.getHp();
        defaultMove.use(user, target, weather, true, true, false, false, false, false);
        assertEquals(initialHP, target.getHp());
    }

    @Test
    void testUseMoveWithSleep() {
        user.setStatus(StatusEffect.SLEEP);
        int initialHP = target.getHp();
        defaultMove.use(user, target, weather, true, true, false, false, false, false);
        assertEquals(initialHP, target.getHp());
    }

    @Test
    void testUseMoveWithConfusion() {
        user.setStatus(StatusEffect.CONFUSION);
        int initialHP = user.getHp();
        defaultMove.use(user, target, weather, true, true, false, true, false, false);
        assertTrue(user.getHp() < initialHP);
    }

    @Test
    void testUseMoveWithCriticalHit() {
        int initialHP = target.getHp();
        defaultMove.use(user, target, weather, true, true, false, false, true, false);
        assertTrue(target.getHp() < initialHP);
    }

    @Test
    void testUseMoveWithEffect() {
        int initialHP = target.getHp();
        defaultMove.use(user, target, weather, true, true, false, false, false, true);
        assertTrue(target.getHp() < initialHP);
        // Assume the effect is correctly applied
    }

    @Test
    void testUseMoveWithFogWeather() {
        weather = Weather.FOG;
        int initialHP = target.getHp();
        defaultMove.use(user, target, weather, true, true, false, false, false, false);
        // If move hits despite the weather, HP should decrease
        assertTrue(target.getHp() < initialHP);
        assertEquals(4, defaultMove.getAP());
    }

    @Test
    void testUseMoveWithVariousCombinations() {
        // Combination of various flags to test different outcomes
        user.setStatus(StatusEffect.PARALYSIS);
        defaultMove.use(user, target, weather, true, true, true, false, false, false);
        assertEquals(136, target.getHp());

        user.setStatus(StatusEffect.NONE);
        target.setStatus(StatusEffect.NONE);
        defaultMove.use(user, target, weather, true, true, false, true, true, true);
        // Check if HP decreases and effects are applied correctly
        assertTrue(target.getHp() < 220);
        assertEquals(3, defaultMove.getAP());
    }

    @Test
    void testUseMoveWithSuperEffectiveHit() {
        int initialHP = target.getHp();
        user.setPrimaryType(Type.ELECTRIC);
        PhysicalMove thunderPunch = new PhysicalMove("Thunder Punch", Type.ELECTRIC, 75, 100, 15, Effect.PARALYSIS,
                0.1);
        thunderPunch.use(user, target, weather, true, true, false, false, false, false);
        assertTrue(target.getHp() < initialHP);
    }

    @Test
    void testDamageCalculation() {
        // Assuming neutral effectiveness, no critical hit, and no weather effect
        int expectedMinDamage = 157; // Minimum expected damage
        int expectedMaxDamage = 185; // Maximum expected damage
        Weather weather = Weather.CLEAR;
        PhysicalMove thunderPunch = new PhysicalMove("Thunder Punch", Type.ELECTRIC, 75, 100, 15, Effect.PARALYSIS,
                0.1);
        Move[] moves = { thunderPunch };
        Pokemon Electevire = new Pokemon(466, "Electevire", Type.ELECTRIC, null, 65, StatusEffect.NONE, 268, 286,
                193,
                239, 223, 239, moves);

        // Note Ambipom's hp value is realistic its increased to 200 for testing
        // purposes
        Pokemon Ambipom = new Pokemon(424, "Ambipom", Type.NORMAL, 65, StatusEffect.NONE, 200, 100, 66, 60,
                66, 115, moves);
        // Note Ambipom's hp value is realistic its increased to 600 for testing
        // purposes
        Pokemon Blastoise = new Pokemon(9, "Blastoise", Type.WATER, 65, StatusEffect.NONE,
                600, 83, 100, 85,
                105, 78, moves);
        Pokemon Venasur = new Pokemon(3, "Venasur", Type.GRASS, Type.POISON, 65, StatusEffect.NONE, 80, 82, 83, 100,
                100, 80, moves);

        // Test normal effectiveness
        int initialHP = Ambipom.getHp();
        thunderPunch.use(Electevire, Ambipom, weather, true, true, false,
                false, false, false);
        int actualDamage = initialHP - Ambipom.getHp();
        assertTrue(actualDamage >= expectedMinDamage && actualDamage <= expectedMaxDamage);
        Ambipom.setHp(Ambipom.getMaxHp());

        // Crit test
        thunderPunch.use(Electevire, Ambipom, weather, true, true, false,
                false, true, false);
        expectedMinDamage = 236;
        expectedMaxDamage = 278;
        actualDamage = initialHP - Ambipom.getHp();
        assertTrue(actualDamage >= expectedMinDamage && actualDamage <= expectedMaxDamage);

        // Super effective test
        initialHP = Blastoise.getHp();
        expectedMinDamage = 243;
        expectedMaxDamage = 287;
        thunderPunch.use(Electevire, Blastoise, weather, true, true, false,
                false, false, false);
        actualDamage = initialHP - Blastoise.getHp();
        assertTrue(actualDamage >= expectedMinDamage && actualDamage <= expectedMaxDamage);

        Blastoise.setHp(Blastoise.getMaxHp());
        expectedMinDamage = 365;
        expectedMaxDamage = 430;
        thunderPunch.use(Electevire, Blastoise, weather, true, true, false,
                false, true, false);
        actualDamage = initialHP - Blastoise.getHp();
        assertTrue(actualDamage >= expectedMinDamage && actualDamage <= expectedMaxDamage);

        // Ineffective test
        initialHP = Venasur.getHp();
        expectedMinDamage = 68;
        expectedMaxDamage = 81;
        thunderPunch.use(Electevire, Venasur, weather, true, true, false,
                false, false, false);
        actualDamage = initialHP - Venasur.getHp();
        assertTrue(actualDamage >= expectedMinDamage && actualDamage <= expectedMaxDamage);

        Venasur.setHp(Venasur.getMaxHp());

        expectedMinDamage = 102;
        expectedMaxDamage = 122;
        thunderPunch.use(Electevire, Venasur, weather, true, true, false,
                false, true, false);
        actualDamage = initialHP - Venasur.getHp();
        System.out.println("Actual Damage: " + actualDamage);
        assertTrue(actualDamage >= expectedMinDamage && actualDamage <= expectedMaxDamage);

    }
}
