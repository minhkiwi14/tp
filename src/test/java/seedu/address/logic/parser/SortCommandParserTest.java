package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortDescriptor;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validName_returnsSortCommand() {
        SortDescriptor descriptor = new SortDescriptor();
        descriptor.setSortByName();
        SortCommand expectedCommand = new SortCommand(descriptor);
        assertParseSuccess(parser, " /by name", expectedCommand);
    }

    @Test
    public void parse_validGrade_returnsSortCommand() {
        SortDescriptor descriptor = new SortDescriptor();
        descriptor.setSortByGrade();
        SortCommand expectedCommand = new SortCommand(descriptor);
        assertParseSuccess(parser, " /by grade", expectedCommand);
    }

    @Test
    public void parse_validAttendance_returnsSortCommand() {
        SortDescriptor descriptor = new SortDescriptor();
        descriptor.setSortByAttendance();
        SortCommand expectedCommand = new SortCommand(descriptor);
        assertParseSuccess(parser, " /by attendance", expectedCommand);
    }

    @Test
    public void parse_validParticipation_returnsSortCommand() {
        SortDescriptor descriptor = new SortDescriptor();
        descriptor.setSortByParticipation();
        SortCommand expectedCommand = new SortCommand(descriptor);
        assertParseSuccess(parser, " /by participation", expectedCommand);
    }

    @Test
    public void parse_missingPrefix_throwsParseException() {
        assertParseFailure(parser, " name", "Sort command must include the /by prefix.\n" + SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " grade", "Sort command must include the /by prefix.\n" + SortCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_missingFieldAfterPrefix_throwsParseException() {
        assertParseFailure(parser, " /by", "Expected exactly one space after the prefix, followed by an argument: /by");
    }

    @Test
    public void parse_invalidField_throwsParseException() {
        assertParseFailure(parser, " /by pizza", "Invalid sort field: 'pizza'.\n" + SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " /by 123", "Invalid sort field: '123'.\n" + SortCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_multipleFields_throwsParseException() {
        assertParseFailure(parser, " /by name grade", "Only one sort field is allowed.\n" + SortCommand.MESSAGE_USAGE);
    }
}
