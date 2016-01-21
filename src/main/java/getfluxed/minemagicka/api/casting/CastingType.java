package getfluxed.minemagicka.api.casting;

public enum CastingType {
    /**
     * Anything which creates something of any kind.
     *
     * Examples: Projectiles, block making, summoning
     */
    CREATE,
    /**
     * Anything which changes the properties of
     * anything without destroying it or adding to it.
     *
     * Examples: Dyes, biome changing, buffs
     */
    CHANGE,
    /**
     * Anything which destroys something else.
     *
     * Examples: Block breaking, breaking objects down, damage spells
     */
    DESTROY,
    /**
     * Anything which prevents something from changing.
     *
     * Examples: Warding, trapping
     */
    SEAL,
    /**
     * Anything which changes the position of
     * something without changing it.
     *
     * Examples: Block placement, teleportation
     */
    MOVE,
    /**
     * Anything which uses more than one category.
     *
     * Example: Block exchanging (DESTROY, MOVE)
     */
    WEAVE;
}
