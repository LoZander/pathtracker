package standard;

import framework.CharacterType;
import framework.Gui;
import framework.InputHandler;
import framework.Tracker;

import java.io.IOException;
import java.util.Scanner;

public class CommandGui implements Gui {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private final Tracker tracker;
    private final Scanner scanner;
    private final InputHandler input;

    public CommandGui(Tracker tracker) {
        this.tracker = tracker;
        scanner = new Scanner(System.in);
        input = new CommandLineInputHandler();
    }

    public void run() {
        String command;
        boolean run = true;
        while(run) {
            command = scanner.nextLine();
            clear();
            try {
                input.execute(tracker, command);
            } catch (IllegalCommandException error) {
                System.out.println(error.getMessage());
            }
            printTracker(tracker);
        }
    }

    private void printTracker(Tracker tracker) {
        System.out.println("o>---<o>-<o>-<o>---<o");
        tracker.getCharacters().forEach(e -> {

            String lead = " - ";
            String type = "p";
            String name = e.getName();
            String initiative = e.getInitiative() + "";
            String dying = e.getDyingCondition() + "";


            boolean isCharacterEnemy = e.getType() == CharacterType.ENEMY;
            if(isCharacterEnemy) {
                type = "b";
            }

            boolean isCharacterInTurn = e.equals(tracker.getCharacterInTurn());
            if (isCharacterInTurn) lead = " * ";

            System.out.format("%-2s%-2s%-10s%2s%2s%n", lead, type, name, initiative, dying);
        });

        System.out.println(">---------o---------<");
    }

    private void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException ignored) {}
    }
}
