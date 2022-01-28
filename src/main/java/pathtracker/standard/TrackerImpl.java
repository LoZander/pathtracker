package pathtracker.standard;

import pathtracker.framework.*;

import java.util.LinkedList;
import java.util.List;

public class TrackerImpl implements Tracker {
    private final List<Charact> characterList = new LinkedList<>();
    private Charact characterInTurn;
    private SortingStrategy sortingStrategy;
    private TrackerObserver observer;
    private int round;

    public TrackerImpl(TrackerFactory trackerFactory) {
        sortingStrategy = trackerFactory.createSortingStrategy();
        observer = new NullObserver();
        round = 1;
    }

    @Override
    public void nextTurn() {
        boolean isTrackerEmpty = characterList.isEmpty();
        if(isTrackerEmpty) return;

        if(characterInTurn == null) {
            characterInTurn = characterList.get(0);
        } else {
            int nextIndex = getNextIndex();
            characterInTurn = characterList.get(nextIndex);
            if(nextIndex == 0) round++;
        }

        observer.endOfTurn(characterInTurn, round);
    }

    private int getNextIndex() {
        int index = characterList.indexOf(characterInTurn);
        return (index + 1) % characterList.size();
    }

    @Override
    public void addCharacter(String name, CharacterType characterType, int initiative) {
        boolean characterAlreadyExists = getCharacter(name) != null;
        if(characterAlreadyExists) return;

        characterList.add(new CharactImpl(name, characterType, initiative));
        sort();

        observer.characterListChanged();
    }

    private void sort() {
        sortingStrategy.sort(characterList);
    }

    @Override
    public void removeCharacter(String name) {
        Charact character = getCharacter(name);

        boolean characterExists = character != null;
        if(!characterExists) return;

        boolean isCharacterInTurn = character.equals(characterInTurn);
        if(isCharacterInTurn) nextTurn();

        characterList.remove(character);

        observer.characterListChanged();
    }

    @Override
    public Charact getCharacterInTurn() {
        return characterInTurn;
    }

    @Override
    public Charact getCharacter(String name) {
        return characterList.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public List<Charact> getCharacters() {
        return new LinkedList<>(characterList);
    }

    @Override
    public int getRound() {
        return round;
    }

    @Override
    public void setDyingCondOfCharacter(String name, int dyingDegree) {
        boolean characterExists = getCharacter(name) != null;
        if(!characterExists) return;

        Charact character = getCharacter(name);
        character.setDyingCondition(dyingDegree);

        observer.characterChanged(character);
    }

    @Override
    public void clear() {
        characterList.clear();
        observer.characterListChanged();
        round = 0;
        observer.clear();
    }

    @Override
    public void addObserver(TrackerObserver observer) {
        this.observer = observer;
    }
}
