package pathtracker.framework;

import java.util.List;

/**
 * The tracker is the main interface of the pathtracker. It acts as a coordinator or a hub if
 * you will. Most associations should be through the tracker.
 */
public interface Tracker {
    /**
     * Advances the tacker to the next character in line. If the tracker is empty, nothing happens.
     */
    void nextTurn();

    /**
     * Adds a new character to the tracker. The name doesn't have to be unique,
     * but the initiative does. The type can be one of two:
     *      Ally
     *      Enemy
     * @param name Name of the character.
     * @param type Type of the character.
     * @param initiative Initiative of the character.
     * @exception IllegalArgumentException Thrown if the arguments are invalid:
     *      Invalid name: empty string or duplicate name
     */
    void addCharacter(String name, CharacterType type, int initiative);

    /**
     * Removes a character from the tracker.
     * @param name Name of the character to be selected.
     * @exception IllegalArgumentException Thrown if there is no character by the name.
     */
    void removeCharacter(String name);

    /**
     * Returns the character whose turn it is.
     * @return The character whose turn it is.
     */
    Charact getCharacterInTurn();

    /**
     * Return character of a specific name.
     * @param name Name of the character.
     * @return Character of the name, or null if the tracker has no character by the name.
     */
    Charact getCharacter(String name);

    /**
     * Returns a list of characters in the tracker.
     * @return The list of characters.
     */
    List<Charact> getCharacters();

    /**
     * Returns the round count.
     * @return Round count.
     */
    int getRound();

    /**
     * Sets the dying condition of the character. This can normally be a value of 0-3.
     * This value is increased if:
     *      The character goes to 0 HP
     *      When the character fails a dying saving throw
     *      Increases by 2 if the dying saving throw is a critical failure
     * If a characters dying condition is beyond 3, they are dead.
     * The exception is a character with the Die Hard feat, which die when their
     * dying condition is beyond 4.
     *
     * The dying conditions don't reset when the characters get up, but become
     * "inactive" in the form of wounded conditions, the current wounded conditions are
     * are stacked on top of the new dying condition when the character goes down to 0 HP.
     * @param name Name of character.
     * @param dyingDegree The degree of the dying condition.
     */
    void setDyingCondOfCharacter(String name, int dyingDegree);

    /**
     * Removes all characters from the tracker and resets round count.
     */
    void clear();

    /**
     * Adds a tracker observer to the tracker, for purpose of observer pattern.
     * @param observer The tracker observer to be added.
     */
    void addObserver(TrackerObserver observer);
}
