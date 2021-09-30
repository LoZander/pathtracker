import framework.CharacterType;
import framework.Tracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import standard.TrackerImpl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestAlphaTracker {
    private Tracker tracker;

    @BeforeEach
    public void setup() {
        tracker = new TrackerImpl();
    }

    @Test
    public void shouldAddedAllyCharacterToTrackerHaveCorrectType() {
        tracker.addCharacter("Test", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacterInTurn().getType(), is(CharacterType.ALLY));
    }

    @Test
    public void shouldAddedEnemyCharacterToTrackerHaveCorrectType() {
        tracker.addCharacter("Test", CharacterType.ENEMY, 10);
        assertThat(tracker.getCharacterInTurn().getType(), is(CharacterType.ENEMY));
    }

    @Test
    public void characterHasCorrectTestName() {
        tracker.addCharacter("Test", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacterInTurn().getName(), is("Test"));
    }

    @Test
    public void characterHasCorrectTest2Name() {
        tracker.addCharacter("Test2", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacterInTurn().getName(), is("Test2"));
    }

    @Test
    public void characterWithInitiative10HasInitiative10() {
        tracker.addCharacter("Test", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacterInTurn().getInitiative(), is(10));
    }

    @Test
    public void characterWithInitiative20HasInitiative20() {
        tracker.addCharacter("Test", CharacterType.ALLY,20);
        assertThat(tracker.getCharacterInTurn().getInitiative(), is(20));
    }

    @Test
    public void shouldBeAbleToAdd2CharactersToTracker() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.addCharacter("Test2", CharacterType.ALLY, 10);
        assertThat(tracker.getCharacterInTurn().getName(), is("Test1"));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getName(), is("Test2"));
    }

    @Test
    public void shouldNextTurnChangeToNextCharacter() {
        tracker.addCharacter("Test1", CharacterType.ALLY, 20);
        tracker.addCharacter("Test2", CharacterType.ALLY, 10);
        tracker.addCharacter("Test3", CharacterType.ALLY, 5);
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
        tracker.addCharacter("Test2", CharacterType.ALLY, 20);

        assertThat(tracker.getCharacterInTurn().getInitiative(), is(20));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getInitiative(), is(15));
        tracker.nextTurn();
        assertThat(tracker.getCharacterInTurn().getInitiative(), is(10));
    }
}
