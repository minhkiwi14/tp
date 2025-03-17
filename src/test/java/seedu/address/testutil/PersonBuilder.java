package seedu.address.testutil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Attendance;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Participation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_ID = "A0123456N";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_COURSE = "CS2103T";

    private Id id;
    private Name name;
    private Phone phone;
    private Email email;
    private Course course;
    private Attendance attendance;
    private Participation participation;
    private Grade grade;
    private Set<Note> notes;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        id = new Id(DEFAULT_ID);
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        course = new Course(DEFAULT_COURSE);
        attendance = new Attendance();
        participation = new Participation();
        grade = new Grade();
        notes = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        id = personToCopy.getId();
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        course = personToCopy.getCourse();
        attendance = personToCopy.getAttendance();
        participation = personToCopy.getParticipation();
        grade = personToCopy.getGrade();
        notes = Set.of();
    }

    /**
     * Sets the {@code Id} of the {@code Person} that we are building.
     */
    public PersonBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Course} of the {@code Person} that we are building.
     */
    public PersonBuilder withCourse(String course) {
        this.course = new Course(course);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Person} that we are building.
     */
    public PersonBuilder withAttendance(String attendance) {
        this.attendance = new Attendance(attendance);
        return this;
    }

    /**
     * Sets the {@code Participation} of the {@code Person} that we are building.
     */
    public PersonBuilder withParticipation(String participation) {
        this.participation = new Participation(participation);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Person} that we are building.
     */
    public PersonBuilder withGrade(int grade) {
        this.grade = new Grade(grade);
        return this;
    }

    /**
     * Builds the person object.
     */
    public Person build() {
        return new Person(id, name, phone, email, course, attendance, participation, grade, List.of());
    }
}
