import pathtracker.framework.Gui;
import pathtracker.gui.commandGui.CommandGui;
import pathtracker.standard.TrackerImpl;
import pathtracker.standard.factories.AlphaTrackerFactory;
import pathtracker.gui.wimp.WimpGui;

public class main {

    public static void main(String[] args) {
        Gui gui;
        switch (args[0]) {
            case "-wimp": gui = new WimpGui(new TrackerImpl(new AlphaTrackerFactory())); break;
            default: gui = new CommandGui(new TrackerImpl(new AlphaTrackerFactory()));
        }
        gui.run();
    }
}
