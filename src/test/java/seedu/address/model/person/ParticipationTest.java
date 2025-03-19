package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ParticipationTest {

    @Test
    public void defaultConstructor_fixedValue() {
        assertEquals(ParticipationStatus.UNMARKED, new Participation().status);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Participation(null));
    }

    @Test
    public void constructor_invalidParticipationStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Participation(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null participation status
        assertThrows(NullPointerException.class, () -> new Participation(null));

        // invalid participation status
        assertFalse(Participation.isValidStatus("")); // empty string
        assertFalse(Participation.isValidStatus(" ")); // spaces only
        assertFalse(Participation.isValidStatus("status 100")); // contains non-alphabetic characters
        assertFalse(Participation.isValidStatus("fine")); // is not a valid participation status

        // valid participation status
        assertTrue(Participation.isValidStatus("excellent")); // all lowercase
        assertTrue(Participation.isValidStatus("Good")); // capitalised
        assertTrue(Participation.isValidStatus("AVERAGE")); // all uppercase
        assertTrue(Participation.isValidStatus("poOr")); // with capital letters
        assertTrue(Participation.isValidStatus("NOnE")); // with many capital letters
    }

    @Test
    public void equals() {
        Participation participation = new Participation("EXCELLENT");

        // same values -> returns true
        assertTrue(participation.equals(new Participation("EXCELLENT")));

        // same object -> returns true
        assertTrue(participation.equals(participation));

        // null -> returns false
        assertFalse(participation.equals(null));

        // different types -> returns false
        assertFalse(participation.equals(5.0f));

        // different values -> returns false
        assertFalse(participation.equals(new Participation("POOR")));
    }
}
