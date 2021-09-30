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
}
