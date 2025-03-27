package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import seedu.address.model.Model;

/**
 * Displays a bar chart of the grades of all students in the current displayed list.
 */
public class DisplayCommand extends Command {

    public static final String COMMAND_WORD = "displayChart";

    public static final String MESSAGE_SUCCESS = "Created graph from all students";

    @FXML
    private StackPane histogramPlaceholder;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (histogramPlaceholder.isVisible()) {
            histogramPlaceholder.setVisible(false);
        } else {
            histogramPlaceholder.setVisible(true);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
