package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BY;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object.
 */
public class SortCommandParser implements Parser<SortCommand> {

    @Override
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_BY);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException("Sort command must include the /by prefix.\n" + SortCommand.MESSAGE_USAGE);
        }

        if (!argMultimap.getValue(PREFIX_BY).isPresent()) {
            throw new ParseException("Missing /by and sort field.\n" + SortCommand.MESSAGE_USAGE);
        }


        String[] tokens = argMultimap.getValue(PREFIX_BY).get().trim().toLowerCase().split("\\s+");

        if (tokens.length != 1) {
            throw new ParseException("Only one sort field is allowed.\n" + SortCommand.MESSAGE_USAGE);
        }

        String field = tokens[0];


        SortDescriptor descriptor = new SortDescriptor();

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
