package getfluxed.minemagicka.reference;

import fluxedCore.buffs.Buff;
import fluxedCore.buffs.BuffHelper;
import getfluxed.minemagicka.buffs.BuffBurning;

public class BuffReference {
	public static Buff burning = new BuffBurning();

	public static void preInit() {
		BuffHelper.registerBuff("burning", burning);
	}

}
