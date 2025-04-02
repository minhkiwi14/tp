package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's course in BetterCallTA.
 * Guarantees: immutable; is valid as declared in {@link #isValidCourse(String)}
 */
public class Course {

    public static final String MESSAGE_CONSTRAINTS =
            "The inputted course has an incorrect format. "
            + "Please refer to the User Guide for the accepted formats.";

    /*
     * The course code should follow NUS's course code format:
     * 1. The first two or three characters are uppercase letters.
     * 2. The next four characters are digits.
     * 3. The last two characters are optional and can be uppercase letters.
     */
    public static final String VALIDATION_REGEX = "^[A-Z]{2,3}\\d{4}[A-Z]{0,2}$";

    public final String course;

    /**
     * Constructs a {@code Course}.
     *
     * @param course A valid course.
     */
    public Course(String course) {
        requireNonNull(course);
        String uppercaseCourse = course.toUpperCase();
        checkArgument(isValidCourse(uppercaseCourse), MESSAGE_CONSTRAINTS);
        this.course = uppercaseCourse;
    }

    /**
     * Returns true if a given string is a valid course.
     */
    public static boolean isValidCourse(String test) {
        String uppercaseTest = test.toUpperCase();
        return uppercaseTest.matches(VALIDATION_REGEX);
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
