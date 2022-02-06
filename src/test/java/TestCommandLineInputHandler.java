import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pathtracker.framework.CharacterType;
import pathtracker.framework.InputHandler;
import pathtracker.framework.Tracker;
import pathtracker.gui.commandGui.CommandLineInputHandler;
import pathtracker.standard.TrackerImpl;
import pathtracker.standard.factories.AlphaTrackerFactory;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pathtracker.framework.Commands.*;
import static pathtracker.framework.Commands.END_TURN;

public class TestCommandLineInputHandler {
    private Tracker tracker;
    private InputHandler input;

    @BeforeEach
    public void setup() {
        tracker = new TrackerImpl(new AlphaTrackerFactory());
        input = new CommandLineInputHandler();
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
        Exception thrown = assertThrows(Exception.class,() -> input.execute(tracker, "p Test abc"));
        assertThat(thrown.getMessage(), is("Initiative must be an integer"));
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
        Exception thrown = assertThrows(Exception.class, () -> input.execute(tracker,ADD_ENEMY + " Test abc"));
        assertThat(thrown.getMessage(), is("Initiative must be an integer"));
    }

    @Test
    public void inputBTest20ShouldCreateAnEnemyCharacter() {
        input.execute(tracker, ADD_ENEMY + " Test 20");
        assertThat(tracker.getCharacter("Test").getType(), is(CharacterType.ENEMY));
    }

    @Test
    public void inputDTestShouldRemoveTheCharacterByTheName() {
        tracker.addCharacter("Test", CharacterType.ALLY, 20);
        input.execute(tracker, DELETE + " Test");
        assertThat(tracker.getCharacter("Test"), is(nullValue()));
    }

    @Test
    public void inputDTestShouldForwardExcetionIfTheCharacterDoesntExist() {
        boolean exceptionThrown = false;
        try {
            input.execute(tracker, "d Test");
        } catch (IllegalArgumentException error) {
            exceptionThrown = true;
        }
        assertThat(exceptionThrown, is(true));
    }

    @Test
    public void ifNoCharacterInTurnInputRShouldMakeItTheFirstCharactersTurn() {
        tracker.addCharacter("Test", CharacterType.ALLY, 30);
        tracker.addCharacter("TestTwo", CharacterType.ALLY, 20);
        input.execute(tracker, END_TURN);
        assertThat(tracker.getCharacterInTurn().getName(), is("Test"));
    }

    @Test
    public void ifFirstCharacterIsInTurnInputRShouldChangeTurnToNextCharacter() {
        tracker.addCharacter("Test", CharacterType.ALLY, 30);
        tracker.addCharacter("TestTwo", CharacterType.ALLY, 20);
        input.execute(tracker, END_TURN);
        input.execute(tracker, END_TURN);
        assertThat(tracker.getCharacterInTurn().getName(), is("TestTwo"));
    }

    @Test
    public void invalidCommandWordShouldCauseException() {
        Exception thrown = assertThrows(Exception.class, () -> input.execute(tracker,"FakeCommandWord"));
        assertThat(thrown.getMessage(), is("FakeCommandWord isn't a valid command"));
    }

    @Test
    public void missingCommandVariablesShouldCauseException() {
        Exception thrown = assertThrows(Exception.class, () -> input.execute(tracker, "p"));
        assertThat(thrown.getMessage(), is("Missing argument. Command must follow the syntax: [name] [initiative]"));
    }

    @Test
    public void inputWTest1ShouldSetDyingCondOfCharacterTestTo1() {
        tracker.addCharacter("Test", CharacterType.ALLY, 20);
        input.execute(tracker, DYING + " Test 1");
        assertThat(tracker.getCharacter("Test").getDyingCondition(), is(1));
    }
}
