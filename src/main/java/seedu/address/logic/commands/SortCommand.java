package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.Comparator;

/**
 * Sorts all persons in the address book by name in alphabetical order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons by name in alphabetical order.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Persons sorted by name alphabetically.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(Comparator.comparing(person -> person.getName().toString()));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
