package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's note in BetterCallTA.
 * Guarantees: immutable; is valid as declared in {@link #isValidNote(String)}
 */
public class Note {
    
    public static final String MESSAGE_CONSTRAINTS =
            "Notes can take any values, and it should not be blank";
    
    /**
     * The first character of the note must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String message;

    /**
     * Constructs a {@code Note}.
     * 
     * @param message A valid note.
     */
    public Note(String message) {
        requireNonNull(message);
        checkArgument(isValidNote(message), MESSAGE_CONSTRAINTS);
        this.message = message;
    }

    /**
     * Returns true if a given string is a valid note.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.message;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        return this.message.equals(otherNote.message);
    }

    @Override
    public int hashCode() {
        return this.message.hashCode();
    }
}
