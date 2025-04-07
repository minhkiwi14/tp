package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COURSE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private static final String INVALID_GRADE_DESC = " /grade -1"; // invalid grade

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingId_failure() {
        String missingName = " /name John Doe";

        assertParseFailure(parser, missingName, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        String startingId = " /id A0123456N";
        final String invalidGrade = " /grade -1"; // invalid grade

        assertParseFailure(parser, startingId + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, startingId + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, startingId + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, startingId + invalidGrade, Grade.MESSAGE_CONSTRAINTS);

        // invalid phone followed by valid email
        assertParseFailure(parser, startingId + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, startingId + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                "Expected exactly one space after the prefix, "
                        + "followed by an argument: /name");

    }

    @Test
    public void parse_noFieldSpecified_failure() {
        String noFieldSpecified = " /id A0123456N";

        assertParseFailure(parser, noFieldSpecified, EditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        String startingId = " /id A0123456N";

        // valid followed by invalid
        String userInput = startingId + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = startingId + PHONE_DESC_BOB + INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = startingId + PHONE_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL));

        // // multiple invalid values
        userInput = startingId + INVALID_PHONE_DESC + INVALID_EMAIL_DESC + INVALID_PHONE_DESC
                + INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Id startingId = new Id("A0123456N");
        String startingIdDesc = " /id " + startingId.toString();

        String userInput = startingIdDesc + " " + PREFIX_NEW_ID + " " + VALID_ID_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + NAME_DESC_AMY + COURSE_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withId(VALID_ID_BOB)
                .withName(VALID_NAME_AMY).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY)
                .withCourse(VALID_COURSE_BOB).build();
        EditCommand expectedCommand = new EditCommand(startingId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Id startingId = new Id("A0123456N");
        String startingIdDesc = " /id " + startingId.toString();

        String userInput = startingIdDesc + PHONE_DESC_BOB + EMAIL_DESC_AMY + COURSE_DESC_BOB;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).withCourse(VALID_COURSE_BOB).build();
        EditCommand expectedCommand = new EditCommand(startingId, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Id startingId = new Id("A0123456N");
        String startingIdDesc = " /id " + startingId.toString();

        // name
        String userInput = startingIdDesc + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(startingId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = startingIdDesc + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(startingId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = startingIdDesc + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(startingId, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
