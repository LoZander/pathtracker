package standard;

import framework.CharacterType;
import framework.InputHandler;
import framework.Tracker;

import java.util.List;

public class CommandLineInputHandler implements InputHandler {
    @Override
    public void execute(Tracker tracker, String commandString) throws NumberFormatException {
        Command command = new Command(commandString);
        String commandWord = command.getCommandWord();
        List<String> variables = command.getVariables();
        switch (commandWord) {
            case "p":
                createCharacter(tracker, variables, CharacterType.ALLY);
                break;
            case "b":
                createCharacter(tracker, variables, CharacterType.ENEMY);
                break;
            case "d":
                tracker.removeCharacter(variables.get(0));
                break;
            case "r":
                tracker.nextTurn();
                break;
            default: throw new IllegalCommandException(command + " isn't a valid command");
        }
    }

    private void createCharacter(Tracker tracker, List<String> variables, CharacterType type) throws IllegalArgumentException {
        String name;
        int initiative;
        try {
            name = variables.get(0);
            initiative = Integer.parseInt(variables.get(1));
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
