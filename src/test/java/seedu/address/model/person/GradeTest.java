package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GradeTest {
    
    @Test
    public void defaultConstructor_fixedValue() {
        assertEquals(-1, new Grade().grade);
    }

    @Test
    public void constructor_invalidGrade_throwsIllegalArgumentException() {
        int invalidGrade = -1;
        assertThrows(IllegalArgumentException.class, () -> new Grade(invalidGrade));
    }

    @Test
    public void isValidGrade() {
        // invalid grade
        assertFalse(Grade.isValidGrade(-1)); // below 0
        assertFalse(Grade.isValidGrade(101)); // above 100

        // valid grade
        assertTrue(Grade.isValidGrade(0)); // extreme case: 0
        assertTrue(Grade.isValidGrade(57)); // integer between 0 and 100
        assertTrue(Grade.isValidGrade(100)); // extreme case: 100
    }

    @Test
    public void equals() {
        Grade grade = new Grade(57);

        // same values -> returns true
        assertTrue(grade.equals(new Grade(57)));

        // same object -> returns true
        assertTrue(grade.equals(grade));

        // null -> returns false
        assertFalse(grade.equals(null));

        // different types -> returns false
        assertFalse(grade.equals(5.0f));

        // different values -> returns false
        assertFalse(grade.equals(new Grade(100)));
    }
}
