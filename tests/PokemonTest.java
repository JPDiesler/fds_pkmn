import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {

    private Pokemon infernape;

    @BeforeEach
    public void setUp() {
        infernape = new Pokemon();
    }

    @Test
    public void testDefaultConstructor() {
        assertEquals(392, infernape.getId());
        assertEquals("Infernape", infernape.getName());
        assertEquals(Type.FIRE, infernape.getPrimaryType());
        assertEquals(Type.FIGHTING, infernape.getSecondaryType());
        assertEquals(50, infernape.getLevel());
        assertEquals(183, infernape.getMaxHp());
        assertEquals(183, infernape.getHp());
    }

    @Test
    public void testSingleTypeConstructor() {
        Move[] moves = { new PhysicalMove("Tackle", Type.NORMAL, 40, 100, 35) };
        Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", Type.GRASS, 5, StatusEffect.NONE, 45, 49, 49, 65, 65, 45,
                moves);

        assertEquals(1, bulbasaur.getId());
        assertEquals("Bulbasaur", bulbasaur.getName());
        assertEquals(Type.GRASS, bulbasaur.getPrimaryType());
        assertNull(bulbasaur.getSecondaryType());
        assertEquals(5, bulbasaur.getLevel());
        assertEquals(24, bulbasaur.getMaxHp());
        assertEquals(24, bulbasaur.getHp());
    }

    @Test
    public void testDualTypeConstructor() {
        Move[] moves = { new PhysicalMove("Tackle", Type.NORMAL, 40, 100, 35) };
        Pokemon charizard = new Pokemon(6, "Charizard", Type.FIRE, Type.FLYING, 36, StatusEffect.NONE, 78, 84, 78, 109,
                85, 100, moves);

        assertEquals(6, charizard.getId());
        assertEquals("Charizard", charizard.getName());
        assertEquals(Type.FIRE, charizard.getPrimaryType());
        assertEquals(Type.FLYING, charizard.getSecondaryType());
        assertEquals(36, charizard.getLevel());
        assertEquals(136, charizard.getMaxHp());
        assertEquals(136, charizard.getHp());
    }

    @Test
    public void testCalculateHP() {
        infernape.calculateHP();
        assertEquals(183, infernape.getMaxHp());
    }

    @Test
    public void testCalculateStats() {
        infernape.calculateStats();
        assertEquals(156, infernape.getAttack());
        assertEquals(123, infernape.getDefense());
        assertEquals(156, infernape.getSpecialAttack());
        assertEquals(123, infernape.getSpecialDefense());
        assertEquals(160, infernape.getSpeed());
    }

    @Test
    public void testApplyStatusEffect() {
        infernape.applyStatusEffect(StatusEffect.BURN);
        assertEquals(StatusEffect.BURN, infernape.getStatus());
    }

    @Test
    public void testApplyStatusDamage() {
        infernape.applyStatusEffect(StatusEffect.BURN);
        infernape.applyStatusDamage(false);
        assertTrue(infernape.getHp() < infernape.getMaxHp());
    }

    @Test
    public void testAddEXP() {
        infernape.addEXP(50000); // Add enough experience to level up
        assertEquals(51, infernape.getLevel());
    }

    @Test
    public void testSetMoves() {
        Move[] newMoves = {
                new PhysicalMove("Punch", Type.FIGHTING, 40, 100, 35),
                new SpecialMove("Fire Blast", Type.FIRE, 110, 85, 5)
        };
        infernape.setMoves(newMoves);
        assertArrayEquals(newMoves, infernape.getMoves());
    }

    @Test
    public void testIllegalMovesArray() {
        Move[] tooManyMoves = {
                new PhysicalMove("Punch", Type.FIGHTING, 40, 100, 35),
                new SpecialMove("Fire Blast", Type.FIRE, 110, 85, 5),
                new PhysicalMove("Tackle", Type.NORMAL, 40, 100, 35),
                new PhysicalMove("Scratch", Type.NORMAL, 40, 100, 35),
                new PhysicalMove("Growl", Type.NORMAL, 0, 100, 40)
        };
        assertThrows(IllegalArgumentException.class, () -> {
            infernape.setMoves(tooManyMoves);
        });
    }

    @Test
    public void testSetHp() {
        infernape.setHp(50);
        assertEquals(50, infernape.getHp());
        infernape.setHp(-50);
        assertEquals(0, infernape.getHp()); // Should not allow negative HP
        infernape.setHp(100);
        assertEquals(100, infernape.getHp());
        infernape.setHp(300);
        assertEquals(infernape.getMaxHp(), infernape.getHp()); // Should cap at max HP
    }
}
