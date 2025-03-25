package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Displays a bar chart of the grades of all students in the current displayed list.
 */
public class DisplayCommand extends Command {
    
    public static final String COMMAND_WORD = "displayChart";

    public static final String MESSAGE_SUCCESS = "Created graph from all students";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
