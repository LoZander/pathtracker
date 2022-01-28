package pathtracker.standard.factories;

import pathtracker.framework.SortingStrategy;
import pathtracker.standard.variants.InitiativeNameSortingStrategy;
import pathtracker.framework.TrackerFactory;

public class AlphaTrackerFactory implements TrackerFactory {
    @Override
    public SortingStrategy createSortingStrategy() {
        return new InitiativeNameSortingStrategy();
    }
}
