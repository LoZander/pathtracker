package standard.gui.commandGui;

import framework.*;

import java.util.Arrays;
import java.util.List;

import static framework.CharacterType.*;
import static framework.Commands.*;

public class CommandLineInputHandler implements InputHandler {
    @Override
    public void execute(Tracker tracker, String commandString) throws NumberFormatException {
        String command = (commandString.split(" "))[0];
        List<String> variables = getCommandVariables(commandString);
        switch (command) {
            case ADD_ALLY:
                createCharacter(tracker, variables, ALLY);
                break;
            case ADD_ENEMY:
                createCharacter(tracker, variables, ENEMY);
                break;
            case DELETE:
                tracker.removeCharacter(variables.get(0));
                break;
            case END_TURN:
                tracker.nextTurn();
                break;
            case CLEAR:
                tracker.clear();
                break;
            case DYING:
                setDyingCondition(tracker, variables);
                break;
            default: throw new IllegalCommandException(commandString + " isn't a valid command");
        }
    }

    private void setDyingCondition(Tracker tracker, List<String> variables) {
        String name;
        int dying;
        try {
            name = variables.get(0);
            dying = Integer.parseInt(variables.get(1));
        } catch (IndexOutOfBoundsException error) {
            throw new IllegalCommandException("Missing argument. Command should follow syntax: [name] [dying condition]");
        } catch (NumberFormatException error) {
            throw new IllegalCommandException("Dying condition must be an integer");
        }

        tracker.setDyingCondOfCharacter(name, dying);
    }

    private List<String> getCommandVariables(String commandString) {
        List<String> commandWords = Arrays.asList(commandString.split(" "));
        List<String> variables = commandWords.subList(1,commandWords.size());
        return variables;
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
