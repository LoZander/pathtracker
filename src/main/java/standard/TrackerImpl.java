package standard;

import framework.Charact;
import framework.CharacterType;
import framework.Tracker;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TrackerImpl implements Tracker {
    private final List<Charact> characterList = new LinkedList<>();
    private Charact characterInTurn;

    @Override
    public void nextTurn() {
        if(characterInTurn == null) {
            characterInTurn = characterList.get(0);
        } else {
            int index = characterList.indexOf(characterInTurn);
            int nextIndex = (index + 1) % characterList.size();
            characterInTurn = characterList.get(nextIndex);
        }
    }

    @Override
    public void addCharacter(String name, CharacterType characterType, int initiative) {
        characterList.add(new CharactImpl(name, characterType, initiative));
        sort();
    }

    private void sort() {
        characterList.sort(Comparator.comparing(Charact::getInitiative, (a,b) -> b - a)
                .thenComparing(Charact::getType)
                .thenComparing(Charact::getName));
    }

    @Override
    public void removeCharacter(String name) {
        Charact character = getCharacter(name);
        if(character.equals(characterInTurn)) nextTurn();
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
