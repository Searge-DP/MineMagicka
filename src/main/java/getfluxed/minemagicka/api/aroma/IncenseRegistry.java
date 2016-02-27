package getfluxed.minemagicka.api.aroma;

import fluxedCore.buffs.Buff;

import java.util.HashMap;
import java.util.Map;

public class IncenseRegistry {

    public static Map<String, Buff> incense = new HashMap<>();

    public static Map<String, Buff> getIncense() {
        return incense;
    }

    public void registerInsence(String name, Buff buff) {
        incense.put(name, buff);
    }

}
