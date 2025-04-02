package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    @Override
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim().toLowerCase();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException("Missing sort field.\n" + SortCommand.MESSAGE_USAGE);
        }

        String[] tokens = trimmedArgs.split("\\s+");

        if (tokens.length > 1) {
            throw new ParseException("Only one sort field is allowed.\n" + SortCommand.MESSAGE_USAGE);
        }

        SortDescriptor descriptor = new SortDescriptor();
        String field = tokens[0];

        switch (field) {
        case "name":
            descriptor.setSortByName();
            break;
        case "grade":
            descriptor.setSortByGrade();
            break;
        case "attendance":
            descriptor.setSortByAttendance();
            break;
        case "participation":
            descriptor.setSortByParticipation();
            break;
        default:
            throw new ParseException("Invalid sort field: '" + field + "'.\n" + SortCommand.MESSAGE_USAGE);
        }

        return new SortCommand(descriptor);
    }
}
