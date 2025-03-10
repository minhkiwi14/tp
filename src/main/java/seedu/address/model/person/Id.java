package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Id {

    public static final String MESSAGE_CONSTRAINTS = 
            "IDs should start with an uppercase letter, followed by 7 digits, and end with an uppercase letter.";

    public static final String VALIDATION_REGEX = "^[A]\\d{7}[A-Z]$";

    public final String id;

    public Id(String id) {
        requireNonNull(id);
        checkArgument(isValidId(id), MESSAGE_CONSTRAINTS);
        this.id = id;
    }

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
        return id.equals(otherId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}