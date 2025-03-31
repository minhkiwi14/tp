package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class ResetRecordsCommand extends Command {
    public static final String COMMAND_WORD = "resetRecords";

    public static final String MESSAGE_SUCCESS = "All records have been reset.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Resets all records in the address book.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetRecords();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
