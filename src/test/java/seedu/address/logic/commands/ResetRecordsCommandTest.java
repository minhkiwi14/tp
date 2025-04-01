package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ResetRecordsCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ResetRecordsCommand(), model, ResetRecordsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        for (Person person : getTypicalPersons()) {
            Person originalPerson = new PersonBuilder(person).build();
            Id originalId = originalPerson.getId();

            PersonBuilder personInList = new PersonBuilder(originalPerson);
            Person resetPerson = personInList.withAttendance("UNMARKED").withParticipation("UNMARKED")
                    .withGrade(-1).build();

            expectedModel.setPerson(person, resetPerson);
        }

        assertCommandSuccess(new ResetRecordsCommand(), model, ResetRecordsCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
