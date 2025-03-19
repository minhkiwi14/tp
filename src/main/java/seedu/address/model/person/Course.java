package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's course in BetterCallTA.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourse(String)}
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS = "Course names should only contain alphanumeric"
            + " characters and spaces, and it should not be blank";

    // Check if want course codes only

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String course;

    /**
     * Constructs a {@code Course}.
     *
     * @param course A valid course.
     */
    public Course(String course) {
        requireNonNull(course);
        checkArgument(isValidCourse(course), MESSAGE_CONSTRAINTS);
        this.course = course;
    }

    /**
     * Returns true if a given string is a valid course.
     */
    public static boolean isValidCourse(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.course;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Course)) {
            return false;
        }

        Course otherCourse = (Course) other;
        return this.course.equals(otherCourse.course);
    }

    @Override
    public int hashCode() {
        return this.course.hashCode();
    }
}
