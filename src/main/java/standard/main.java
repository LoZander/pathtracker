package standard;

import framework.Gui;

public class main {

    public static void main(String[] args) {
        switch (args[0]) {
            case "-console": consoleVariant();
            default: consoleVariant();
        }
    }

    private static void consoleVariant() {
        Gui gui = new CommandGui(new TrackerImpl());
        gui.run();
    }
}
