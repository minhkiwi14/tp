package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "Student with ID %s already exists in the address book";

    private static final String MESSAGE_USAGE_UNFORMATTED = """
            %s: Adds a person to the address book.
            Parameters:
            %s NAME
            %s ID (Student ID)

            Optional Parameters:
            (Cannot have duplicate parameters)
            %s PHONE
            %s EMAIL
            %s COURSE

            Example:
            add /name Walter White /id A1234567N
            add /name Jesse Pinkman /id HT1234567U /phone 98765432 /email jesse@nus.edu.sg /course CS2103T
            """;

    public static final String MESSAGE_USAGE = String.format(MESSAGE_USAGE_UNFORMATTED, COMMAND_WORD,
            PREFIX_NAME, PREFIX_ID, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COURSE);

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, toAdd.getId()));
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
