import java.util.function.BiConsumer;

public enum Ability {
    OVERGROW(Trigger.HP_LOW, Target.SELF, (self, opponent) -> self.increaseAttack()),
    BLAZE(Trigger.HP_LOW, Target.SELF, (self, opponent) -> self.increaseAttack()),
    TORRENT(Trigger.HP_LOW, Target.SELF, (self, opponent) -> self.increaseAttack()),
    SWARM(Trigger.HP_LOW, Target.SELF, (self, opponent) -> self.increaseAttack()),
    SHED_SKIN(Trigger.EACH_TURN, Target.SELF, (self, opponent) -> self.cureStatus()),
    GUTS(Trigger.STATUS_CONDITION, Target.SELF, (self, opponent) -> self.increaseAttack()),
    STATIC(Trigger.HIT_BY_PHYSICAL_MOVE, Target.OPPONENT, (self, opponent) -> opponent.paralyze()),
    CUTE_CHARM(Trigger.HIT_BY_PHYSICAL_MOVE, Target.OPPONENT, (self, opponent) -> opponent.infatuate()),
    FLASH_FIRE(Trigger.HIT_BY_FIRE_MOVE, Target.SELF, (self, opponent) -> self.increaseFirePower()),
    SHADOW_TAG(Trigger.OPPONENT_SWITCH, Target.OPPONENT, (self, opponent) -> opponent.preventSwitch()),
    SPEED_BOOST(Trigger.EACH_TURN, Target.SELF, (self, opponent) -> self.increaseSpeed()),
    WATER_ABSORB(Trigger.HIT_BY_WATER_MOVE, Target.SELF, (self, opponent) -> self.heal());

    private final Trigger trigger;
    private final Target target;
    private final BiConsumer<Pokemon, Pokemon> effect;

    Ability(Trigger trigger, Target target, BiConsumer<Pokemon, Pokemon> effect) {
        this.trigger = trigger;
        this.target = target;
        this.effect = effect;
    }

    public void applyEffect(Pokemon self, Pokemon opponent) {
        effect.accept(self, opponent);
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public Target getTarget() {
        return target;
    }
}

public enum Trigger {
    HP_LOW,
    EACH_TURN,
    STATUS_CONDITION,
    HIT_BY_PHYSICAL_MOVE,
    HIT_BY_FIRE_MOVE,
    OPPONENT_SWITCH,
    HIT_BY_WATER_MOVE
}

public enum Target {
    SELF,
    OPPONENT,
    BOTH
}
