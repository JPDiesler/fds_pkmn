import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatusEffectTest {

    @Test
    void testGetDamage() {
        assertEquals(0.0625, StatusEffect.BURN.getDamage());
        assertEquals(0, StatusEffect.FREEZE.getDamage());
        assertEquals(0, StatusEffect.PARALYSIS.getDamage());
        assertEquals(0.0625, StatusEffect.POISON.getDamage());
        assertEquals(0, StatusEffect.SLEEP.getDamage());
        assertEquals(0, StatusEffect.CONFUSION.getDamage());
        assertEquals(0, StatusEffect.NONE.getDamage());
    }

    @Test
    void testGetDuration() {
        assertEquals(2, StatusEffect.BURN.getDuration());
        assertEquals(2, StatusEffect.FREEZE.getDuration());
        assertEquals(3, StatusEffect.PARALYSIS.getDuration());
        assertEquals(-1, StatusEffect.POISON.getDuration());
        assertEquals(3, StatusEffect.SLEEP.getDuration());
        assertEquals(3, StatusEffect.CONFUSION.getDuration());
        assertEquals(0, StatusEffect.NONE.getDuration());
    }

    @Test
    void testGetTag() {
        assertEquals("\u001B[41m\u001B[30m BRN \u001B[0m", StatusEffect.BURN.getTag());
        assertEquals("\u001B[44m\u001B[37m FRZ \u001B[0m", StatusEffect.FREEZE.getTag());
        assertEquals("\u001B[43m\u001B[30m PAR \u001B[0m", StatusEffect.PARALYSIS.getTag());
        assertEquals("\u001B[45m\u001B[37m PSN \u001B[0m", StatusEffect.POISON.getTag());
        assertEquals("\u001B[47m\u001B[30m SLP \u001B[0m", StatusEffect.SLEEP.getTag());
        assertEquals("\u001B[47m\u001B[30m CNF \u001B[0m", StatusEffect.CONFUSION.getTag());
        assertEquals("", StatusEffect.NONE.getTag());
    }

    @Test
    void testToString() {
        assertEquals("burning", StatusEffect.BURN.toString());
        assertEquals("frozen", StatusEffect.FREEZE.toString());
        assertEquals("paralyzed", StatusEffect.PARALYSIS.toString());
        assertEquals("poisoned", StatusEffect.POISON.toString());
        assertEquals("asleep", StatusEffect.SLEEP.toString());
        assertEquals("confused", StatusEffect.CONFUSION.toString());
        assertEquals("", StatusEffect.NONE.toString());
    }
}
