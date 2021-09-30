package standard;

import framework.Charact;
import framework.CharacterType;

public class CharactImpl implements Charact {
    private final String name;
    private CharacterType type;
    private int initiative;

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
        return 0;
    }
}
