package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_APPEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_LOAD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_SAVE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a FileCommand which can load, save or append data to a file.
 */
public class FileCommand extends Command {
    /**
     * Represents the operation of the FileCommand.
     */
    public enum FileOperation {
        LOAD, SAVE, APPEND
    }

    public static final String COMMAND_WORD = "file";
    public static final String MESSAGE_SUCCESS = "File operation successful: %1$s";
    public static final String MESSAGE_ERROR = "File operation failed: %1$s";
    public static final String MESSAGE_STRING_UNFORMATTED = """
            %s: Loads, saves or appends data to a file.
            Parameters:
            %s FILE_PATH
            %s FILE_PATH
            %s FILE_PATH

            Example:
            file /load data.txt
            file /save data.txt
            file /append data.txt
            """;

    public static final String MESSAGE_USAGE = String.format(MESSAGE_STRING_UNFORMATTED, COMMAND_WORD, PREFIX_FILE_LOAD,
            PREFIX_FILE_SAVE, PREFIX_FILE_APPEND);

    private final FileOperation operation;

    private final String fileName;

    /**
     * Creates a FileCommand to load, save or append data to a file.
     */
    public FileCommand(FileOperation operation, String filePath) {
        this.operation = operation;
        this.fileName = filePath;
    }

    /**
     * Executes the FileCommand.
     *
     * @param model Model
     * @return CommandResult
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (operation) {
        case LOAD:
            return load(model);
        case SAVE:
            return save(model);
        case APPEND:
            return append(model);
        default:
            throw new CommandException(String.format(MESSAGE_ERROR, "Invalid file operation"));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FileCommand // instanceof handles nulls
                        && operation.equals(((FileCommand) other).operation)
                        && fileName.equals(((FileCommand) other).fileName)); // state check
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("operation", operation)
                .add("filePath", fileName)
                .toString();
    }

    /**
     * Returns the operation of the FileCommand.
     *
     * @return FileOperation
     */
    public FileOperation getOperation() {
        return operation;
    }

    /**
     * Returns the file path of the FileCommand.
     *
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Executes the load operation.
     *
     * @param model Model
     * @return CommandResult
     */
    public CommandResult load(Model model) {
        Boolean status = false;
        if (status == false) {
            return new CommandResult(String.format(MESSAGE_ERROR, "Failed to load data from " + fileName));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, "Loaded data from " + fileName));
    }

    /**
     * Executes the save operation.
     *
     * @param model Model
     * @return CommandResult
     */
    public CommandResult save(Model model) {
        Boolean status = false;
        if (status == false) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, "Failed to save data to " + fileName));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, "Saved data to " + fileName));
    }

    /**
     * Executes the append operation.
     *
     * @param model Model
     * @return CommandResult
     */
    public CommandResult append(Model model) {
        Boolean status = false;
        if (status == false) {
            return new CommandResult(String.format(MESSAGE_ERROR, "Failed to append data to " + fileName));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, "Appended data to " + fileName));
    }
}
