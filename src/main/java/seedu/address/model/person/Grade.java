package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Person's grade in BetterCallTA.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS =
            "Grade should only be an integer between 0 and 100 inclusive, or NA (not applicable) "
                    + "if the grade is not yet available.";

    public final int grade;

    /**
     * Constructs a {@code Grade}.
     * Used when initializing a new Person object. -1 is used to indicate an uninitialized grade.
     */
    public Grade() {
        this.grade = -1;
    }

    /**
     * Constructs a {@code Grade} from Add/Edit command.
     *
     * @param grade A valid grade.
     */
    public Grade(String grade) throws ParseException {
        requireNonNull(grade);
        try {
            if (grade.toUpperCase().equals("NA")) {
                this.grade = -1;
                return;
            }

            int parsedGrade = Integer.parseInt(grade);

            if (parsedGrade == -1) {
                throw new ParseException(MESSAGE_CONSTRAINTS);
            }

            checkArgument(isValidGrade(parsedGrade), MESSAGE_CONSTRAINTS);
            this.grade = parsedGrade;
        } catch (NumberFormatException e) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Constructs a {@code Grade} from Storage.
     *
     * @param grade A valid grade.
     */
    public Grade(int grade) {
        requireNonNull(grade);
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        this.grade = grade;
    }

    /**
     * Returns true if a given integer is a valid grade.
     * A valid grade is an integer between 0 and 100, inclusive.
     */
    public static boolean isValidGrade(int test) {
        return test >= -1 && test <= 100;
    }

    @Override
    public String toString() {
        return Integer.toString(this.grade);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Grade)) {
            return false;
        }

        Grade otherGrade = (Grade) other;
        return this.grade == otherGrade.grade;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(this.grade);
    }
}
