package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    @Override
    public SortCommand parse(String args) throws ParseException {
        // No arguments expected for sort
        if (!args.trim().isEmpty()) {
            throw new ParseException(SortCommand.MESSAGE_USAGE);
        }
        return new SortCommand();
    }
}
