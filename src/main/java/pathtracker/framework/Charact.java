package pathtracker.framework;

/**
 * The characters model either player/NPC (ally) and enemies.
 * The characters are IDed by their initiative, which are unique on the tracker.
 * In case two characters have the same initiative, one or both are to be adjusted.
 *
 * A characters dying condition is normally 0-3 and if it goes above that threshold, the
 * character is dead. Characters with the Die Hard feat can go up to a dying condition of 4
 * without being dead.
 *
 * A character is responsible for knowing it's own values and changing them, but not knowing when to change
 * or to what.
 *
 * A character is unique by it's name.
 */
public interface Charact {

    /**
     * Returns the initiative of the character.
     * @return The initiative of the character.
     */
    int getInitiative();

    /**
     * Returns the name of the character.
     * @return The name of the character.
     */
    String getName();

    /**
     * Returns the type of the character.
     * @return The type of the character.
     */
    CharacterType getType();

    /**
     * Returns the dying condition of the character.
     * @return The dying condition of the character.
     */
    int getDyingCondition();

    void setDyingCondition(int dyingDegree);
}
