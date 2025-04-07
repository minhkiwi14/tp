package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_ID_AMY = "A0457812N";
    public static final String VALID_ID_BOB = "NT0123456U";
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_COURSE_AMY = "CS2103T";
    public static final String VALID_COURSE_BOB = "CS2101";
    public static final String VALID_ATTENDANCE_AMY = "EXCUSED";
    public static final String VALID_ATTENDANCE_BOB = "ABSENT";
    public static final String VALID_PARTICIPATION_AMY = "UNMARKED";
    public static final String VALID_PARTICIPATION_BOB = "AVERAGE";
    public static final int VALID_GRADE_AMY = 74;
    public static final int VALID_GRADE_BOB = 86;
    public static final String VALID_NOTE_AMY = "On Medical Leave";
    public static final String VALID_NOTE_BOB = "Disturbs other students";

    public static final String ID_DESC_AMY = " " + PREFIX_ID + " " + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + " " + VALID_ID_BOB;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + " " + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + " " + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + " " + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + " " + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + " " + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + " " + VALID_EMAIL_BOB;
    public static final String COURSE_DESC_AMY = " " + PREFIX_COURSE + " " + VALID_COURSE_AMY;
    public static final String COURSE_DESC_BOB = " " + PREFIX_COURSE + " " + VALID_COURSE_BOB;
    public static final String ATTENDANCE_DESC_AMY = " " + PREFIX_ATTENDANCE + " " + VALID_ATTENDANCE_AMY;
    public static final String ATTENDANCE_DESC_BOB = " " + PREFIX_ATTENDANCE + " " + VALID_ATTENDANCE_BOB;
    public static final String PARTICIPATION_DESC_AMY = " " + PREFIX_PARTICIPATION + " "
            + VALID_PARTICIPATION_AMY;
    public static final String PARTICIPATION_DESC_BOB = " " + PREFIX_PARTICIPATION + " "
            + VALID_PARTICIPATION_BOB;
    public static final String GRADE_DESC_AMY = " " + PREFIX_GRADE + " " + VALID_GRADE_AMY;
    public static final String GRADE_DESC_BOB = " " + PREFIX_GRADE + " " + VALID_GRADE_BOB;
    public static final String NOTE_DESC_AMY = " " + PREFIX_NOTE + " " + VALID_NOTE_AMY;
    public static final String NOTE_DESC_BOB = " " + PREFIX_NOTE + " " + VALID_NOTE_BOB;

    public static final String INVALID_ID = "A1234567"; // No ending letter
    public static final String INVALID_NAME = " "; // Name cannot be a blank space
    public static final String INVALID_PHONE = " +65 1234"; // Cannot have spaces in between
    public static final String INVALID_EMAIL = " kiwi.com"; // Missing '@' symbol
    public static final String INVALID_COURSE = "1234"; // Course must have leading letters
    public static final String INVALID_ATTENDANCE = "NORMAL"; // Not a valid attendance status
    public static final String INVALID_PARTICIPATION = "ACTIVE"; // Not a valid participation status
    public static final Integer INVALID_GRADE = 101; // Grade must be between 0 and 100 or NA

    public static final String INVALID_ID_DESC = " " + PREFIX_ID + INVALID_ID;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + INVALID_NAME;
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + INVALID_PHONE;
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + INVALID_EMAIL;
    public static final String INVALID_COURSE_DESC = " " + PREFIX_COURSE + INVALID_COURSE;
    public static final String INVALID_ATTENDANCE_DESC = " " + PREFIX_ATTENDANCE + INVALID_ATTENDANCE;
    public static final String INVALID_PARTICIPATION_DESC = " " + PREFIX_PARTICIPATION + INVALID_PARTICIPATION;
    public static final String INVALID_GRADE_DESC = " " + PREFIX_GRADE + INVALID_GRADE;
    public static final String INVALID_NOTE_DESC = " " + PREFIX_NOTE + "";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withId(VALID_ID_AMY).withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withCourse(VALID_COURSE_AMY)
                .withAttendance(VALID_ATTENDANCE_AMY).withParticipation(VALID_PARTICIPATION_AMY)
                .withGrade(VALID_GRADE_AMY).withNote(VALID_NOTE_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withId(VALID_ID_BOB).withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withCourse(VALID_COURSE_BOB)
                .withAttendance(VALID_ATTENDANCE_BOB).withParticipation(VALID_PARTICIPATION_BOB)
                .withGrade(VALID_GRADE_BOB).withNote(VALID_NOTE_BOB).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
