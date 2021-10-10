package standard;

import framework.*;

import java.util.LinkedList;
import java.util.List;

public class TrackerImpl implements Tracker {
    private final List<Charact> characterList = new LinkedList<>();
    private Charact characterInTurn;
    private SortingStrategy sortingStrategy;

    public TrackerImpl(TrackerFactory trackerFactory) {
        sortingStrategy = trackerFactory.createSortingStrategy();
    }

    @Override
    public void nextTurn() {
        if(characterInTurn == null) {
            characterInTurn = characterList.get(0);
        } else {
            characterInTurn = characterList.get(getNextIndex());
        }
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
    public void setDyingCondOfCharacter(String name, int dyingDegree) {

    }

    @Override
    public int getRound() {
        return 0;
    }
}
