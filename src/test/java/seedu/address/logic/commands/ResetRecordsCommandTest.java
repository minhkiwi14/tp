package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ResetRecordsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
    }

    @Test
    public void execute_emptyAddressBook_throwsCommandException() {
        Model emptyModel = new ModelManager();
        assertCommandFailure(
                new ResetRecordsCommand(),
                emptyModel,
                ResetRecordsCommand.MESSAGE_EMPTY_ADDRESS_BOOK);
    }

    @Test
    public void execute_allRecordsAlreadyUnmarked_throwsCommandException() {
        // Create a model with all records unmarked
        AddressBook unmarkedAddressBook = new AddressBook();
        for (Person person : getTypicalPersons()) {
            Person unmarkedPerson = new PersonBuilder(person)
                    .withAttendance("UNMARKED")
                    .withParticipation("UNMARKED")
                    .build();
            unmarkedAddressBook.addPerson(unmarkedPerson);
        }
        Model unmarkedModel = new ModelManager(unmarkedAddressBook, new UserPrefs());

        assertCommandFailure(
                new ResetRecordsCommand(),
                unmarkedModel,
                ResetRecordsCommand.MESSAGE_ALREADY_UNMARKED);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        // Set up expected model with all records reset
        for (Person person : getTypicalPersons()) {
            Person resetPerson = new PersonBuilder(person)
                    .withAttendance("UNMARKED")
                    .withParticipation("UNMARKED")
                    .build();
            expectedModel.setPerson(person, resetPerson);
        }

        assertCommandSuccess(
                new ResetRecordsCommand(),
                model,
                ResetRecordsCommand.MESSAGE_SUCCESS,
                expectedModel);
    }

    @Test
    public void execute_partialRecordsMarked_success() {
        // Create a model with some records marked and some unmarked
        AddressBook mixedAddressBook = new AddressBook();
        Person markedPerson = new PersonBuilder()
                .withAttendance("PRESENT")
                .withParticipation("GOOD")
                .build();
        Person unmarkedPerson = new PersonBuilder()
                .withId("A1234567A") // another person
                .withAttendance("UNMARKED")
                .withParticipation("UNMARKED")
                .build();
        mixedAddressBook.addPerson(markedPerson);
        mixedAddressBook.addPerson(unmarkedPerson);
        Model mixedModel = new ModelManager(mixedAddressBook, new UserPrefs());

        // Set up expected model with all records reset
        AddressBook expectedAddressBook = new AddressBook();
        Person expectedMarkedPerson = new PersonBuilder(markedPerson)
                .withAttendance("UNMARKED")
                .withParticipation("UNMARKED")
                .build();
        Person expectedUnmarkedPerson = new PersonBuilder(unmarkedPerson).build(); // already unmarked
        expectedAddressBook.addPerson(expectedMarkedPerson);
        expectedAddressBook.addPerson(expectedUnmarkedPerson);
        Model expectedMixedModel = new ModelManager(expectedAddressBook, new UserPrefs());

        assertCommandSuccess(
                new ResetRecordsCommand(),
                mixedModel,
                ResetRecordsCommand.MESSAGE_SUCCESS,
                expectedMixedModel);
    }
}
