package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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

    /**
     * Overloaded Constructor for Person.
     *
     * @param id  The id of the person.
     * @param name The name of the person.
     */
    public Person(Id id, Name name) {
        this(id, name, new Phone(""), new Email(""), new Course(""));
    }

    /**
     * Constructor for Person.
     *
     * @param id  The id of the person.
     * @param name The name of the person.
     * @param phone The phone number of the person.
     * @param email The email of the person.
     * @param course The course of the person.
     */
    public Person(Id id, Name name, Phone phone, Email email, Course course) {
        requireAllNonNull(id, name, phone, email);
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.course = course;
    }

    /**
     * Returns the id of the person.
     *
     * @return The id of the person.
     */
    public Id getId() {
        return id;
    }

    /**
     * Returns the name of the person.
     *
     * @return The name of the person.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the phone number of the person.
     *
     * @return The phone number of the person.
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns the email of the person.
     *
     * @return The email of the person.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns the course of the person.
     *
     * @return The course of the person.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param id The id of the person.
     * @param name The name of the person.
     * @param phone The phone number of the person.
     * @param email The email of the person.
     * @param course The course of the person.
     * @return A new Person with the given fields.
     */
    public Person setId(Id id) {
        return new Person(id, name, phone, email, course);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param name The name of the person.
     * @return A new Person with the given fields.
     */
    public Person setName(Name name) {
        return new Person(id, name, phone, email, course);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param phone The phone number of the person.
     * @return A new Person with the given fields.
     */
    public Person setPhone(Phone phone) {
        return new Person(id, name, phone, email, course);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param email The email of the person.
     * @return A new Person with the given fields.
     */
    public Person setEmail(Email email) {
        return new Person(id, name, phone, email, course);
    }

    /**
     * Returns a new Person with the given fields.
     *
     * @param course The course of the person.
     * @return A new Person with the given fields.
     */
    public Person setCourse(Course course) {
        return new Person(id, name, phone, email, course);
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
        return id.equals(otherPerson.id)
                && name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email);
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
