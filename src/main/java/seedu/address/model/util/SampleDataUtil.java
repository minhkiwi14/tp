package seedu.address.model.util;

import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code BetterCallTA} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Id("A0123456N"), new Name("Walter White"), new Phone("12345678"),
                    new Email("walter.w@example.com"), new Course("HS2915"), new Attendance("PRESENT"),
                    new Participation("EXCELLENT"), new Grade(98),
                    List.of(new Note("Requested for a meeting"), new Note("Will be absent next week"))),
            new Person(new Id("A0123457N"), new Name("Jesse Pinkman"), new Phone("87654321"),
                    new Email("jesse.p@example.com"), new Course("HS2915"), new Attendance("PRESENT"),
                    new Participation("GOOD"), new Grade(96), List.of())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person p : getSamplePersons()) {
            sampleAb.addPerson(p);
        }
        return sampleAb;
    }
}
