package standard;

import framework.CharacterType;
import framework.InputHandler;
import framework.Tracker;

public class CommandLineInputHandler implements InputHandler {
    @Override
    public void execute(Tracker tracker, String command) throws NumberFormatException {
        String[] words = command.split(" ");
        String var1 = words[1];
        int var2;
        try {
            var2 = Integer.parseInt(words[2]);
        } catch (NumberFormatException error) {
            throw new IllegalArgumentException("Invalid command");
        }
        tracker.addCharacter(var1, CharacterType.ALLY, var2);
    }
}
