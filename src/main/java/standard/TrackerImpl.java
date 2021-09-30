package standard;

import framework.Charact;
import framework.CharacterType;
import framework.Tracker;

import java.util.LinkedList;
import java.util.List;

public class TrackerImpl implements Tracker {
    private final List<Charact> characterList = new LinkedList<>();
    private int turn;

    @Override
    public void nextTurn() {
        turn++;
    }

    @Override
    public void addCharacter(String name, CharacterType characterType, int initiative) {
        characterList.add(new CharactImpl(name, characterType, initiative));
    }

    @Override
    public void removeCharacter(double initiative) {

    }

    @Override
    public Charact getCharacterInTurn() {
        return characterList.get(turn);
    }

    @Override
    public void setDyingCondOfCharacter(double initiative) {

    }

    @Override
    public int getRound() {
        return 0;
    }
}
