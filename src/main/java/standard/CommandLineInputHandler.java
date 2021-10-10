package standard;

import framework.CharacterType;
import framework.InputHandler;
import framework.Tracker;

public class CommandLineInputHandler implements InputHandler {
    @Override
    public void execute(Tracker tracker, String command) throws NumberFormatException {
        String[] words = command.split(" ");
        String commandWord = words[0];
        switch (commandWord) {
            case "p":
                createCharacter(tracker, words[1], CharacterType.ALLY, words[2]);
                break;
            case "b":
                createCharacter(tracker, words[1], CharacterType.ENEMY, words[2]);
                break;
            case "d":
                String var1 = words[1];
                tracker.removeCharacter(var1);
                break;
        }
    }

    private void createCharacter(Tracker tracker, String name, CharacterType type, String initiative) throws IllegalArgumentException {
        int init;
        try {
            init = Integer.parseInt(initiative);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("Invalid command");
        }
        tracker.addCharacter(name, type, init);
    }
}
