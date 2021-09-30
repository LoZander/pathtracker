package standard;

import framework.CharacterType;
import framework.Tracker;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) throws InterruptedException, IOException {
        switch (args[0]) {
            case "-console": consoleVariant();
            default: consoleVariant();
        }
    }

    private static void consoleVariant() {
        Tracker tracker = new TrackerImpl();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            String command = scanner.nextLine();
            //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            String[] com = command.split(" ");
            switch (com[0]) {
                case "g" -> tracker.addCharacter(com[1], CharacterType.ALLY, Integer.parseInt(com[2]));
                case "e" -> tracker.addCharacter(com[1], CharacterType.ENEMY, Integer.parseInt(com[2]));
                case "n" -> tracker.nextTurn();
                case "r" -> tracker.removeCharacter(com[1]);
                //case "d" -> tracker.setDyingCondOfCharacter(com[1],Integer.parseInt(com[2]));
                case "exit" -> run = false;
            }
            printTracker(tracker);
        }
    }

    private static void printTracker(Tracker tracker) {
        System.out.println("o>--<o>-<o>-<o>--<o");
        tracker.getCharacters().forEach(e -> {

            String lead = " - ";
            String name = e.getName();
            String initiative = e.getInitiative() + "";
            String dying = e.getDyingCondition() + "";
            String colour = ANSI_RESET;

            boolean isCharacterEnemy = e.getType() == CharacterType.ENEMY;
            if(isCharacterEnemy) {
                colour = ANSI_RED;
            }

            boolean isCharacterInTurn = e.equals(tracker.getCharacterInTurn());
            if (isCharacterInTurn) lead = ANSI_YELLOW + " * " + ANSI_RESET;

            System.out.format("%s" + colour + "%-10s%2s%2s%n" + ANSI_RESET, lead, name, initiative, dying);
        });

        System.out.println(">--------o--------<");
    }
}
