import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class StatusMoveTest {

    private Pokemon user;
    private Pokemon target;
    private Weather weather;
    private StatusMove confuseRay;

    @Before
    public void setUp() {
        user = new Pokemon();
        user.setPrimaryType(Type.GHOST);
        user.setSecondaryType(null);
        target = new Pokemon();
        target.setPrimaryType(Type.NORMAL);
        target.setSecondaryType(null);
        weather = Weather.CLEAR;
        confuseRay = new StatusMove();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals("Confuse Ray", confuseRay.getName());
        assertEquals(Type.GHOST, confuseRay.getType());
        assertEquals(0, confuseRay.getPower());
        assertEquals(100, confuseRay.getAccuracy());
        assertEquals(10, confuseRay.getAP());
        assertEquals(Effect.CONFUSE, confuseRay.getEffect());
    }

    @Test
    public void testCustomConstructor() {
        StatusMove customMove = new StatusMove("Thunder Wave", Type.ELECTRIC, 90, Effect.PARALYSIS, 20);
        assertEquals("Thunder Wave", customMove.getName());
        assertEquals(Type.ELECTRIC, customMove.getType());
        assertEquals(0, customMove.getPower());
        assertEquals(90, customMove.getAccuracy());
        assertEquals(20, customMove.getAP());
        assertEquals(Effect.PARALYSIS, customMove.getEffect());
    }

    @Test
    public void testUseMoveReducesAP() {
        int initialAP = confuseRay.getAP();
        confuseRay.use(user, target, weather, false);
        assertEquals(initialAP - 1, confuseRay.getAP());
    }

    @Test
    public void testMoveCannotDamage() {
        int initialTargetHP = target.getHp();
        confuseRay.use(user, target, weather, false);
        assertEquals(initialTargetHP, target.getHp());
    }

    @Test
    public void testMoveMissesInFog() {
        weather = Weather.FOG;
        int initialTargetHP = target.getHp();
        confuseRay.use(user, target, weather, false);
        assertEquals(initialTargetHP, target.getHp());
    }

    @Test
    public void testEffectAppliedOnHit() {
        confuseRay.use(user, target, weather, false, true, false, false);
        assertEquals(StatusEffect.NONE, target.getStatus());
        Pokemon target2 = new Pokemon();
        confuseRay.use(user, target2, weather, false, true, true, false);
        assertEquals(StatusEffect.CONFUSION, target2.getStatus());
    }

    @Test
    public void testMoveParalyzedUserCannotMove() {
        user.setStatus(StatusEffect.PARALYSIS);
        int initialTargetHP = target.getHp();
        confuseRay.use(user, target, weather, false, true, true, false);
        assertEquals(initialTargetHP, target.getHp());
    }

    @Test
    public void testMoveFrozenUserCannotMove() {
        user.setStatus(StatusEffect.FREEZE);
        int initialTargetHP = target.getHp();
        confuseRay.use(user, target, weather, false);
        assertEquals(initialTargetHP, target.getHp());
    }

    @Test
    public void testMoveSleepingUserCannotMove() {
        user.setStatus(StatusEffect.SLEEP);
        int initialTargetHP = target.getHp();
        confuseRay.use(user, target, weather, false);
        assertEquals(initialTargetHP, target.getHp());
    }

    @Test
    public void testConfusedUserHurtsItself() {
        user.setStatus(StatusEffect.CONFUSION);
        int initialUserHP = user.getHp();
        confuseRay.use(user, target, weather, false, true, false, true);
        assertTrue(user.getHp() < initialUserHP);
    }
}
