package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String args = " /name Alice /id A0123452N /phone 94351253 /email alice@example.com /course CS2103T";

        AddCommand expectedCommand = new AddCommand(
                new PersonBuilder()
                        .withName("Alice")
                        .withId("A0123452N")
                        .withPhone("94351253")
                        .withEmail("alice@example.com")
                        .withCourse("CS2103T")
                        .build());

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        String args = " /name Alice /id A0123456N";

        AddCommand expectedCommand = new AddCommand(
                new PersonBuilder()
                        .withName("Alice")
                        .withId("A0123456N")
                        .withPhone("00000000")
                        .withEmail("alice@u.nus.edu")
                        .withCourse("AAA0000AA")
                        .build());

        assertParseSuccess(parser, args, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, " /id A0123456N", expectedMessage);

        // missing id prefix
        assertParseFailure(parser, " /name Alice", expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // Invalid Name
        assertParseFailure(parser, " /name ` /id A0236291A", Name.MESSAGE_CONSTRAINTS);

        // Invalid Email
        assertParseFailure(parser, " /name charlie /id A0237297A /email 123", Email.MESSAGE_CONSTRAINTS);

        // Invalid Phone
        assertParseFailure(parser, " /name bob /id A0123456N /phone 12345678a", Phone.MESSAGE_CONSTRAINTS);

        // Invalid Preamble
        assertParseFailure(parser, " /invalid hurdur",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
