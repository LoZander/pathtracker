package standard;

import framework.CharacterType;
import framework.InputHandler;
import framework.Tracker;

import java.util.Arrays;

public class CommandLineInputHandler implements InputHandler {
    @Override
    public void execute(Tracker tracker, String command) throws NumberFormatException {
        String[] words = command.split(" ",2);
        String commandWord = words[0];
        String variables;
        switch (commandWord) {
            case "p":
                variables = words[1];
                createCharacter(tracker, variables, CharacterType.ALLY);
                break;
            case "b":
                variables = words[1];
                createCharacter(tracker, variables, CharacterType.ENEMY);
                break;
            case "d":
                variables = words[1];
                tracker.removeCharacter(variables);
                break;
            case "r":
                tracker.nextTurn();
                break;
            default: throw new IllegalCommandException(commandWord + " isn't a valid command");
        }
    }

    private void createCharacter(Tracker tracker, String variables, CharacterType type) throws IllegalArgumentException {
        String[] words = variables.split(" ");
        String name;
        int initiative;
        try {
            name = words[0];
            initiative = Integer.parseInt(words[1]);
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalCommandException("Missing argument. Command must follow the syntax: [name] [initiative]");
        } catch (NumberFormatException error) {
            throw new IllegalCommandException("Initiative must be an integer");
        }

        tracker.addCharacter(name, type, initiative);
    }
}

class IllegalCommandException extends RuntimeException {
    public IllegalCommandException() {super();}
    public IllegalCommandException(String s) {super(s);}
}
