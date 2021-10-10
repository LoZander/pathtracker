package standard;

import framework.CharacterType;
import framework.InputHandler;
import framework.Tracker;

public class CommandLineInputHandler implements InputHandler {
    @Override
    public void execute(Tracker tracker, String command) throws NumberFormatException {
        String[] words = command.split(" ");
        String commandWord = words[0];
        String var1 = words[1];
        int var2;
        CharacterType type;
        try {
            var2 = Integer.parseInt(words[2]);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("Invalid command");
        }
        type = switch (commandWord) {
            case "p" -> CharacterType.ALLY;
            case "b" -> CharacterType.ENEMY;
            default -> null;
        };
        tracker.addCharacter(var1, type, var2);
    }
}
