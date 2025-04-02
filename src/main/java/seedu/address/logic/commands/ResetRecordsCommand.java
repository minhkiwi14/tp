package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Resets all attendance and participation records in the address book to their default state.
 * This command preserves all personal information and only clears the attendance, participation
 * and grade for all students in the system.
 */
public class ResetRecordsCommand extends Command {
    public static final String COMMAND_WORD = "resetRecords";

    public static final String MESSAGE_SUCCESS = "All records have been reset.";

    public static final String MESSAGE_EMPTY_ADDRESS_BOOK = "No contacts to reset - the address book is empty.";

    public static final String MESSAGE_ALREADY_UNMARKED =
            "No records to reset - all students' attendance and participation are already unmarked.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resets all records in the address book.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * Executes the reset records command after validating preconditions.
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} containing a success message upon successful execution.
     * @throws CommandException if the address book is empty or records are already unmarked.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Person> personList = model.getFilteredPersonList();

        // Check for empty address book
        if (personList.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_ADDRESS_BOOK);
        }

        // Check if all records are already unmarked
        if (model.areAllRecordsUnmarked()) {
            throw new CommandException(MESSAGE_ALREADY_UNMARKED);
        }

        model.resetRecords();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    // /**
    //  * Checks if all students' attendance and participation records are unmarked.
    //  *
    //  * @param personList List of persons to check
    //  * @return true if all records are unmarked, false otherwise
    //  */
    // private boolean areAllRecordsUnmarked(ObservableList<Person> personList) {
    //     return personList.stream()
    //             .allMatch(person ->
    //                     person.getAttendance().getStatus().toUpperCase().equals("UNMARKED")
    //                             && person.getParticipation().toString().toUpperCase().equals("UNMARKED"));
    // }
}
