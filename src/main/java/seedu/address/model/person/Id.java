package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Student ID in BetterCallTA.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(String)}
 */
public class Id {

    public static final String MESSAGE_CONSTRAINTS =
            "The inputted ID has an incorrect format. "
            + "Please refer to the User Guide for the accepted formats.";

    /**
     * The first character of the ID must be 'A',
     * followed by 7 digits, and end with an uppercase letter.
     */
    public static final String VALIDATION_REGEX = "^(A|U|HT|NT)\\d{7}[ABEHJLMNRUWXY]$";

    public final String id;

    /**
     * Constructs a {@code Id}.
     *
     * @param id A valid id.
     */
    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.id;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Id)) {
            return false;
        }

        Id otherId = (Id) other;
        return this.id.equals(otherId.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
