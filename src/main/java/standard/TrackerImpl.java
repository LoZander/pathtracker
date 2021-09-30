package standard;

import framework.Charact;
import framework.CharacterType;
import framework.Tracker;

public class TrackerImpl implements Tracker {
    private Charact character;

    @Override
    public void nextTurn() {

    }

    @Override
    public void addCharacter(String name, CharacterType characterType, double initiative) {
        character = new CharactImpl(characterType);
    }

    @Override
    public void removeCharacter(double initiative) {

    }

    @Override
    public Charact getCharacterInTurn() {
        return character;
    }

    @Override
    public void setDyingCondOfCharacter(double initiative) {

    }

    @Override
    public int getRound() {
        return 0;
    }
}
