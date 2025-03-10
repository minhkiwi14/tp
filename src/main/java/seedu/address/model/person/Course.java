package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Course {

    public static final String MESSAGE_CONSTRAINTS =
            "Course names should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String course;

    public Course(String course) {
        requireNonNull(course);
        checkArgument(isValidCourse(course), MESSAGE_CONSTRAINTS);
        this.course = course;
    }

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
        return course.equals(otherCourse.course);
    }

    @Override
    public int hashCode() {
        return course.hashCode();
    }
}
