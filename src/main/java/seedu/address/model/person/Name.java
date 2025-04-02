package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's name in BetterCallTA.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should not be blank.";

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        String trimmedName = trimExtraSpaces(name);
        checkArgument(isValidName(trimmedName), MESSAGE_CONSTRAINTS);
        this.fullName = trimmedName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the name without extra spaces.
     * Extra spaces are defined as more than one space between words.
     */
    public static String trimExtraSpaces(String name) {
        String trimmedName = name.trim();
        StringBuilder sb = new StringBuilder();
        boolean lastWasSpace = false;
        for (char c : trimmedName.toCharArray()) {
            if (c == ' ') {
                if (!lastWasSpace) {
                    sb.append(c);
                }
                lastWasSpace = true;
            } else {
                sb.append(c);
                lastWasSpace = false;
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String toString() {
        return this.fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Name)) {
            return false;
        }

        Name otherName = (Name) other;
        return this.fullName.equals(otherName.fullName);
    }

    @Override
    public int hashCode() {
        return this.fullName.hashCode();
    }

}
