package pathtracker.doubles;

import pathtracker.doubles.CharactStub;
import pathtracker.framework.Charact;
import pathtracker.framework.CharacterType;
import pathtracker.framework.Tracker;
import pathtracker.framework.TrackerObserver;
import pathtracker.gui.wimp.WimpGui;

import java.util.LinkedList;
import java.util.List;

public class TrackerStub2 implements Tracker {
    private final List<Charact> characters;
    private int round;
    private int turn;
    private TrackerObserver observer;

    public TrackerStub2() {
        characters = new LinkedList<>();
        characters.add(new CharactStub("Feldspar",30,CharacterType.ALLY));
        characters.add(new CharactStub("Solanum",20,CharacterType.ALLY));
        characters.add(new CharactStub("Riebeck",16,CharacterType.ALLY));
        characters.add(new CharactStub("Gabbro",14,CharacterType.ALLY));
        characters.add(new CharactStub("Chert",12,CharacterType.ALLY));
        characters.add(new CharactStub("Esker",8,CharacterType.ALLY));
        characters.add(new CharactStub("Prisoner",2,CharacterType.ALLY));
        characters.add(new CharactStub("Prisoner2",2,CharacterType.ALLY));
        characters.add(new CharactStub("Prisoner3",2,CharacterType.ALLY));
        characters.add(new CharactStub("Prisoner4",2,CharacterType.ALLY));
        turn = 2;
        round = 5;
    }

    @Override
    public void nextTurn() {
        turn = (turn + 1) % 10;
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
