package standard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Command {
    private final String commandWord;
    private final List<String> variables;

    public Command(String commandString) {
        String[] words = commandString.split(" ");
        commandWord = words[0];
        variables = Arrays.stream(Arrays.copyOfRange(words, 1, words.length)).collect(Collectors.toList());
    }

    public String getCommandWord() {
        return commandWord;
    }

    public List<String> getVariables() {
        return variables;
    }

    @Override
    public String toString() {
        return commandWord + variables.stream().reduce("",(a,b) -> a + " " + b);
    }
}
