package pathtracker.framework;

public interface TrackerObserver {
    void endOfTurn(Charact nextCharacter, int round);
    void characterListChanged();
    void characterChanged(Charact character);
    void clear();
}
