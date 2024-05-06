public enum Weather {
    CLEAR,
    SUNNY,
    RAIN,
    SANDSTORM,
    HAIL,
    HARSH_SUNLIGHT,
    HEAVY_RAIN,
    EXTREMELY_HARSH_SUNLIGHT,
    FOG,
    SNOW;

    public void applyEffect(Pokemon pokemon1, Pokemon pokemon2) {
        switch (this) {
            case SANDSTORM:
                // Apply sandstorm effects
                Type[] types1 = pokemon1.getTyping();
                Type[] types2 = pokemon2.getTyping();
                if (!(types1[0] == Type.ROCK || types1[0] == Type.GROUND || types1[0] == Type.STEEL || 
                      types1[1] == Type.ROCK || types1[1] == Type.GROUND || types1[1] == Type.STEEL)) {
                    pokemon1.setHp(pokemon1.getHp() - pokemon1.getMaxHp() / 16);
                }
                if (!(types2[0] == Type.ROCK || types2[0] == Type.GROUND || types2[0] == Type.STEEL || 
                      types2[1] == Type.ROCK || types2[1] == Type.GROUND || types2[1] == Type.STEEL)) {
                    pokemon2.setHp(pokemon2.getHp() - pokemon2.getMaxHp() / 16);
                }
                break;
            case HAIL:
                // Apply hail effects
                types1 = pokemon1.getTyping();
                types2 = pokemon2.getTyping();
                if (!(types1[0] == Type.ICE || types1[1] == Type.ICE)) {
                    pokemon1.setHp(pokemon1.getHp() - pokemon1.getMaxHp() / 16);
                }
                if (!(types2[0] == Type.ICE || types2[1] == Type.ICE)) {
                    pokemon2.setHp(pokemon2.getHp() - pokemon2.getMaxHp() / 16);
                }
                break;
            case CLEAR:
            case SUNNY:
            case RAIN:
            case HARSH_SUNLIGHT:
            case HEAVY_RAIN:
            case EXTREMELY_HARSH_SUNLIGHT:
            case FOG:
            case SNOW:
            default:
                // No effects
                break;
        }
    }
}
