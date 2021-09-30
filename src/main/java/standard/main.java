package standard;

import framework.CharacterType;
import framework.Tracker;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws InterruptedException, IOException {
        switch (args[0]) {
            case "-console": consoleVariant(args[1]);
        }
    }

    private static void consoleVariant(String arg) throws IOException, InterruptedException {
        Tracker tracker = new TrackerImpl();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if(arg.equals("-clear")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }

            String[] com = command.split(" ");
            switch (com[0]) {
                case "g":
                    tracker.addCharacter(com[1], CharacterType.ALLY, Integer.parseInt(com[2]));
                    break;
                case "e":
                    tracker.addCharacter(com[1], CharacterType.ENEMY, Integer.parseInt(com[2]));
                    break;
                case "n":
                    tracker.nextTurn();
                    break;
            }
            printTracker(tracker);
        }
    }

    private static void printTracker(Tracker tracker) {
        tracker.getCharacters().forEach(e -> {
            String lead = " - ";

            boolean isCharacterInTurn = e.equals(tracker.getCharacterInTurn());
            if (isCharacterInTurn) {
                lead = " * ";
            }
            System.out.format("%4s%6s%4s%4s%n",
                    lead,
                    e.getName(),
                    e.getInitiative(),
                    e.getDyingCondition());
        });
    }
}
