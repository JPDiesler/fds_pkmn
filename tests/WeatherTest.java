import static org.junit.Assert.*;
import org.junit.Test;

public class WeatherTest {

    @Test
    public void testApplyEffect_ClearWeather() {
        Pokemon pokemon1 = new Pokemon();
        Pokemon pokemon2 = new Pokemon();
        Weather weather = Weather.CLEAR;
        Weather newWeather = weather.applyEffect(pokemon1, pokemon2, true);
        int initialHP_Pokemon1 = pokemon1.getHp();
        int initialHP_Pokemon2 = pokemon2.getHp();
        assertEquals(Weather.CLEAR, newWeather);
        assertTrue(initialHP_Pokemon1 == pokemon1.getHp());
        assertTrue(initialHP_Pokemon2 == pokemon2.getHp());
    }

    @Test
    public void testApplyEffect_SandstormWeather() {
        Move defaultPhysicalMove = new PhysicalMove();
        Move defaultSpecialMove = new SpecialMove();
        Move[] moves = { defaultPhysicalMove, defaultPhysicalMove, defaultSpecialMove, defaultSpecialMove };
        Pokemon pokemon1 = new Pokemon(74, "Geodude", Type.ROCK, 10, StatusEffect.NONE, 40, 80, 100, 30, 30, 20, moves);
        Pokemon pokemon2 = new Pokemon(7, "Squirtle", Type.WATER, 10, StatusEffect.NONE, 44, 48, 65, 50, 64, 43, moves);
        int initialHP_Pokemon1 = pokemon1.getHp();
        int initialHP_Pokemon2 = pokemon2.getHp();
        Weather weather = Weather.SANDSTORM;
        Weather newWeather = weather.applyEffect(pokemon1, pokemon2, true);
        assertNotEquals(Weather.CLEAR, newWeather);
        assertTrue(initialHP_Pokemon1 == pokemon1.getHp());
        assertTrue(initialHP_Pokemon2 > pokemon2.getHp());
    }

    @Test
    public void testGetMultiplier_SunnyWeather() {
        Weather weather = Weather.SUNNY;
        assertEquals(1.5, weather.getMultiplier(Type.FIRE), 0.01);
        assertEquals(0.5, weather.getMultiplier(Type.WATER), 0.01);
    }

    @Test
    public void testGetMultiplier_RainWeather() {
        Weather weather = Weather.RAIN;
        assertEquals(1.5, weather.getMultiplier(Type.WATER), 0.01);
        assertEquals(0.5, weather.getMultiplier(Type.FIRE), 0.01);
    }

    @Test
    public void testApplyWeatherDamage() {
        Pokemon pokemon1 = new Pokemon();
        Pokemon pokemon2 = new Pokemon();
        Weather weather = Weather.HAIL;
        weather.applyEffect(pokemon1, pokemon2, true);
        assertNotEquals(pokemon1.getMaxHp(), pokemon1.getHp());
        assertNotEquals(pokemon2.getMaxHp(), pokemon2.getHp());
        pokemon1.setHp(pokemon1.getMaxHp());
        pokemon2.setHp(pokemon2.getMaxHp());
        weather = Weather.SANDSTORM;
        weather.applyEffect(pokemon1, pokemon2, true);
        assertNotEquals(pokemon1.getMaxHp(), pokemon1.getHp());
        assertNotEquals(pokemon2.getMaxHp(), pokemon2.getHp());

    }
}
