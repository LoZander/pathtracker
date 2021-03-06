import pathtracker.framework.CharacterType;
import pathtracker.framework.InputHandler;
import pathtracker.framework.Tracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pathtracker.standard.factories.AlphaTrackerFactory;
import pathtracker.gui.commandGui.CommandLineInputHandler;
import pathtracker.standard.TrackerImpl;

import static pathtracker.framework.Commands.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAlphaTracker {
    private Tracker tracker;
    private InputHandler input;

    @BeforeEach
    public void setup() {
        tracker = new TrackerImpl(new AlphaTrackerFactory());
        input = new CommandLineInputHandler();
    }

    @Test
    public void shouldAddedAllyCharacterToTrackerHaveCorrectType() {
        tracker.addCharacter("Test", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacter("Test").getType(), is(CharacterType.ALLY));
    }

    @Test
    public void shouldAddedEnemyCharacterToTrackerHaveCorrectType() {
        tracker.addCharacter("Test", CharacterType.ENEMY, 10);
        assertThat(tracker.getCharacter("Test").getType(), is(CharacterType.ENEMY));
    }

    @Test
    public void characterHasCorrectTestName() {
        tracker.addCharacter("Test", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacter("Test").getName(), is("Test"));
    }

    @Test
    public void characterHasCorrectTest2Name() {
        tracker.addCharacter("Test2", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacter("Test2").getName(), is("Test2"));
    }

    @Test
    public void characterWithInitiative10HasInitiative10() {
        tracker.addCharacter("Test", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacter("Test").getInitiative(), is(10));
    }

    @Test
    public void characterWithInitiative20HasInitiative20() {
        tracker.addCharacter("Test", CharacterType.ALLY,20);
        assertThat(tracker.getCharacter("Test").getInitiative(), is(20));
    }

    @Test
    public void shouldBeAbleToAddMultipleCharactersToTracker() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.addCharacter("Test2", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacter("Test1").getName(), is("Test1"));
        assertThat(tracker.getCharacter("Test2").getName(), is("Test2"));
    }

    @Test
    public void whenNoCharacterInTheNextTurnIsTheHighestInitiativeCharacter() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.addCharacter("Test2", CharacterType.ALLY, 30);
        assertThat(tracker.getCharacterInTurn(), is(nullValue()));

        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn(), is(tracker.getCharacter("Test2")));
    }

    @Test
    public void shouldNextTurnChangeToNextCharacter() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.addCharacter("Test2", CharacterType.ALLY, 10);
        tracker.addCharacter("Test3", CharacterType.ALLY, 5);

        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test2"));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test3"));
    }

    @Test
    public void shouldBeFirstCharacterInTrackerAfterLastCharacter() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.addCharacter("Test2", CharacterType.ALLY, 10);
        tracker.addCharacter("Test3", CharacterType.ALLY, 5);

        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));

        tracker.nextTurn();
        tracker.nextTurn();
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));
    }

    /**
     * Covers ECs [a2],[b1],[c2] of the nextTurn method
     */
    @Test
    public void shouldBeCharacter4After3WhenTurnEndsAndRoundCountShouldntChange() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 30);
        tracker.addCharacter("Test2", CharacterType.ALLY, 28);
        tracker.addCharacter("Test3", CharacterType.ALLY, 22);
        tracker.addCharacter("Test4", CharacterType.ALLY, 15);
        tracker.addCharacter("Test5", CharacterType.ALLY, 10);

        tracker.nextTurn();
        tracker.nextTurn();
        tracker.nextTurn();
        int beforeRound = tracker.getRound();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test3"));

        tracker.nextTurn();
        int afterRound = tracker.getRound();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test4"));

        assertThat(afterRound - beforeRound, is(0));
    }

    /**
     * Covers ECs [a2],[b2],[c1] of the nextTurn method
     */
    @Test
    public void shouldBeFirstCharacterAfterLastCharacterAndRoundCountShouldUpdate() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 30);
        tracker.addCharacter("Test2", CharacterType.ALLY, 28);
        tracker.addCharacter("Test3", CharacterType.ALLY, 22);

        tracker.nextTurn();
        tracker.nextTurn();
        tracker.nextTurn();

        int beforeRound = tracker.getRound();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test3"));

        tracker.nextTurn();
        int afterRound = tracker.getRound();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));

        assertThat(afterRound - beforeRound, is(1));
    }

    /**
     * Covers ECs [a2],[b3],[c3] of the nextTurn method
     */
    @Test
    public void shouldBeFirstCharacterAfterNoCharacterAndRoundCountShouldBe1() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 30);
        tracker.addCharacter("Test2", CharacterType.ALLY, 28);
        tracker.addCharacter("Test3", CharacterType.ALLY, 22);

        assertThat(tracker.getCharacterInTurn(), is(nullValue()));

        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));
        assertThat(tracker.getRound(), is(1));
    }

    /**
     * Covers ECs [a1] of the nextTurn method
     */
    @Test
    public void nothingShouldHappenWhenTurnEndsWithNoCharacters() {
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn(), is(nullValue()));
    }

    @Test
    public void charactersAreSortedByInitiative() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 10);
        tracker.addCharacter("Test2", CharacterType.ALLY, 15);
        tracker.addCharacter("Test3", CharacterType.ALLY, 20);

        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getInitiative(), is(20));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getInitiative(), is(15));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getInitiative(), is(10));
    }

    @Test
    public void charactersAreSortedByTypeSecondly() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 10);
        tracker.addCharacter("Test2", CharacterType.ENEMY, 15);
        tracker.addCharacter("Test3", CharacterType.ALLY, 15);

        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test3"));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test2"));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));
    }

    @Test
    public void charactersAreSortedByNameThirdlyAtoZ() {
        tracker.addCharacter("D", CharacterType.ENEMY, 15);     // fourth
        tracker.addCharacter("C", CharacterType.ENEMY, 15);     // third
        tracker.addCharacter("B", CharacterType.ALLY, 15);      // second
        tracker.addCharacter("A", CharacterType.ALLY, 20);      // first

        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("A"));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("B"));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("C"));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("D"));
    }

    @Test
    public void charactersCanBeRemoved() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.removeCharacter("Test1");
        assertThat(tracker.getCharacter("Test1"), is(nullValue()));
    }

    @Test
    public void removingACharacterShouldNotRemoveAnythingElse() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.addCharacter("Test2", CharacterType.ALLY, 20);
        tracker.removeCharacter("Test1");
        assertThat(tracker.getCharacter("Test2").getName(), is("Test2"));
    }


    @Test
    public void canGetCharacterByName() {
        tracker.addCharacter("Test", CharacterType.ALLY, 20);
        assertThat(tracker.getCharacter("Test").getInitiative(), is(20));
    }

    @Test
    public void getCharacterByNameReturnsNullIfNoCharacterByTheName() {
        assertThat(tracker.getCharacter("Test"), is(nullValue()));
    }

    @Test
    public void getPlayerInTurnGivesNullIfThereAreNoPlayers() {
        assertThat(tracker.getCharacterInTurn(), is(nullValue()));
    }

    @Test
    public void shouldBeNextPlayerIfPlayerInTurnIsRemoved() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.addCharacter("Test2", CharacterType.ALLY, 15);

        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));
        tracker.removeCharacter("Test1");
        assertThat(tracker.getCharacterInTurn().getName(), is("Test2"));
    }

    @Test
    public void shouldNotChangePlayerInTurnWhenAddingAPlayerWithHigherInitiative() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.addCharacter("Test2", CharacterType.ALLY, 15);

        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));
        tracker.addCharacter("Test3", CharacterType.ALLY, 25);
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));
    }



    @Test
    public void clearingTrackerShouldRemoveAllCharacters() {
        tracker.addCharacter("Test", CharacterType.ALLY, 20);
        tracker.clear();
        assertThat(tracker.getCharacters().size(), is(0));
    }

    @Test
    public void inputClearShouldRemoveAllCharacters() {
        tracker.addCharacter("Test", CharacterType.ALLY, 20);
        tracker.addCharacter("TestTwo", CharacterType.ALLY, 18);
        input.execute(tracker, CLEAR);
        assertThat(tracker.getCharacters().size(), is(0));
    }

    @Test
    public void shouldBeAbleToSetDyingConditionOfCharacter() {
        tracker.addCharacter("Test", CharacterType.ALLY, 20);
        tracker.setDyingCondOfCharacter("Test", 1);
        assertThat(tracker.getCharacter("Test").getDyingCondition(), is(1));
    }

    @Test
    public void characterDyingConditionShouldStartAtZero() {
        tracker.addCharacter("Test", CharacterType.ALLY, 20);
        assertThat(tracker.getCharacter("Test").getDyingCondition(), is(0));
    }

    @Test
    public void settingDyingConditionOfNonexistingCharacterShouldDoNothing() {
        tracker.setDyingCondOfCharacter("Test", 1);
    }

    @Test
    public void addingAlreadyExistingCharacterShouldThrowException() {
        tracker.addCharacter("Test", CharacterType.ALLY, 20);
        String exceptionMessage = "";

        try {
            tracker.addCharacter("Test", CharacterType.ALLY, 2);
        } catch (IllegalArgumentException error) {
            exceptionMessage = error.getMessage();
        }

        assertThat(exceptionMessage, is("Character by the name Test already exists"));
    }

    @Test
    public void removingACharacterThatDoesntExistShouldThrowException() {
        String exceptionMessage = "";
        try {
            tracker.removeCharacter("RidiculousName");
        } catch (IllegalArgumentException error) {
            exceptionMessage = error.getMessage();
        }
        assertThat(exceptionMessage, is("There is no character by the name RidiculousName and thus it cannot be removed"));
    }

    @Test
    public void addingACharacterWithNoNameShouldThrowAnException() {
        String exceptionMessage = "";
        try {
            tracker.addCharacter("", CharacterType.ALLY, 20);
        } catch (IllegalArgumentException error) {
            exceptionMessage = error.getMessage();
        }

        assertThat(exceptionMessage, is("A character with no name cannot be added to the tracker"));
    }
}
