package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_LIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_LOAD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_SAVE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.FileCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new FileCommand object
 */
public class FileCommandParser implements Parser<FileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FileCommand
     * and returns a FileCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILE_LOAD, PREFIX_FILE_SAVE,
                PREFIX_FILE_LIST);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FILE_LOAD, PREFIX_FILE_SAVE,
                PREFIX_FILE_LIST);
        argMultimap.verifyOnlyOnePrefix(PREFIX_FILE_LOAD, PREFIX_FILE_SAVE,
                PREFIX_FILE_LIST);

        if (argMultimap.getValue(PREFIX_FILE_LOAD).isPresent()) { // Load command
            return new FileCommand(
                    FileCommand.FileOperation.LOAD,
                    sanitizeFileName(argMultimap.getValue(PREFIX_FILE_LOAD).get()));

        } else if (argMultimap.getValue(PREFIX_FILE_SAVE).isPresent()) { // Save command
            return new FileCommand(
                    FileCommand.FileOperation.SAVE,
                    sanitizeFileName(argMultimap.getValue(PREFIX_FILE_SAVE).get()));

        } else if (argMultimap.getValue(PREFIX_FILE_LIST).isPresent()) { // List command
            return new FileCommand(
                    FileCommand.FileOperation.LIST,
                    argMultimap.getValue(PREFIX_FILE_LIST).get());
        }
        throw new ParseException(FileCommand.MESSAGE_USAGE);
    }

    /**
     * Sanitizes the input file name to prevent directory traversal and invalid characters.
     *
     * @param inputName The original file name to be sanitized, without the file extension.
     * @return The sanitized file name, without the file extension
     */
    private static String sanitizeFileName(String inputName) {
        // Remove path separators and reserved characters
        String sanitized = inputName.replaceAll("[\\\\/:*?\"<>|]", ""); // removes \ / : * ? " < > |

        // Replace whitespace with underscores
        sanitized = sanitized.replaceAll("\\s+", "_");

        // Prevent directory traversal by removing any occurrences of ".."
        sanitized = sanitized.replaceAll("\\.\\.+", "");

        // Limit the length of the file name to 255 characters
        int maxLength = 255;
        if (sanitized.length() > maxLength) {
            return sanitized.substring(0, maxLength);
        }

        // edge case: if the sanitized name is empty, return a default name
        if (sanitized.isBlank()) {
            return String.format("file_%s",
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")));
        }

        assert sanitized.length() <= maxLength : "Sanitized file name exceeds maximum length";
        assert !sanitized.contains("..") : "Sanitized file name contains directory traversal characters";
        assert !sanitized.matches(".*[\\\\/:*?\"<>|].*") : "Sanitized file name contains invalid characters";
        assert !sanitized.isEmpty() : "Sanitized file name is empty";

        return sanitized;
    }
}
