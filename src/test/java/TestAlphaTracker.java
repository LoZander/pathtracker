import framework.CharacterType;
import framework.InputHandler;
import framework.Tracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import standard.CommandLineInputHandler;
import standard.TrackerImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestAlphaTracker {
    private Tracker tracker;
    private InputHandler input;

    @BeforeEach
    public void setup() {
        tracker = new TrackerImpl();
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
    public void shouldBeAbleToAdd2CharactersToTracker() {
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
    public void shouldPTest23CreateCharacterWIthNameTest() {
        input.execute(tracker, "p Test 23");
        assertThat(tracker.getCharacter("Test").getName(), is("Test"));
    }
    @Test
    public void shouldPTest23CreateCharacterWithInitiative23() {
        input.execute(tracker,"p Test 23");
        assertThat(tracker.getCharacter("Test").getInitiative(), is(23));
    }

    @Test
    public void shouldPTestTwo20CreateCharacterWithNameTestTwo() {
        input.execute(tracker,"p TestTwo 20");
        assertThat(tracker.getCharacter("TestTwo").getName(), is("TestTwo"));
    }

    @Test
    public void shouldPTestTwo20CreateCharacterWithInitiative20() {
        input.execute(tracker,"p TestTwo 20");
        assertThat(tracker.getCharacter("TestTwo").getInitiative(), is(20));
    }

    @Test
    public void inputPTestabcShouldThrowException() {
        Exception thrown = assertThrows(IllegalArgumentException.class,() -> input.execute(tracker, "p Test abc"));
        assertThat(thrown.getMessage(), is("Invalid command"));
    }

    @Test
    public void inputPTest20ShouldCreatePlayerCharacter() {
        input.execute(tracker, "p Test 20");
        assertThat(tracker.getCharacter("Test").getType(), is(CharacterType.ALLY));
    }

    @Test
    public void inputBTest20ShouldCreateACharacterWithNameTest() {
        input.execute(tracker, "b Test 20");
        assertThat(tracker.getCharacter("Test").getName(), is("Test"));
    }

    @Test
    public void inputBTest20ShouldCreateACharacterWithInitiative20() {
        input.execute(tracker, "b Test 20");
        assertThat(tracker.getCharacter("Test").getInitiative(), is(20));
    }

    @Test
    public void inputBTestabcShouldThrowAnException() {
        Exception thrown = assertThrows(IllegalArgumentException.class, () -> input.execute(tracker,"b Test abc"));
        assertThat(thrown.getMessage(), is("Invalid command"));
    }

    @Test
    public void inputBTest20ShouldCreateAnEnemyCharacter() {
        input.execute(tracker, "b Test 20");
        assertThat(tracker.getCharacter("Test").getType(), is(CharacterType.ENEMY));
    }

    @Test
    public void inputDTestShouldRemoveTheCharacterByTheName() {
        tracker.addCharacter("Test", CharacterType.ALLY, 20);
        input.execute(tracker, "d Test");
        assertThat(tracker.getCharacter("Test"), is(nullValue()));
    }
}
