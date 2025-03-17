package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.util.Pair;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_oneFieldSpecified_success() {
        Person originalPerson = new PersonBuilder(TypicalPersons.ALICE).build();
        Id originalId = originalPerson.getId();
        
        PersonBuilder personInList = new PersonBuilder(originalPerson);
        Person editedPerson = personInList.withId(VALID_ID_BOB).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(originalPerson)
                .withId(VALID_ID_BOB).build();
        EditCommand editCommand = new EditCommand(originalId, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.editFormat(originalId, List.of(new Pair<>("New Id", VALID_ID_BOB))));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(TypicalPersons.ALICE, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Person originalPerson = new PersonBuilder(TypicalPersons.ALICE).build();
        Id originalId = originalPerson.getId();
        
        PersonBuilder personInList = new PersonBuilder(originalPerson);
        Person editedPerson = personInList.withId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(originalPerson)
                .withId(VALID_ID_BOB).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        EditCommand editCommand = new EditCommand(originalId, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                Messages.editFormat(originalId, List.of(new Pair<>("New Id", VALID_ID_BOB),
                new Pair<>("Phone", VALID_PHONE_BOB), new Pair<>("Email", VALID_EMAIL_BOB))));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(TypicalPersons.ALICE, editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_failure() {
        Person originalPerson = new PersonBuilder(TypicalPersons.ALICE).build();
        String duplicateId = TypicalPersons.BENSON.getId().toString();
        
        PersonBuilder personInList = new PersonBuilder(originalPerson);
        Person editedPerson = personInList.withId(duplicateId).build();

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertThrows(
                DuplicatePersonException.class,
                () -> expectedModel.setPerson(TypicalPersons.ALICE, editedPerson),
                "DuplicatePersonException should have been thrown.");
    }

    @Test
    public void equals() {
        EditPersonDescriptor amyDescriptor = new EditPersonDescriptor(DESC_AMY);
        final EditCommand standardCommand = new EditCommand(new Id(VALID_ID_AMY), amyDescriptor);

        // same values -> returns true
        EditCommand commandWithSameValues = new EditCommand(new Id(VALID_ID_AMY), amyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different id -> returns false
        assertFalse(standardCommand.equals(new EditCommand(new Id(VALID_ID_BOB), amyDescriptor)));

        // different descriptor -> returns false
        EditPersonDescriptor bobDescriptor = new EditPersonDescriptor(DESC_BOB);
        assertFalse(standardCommand.equals(new EditCommand(new Id(VALID_ID_AMY), bobDescriptor)));
    }

    @Test
    public void toStringMethod() {
        Id id = new Id(VALID_ID_AMY);
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditCommand editCommand = new EditCommand(id, editPersonDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{id=" + id + ", editPersonDescriptor="
                + editPersonDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
