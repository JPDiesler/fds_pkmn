import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EffectTest {

    private Pokemon user;
    private Pokemon target;

    @BeforeEach
    public void setUp() {
        // Initialize the default Pokemon objects for testing
        user = new Pokemon();
        target = new Pokemon();
    }

    @Test
    public void testApplyConfuse() {
        Effect.CONFUSE.apply(user, target, true);
        assertEquals(StatusEffect.CONFUSION, target.getStatus());
    }

    @Test
    public void testApplySleep() {
        Effect.SLEEP.apply(user, target, true);
        assertEquals(StatusEffect.SLEEP, target.getStatus());
    }

    @Test
    public void testApplyFreeze() {
        Effect.FREEZE.apply(user, target, true);
        assertEquals(StatusEffect.FREEZE, target.getStatus());
    }

    @Test
    public void testApplyPoison() {
        Effect.POISON.apply(user, target, true);
        assertEquals(StatusEffect.POISON, target.getStatus());
    }

    @Test
    public void testApplyBurn() {
        Effect.BURN.apply(user, target, true);
        assertEquals(StatusEffect.BURN, target.getStatus());
    }

    @Test
    public void testApplyParalysis() {
        Effect.PARALYSIS.apply(user, target, true);
        assertEquals(StatusEffect.PARALYSIS, target.getStatus());
    }

    @Test
    public void testApplyLowerAttacks() {
        int initialAttack = user.getAttack();
        int initialSpecialAttack = user.getSpecialAttack();
        Effect.LOWERATTACKS.apply(user, target, true);
        assertEquals(initialAttack - 1, user.getAttack());
        assertEquals(initialSpecialAttack - 1, user.getSpecialAttack());
    }

    @Test
    public void testApplyLowerDefences() {
        int initialDefense = user.getDefense();
        int initialSpecialDefense = user.getSpecialDefense();
        Effect.LOWERDEFENCES.apply(user, target, true);
        assertEquals(initialDefense - 1, user.getDefense());
        assertEquals(initialSpecialDefense - 1, user.getSpecialDefense());
    }

    @Test
    public void testApplyNone() {
        // Ensures no changes are made
        Effect.NONE.apply(user, target, true);
        assertEquals(StatusEffect.NONE, target.getStatus());
    }
}
