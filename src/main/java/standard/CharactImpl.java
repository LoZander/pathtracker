package standard;

import framework.Charact;
import framework.CharacterType;

public class CharactImpl implements Charact {
    private final String name;
    private CharacterType type;

    public CharactImpl(String name, CharacterType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public double getInitiative() {
        return 0;
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
