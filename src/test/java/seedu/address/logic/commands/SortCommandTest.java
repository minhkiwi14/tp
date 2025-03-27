package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_sortByName_success() {
        expectedModel.sortFilteredPersonList(Comparator.comparing(p -> p.getName().toString()));
        SortCommand sortCommand = new SortCommand();

        assertCommandSuccess(sortCommand, model, SortCommand.MESSAGE_SUCCESS, expectedModel);

        List<String> sortedNames = model.getFilteredPersonList().stream()
                .map(p -> p.getName().toString())
                .collect(Collectors.toList());

        List<String> expectedNames = expectedModel.getFilteredPersonList().stream()
                .map(p -> p.getName().toString())
                .collect(Collectors.toList());

        assertEquals(expectedNames, sortedNames);
    }
}
