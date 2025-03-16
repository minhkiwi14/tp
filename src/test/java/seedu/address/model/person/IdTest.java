package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IdTest {
    
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidId() {
        // null id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // blank id
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only

        // missing parts
        assertFalse(Id.isValidId("0123456U")); // missing starting "A"
        assertFalse(Id.isValidId("A012456U")); // missing digits
        assertFalse(Id.isValidId("A0123456")); // missing closing letter

        // invalid parts
        assertFalse(Id.isValidId(" A0123456U")); // leading space
        assertFalse(Id.isValidId("A0123456U ")); // trailing space
        assertFalse(Id.isValidId("A012 3456U")); // space in between ID
        assertFalse(Id.isValidId("A0123@3456U")); // special characters in ID
        assertFalse(Id.isValidId("00123456U")); // 1st position is not a letter
        assertFalse(Id.isValidId("X0123456U")); // Starting letter(s) are not one of the valid patterns
        assertFalse(Id.isValidId("A01234S6U")); // Contains letters in digits portion
        assertFalse(Id.isValidId("A01234567")); // Last position is not a letter
        assertFalse(Id.isValidId("A0123456C")); // Last position is not a valid letter

        // valid ids
        assertTrue(Id.isValidId("U0123456U")); // Starting portion has 1 letter
        assertTrue(Id.isValidId("HT0123456U")); // Starting portion has 2 letters
        assertTrue(Id.isValidId("A0123456R")); // Last position is a valid letter
    }

    @Test
    public void equals() {
        Id id = new Id("A0111111N");

        // same values -> returns true
        assertTrue(id.equals(new Id("A0111111N")));

        // same object -> returns true
        assertTrue(id.equals(id));

        // null -> returns false
        assertFalse(id.equals(null));

        // different types -> returns false
        assertFalse(id.equals(5.0f));

        // different values -> returns false
        assertFalse(id.equals(new Id("A0222222U")));
    }
}
