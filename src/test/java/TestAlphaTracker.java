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
        assertThat(tracker.getCharacter(10).getType(), is(CharacterType.ALLY));
    }

    @Test
    public void shouldAddedEnemyCharacterTOTrackerHaveCorrectType() {
        tracker.addCharacter("Test", CharacterType.ENEMY, 10);
        assertThat(tracker.getCharacter(10).getType(), is(CharacterType.ENEMY));
    }
}
