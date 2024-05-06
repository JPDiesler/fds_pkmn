public class Main {
    public static void main(String[] args) {
        Move CloseCombat = new PhysicalMove("Close Combat", Type.FIGHTING, 120, 100, 5);
        Move BlastBurn = new SpecialMove("Blast Burn", Type.FIRE, 150, 90, 5);
        Move GunkShot = new PhysicalMove("Gunk Shot", Type.POISON, 120, 80, 5);
        Move Earthquake = new PhysicalMove("Earthquake", Type.GROUND, 100, 100, 10);
        Move WildCharge = new PhysicalMove("Wild Charge", Type.ELECTRIC, 90, 100, 15);
        Move Thunder = new SpecialMove("Thunder", Type.ELECTRIC, 110, 70, 10);
        Move TakeDown = new PhysicalMove("Take Down", Type.NORMAL, 90, 85, 20);
        Move Crunch = new PhysicalMove("Crunch", Type.DARK, 80, 100, 15);
        Move Outrage = new PhysicalMove("Outrage", Type.DRAGON, 120, 100, 10);
        Move DracoMeteor = new SpecialMove("Draco Meteor", Type.DRAGON, 130, 90, 5);
        Move FireStorm = new SpecialMove("Fire Storm", Type.FIRE, 110, 85, 10);
        Move HyperBeam = new SpecialMove("HyperBeam", Type.NORMAL, 150, 90, 5);
        Move BraveBird = new PhysicalMove("Brave Bird", Type.FLYING, 120, 100, 15);
        Move Hurricane = new SpecialMove("Hurricane", Type.FLYING, 110, 70, 10);
        
        Move[] InfernapeMoves = {CloseCombat,BlastBurn,GunkShot,Earthquake};
        Move[] GarchompMoves = {Earthquake,Outrage,DracoMeteor,FireStorm};
        Move[] LuxrayMoves = {Thunder,WildCharge,TakeDown,Crunch};
        Move[] StaraptorMoves = {BraveBird,HyperBeam,CloseCombat,Hurricane};

        Pokemon Infernape = new Pokemon(392,"Infernape", Type.FIRE, Type.FIGHTING, 75, StatusEffect.NONE, 269, 269, 254, 200,254,200,260,InfernapeMoves);
        Pokemon Garchomp = new Pokemon(445,"Garchomp", Type.DRAGON, Type.GROUND, 75, StatusEffect.NONE, 329, 329, 300, 200,254,200,260,GarchompMoves);
        Pokemon Luxray = new Pokemon(405,"Luxray", Type.ELECTRIC, null, 75, StatusEffect.NONE, 319, 319, 300, 200,254,200,260,LuxrayMoves);
        Pokemon Staraptor = new Pokemon(398,"Staraptor", Type.NORMAL, Type.FLYING, 75, StatusEffect.NONE, 283, 283, 280, 198,165,181,247,StaraptorMoves);
    
        Pokemon[] RedsTeam = {Infernape,Garchomp};
        Pokemon[] BluesTeam = {Luxray,Staraptor};
        Trainer Red = new Trainer(1,"Red", RedsTeam);
        Trainer Blue = new Trainer(2,"Blue", BluesTeam);
        int won = Red.battle(Blue, false);
        if (won == 1) {
            System.out.println(Red.getName() + " won the battle!");
        } else if (won == -1) {
            System.out.println(Blue.getName() + " won the battle!");
        } else {
            System.out.println("It was a tie!");
        }

    }
}