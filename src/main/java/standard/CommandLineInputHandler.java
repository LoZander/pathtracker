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
                createCharacter(tracker, words, CharacterType.ALLY);
                break;
            case "b":
                createCharacter(tracker, words, CharacterType.ENEMY);
                break;
            case "d":
                tracker.removeCharacter(words[1]);
                break;
            case "r":
                tracker.nextTurn();
                break;
            default: throw new IllegalCommandException(command + " isn't a valid command");
        }
    }

    private void createCharacter(Tracker tracker, String[] variables, CharacterType type) throws IllegalArgumentException {
        String name;
        int initiative;
        try {
            name = variables[1];
            initiative = Integer.parseInt(variables[2]);
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
