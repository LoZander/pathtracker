package pathtracker.standard.variants;

import pathtracker.framework.Charact;
import pathtracker.framework.SortingStrategy;

import java.util.Comparator;
import java.util.List;

public class InitiativeNameSortingStrategy implements SortingStrategy {
    @Override
    public void sort(List<Charact> characterList) {
        characterList.sort(Comparator
                .comparing(Charact::getInitiative, (a,b) -> b - a)
                .thenComparing(Charact::getType)
                .thenComparing(Charact::getName));
    }
}
