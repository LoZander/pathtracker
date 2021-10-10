package standard;

import framework.CharacterType;
import framework.Gui;
import framework.InputHandler;
import framework.Tracker;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class CommandGui implements Gui {

    private final Tracker tracker;
    private final Scanner scanner;
    private final InputHandler input;

    public CommandGui(Tracker tracker) {
        this.tracker = tracker;
        scanner = new Scanner(System.in);
        input = new CommandLineInputHandler();
    }

    public void run() {
        printIntroduction();
        String command;
        boolean run = true;
        while(run) {
            System.out.print("> ");
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

    private void printIntroduction() {
        String[] introduction = new String[]{
                "Welcome to Pathtracker (Alpha)",
                "The available commands are:",
                " - Create a player/ally: p [Name] [Initiative]",
                " - Create an enemy: p [Name] [Initiative]",
                " - Remove a character: d [Name]",
                " - End turn: r"
        };
        Arrays.stream(introduction).forEach(System.out::println);
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
