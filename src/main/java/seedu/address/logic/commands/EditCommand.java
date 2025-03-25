package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javafx.util.Pair;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
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
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE_UNFORMATTED = """
            %s: Edits the details of the person identified by the student ID.
            Parameters:
            %s ID (Student ID)

            Optional Parameters:
            (Must choose at least 1. Cannot have duplicate parameters)
            %s newId
            %s NAME
            %s PHONE
            %s EMAIL
            %s COURSE

            Example:
            edit /id A1234567B /newid A1234567C /name Walter White
            edit /id A0348275N /phone 98765432 /email jessy@gmail.com /course CS2103T
            """;
    public static final String MESSAGE_USAGE = String.format(MESSAGE_USAGE_UNFORMATTED, COMMAND_WORD,
            PREFIX_ID, PREFIX_NEW_ID, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_COURSE);

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Id id;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param id of the person to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Id id, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(id);
        requireNonNull(editPersonDescriptor);

        this.id = id;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Person personToEdit = model.getPerson(id);
            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

            List<Pair<String, String>> updatedFields = getUpdatedFields(personToEdit, editedPerson);

            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS,
                    Messages.editFormat(personToEdit.getId(), updatedFields)));
        } catch (PersonNotFoundException e) {
            throw new CommandException(e.getMessage());
        }
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Id updatedId = editPersonDescriptor.getNewId().orElse(personToEdit.getId());
        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Course updatedCourse = editPersonDescriptor.getCourse().orElse(personToEdit.getCourse());
        Grade updatedGrade = editPersonDescriptor.getGrade().orElse(personToEdit.getGrade());

        return new Person(updatedId, updatedName, updatedPhone, updatedEmail, updatedCourse,
                personToEdit.getAttendance(), personToEdit.getParticipation(), updatedGrade,
                personToEdit.getNotes());
    }

    /**
     * Returns the list of changed fields between {@code personToEdit} and
     * {@code editedPerson} and their values.
     */
    private List<Pair<String, String>> getUpdatedFields(Person personToEdit, Person editedPerson) {
        List<Pair<String, String>> updatedFields = new ArrayList<>();

        if (!personToEdit.getId().equals(editedPerson.getId())) {
            updatedFields.add(new Pair<>("New Id", editedPerson.getId().toString()));
        }

        if (!personToEdit.getName().equals(editedPerson.getName())) {
            updatedFields.add(new Pair<>("Name", editedPerson.getName().toString()));
        }

        if (!personToEdit.getPhone().equals(editedPerson.getPhone())) {
            updatedFields.add(new Pair<>("Phone", editedPerson.getPhone().toString()));
        }

        if (!personToEdit.getEmail().equals(editedPerson.getEmail())) {
            updatedFields.add(new Pair<>("Email", editedPerson.getEmail().toString()));
        }

        if (!personToEdit.getCourse().equals(editedPerson.getCourse())) {
            updatedFields.add(new Pair<>("Course", editedPerson.getCourse().toString()));
        }

        if (!personToEdit.getGrade().equals(editedPerson.getGrade())) {
            updatedFields.add(new Pair<>("Grade", editedPerson.getGrade().toString()));
        }

        return updatedFields;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return id.equals(otherEditCommand.id)
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("id", id)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Id newId;
        private Name name;
        private Phone phone;
        private Email email;
        private Course course;
        private Attendance attendance;
        private Participation participation;
        private Grade grade;
        private Note note;

        /**
         * Default constructor.
         * Sets {@code attendance}, {@code participation}, {@code grade} and {@code note} to their default
         * values.
         */
        public EditPersonDescriptor() {
            setAttendance(new Attendance());
            setParticipation(new Participation());
            setNote(new Note("NA"));
        }

        /**
         * Copy constructor.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setNewId(toCopy.newId);
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setCourse(toCopy.course);
            setAttendance(toCopy.attendance);
            setParticipation(toCopy.participation);
            setGrade(toCopy.grade);
            setNote(toCopy.note);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(newId, name, phone, email, course, grade);
        }

        public void setNewId(Id newId) {
            this.newId = newId;
        }

        public Optional<Id> getNewId() {
            return Optional.ofNullable(newId);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setCourse(Course course) {
            this.course = course;
        }

        public Optional<Course> getCourse() {
            return Optional.ofNullable(course);
        }

        public void setAttendance(Attendance attendance) {
            this.attendance = attendance;
        }

        public Optional<Attendance> getAttendance() {
            return Optional.ofNullable(attendance);
        }

        public void setParticipation(Participation participation) {
            this.participation = participation;
        }

        public Optional<Participation> getParticipation() {
            return Optional.ofNullable(participation);
        }

        public void setGrade(Grade grade) {
            this.grade = grade;
        }

        public Optional<Grade> getGrade() {
            return Optional.ofNullable(grade);
        }

        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(newId, otherEditPersonDescriptor.newId)
                    && Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(course, otherEditPersonDescriptor.course)
                    && Objects.equals(attendance, otherEditPersonDescriptor.attendance)
                    && Objects.equals(participation, otherEditPersonDescriptor.participation)
                    && Objects.equals(grade, otherEditPersonDescriptor.grade)
                    && Objects.equals(note, otherEditPersonDescriptor.note);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("new ID", newId)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("course", course)
                    .add("attendance", attendance)
                    .add("participation", participation)
                    .add("grade", grade)
                    .add("note", note)
                    .toString();
        }
    }
}
