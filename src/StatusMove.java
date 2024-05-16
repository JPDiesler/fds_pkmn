public class StatusMove extends Move{
    
    public StatusMove(String name, Type type, int power, int accuracy, int attackPoints) {
        super(name,type, power, accuracy, attackPoints);
    }

    @Override
    public void use(Pokemon user, Pokemon target, Weather weather){
        
    }

}
