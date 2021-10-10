import framework.Gui;
import standard.CommandGui;
import standard.TrackerImpl;
import standard.factories.AlphaTrackerFactory;

public class main {

    public static void main(String[] args) {
        Gui gui;
        switch (args[0]) {
            case "-alpha": gui = new CommandGui(new TrackerImpl(new AlphaTrackerFactory()));
            default: gui = new CommandGui(new TrackerImpl(new AlphaTrackerFactory()));
        }
        gui.run();
    }
}
