package pathtracker.doubles;

import pathtracker.framework.Charact;
import pathtracker.framework.CharacterType;

public class CharactStub implements Charact {

    private final String name;
    private final int initiative;
    private final CharacterType type;

    public CharactStub(String name, int initiative, CharacterType type) {
        this.name = name;
        this.initiative = initiative;
        this.type = type;
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
        return type;
    }

    @Override
    public int getDyingCondition() {
        return 0;
    }

    @Override
    public void setDyingCondition(int dyingDegree) {

    }
}
