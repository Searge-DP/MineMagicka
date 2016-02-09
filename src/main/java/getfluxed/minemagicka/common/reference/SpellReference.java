package getfluxed.minemagicka.common.reference;

import getfluxed.minemagicka.MineMagicka;
import getfluxed.minemagicka.api.SpellRegistry;
import getfluxed.minemagicka.api.spells.ISpell;
import getfluxed.minemagicka.common.spells.SpellCure;
import getfluxed.minemagicka.common.spells.SpellEarthBall;
import getfluxed.minemagicka.common.spells.SpellFireBall;
import getfluxed.minemagicka.common.spells.SpellSolidify;
import getfluxed.minemagicka.common.spells.SpellTest;

public class SpellReference {

    public static ISpell fireball = new SpellFireBall();
    public static ISpell earthball = new SpellEarthBall();
    public static ISpell heal = new SpellCure();
    public static ISpell solidify = new SpellSolidify();

    public static ISpell test = new SpellTest();

    public static void preInit() {
        SpellRegistry.registerSpell(fireball);
        SpellRegistry.registerSpell(earthball);
        SpellRegistry.registerSpell(heal);
        SpellRegistry.registerSpell(solidify);

        if (MineMagicka.isDevEnv)
            SpellRegistry.registerSpell(test);
    }

}
