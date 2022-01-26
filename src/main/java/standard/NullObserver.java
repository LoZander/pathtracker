package standard;

import framework.Charact;
import framework.TrackerObserver;

public class NullObserver implements TrackerObserver {
    @Override
    public void endOfTurn(Charact nextCharacter, int round) {}

    @Override
    public void characterListChanged() {}
    @Override
    public void characterChanged(Charact character) {}
}
