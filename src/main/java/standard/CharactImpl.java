package standard;

import framework.Charact;
import framework.CharacterType;

public class CharactImpl implements Charact {
    private CharacterType type;

    public CharactImpl(CharacterType type) {
        this.type = type;
    }

    @Override
    public double getInitiative() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
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
