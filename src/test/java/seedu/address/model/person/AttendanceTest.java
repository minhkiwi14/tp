package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void defaultConstructor_fixedValue() {
        assertEquals(AttendanceStatus.UNMARKED, new Attendance().status);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null));
    }

    @Test
    public void constructor_invalidAttendanceStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Attendance(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // null attendance status
        assertThrows(NullPointerException.class, () -> new Attendance(null));

        // invalid attendance status
        assertFalse(Attendance.isValidStatus("")); // empty string
        assertFalse(Attendance.isValidStatus(" ")); // spaces only
        assertFalse(Attendance.isValidStatus("status 100")); // contains non-alphabetic characters
        assertFalse(Attendance.isValidStatus("here")); // is not a valid attendance status

        // valid attendance status
        assertTrue(Attendance.isValidStatus("present")); // all lowercase
        assertTrue(Attendance.isValidStatus("ABSENT")); // all uppercase
        assertTrue(Attendance.isValidStatus("exCUsed")); // with capital letters
    }

    @Test
    public void equals() {
        Attendance attendance = new Attendance("PRESENT");

        // same values -> returns true
        assertTrue(attendance.equals(new Attendance("PRESENT")));

        // same object -> returns true
        assertTrue(attendance.equals(attendance));

        // null -> returns false
        assertFalse(attendance.equals(null));

        // different types -> returns false
        assertFalse(attendance.equals(5.0f));

        // different values -> returns false
        assertFalse(attendance.equals(new Attendance("ABSENT")));
    }
}
