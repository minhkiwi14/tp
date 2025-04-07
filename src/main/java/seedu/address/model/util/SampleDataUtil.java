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
                new Participation("GOOD"), new Grade(96), List.of()),
            new Person(new Id("A0123458N"), new Name("Gus Fring"), new Phone("+65-1111-1111"),
                new Email("gus.fring@example.com"), new Course("HS2915"), new Attendance("EXCUSED"),
                new Participation("UNMARKED"), new Grade(100), List.of(new Note("los pollos hermanos"))),
            new Person(new Id("HT0111111X"), new Name("Saul Goodman"), new Phone("12344321"),
                new Email("saul_goodman@gmail.com"), new Course("HS2195"), new Attendance("PRESENT"),
                new Participation("AVERAGE"), new Grade(74), List.of()),
            new Person(new Id("U0987654U"), new Name("Tuco Salamanca"), new Phone("00000000"),
                new Email("example@u.nus.edu"), new Course("AAA0000AA"), new Attendance("ABSENT"),
                new Participation("UNMARKED"), new Grade(42), List.of())
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
