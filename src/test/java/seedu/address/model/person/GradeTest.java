package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;

public class GradeTest {

    @Test
    public void defaultConstructor_fixedValue() {
        assertEquals(-1, new Grade().grade);
    }

    @Test
    public void stringConstructor_naGrade_success() {
        String naGrade = "NA";
        try {
            Grade grade = new Grade(naGrade);
            assertEquals(-1, grade.grade);
        } catch (ParseException e) {
            fail("ParseException should not be thrown for NA grade");
        }
    }

    @Test
    public void stringConstructor_invalidGrade_throwsIllegalArgumentException() {
        String invalidGrade = "-1";
        assertThrows(ParseException.class, () -> new Grade(invalidGrade));
    }

    @Test
    public void intConstructor_invalidGrade_throwsIllegalArgumentException() {
        int invalidGrade = -2;
        assertThrows(IllegalArgumentException.class, () -> new Grade(invalidGrade));
    }

    @Test
    public void isValidGrade() {
        // invalid grade
        assertFalse(Grade.isValidGrade(-2)); // below -1
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
