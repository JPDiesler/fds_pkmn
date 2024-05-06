public class PhysicalMove extends Move {
    
    public PhysicalMove(String name, Type type, int power, int accuracy, int attackPoints) {
        super(name,type, power, accuracy, attackPoints);
    }

    @Override
    public void use(Pokemon user, Pokemon[] targets, Weather weather){
        int weather_multiplier = 1;
        for (Pokemon target : targets){
            double random_multiplier = Math.random() * 0.16 + 0.85;
            double stab_multiplier = this.calculateStabMultiplier(user);
            double type_multiplier = this.getMoveEffectiveness(target.getPrimaryType(), target.getSecondaryType(), this.getType());
            if (type_multiplier ==0){
                System.out.println("It doesn't affect " + target.getName() + "...");
            }else if(type_multiplier == 0.5){
                System.out.println("It's not very effective...");
            }else if(type_multiplier == 2){
                System.out.println("It's very effective!");
            }
            if (type_multiplier > 0){
                double base_damage = (((((user.level*2)/5)+2)*this.getPower()*(user.getAttack()/target.getDefense())/50)+2);
                double effective_damage = base_damage * stab_multiplier * type_multiplier * random_multiplier * weather_multiplier;
                target.setHp((int)(target.getHp() - effective_damage));
            }
            
        }
    }

    @Override
    public void use(Pokemon user, Pokemon target, Weather weather){
        int weather_multiplier = 1;
        double random_multiplier = Math.random() * 0.16 + 0.85;
        double stab_multiplier = this.calculateStabMultiplier(user);
        double type_multiplier = this.getMoveEffectiveness(target.getPrimaryType(), target.getSecondaryType(), this.getType());
        System.out.println("type_multiplier: " + type_multiplier);
        if (type_multiplier ==0){
            System.out.println("It doesn't affect " + target.getName() + "...");
        }else if(type_multiplier == 0.5){
            System.out.println("It's not very effective...");
        }else if(type_multiplier == 2){
            System.out.println("It's very effective!");
        }
        if (type_multiplier > 0){
            double base_damage = (((((user.level*2)/5)+2)*this.getPower()*(user.getAttack()/target.getDefense())/50)+2);
            double effective_damage = base_damage * stab_multiplier * type_multiplier * random_multiplier * weather_multiplier;
            target.setHp((int)(target.getHp() - effective_damage));
        }
    }
}
