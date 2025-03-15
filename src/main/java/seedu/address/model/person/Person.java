package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Id id;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Course course;
    private final Attendance attendance;
    private final Participation participation;
    private final Grade grade;
    private final List<Note> notes;

    /**
     * Constructor for Person.
     * Used when initializing a new Person object with full details.
     *
     * @param id     The student ID of the person.
     * @param name   The name of the person.
     * @param phone  The phone number of the person.
     * @param email  The email of the person.
     * @param course The course of the person.
     */
    public Person(Id id, Name name, Phone phone, Email email, Course course) {
        requireAllNonNull(id, name, phone, email);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.course = course;
        this.attendance = new Attendance();
        this.participation = new Participation();
        this.grade = new Grade();
        this.notes = List.of();
    }

    /**
     * Constructor for Person.
     * Used when setting all fields of a Person object.
     *
     * @param id            The student ID of the person.
     * @param name          The name of the person.
     * @param phone         The phone number of the person.
     * @param email         The email of the person.
     * @param course        The course of the person.
     * @param attendance    The attendance of the person.
     * @param participation The participation of the person.
     * @param grade         The grade of the person.
     * @param notes         The notes of the person.
     */
    public Person(Id id, Name name, Phone phone, Email email, Course course, Attendance attendance,
                  Participation participation, Grade grade, List<Note> notes) {
        requireAllNonNull(id, name, phone, email, course, attendance, participation, grade, notes);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.course = course;
        this.attendance = attendance;
        this.participation = participation;
        this.grade = grade;
        this.notes = notes;
    }

    /**
     * Returns the student ID of the person.
     *
     * @return The student ID of the person.
     */
    public Id getId() {
        return this.id;
    }

    /**
     * Returns the name of the person.
     *
     * @return The name of the person.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * Returns the phone number of the person.
     *
     * @return The phone number of the person.
     */
    public Phone getPhone() {
        return this.phone;
    }

    /**
     * Returns the email of the person.
     *
     * @return The email of the person.
     */
    public Email getEmail() {
        return this.email;
    }

    /**
     * Returns the course of the person.
     *
     * @return The course of the person.
     */
    public Course getCourse() {
        return this.course;
    }

    /**
     * Returns the attendance of the person.
     *
     * @return The attendance of the person.
     */
    public Attendance getAttendance() {
        return this.attendance;
    }

    /**
     * Returns the participation of the person.
     *
     * @return The participation of the person.
     */
    public Participation getParticipation() {
        return this.participation;
    }

    /**
     * Returns the grade of the person.
     *
     * @return The grade of the person.
     */
    public Grade getGrade() {
        return this.grade;
    }

    /**
     * Returns the notes of the person.
     *
     * @return The notes of the person.
     */
    public List<Note> getNotes() {
        return this.notes;
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param id The student ID of the person.
     * @return   A new Person with the given fields.
     */
    public Person setId(Id id) {
        return new Person(id, name, phone, email, course, attendance, participation, grade, notes);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param name The name of the person.
     * @return     A new Person with the given fields.
     */
    public Person setName(Name name) {
        return new Person(id, name, phone, email, course, attendance, participation, grade, notes);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param phone The phone number of the person.
     * @return      A new Person with the given fields.
     */
    public Person setPhone(Phone phone) {
        return new Person(id, name, phone, email, course, attendance, participation, grade, notes);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param email The email of the person.
     * @return      A new Person with the given fields.
     */
    public Person setEmail(Email email) {
        return new Person(id, name, phone, email, course, attendance, participation, grade, notes);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param course The course of the person.
     * @return       A new Person with the given fields.
     */
    public Person setCourse(Course course) {
        return new Person(id, name, phone, email, course, attendance, participation, grade, notes);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param attendance The attendance of the person.
     * @return           A new Person with the given fields.
     */
    public Person setAttendance(Attendance attendance) {
        return new Person(id, name, phone, email, course, attendance, participation, grade, notes);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param participation The participation of the person.
     * @return              A new Person with the given fields.
     */
    public Person setParticipation(Participation participation) {
        return new Person(id, name, phone, email, course, attendance, participation, grade, notes);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param grade The grade of the person.
     * @return      A new Person with the given fields.
     */
    public Person setGrade(Grade grade) {
        return new Person(id, name, phone, email, course, attendance, participation, grade, notes);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param notes The notes of the person.
     * @return      A new Person with the given fields.
     */
    public Person setNotes(List<Note> notes) {
        return new Person(id, name, phone, email, course, attendance, participation, grade, notes);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getId().equals(getId());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return this.id.equals(otherPerson.id)
                && this.name.equals(otherPerson.name)
                && this.phone.equals(otherPerson.phone)
                && this.email.equals(otherPerson.email);
    }

    /**
     * Returns the hashcode of the person.
     *
     * @return The hashcode of the person.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(id, name, phone, email);
    }

    /**
     * Returns the string representation of the person.
     *
     * @return The string representation of the person.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", id)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("course", course)
                .toString();
    }
}
