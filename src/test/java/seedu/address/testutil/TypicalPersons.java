package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ATTENDANCE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB; 
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PARTICIPATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withId("A0123451N")
                .withName("Alice Pauline").withPhone("94351253")
                .withEmail("alice@example.com").withCourse("CS2103T")
                .withAttendance("PRESENT").withParticipation("EXCELLENT")
                .withGrade(95).build();
    public static final Person BENSON = new PersonBuilder().withId("A0123452N")
                .withName("Benson Meier").withPhone("98765432")
                .withEmail("johnd@example.com").withCourse("CS2103T")
                .withAttendance("PRESENT").withParticipation("AVERAGE")
                .withGrade(74).build();
    public static final Person CARL = new PersonBuilder().withId("A0123453N").withName("Carl Kurz")
                .withPhone("95352563").withEmail("heinz@example.com").withCourse("CS2103T")
                .withAttendance("PRESENT").withParticipation("POOR")
                .withGrade(67).build();
    public static final Person DANIEL = new PersonBuilder().withId("A0123454N")
                .withName("Daniel Meier").withPhone("87652533")
                .withEmail("cornelia@example.com").withCourse("CS2103T")
                .withAttendance("PRESENT").withParticipation("EXCELLENT")
                .withGrade(78).build();
    public static final Person ELLE = new PersonBuilder().withId("A0123455N").withName("Elle Meyer")
                .withPhone("9482224").withEmail("werner@example.com").withCourse("CS2103T")
                .withAttendance("PRESENT").withParticipation("GOOD")
                .withGrade(83).build();
    public static final Person FIONA = new PersonBuilder().withId("A0123456N").withName("Fiona Kunz")
                .withPhone("9482427").withEmail("lydia@example.com").withCourse("CS2103T")
                .withAttendance("PRESENT").withParticipation("AVERAGE")
                .withGrade(82).build();
    public static final Person GEORGE = new PersonBuilder().withId("A0123457N")
                .withName("George Best").withPhone("9482442").withEmail("anna@example.com")
                .withCourse("CS2103T").withAttendance("PRESENT")
                .withParticipation("NONE").withGrade(56).build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withId("A0123458N").withName("Hoon Meier")
                .withPhone("8482424").withEmail("stefan@example.com").withCourse("CS2103T")
                .withAttendance("ABSENT").withParticipation("UNMARKED")
                .withGrade(62).build();
    public static final Person IDA = new PersonBuilder().withId("A0123459N").withName("Ida Mueller")
                .withPhone("8482131").withEmail("hans@example.com").withCourse("CS2103T")
                .withAttendance("EXCUSED").withParticipation("UNMARKED")
                .withGrade(84).build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withId(VALID_ID_AMY).withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withCourse(VALID_COURSE_AMY)
                .withAttendance(VALID_ATTENDANCE_AMY).withParticipation(VALID_PARTICIPATION_AMY)
                .withGrade(VALID_GRADE_AMY).build();
    public static final Person BOB = new PersonBuilder().withId(VALID_ID_BOB).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withCourse(VALID_COURSE_BOB)
                .withAttendance(VALID_ATTENDANCE_BOB).withParticipation(VALID_PARTICIPATION_BOB)
                .withGrade(VALID_GRADE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
