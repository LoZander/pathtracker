import framework.*;
import standard.NullObserver;
import standard.TrackerImpl;
import standard.factories.AlphaTrackerFactory;
import standard.gui.wimp.WimpGui;

import java.util.LinkedList;
import java.util.List;

public class ShowLayout {
    public static void main(String[] args) {
        Gui gui = new WimpGui(new TrackerImpl(new AlphaTrackerFactory()));
        gui.run();
    }
}

class TrackerStump implements Tracker {

    private TrackerObserver observer;
    private int roundCount;
    private List<Charact> characters;

    public TrackerStump() {
        this.observer = new NullObserver();
        characters = new LinkedList<>();
    }

    @Override
    public void nextTurn() {
        roundCount++;
        observer.endOfTurn(null, roundCount);
    }

    @Override
    public void addCharacter(String name, CharacterType characterType, int initiative) {
        Charact character = new CharactStump(name, initiative);
        characters.add(character);
        observer.characterListChanged();
    }

    @Override
    public void removeCharacter(String name) {
        characters.removeIf(e -> e.getName().equals(name));
        observer.characterListChanged();
    }

    @Override
    public Charact getCharacterInTurn() {
        return null;
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

class CharactStump implements Charact {

    private final int initiative;
    private final String name;

    public CharactStump(String name, int initiative) {
        this.name = name;
        this.initiative = initiative;
    }

    @Override
    public int getInitiative() {
        return initiative;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CharacterType getType() {
        return null;
    }

    @Override
    public int getDyingCondition() {
        return 0;
    }

    @Override
    public void setDyingCondition(int dyingDegree) {

    }
}
