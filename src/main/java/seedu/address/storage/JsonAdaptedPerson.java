package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Participation;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String id;
    private final String name;
    private final String phone;
    private final String email;
    private final String course;
    private final String attendance;
    private final String participation;
    private final Integer grade;
    private final List<JsonAdaptedNote> notes = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("id") String id, @JsonProperty("name") String name,
                             @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                             @JsonProperty("course") String course, @JsonProperty("attendance") String attendance,
                             @JsonProperty("participation") String participation, @JsonProperty("grade") int grade,
                             @JsonProperty("notes") List<JsonAdaptedNote> notes) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.course = course;
        this.attendance = attendance;
        this.participation = participation;
        this.grade = grade;
        if (notes != null) {
            this.notes.addAll(notes);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        id = source.getId().id;
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        course = source.getCourse().course;
        attendance = String.valueOf(source.getAttendance().status);
        participation = String.valueOf(source.getParticipation().status);
        grade = source.getGrade().grade;
        notes.addAll(source.getNotes().stream().map(JsonAdaptedNote::new).collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Note> studentNotes = new ArrayList<>();
        for (JsonAdaptedNote note : notes) {
            studentNotes.add(note.toModelType());
        }

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Id.isValidId(id)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }
        final Id modelId = new Id(id);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (course == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Course.class.getSimpleName()));
        }
        if (!Course.isValidCourse(course)) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
        final Course modelCourse = new Course(course);

        if (attendance == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Attendance.class.getSimpleName()));
        }
        if (!Attendance.isValidStatus(attendance)) {
            throw new IllegalValueException(Attendance.MESSAGE_CONSTRAINTS);
        }
        final Attendance modelAttendance = new Attendance(attendance);

        if (participation == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Participation.class.getSimpleName()));
        }
        if (!Participation.isValidStatus(participation)) {
            throw new IllegalValueException(Participation.MESSAGE_CONSTRAINTS);
        }
        final Participation modelParticipation = new Participation(participation);

        if (grade == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Grade.class.getSimpleName()));
        }
        if (!Grade.isValidGrade(grade)) {
            throw new IllegalValueException(Grade.MESSAGE_CONSTRAINTS);
        }
        final Grade modelGrade = new Grade(grade);

        final List<Note> modelNotes = new ArrayList<>(studentNotes);
        return new Person(modelId, modelName, modelPhone, modelEmail,
                modelCourse, modelAttendance, modelParticipation, modelGrade, modelNotes);
    }
}
