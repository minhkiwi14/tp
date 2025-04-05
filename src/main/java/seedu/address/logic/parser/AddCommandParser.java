package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    // Placeholder values for optional fields
    private static final String PHONE_PLACEHOLDER = "00000000";
    private static final String EMAIL_PLACEHOLDER = "example@u.nus.edu";
    private static final String COURSE_PLACEHOLDER = "AAA0000AA";

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args,
                PREFIX_NAME,
                PREFIX_PHONE,
                PREFIX_EMAIL,
                PREFIX_ID,
                PREFIX_COURSE,
                PREFIX_NOTE);

        // Check if any invalid prefixes are present
        if (args.contains(PREFIX_GRADE.getPrefix())
                || args.contains(PREFIX_ATTENDANCE.getPrefix())
                || args.contains(PREFIX_PARTICIPATION.getPrefix())) {
            throw new ParseException(
                    "Grade, Attendance and Participation cannot be added with the add command. "
                            + "Please add them using the edit command.");
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ID)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ID, PREFIX_NAME, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_COURSE, PREFIX_NOTE);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).orElse(PHONE_PLACEHOLDER));
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL)
                .orElse(EMAIL_PLACEHOLDER));
        Course course = ParserUtil.parseCourse(argMultimap.getValue(PREFIX_COURSE).orElse(COURSE_PLACEHOLDER));
        List<Note> noteList = ParserUtil.parseNotes(argMultimap.getAllValues(PREFIX_NOTE));

        Person person = new Person(id, name, phone, email, course, noteList);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> {
            Optional<String> value = argumentMultimap.getValue(prefix);
            // Also check if the value is not empty
            boolean isPresent = value.isPresent() && !value.get().trim().isEmpty();

            if (!isPresent) {
                System.out.println("Missing or empty value for prefix: " + prefix.getPrefix());
            }
            return isPresent;
        });
    }

}
