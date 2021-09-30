package framework;

public interface Tracker {
    void nextTurn();
    void addCharacter(String name, String characterType, double initiative);
    void removeCharacter(double initiative);
    Charact getCharacter(double initiative);
    void setDyingCondOfCharacter(double initiative);
    int getRound();
}
