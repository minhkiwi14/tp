package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ATTENDANCE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COURSE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PARTICIPATION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Participation;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {
    private static final String VALID_ID = BENSON.getId().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_COURSE = BENSON.getCourse().toString();
    private static final String VALID_ATTENDANCE = BENSON.getAttendance().getStatus();
    private static final String VALID_PARTICIPATION = BENSON.getParticipation().toString();
    private static final int VALID_GRADE = BENSON.getGrade().grade;
    private static final List<JsonAdaptedNote> VALID_NOTES = BENSON.getNotes().stream()
            .map(JsonAdaptedNote::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = Id.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullId_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(null, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, null, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, null, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, null, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidCourse_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = Course.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullCourse_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Course.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAttendance_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        INVALID_ATTENDANCE, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = Attendance.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAttendance_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        null, VALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidParticipation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, INVALID_PARTICIPATION, VALID_GRADE, VALID_NOTES);
        String expectedMessage = Participation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullParticipation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, null, VALID_GRADE, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Participation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGrade_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, INVALID_GRADE, VALID_NOTES);
        String expectedMessage = Grade.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGrade_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_ID, VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_COURSE,
                        VALID_ATTENDANCE, VALID_PARTICIPATION, null, VALID_NOTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
