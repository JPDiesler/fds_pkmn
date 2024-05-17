import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class TypeTest {

    @Test
    public void testNormalType() {
        Type normal = Type.NORMAL;
        assertEquals(List.of(), normal.getImmune());
        assertEquals(List.of(Type.ROCK, Type.STEEL), normal.getIneffective());
        assertEquals(List.of(Type.GHOST), normal.getEffective());
        assertEquals("\u001B[47m\u001B[30m NORMAL \u001B[0m", normal.getTag());
    }

    @Test
    public void testFireType() {
        Type fire = Type.FIRE;
        assertEquals(List.of(), fire.getImmune());
        assertEquals(List.of(Type.FIRE, Type.WATER, Type.ROCK, Type.DRAGON), fire.getIneffective());
        assertEquals(List.of(Type.GRASS, Type.ICE, Type.BUG, Type.STEEL), fire.getEffective());
        assertEquals("\u001B[101m\u001B[30m FIRE \u001B[0m", fire.getTag());
    }

    @Test
    public void testWaterType() {
        Type water = Type.WATER;
        assertEquals(List.of(), water.getImmune());
        assertEquals(List.of(Type.WATER, Type.GRASS, Type.DRAGON), water.getIneffective());
        assertEquals(List.of(Type.FIRE, Type.GROUND, Type.ROCK), water.getEffective());
        assertEquals("\u001B[44m\u001B[37m WATER \u001B[0m", water.getTag());
    }

    @Test
    public void testElectricType() {
        Type electric = Type.ELECTRIC;
        assertEquals(List.of(Type.GROUND), electric.getImmune());
        assertEquals(List.of(Type.ELECTRIC, Type.GRASS, Type.DRAGON), electric.getIneffective());
        assertEquals(List.of(Type.WATER, Type.FLYING), electric.getEffective());
        assertEquals("\u001B[103m\u001B[30m ELECTRIC \u001B[0m", electric.getTag());
    }

    @Test
    public void testGrassType() {
        Type grass = Type.GRASS;
        assertEquals(List.of(), grass.getImmune());
        assertEquals(List.of(Type.FIRE, Type.GRASS, Type.POISON, Type.FLYING, Type.BUG, Type.DRAGON, Type.STEEL),
                grass.getIneffective());
        assertEquals(List.of(Type.WATER, Type.GROUND, Type.ROCK), grass.getEffective());
        assertEquals("\u001B[42m\u001B[30m GRASS \u001B[0m", grass.getTag());
    }

    @Test
    public void testIceType() {
        Type ice = Type.ICE;
        assertEquals(List.of(), ice.getImmune());
        assertEquals(List.of(Type.FIRE, Type.WATER, Type.ICE, Type.STEEL), ice.getIneffective());
        assertEquals(List.of(Type.GRASS, Type.GROUND, Type.FLYING, Type.DRAGON), ice.getEffective());
        assertEquals("\u001B[106m\u001B[30m ICE \u001B[0m", ice.getTag());
    }

    @Test
    public void testFightingType() {
        Type fighting = Type.FIGHTING;
        assertEquals(List.of(Type.GHOST), fighting.getImmune());
        assertEquals(List.of(Type.POISON, Type.FLYING, Type.PSYCHIC, Type.BUG, Type.FAIRY), fighting.getIneffective());
        assertEquals(List.of(Type.NORMAL, Type.ICE, Type.ROCK, Type.DARK, Type.STEEL), fighting.getEffective());
        assertEquals("\u001B[43m\u001B[30m FIGHTING \u001B[0m", fighting.getTag());
    }

    @Test
    public void testPoisonType() {
        Type poison = Type.POISON;
        assertEquals(List.of(Type.STEEL), poison.getImmune());
        assertEquals(List.of(Type.POISON, Type.GROUND, Type.ROCK, Type.GHOST), poison.getIneffective());
        assertEquals(List.of(Type.GRASS, Type.FAIRY), poison.getEffective());
        assertEquals("\u001B[105m\u001B[30m POISON \u001B[0m", poison.getTag());
    }

    @Test
    public void testGroundType() {
        Type ground = Type.GROUND;
        assertEquals(List.of(Type.FLYING), ground.getImmune());
        assertEquals(List.of(Type.GRASS, Type.BUG), ground.getIneffective());
        assertEquals(List.of(Type.FIRE, Type.ELECTRIC, Type.POISON, Type.ROCK, Type.STEEL), ground.getEffective());
        assertEquals("\u001B[43m\u001B[30m GROUND \u001B[0m", ground.getTag());
    }

    @Test
    public void testFlyingType() {
        Type flying = Type.FLYING;
        assertEquals(List.of(), flying.getImmune());
        assertEquals(List.of(Type.ELECTRIC, Type.ROCK, Type.STEEL), flying.getIneffective());
        assertEquals(List.of(Type.GRASS, Type.FIGHTING, Type.BUG), flying.getEffective());
        assertEquals("\u001B[46m\u001B[30m FLYING \u001B[0m", flying.getTag());
    }

    @Test
    public void testPsychicType() {
        Type psychic = Type.PSYCHIC;
        assertEquals(List.of(Type.DARK), psychic.getImmune());
        assertEquals(List.of(Type.STEEL, Type.PSYCHIC), psychic.getIneffective());
        assertEquals(List.of(Type.FIGHTING, Type.POISON), psychic.getEffective());
        assertEquals("\u001B[105m\u001B[30m PSYCHIC \u001B[0m", psychic.getTag());
    }

    @Test
    public void testBugType() {
        Type bug = Type.BUG;
        assertEquals(List.of(), bug.getImmune());
        assertEquals(List.of(Type.FIRE, Type.FIGHTING, Type.POISON, Type.FLYING, Type.GHOST, Type.STEEL, Type.FAIRY),
                bug.getIneffective());
        assertEquals(List.of(Type.GRASS, Type.PSYCHIC, Type.DARK), bug.getEffective());
        assertEquals("\u001B[42m\u001B[30m BUG \u001B[0m", bug.getTag());
    }

    @Test
    public void testRockType() {
        Type rock = Type.ROCK;
        assertEquals(List.of(), rock.getImmune());
        assertEquals(List.of(Type.FIGHTING, Type.GROUND, Type.STEEL), rock.getIneffective());
        assertEquals(List.of(Type.FIRE, Type.ICE, Type.FLYING, Type.BUG), rock.getEffective());
        assertEquals("\u001B[43m\u001B[30m ROCK \u001B[0m", rock.getTag());
    }

    @Test
    public void testGhostType() {
        Type ghost = Type.GHOST;
        assertEquals(List.of(Type.NORMAL), ghost.getImmune());
        assertEquals(List.of(Type.DARK), ghost.getIneffective());
        assertEquals(List.of(Type.PSYCHIC, Type.GHOST), ghost.getEffective());
        assertEquals("\u001B[45m\u001B[37m GHOST \u001B[0m", ghost.getTag());
    }

    @Test
    public void testDragonType() {
        Type dragon = Type.DRAGON;
        assertEquals(List.of(Type.FAIRY), dragon.getImmune());
        assertEquals(List.of(Type.STEEL), dragon.getIneffective());
        assertEquals(List.of(Type.DRAGON), dragon.getEffective());
        assertEquals("\u001B[105m\u001B[30m DRAGON \u001B[0m", dragon.getTag());
    }

    @Test
    public void testDarkType() {
        Type dark = Type.DARK;
        assertEquals(List.of(), dark.getImmune());
        assertEquals(List.of(Type.FIGHTING, Type.DARK, Type.FAIRY), dark.getIneffective());
        assertEquals(List.of(Type.PSYCHIC, Type.GHOST), dark.getEffective());
        assertEquals("\u001B[40m\u001B[37m DARK \u001B[0m", dark.getTag());
    }

    @Test
    public void testSteelType() {
        Type steel = Type.STEEL;
        assertEquals(List.of(Type.POISON), steel.getImmune());
        assertEquals(List.of(Type.FIRE, Type.WATER, Type.ELECTRIC, Type.STEEL), steel.getIneffective());
        assertEquals(List.of(Type.ICE, Type.ROCK, Type.FAIRY), steel.getEffective());
        assertEquals("\u001B[47m\u001B[30m STEEL \u001B[0m", steel.getTag());
    }

    @Test
    public void testFairyType() {
        Type fairy = Type.FAIRY;
        assertEquals(List.of(), fairy.getImmune());
        assertEquals(List.of(Type.FIRE, Type.POISON, Type.STEEL), fairy.getIneffective());
        assertEquals(List.of(Type.FIGHTING, Type.DRAGON, Type.DARK), fairy.getEffective());
        assertEquals("\u001B[105m\u001B[30m FAIRY \u001B[0m", fairy.getTag());
    }
}
