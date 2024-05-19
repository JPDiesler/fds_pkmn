public class Main {
        public static void main(String[] args) {
                Move CloseCombat = new PhysicalMove("Close Combat", Type.FIGHTING, 120, 100, 5, Effect.LOWERDEFENCES,
                                1);
                Move BlastBurn = new SpecialMove("Blast Burn", Type.FIRE, 150, 90, 5, Effect.BURN, 0.75);
                Move GunkShot = new PhysicalMove("Gunk Shot", Type.POISON, 120, 80, 5, Effect.POISON, 0.3);
                Move Earthquake = new PhysicalMove("Earthquake", Type.GROUND, 100, 100, 10);
                Move WildCharge = new PhysicalMove("Wild Charge", Type.ELECTRIC, 90, 100, 15);
                Move Thunder = new SpecialMove("Thunder", Type.ELECTRIC, 110, 70, 10, Effect.PARALYSIS, 0.5);
                Move TakeDown = new PhysicalMove("Take Down", Type.NORMAL, 90, 85, 20);
                Move Crunch = new PhysicalMove("Crunch", Type.DARK, 80, 100, 15);
                Move Outrage = new PhysicalMove("Outrage", Type.DRAGON, 120, 100, 10, Effect.LOWERATTACKS, 1);
                Move DracoMeteor = new SpecialMove("Draco Meteor", Type.DRAGON, 130, 90, 5);
                Move FireBlast = new SpecialMove("Fire Blast", Type.FIRE, 110, 85, 10, Effect.BURN, 0.6);
                Move HyperBeam = new SpecialMove("HyperBeam", Type.NORMAL, 150, 90, 5);
                Move BraveBird = new PhysicalMove("Brave Bird", Type.FLYING, 120, 100, 15);
                Move Hurricane = new SpecialMove("Hurricane", Type.FLYING, 110, 70, 10);
                Move FrenzyPlant = new SpecialMove("Frenzy Plant", Type.GRASS, 150, 90, 5);
                Move FocusPunch = new PhysicalMove("Focus Punch", Type.FIGHTING, 150, 100, 5);
                Move IceBeam = new SpecialMove("Ice Beam", Type.ICE, 90, 100, 10, Effect.FREEZE, 0.85);
                Move HydroPump = new SpecialMove("Hydro Pump", Type.WATER, 110, 80, 5);
                Move TemperFlare = new PhysicalMove("Temper Flare", Type.FIRE, 75, 100, 10, Effect.BURN, 0.3);
                Move HydroCannon = new SpecialMove("Hydro Cannon", Type.WATER, 150, 90, 5);
                Move RockSlide = new PhysicalMove("Rock Slide", Type.ROCK, 75, 90, 10);
                Move Psychic = new SpecialMove("Psychic", Type.PSYCHIC, 90, 100, 10);
                Move ShadowBall = new SpecialMove("Shadow Ball", Type.GHOST, 80, 100, 15);
                Move EnergyBall = new SpecialMove("Energy Ball", Type.GRASS, 90, 100, 10);
                Move FocusBlast = new SpecialMove("Focus Blast", Type.FIGHTING, 120, 70, 5);
                Move IcePunch = new PhysicalMove("Ice Punch", Type.ICE, 75, 100, 15, Effect.FREEZE, 0.6);
                Move SupercellSlam = new PhysicalMove("Supercell Slam", Type.ELECTRIC, 100, 90, 15);
                Move SteelWing = new PhysicalMove("Steel Wing", Type.STEEL, 70, 90, 15);
                Move GigaImpact = new PhysicalMove("Giga Impact", Type.NORMAL, 150, 90, 5);
                Move PoisonJab = new PhysicalMove("Poison Jab", Type.POISON, 80, 100, 20, Effect.POISON, 1);
                Move Avalanche = new PhysicalMove("Avalanche", Type.ICE, 60, 100, 10);
                Move SolarBeam = new SpecialMove("Solar Beam", Type.GRASS, 120, 100, 10);
                Move IronHead = new PhysicalMove("Iron Head", Type.STEEL, 80, 100, 15);
                Move FlareBlitz = new PhysicalMove("Flare Blitz", Type.FIRE, 120, 100, 15, Effect.BURN, 0.3);
                Move PlayRough = new PhysicalMove("Play Rough", Type.FAIRY, 90, 90, 10);

                Move[] InfernapeMoves = { CloseCombat, BlastBurn, GunkShot, Earthquake };
                Move[] GarchompMoves = { Earthquake, Outrage, DracoMeteor, FireBlast };
                Move[] LuxrayMoves = { Thunder, WildCharge, TakeDown, Crunch };
                Move[] StaraptorMoves = { BraveBird, HyperBeam, CloseCombat, Hurricane };
                Move[] SceptileMoves = { FrenzyPlant, Earthquake, Outrage, FocusPunch };
                Move[] GyaradosMoves = { HydroPump, Outrage, IceBeam, TemperFlare };
                Move[] BlastoiseMoves = { HydroCannon, Earthquake, IceBeam, RockSlide };
                Move[] AlakazamMoves = { Psychic, ShadowBall, EnergyBall, FocusBlast };
                Move[] ElectevireMoves = { Earthquake, SupercellSlam, FocusBlast, IcePunch };
                Move[] PidgeotMoves = { BraveBird, HyperBeam, SteelWing, GigaImpact };
                Move[] ArcanineMoves = { FlareBlitz, IronHead, PlayRough, SolarBeam };
                Move[] NidokingMoves = { Earthquake, FireBlast, Avalanche, PoisonJab };

                Pokemon Infernape = new Pokemon(392, "Infernape", Type.FIRE, Type.FIGHTING, 75, StatusEffect.NONE, 76,
                                104, 71,
                                104, 71, 108, InfernapeMoves);
                Pokemon Garchomp = new Pokemon(445, "Garchomp", Type.DRAGON, Type.GROUND, 75, StatusEffect.NONE, 108,
                                130, 95,
                                80, 85, 102, GarchompMoves);
                Pokemon Luxray = new Pokemon(405, "Luxray", Type.ELECTRIC, null, 75, StatusEffect.NONE, 80, 120, 79,
                                95, 79,
                                70, LuxrayMoves);
                Pokemon Staraptor = new Pokemon(398, "Staraptor", Type.NORMAL, Type.FLYING, 75, StatusEffect.NONE, 85,
                                120, 70,
                                50, 60, 100, StaraptorMoves);
                Pokemon Sceptile = new Pokemon(254, "Sceptile", Type.GRASS, null, 75, StatusEffect.NONE, 85, 120, 70,
                                50, 60,
                                100, SceptileMoves);
                Pokemon Gyarados = new Pokemon(130, "Gyarados", Type.WATER, Type.FLYING, 75, StatusEffect.NONE, 95, 125,
                                79,
                                60, 100, 81, GyaradosMoves);

                Pokemon Blastoise = new Pokemon(9, "Blastoise", Type.WATER, null, 75, StatusEffect.NONE, 79, 83, 100,
                                85, 105,
                                78, BlastoiseMoves);
                Pokemon Alakazam = new Pokemon(65, "Alakazam", Type.PSYCHIC, null, 75, StatusEffect.NONE, 55, 50, 45,
                                135,
                                95, 120, AlakazamMoves);
                Pokemon Electevire = new Pokemon(466, "Electevire", Type.ELECTRIC, null, 75, StatusEffect.NONE, 75, 123,
                                67,
                                95, 85, 95, ElectevireMoves);
                Pokemon Pidgeot = new Pokemon(18, "Pidgeot", Type.NORMAL, Type.FLYING, 75, StatusEffect.NONE, 83, 80,
                                75, 70,
                                70, 101, PidgeotMoves);
                Pokemon Arcanine = new Pokemon(59, "Arcanine", Type.FIRE, null, 75, StatusEffect.NONE, 90, 110, 80,
                                100, 80,
                                95, ArcanineMoves);
                Pokemon Nidoking = new Pokemon(34, "Nidoking", Type.POISON, Type.GROUND, 75, StatusEffect.NONE, 81, 102,
                                77,
                                85, 75, 85, NidokingMoves);

                Pokemon[] PokeDex = { Infernape, Garchomp, Luxray, Staraptor, Sceptile, Gyarados, Blastoise, Alakazam,
                                Electevire,
                                Pidgeot, Arcanine, Nidoking };

                Pokemon[] GreensTeam = { Luxray, Infernape, Pidgeot };
                Pokemon[] RedsTeam = { Infernape, Garchomp, Sceptile, Luxray, Staraptor, Gyarados };
                Pokemon[] BluesTeam = { Blastoise, Alakazam, Electevire, Pidgeot, Arcanine, Nidoking };

                Trainer Green = new Trainer(0, "Green", "PKMN-Trainer", GreensTeam);
                Trainer Red = new Trainer(1, "Red", "PKMN-Trainer", RedsTeam);
                Trainer Blue = new Trainer(2, "Blue", "Champion", BluesTeam);

                Move Pyroball = new PhysicalMove("Pyro Ball", Type.FIRE, 120, 90, 5, Effect.BURN, 0.75);
                Move ScorchingSands = new SpecialMove("Scorching Sands", Type.GROUND, 70, 100, 10, Effect.BURN, 0.3);
                Move Bounce = new PhysicalMove("Bounce", Type.FLYING, 85, 85, 5, Effect.PARALYSIS, 0.3);
                Move HighJumpKick = new PhysicalMove("High Jump Kick", Type.FIGHTING, 130, 90, 10);
                Move Inferno = new SpecialMove("Inferno", Type.FIRE, 100, 50, 5, Effect.BURN, 1);
                Move DragonClaw = new PhysicalMove("Dragon Claw", Type.DRAGON, 80, 100, 15);
                Move ThunderPunch = new PhysicalMove("Thunder Punch", Type.ELECTRIC, 75, 100, 15);
                Move AerialAce = new PhysicalMove("Aerial Ace", Type.FLYING, 60, 100, 20);
                Move Overheat = new SpecialMove("Overheat", Type.FIRE, 130, 90, 5, Effect.BURN, 0.5);
                Move DragonAscent = new PhysicalMove("Dragon Ascent", Type.FLYING, 120, 100, 5);
                Move Blizzard = new SpecialMove("Blizzard", Type.ICE, 110, 70, 5, Effect.FREEZE, 0.1);
                Move[] JP_CinderaceMoves = { Pyroball, Bounce, HighJumpKick, ScorchingSands };
                Move[] JP_CharizardMoves = { Inferno, DragonClaw, Earthquake, ThunderPunch };
                Move[] JP_TyphlosionMoves = { Overheat, Earthquake, IronHead, AerialAce };
                Move[] JP_TalonflameMoves = { BraveBird, Hurricane, SteelWing, FlareBlitz };
                Move[] JP_RayquazaMoves = { DragonAscent, DracoMeteor, Blizzard, Thunder };
                Pokemon JP_Infernape = new Pokemon(392, "Infernape", Type.FIRE, Type.FIGHTING, 90, StatusEffect.NONE,
                                76,
                                104, 71,
                                104, 71, 108, InfernapeMoves);
                Pokemon JP_Cinderace = new Pokemon(815, "Cinderace", Type.FIRE, Type.FIGHTING, 90, StatusEffect.NONE,
                                80,
                                116,
                                75, 65, 75, 119, JP_CinderaceMoves);
                Pokemon JP_Charizard = new Pokemon(6, "Charizard", Type.FIRE, Type.FLYING, 90, StatusEffect.NONE, 78,
                                84,
                                78,
                                109, 85, 100, JP_CharizardMoves);
                Pokemon JP_Typhlosion = new Pokemon(157, "Typhlosion", Type.FIRE, 90, StatusEffect.NONE, 78, 84,
                                78,
                                109, 85, 100, JP_TyphlosionMoves);
                Pokemon JP_Talonflame = new Pokemon(663, "Talonflame", Type.FIRE, Type.FLYING, 90, StatusEffect.NONE,
                                78,
                                81,
                                71, 74, 69, 126, JP_TalonflameMoves);
                Pokemon JP_Rayquaza = new Pokemon(384, "Rayquaza", Type.DRAGON, Type.FLYING, 100, StatusEffect.NONE,
                                105,
                                150,
                                90, 150, 90, 95, JP_RayquazaMoves);

                Pokemon[] JPsTeam = { JP_Infernape, JP_Cinderace, JP_Charizard, JP_Typhlosion, JP_Talonflame,
                                JP_Rayquaza };

                Trainer JP = new Trainer(3, "JP", "Firefighter", JPsTeam);

                Green.printTrainerInfo(true);
                Red.printTrainerInfo(true);
                Blue.printTrainerInfo(true);

                Green.catchWildPokemon(PokeDex);
                Green.catchWildPokemon(PokeDex);
                Green.catchWildPokemon(PokeDex);

                Red.battle(Green, true);

                Green.sleep(8);
                Red.sleep(8);

                Red.battle(Blue, true);

                Red.sleep(8);
                Blue.sleep(8);

                Red.battle(JP, true);
        }
}