import framework.Gui;
import javafx.scene.media.Track;
import standard.gui.commandGui.CommandGui;
import standard.TrackerImpl;
import standard.factories.AlphaTrackerFactory;
import standard.gui.wimp.WimpGui;

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
