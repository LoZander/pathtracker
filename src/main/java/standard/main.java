package standard;

import framework.CharacterType;
import framework.Gui;
import framework.InputHandler;
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
