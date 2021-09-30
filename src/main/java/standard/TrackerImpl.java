package standard;

import framework.Charact;
import framework.CharacterType;
import framework.Tracker;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TrackerImpl implements Tracker {
    private final List<Charact> characterList = new LinkedList<>();
    private int turn;

    @Override
    public void nextTurn() {
        boolean isLastCharacter = turn >= characterList.size() - 1;
        if(isLastCharacter) turn = 0; else turn++;
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
        characterList.removeIf(e -> e.getName().equals(name));
    }

    @Override
    public Charact getCharacterInTurn() {
        try {
            return characterList.get(turn);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
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
