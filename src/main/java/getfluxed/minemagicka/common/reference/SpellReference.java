package getfluxed.minemagicka.common.reference;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.spells.*;

public class SpellReference {

    public static ISpell fireball = new SpellFireBall();
    public static ISpell earthball = new SpellEarthBall();
    public static ISpell heal = new SpellCure();
    public static ISpell solidify = new SpellSolidify();
    public static ISpell waterball = new SpellHydrate();
    public static ISpell stoptime = new SpellStopTime();
    public static ISpell haste = new SpellHaste();
<<<<<<< HEAD
    public static ISpell dig = new SpellDig();
=======
>>>>>>> 94c94518da4b25aa03c784bbdcf96019b3b9636c

    public static ISpell test = new SpellTest();

    public static void preInit() {
        SpellRegistry.registerSpell(fireball);
        SpellRegistry.registerSpell(earthball);
        SpellRegistry.registerSpell(heal);
        SpellRegistry.registerSpell(solidify);
        SpellRegistry.registerSpell(waterball);
        SpellRegistry.registerSpell(stoptime);
        SpellRegistry.registerSpell(haste);
<<<<<<< HEAD
        SpellRegistry.registerSpell(dig);
=======
>>>>>>> 94c94518da4b25aa03c784bbdcf96019b3b9636c

        if (MineMagicka.isDevEnv)
            SpellRegistry.registerSpell(test);
    }

}
