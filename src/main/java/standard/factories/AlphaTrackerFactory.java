package standard.factories;

import framework.SortingStrategy;
import standard.variants.InitiativeNameSortingStrategy;
import framework.TrackerFactory;

public class AlphaTrackerFactory implements TrackerFactory {
    @Override
    public SortingStrategy createSortingStrategy() {
        return new InitiativeNameSortingStrategy();
    }
}
