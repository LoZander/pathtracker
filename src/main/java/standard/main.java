package standard;

import framework.Gui;
import standard.factories.AlphaTrackerFactory;

public class main {

    public static void main(String[] args) {
        switch (args[0]) {
            case "-alpha": consoleVariant();
            default: consoleVariant();
        }
    }

    private static void consoleVariant() {
        Gui gui = new CommandGui(new TrackerImpl(new AlphaTrackerFactory()));
        gui.run();
    }
}
