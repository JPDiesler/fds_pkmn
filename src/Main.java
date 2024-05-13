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
        Move FrenzyPlant = new SpecialMove("Frenzy Plant", Type.GRASS, 150, 90, 5);
        Move FocusPunch = new PhysicalMove("Focus Punch", Type.FIGHTING, 150, 100, 5);
        Move IceBeam = new SpecialMove("Ice Beam", Type.ICE, 90, 100, 10);
        Move HydroPump = new SpecialMove("Hydro Pump", Type.WATER, 110, 80, 5);
        Move TemperFlare = new PhysicalMove("Temper Flare", Type.FIRE, 75, 100, 10);
        Move HydroCannon = new SpecialMove("Hydro Cannon", Type.WATER, 150, 90, 5);
        Move RockSlide = new PhysicalMove("Rock Slide", Type.ROCK, 75, 90, 10);
        Move Psychic = new SpecialMove("Psychic", Type.PSYCHIC, 90, 100, 10);
        Move ShadowBall = new SpecialMove("Shadow Ball", Type.GHOST, 80, 100, 15);
        Move EnergyBall = new SpecialMove("Energy Ball", Type.GRASS, 90, 100, 10);
        Move FocusBlast = new SpecialMove("Focus Blast", Type.FIGHTING, 120, 70, 5);
        Move IcePunch = new PhysicalMove("Ice Punch", Type.ICE, 75, 100, 15);
        Move SupercellSlam = new PhysicalMove("Supercell Slam", Type.ELECTRIC, 100, 90, 15);
        Move SteelWing = new PhysicalMove("Steel Wing", Type.STEEL, 70, 90, 15);
        Move GigaImpact = new PhysicalMove("Giga Impact", Type.NORMAL, 150, 90, 5);
        Move PoisonJab = new PhysicalMove("Poison Jab", Type.POISON, 80, 100, 20);
        Move Avalanche = new PhysicalMove("Avalanche", Type.ICE, 60, 100, 10);
        Move SolarBeam = new SpecialMove("Solar Beam", Type.GRASS, 120, 100, 10);
        Move IronHead = new PhysicalMove("Iron Head", Type.STEEL, 80, 100, 15);
        Move FlareBlitz = new PhysicalMove("Flare Blitz", Type.FIRE, 120, 100, 15);
        Move PlayRough = new PhysicalMove("Play Rough", Type.FAIRY, 90, 90, 10);


        
        Move[] InfernapeMoves = {CloseCombat,BlastBurn,GunkShot,Earthquake};
        Move[] GarchompMoves = {Earthquake,Outrage,DracoMeteor,FireStorm};
        Move[] LuxrayMoves = {Thunder,WildCharge,TakeDown,Crunch};
        Move[] StaraptorMoves = {BraveBird,HyperBeam,CloseCombat,Hurricane};
        Move[] SceptileMoves = {FrenzyPlant,Earthquake,Outrage,FocusPunch};
        Move[] GyaradosMoves = {HydroPump,Outrage,IceBeam,TemperFlare};
        Move[] BlastoiseMoves = {HydroCannon,Earthquake,IceBeam,RockSlide};
        Move[] AlakazamMoves = {Psychic,ShadowBall,EnergyBall,FocusBlast};
        Move[] ElectevireMoves = {Earthquake,SupercellSlam,FocusBlast,IcePunch};
        Move[] PidgeotMoves = {BraveBird,HyperBeam,SteelWing,GigaImpact};
        Move[] ArcanineMoves = {FlareBlitz,IronHead,PlayRough,SolarBeam};
        Move[] NidokingMoves = {Earthquake,FireStorm,Avalanche,PoisonJab};


        Pokemon Infernape = new Pokemon(392,"Infernape", Type.FIRE, Type.FIGHTING, 75, StatusEffect.NONE, 269, 269, 254, 200,254,200,260,InfernapeMoves);
        Pokemon Garchomp = new Pokemon(445,"Garchomp", Type.DRAGON, Type.GROUND, 75, StatusEffect.NONE, 317, 317, 297, 239,214,223,250,GarchompMoves);
        Pokemon Luxray = new Pokemon(405,"Luxray", Type.ELECTRIC, null, 75, StatusEffect.NONE, 275, 275, 280, 213,239,213,198,LuxrayMoves);
        Pokemon Staraptor = new Pokemon(398,"Staraptor", Type.NORMAL, Type.FLYING, 75, StatusEffect.NONE, 283, 283, 280, 198,165,181,247,StaraptorMoves);
        Pokemon Sceptile = new Pokemon(254,"Sceptile", Type.GRASS, null, 75, StatusEffect.NONE, 260, 260, 223, 190,256,223,2890,SceptileMoves);
        Pokemon Gyarados = new Pokemon(130,"Gyarados", Type.WATER, Type.FLYING, 75, StatusEffect.NONE, 274, 274, 220, 247,223,256,211,GyaradosMoves);
        
        Pokemon Blastoise = new Pokemon(9,"Blastoise", Type.WATER, null, 75, StatusEffect.NONE, 299, 299, 254, 298,254,298,198,BlastoiseMoves);
        Pokemon Alakazam = new Pokemon(65,"Alakazam", Type.PSYCHIC, null, 75, StatusEffect.NONE, 238, 238, 165, 157,305,239,280,AlakazamMoves);
        Pokemon Electevire = new Pokemon(466,"Electevire", Type.ELECTRIC, null, 75, StatusEffect.NONE, 268, 268, 286, 193,239,223,239,ElectevireMoves);
        Pokemon Pidgeot = new Pokemon(18,"Pidgeot", Type.NORMAL, Type.FLYING, 75, StatusEffect.NONE, 280, 280, 214, 206,198,198,249,PidgeotMoves);
        Pokemon Arcanine = new Pokemon(59,"Arcanine", Type.FIRE, null, 75, StatusEffect.NONE, 290, 290, 264, 214,247,214,239,ArcanineMoves);
        Pokemon Nidoking = new Pokemon(34,"Nidoking", Type.POISON, Type.GROUND, 75, StatusEffect.NONE, 277, 277, 250, 210,223,206,223,NidokingMoves);

        Pokemon[] RedsTeam = {Infernape,Garchomp,Sceptile,Luxray,Staraptor,Gyarados};
        Pokemon[] BluesTeam = {Blastoise,Alakazam,Electevire,Pidgeot,Arcanine,Nidoking};
        Trainer Red = new Trainer(1,"Red", RedsTeam);
        Trainer Blue = new Trainer(2,"Blue", BluesTeam);
        int won = Red.battle(Blue, true);
    }
}