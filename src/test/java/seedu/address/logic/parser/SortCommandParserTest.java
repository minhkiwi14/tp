package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsSortCommand() {
        SortCommand.SortDescriptor name = new SortCommand.SortDescriptor();
        name.setSortByName();
        assertParseSuccess(parser, "name", new SortCommand(name));

        SortCommand.SortDescriptor grade = new SortCommand.SortDescriptor();
        grade.setSortByGrade();
        assertParseSuccess(parser, "grade", new SortCommand(grade));

        SortCommand.SortDescriptor attendance = new SortCommand.SortDescriptor();
        attendance.setSortByAttendance();
        assertParseSuccess(parser, "attendance", new SortCommand(attendance));

        SortCommand.SortDescriptor participation = new SortCommand.SortDescriptor();
        participation.setSortByParticipation();
        assertParseSuccess(parser, "participation", new SortCommand(participation));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "", "Missing sort field.\n" + SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "name extra", "Only one sort field is allowed.\n" + SortCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "invalid", "Invalid sort field: 'invalid'.\n" + SortCommand.MESSAGE_USAGE);
    }
}
