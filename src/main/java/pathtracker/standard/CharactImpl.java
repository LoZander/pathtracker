package pathtracker.standard;

import pathtracker.framework.Charact;
import pathtracker.framework.CharacterType;

public class CharactImpl implements Charact {
    private final String name;
    private final CharacterType type;
    private final int initiative;
    private int dying;

    public CharactImpl(String name, CharacterType type, int initiative) {
        this.name = name;
        this.type = type;
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
        return type;
    }

    @Override
    public int getDyingCondition() {
        return dying;
    }

    @Override
    public void setDyingCondition(int dyingDegree) {
        dying = dyingDegree;
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (!(other instanceof CharactImpl)) return false;
        CharactImpl o = (CharactImpl) other;
        return this.getName().equals(o.getName());
    }
}
