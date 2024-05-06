public class PhysicalMove extends Move {
    
    public PhysicalMove(String name, Type type, int power, int accuracy, int attackPoints) {
        super(name,type, power, accuracy, attackPoints);
    }

    @Override
    public void use(Pokemon user, Pokemon[] targets, Weather weather,boolean verbose){
        int weather_multiplier = 1;
        for (Pokemon target : targets){
            double random_multiplier = Math.random() * 0.16 + 0.85;
            double stab_multiplier = this.calculateStabMultiplier(user);
            double type_multiplier = this.getMoveEffectiveness(target.getPrimaryType(), target.getSecondaryType(), this.getType());
            boolean hit = Math.random() * 100 < this.getAccuracy();
            double crit = Math.random() < 0.1 ? 1.5 : 1.0;
            System.out.println(user.getName() + " used " + this.getName());
            if (type_multiplier ==0){
                System.out.println("It doesn't affect " + target.getName() + "...");
            }else if(type_multiplier == 0.5){
                if(crit == 1.5){
                    System.out.println("Critical Hit!");
                }
                System.out.println("It's not very effective...");
            }else if(type_multiplier == 2){
                if(crit == 1.5){
                    System.out.println("Critical Hit!");
                }
                System.out.println("It's very effective!");
            }
            if (type_multiplier > 0 && hit){
                double base_damage = (((((user.level*2)/5)+2)*this.getPower()*(user.getAttack()/target.getDefense())/50)+2);
                double effective_damage = base_damage * stab_multiplier * type_multiplier * random_multiplier * crit * weather_multiplier;
                target.setHp((int)(target.getHp() - effective_damage));
                if (verbose){
                    System.out.println(user.getName() + " used " + this.getName() + " of Type " + this.getType() + " on " + target.getName() + " of Type " + target.getPrimaryType() + (target.getSecondaryType() != null ? "/" + target.getSecondaryType() : "") + " and dealt " + (int)effective_damage + " damage!");
                    System.out.println("Random_multiplier: " + random_multiplier);
                    System.out.println("Stab_multiplier: " + stab_multiplier);
                    System.out.println("Type_multiplier: " + type_multiplier);
                    System.out.println("Crit: " + crit);
                    System.out.println("Base Damage: " + base_damage);
                    System.out.println("Effective Damage: " + effective_damage);
                }
            }else if(!hit){
                System.out.println(user.getName() + " missed the attack!");
            }
            
        }
    }

    @Override
    public void use(Pokemon user, Pokemon target, Weather weather,boolean verbose){
        int weather_multiplier = 1;
        double random_multiplier = Math.random() * 0.16 + 0.85;
        double stab_multiplier = this.calculateStabMultiplier(user);
        double type_multiplier = this.getMoveEffectiveness(target.getPrimaryType(), target.getSecondaryType(), this.getType());
        boolean hit = Math.random() * 100 < this.getAccuracy();
        double crit = Math.random() < 0.1 ? 1.5 : 1.0;
        System.out.println(user.getName() + " used " + this.getName());
        if (type_multiplier ==0){
            System.out.println("It doesn't affect " + target.getName() + "...");
        }else if(type_multiplier == 0.5){
            if(crit == 1.5){
                System.out.println("Critical Hit!");
            }
            System.out.println("It's not very effective...");
        }else if(type_multiplier == 2){
            if(crit == 1.5){
                System.out.println("Critical Hit!");
            }
            System.out.println("It's very effective!");
        }
        if (type_multiplier > 0 && hit){
            double base_damage = (((((user.level*2)/5)+2)*this.getPower()*(user.getAttack()/target.getDefense())/50)+2);
            double effective_damage = base_damage * stab_multiplier * type_multiplier * random_multiplier * crit * weather_multiplier;
            target.setHp((int)(target.getHp() - effective_damage));
            if (verbose){
                System.out.println(user.getName() + " used " + this.getName() + " of Type " + this.getType() + " on " + target.getName() + " of Type " + target.getPrimaryType() + (target.getSecondaryType() != null ? "/" + target.getSecondaryType() : "") + " and dealt " + (int)effective_damage + " damage!");
                System.out.println("Random_multiplier: " + random_multiplier);
                    System.out.println("Stab_multiplier: " + stab_multiplier);
                    System.out.println("Type_multiplier: " + type_multiplier);
                    System.out.println("Crit: " + crit);
                    System.out.println("Base Damage: " + base_damage);
                    System.out.println("Effective Damage: " + effective_damage);
            }
        }else if(!hit){
            System.out.println(user.getName() + " missed the attack!");
        }
    }
}
