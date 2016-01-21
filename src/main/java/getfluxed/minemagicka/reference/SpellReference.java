package getfluxed.minemagicka.reference;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.spells.SpellCure;
import getfluxed.minemagicka.spells.SpellEarthBall;
import getfluxed.minemagicka.spells.SpellFireBall;
import getfluxed.minemagicka.spells.SpellTest;

public class SpellReference {

    public static ISpell fireball = new SpellFireBall();
    public static ISpell earthball = new SpellEarthBall();
    public static ISpell heal = new SpellCure();


    public static ISpell test = new SpellTest();

    public static void preInit() {
        SpellRegistry.registerSpell(fireball);
        SpellRegistry.registerSpell(earthball);
        SpellRegistry.registerSpell(heal);

        if (MineMagicka.isDevEnv)
            SpellRegistry.registerSpell(test);
    }

}
