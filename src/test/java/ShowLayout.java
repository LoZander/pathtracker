import framework.Charact;
import framework.CharacterType;
import framework.Tracker;
import standard.gui.wimp.WimpGui;

import java.util.LinkedList;
import java.util.List;

public class ShowLayout {
    public static void main(String[] args) {
        new WimpGui(new TrackerStump());
    }
}

class TrackerStump implements Tracker {

    @Override
    public void nextTurn() {

    }

    @Override
    public void addCharacter(String name, CharacterType characterType, int initiative) {

    }

    @Override
    public void removeCharacter(String name) {

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
        List<Charact> list = new LinkedList<>();
        list.add(new CharactStump("Test1", 22));
        list.add(new CharactStump("Test2", 18));
        list.add(new CharactStump("Test3", 12));
        list.add(new CharactStump("Test4", 9));
        return list;
    }

    @Override
    public void setDyingCondOfCharacter(String name, int dyingDegree) {

    }

    @Override
    public void clear() {

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
        return 0;
    }

    @Override
    public String getName() {
        return null;
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
