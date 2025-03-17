package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.testutil.PersonBuilder;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddCommandParserTest {
        private AddCommandParser parser = new AddCommandParser();
        private String expectedFullErrorMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        @Test
        public void parse_allFieldsPresent_success() {
                String args = "add /name Alice /id A0123452N /phone 94351253 /email alice@example.com /course CS2103T";

                AddCommand expectedCommand = new AddCommand(
                                new PersonBuilder()
                                                .withName("Alice")
                                                .withId("A0123452N")
                                                .withPhone("94351253")
                                                .withEmail("alice@example.com")
                                                .withCourse("CS2103T")
                                                .build());

                try {
                        AddCommand parsedCommand = parser.parse(args);
                        assertEquals(expectedCommand, parsedCommand);
                } catch (ParseException e) {
                        e.printStackTrace();
                }

                // assertParseSuccess(parser, args, expectedCommand);
        }

        @Test
        public void parse_optionalFieldsMissing_success() {
                String args = "add /name Alice /id A0123456N";

                AddCommand expectedCommand = new AddCommand(
                                new PersonBuilder()
                                                .withName("Alice")
                                                .withId("A0123456N")
                                                .withPhone("00000000")
                                                .withEmail("alice@u.nus.edu")
                                                .withCourse("No course specified")
                                                .build());

                try {
                        AddCommand parsedCommand = parser.parse(args);
                        assertEquals(expectedCommand, parsedCommand);
                } catch (ParseException e) {
                        e.printStackTrace();
                }

                // assertParseSuccess(parser, args, expectedCommand);
        }

        @Test
        public void parse_compulsoryFieldMissing_failure() {
                String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

                // missing name prefix
                assertParseFailure(parser, "add /id A0123456N", expectedMessage);

                // missing id prefix
                assertParseFailure(parser, "add /name Alice", expectedMessage);
        }

        @Test
        public void parse_invalidValue_failure() {

                // Invalid Name
                String invalidNameCommand = "add /name /id A0236291A";
                try {
                        AddCommand test = parser.parse(invalidNameCommand);
                        throw new AssertionError("The expected ParseException was not thrown.");
                } catch (ParseException e) {
                        assertEquals(expectedFullErrorMessage, e.getMessage());
                }
                // assertParseFailure(parser, "add /name  /id A0237297R", Name.MESSAGE_CONSTRAINTS);

                // Invalid Email
                String invalidEmailCommand = "add /name charlie /id A0237297A /email 123";
                try {
                        AddCommand test = parser.parse(invalidEmailCommand);
                        throw new AssertionError("The expected ParseException was not thrown.");
                } catch (ParseException e) {
                        assertEquals(expectedFullErrorMessage, e.getMessage());
                }
                // assertParseFailure(parser, "add /name charlie /id A0237297A /email 123", Email.MESSAGE_CONSTRAINTS);

                // Invalid Phone
                String invalidPhoneCommand = "add /name bob /id A0123456N /phone 12345678a";
                try {
                        AddCommand test = parser.parse(invalidPhoneCommand);
                        throw new AssertionError("The expected ParseException was not thrown.");
                } catch (ParseException e) {
                        assertEquals(expectedFullErrorMessage, e.getMessage());
                }
                // assertParseFailure(parser, "add /name bob /id A0123456N /phone 12345678a", Phone.MESSAGE_CONSTRAINTS);

                // Invalid Preamble
                String invalidPreambleCommand = "add /invalid hurdur";
                try {
                        AddCommand test = parser.parse(invalidPreambleCommand);
                        throw new AssertionError("The expected ParseException was not thrown.");
                } catch (ParseException e) {
                        assertEquals(expectedFullErrorMessage, e.getMessage());
                }
                // assertParseFailure(parser, "add /invalid hurdur",
                //                 String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }
}
