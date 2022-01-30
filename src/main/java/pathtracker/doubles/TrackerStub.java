package pathtracker.doubles;

import pathtracker.framework.Charact;
import pathtracker.framework.CharacterType;
import pathtracker.framework.Tracker;
import pathtracker.framework.TrackerObserver;

import java.util.LinkedList;
import java.util.List;

public class TrackerStub implements Tracker {
    List<Charact> characters;
    private TrackerObserver observer;
    private int turn;
    private int round;

    public TrackerStub() {
        characters = new LinkedList<>();
        characters.add(new CharactStub("Richard", 25, CharacterType.ALLY));
        characters.add(new CharactStub("Scarlett", 22, CharacterType.ALLY));
        characters.add(new CharactStub("Asher", 20, CharacterType.ALLY));
        characters.add(new CharactStub("Galasius", 19, CharacterType.ENEMY));
        characters.add(new CharactStub("Bad_Character", 10, CharacterType.ALLY));
        turn = 2;
        round = 4;
    }

    @Override
    public void nextTurn() {
        turn = (turn + 1) % 5;
        if(turn == 0) round++;
        observer.endOfTurn(characters.get(turn),round);
    }

    @Override
    public void addCharacter(String name, CharacterType characterType, int initiative) {

    }

    @Override
    public void removeCharacter(String name) {

    }

    @Override
    public Charact getCharacterInTurn() {
        return characters.get(turn);
    }

    @Override
    public Charact getCharacter(String name) {
        return null;
    }

    @Override
    public List<Charact> getCharacters() {
        return characters;
    }

    @Override
    public int getRound() {
        return round;
    }

    @Override
    public void setDyingCondOfCharacter(String name, int dyingDegree) {

    }

    @Override
    public void clear() {

    }

    @Override
    public void addObserver(TrackerObserver observer) {
        this.observer = observer;
    }
}
