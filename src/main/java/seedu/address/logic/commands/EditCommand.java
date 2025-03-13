package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Course;
import seedu.address.model.person.Email;
import seedu.address.model.person.Id;
import seedu.address.model.person.Name;
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
            %s NEW_ID
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
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
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

        return new Person(updatedId, updatedName, updatedPhone, updatedEmail, updatedCourse, 
                personToEdit.getAttendance(), personToEdit.getParticipation(), personToEdit.getGrade(),
                personToEdit.getNotes());
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
        return editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Id new_id;
        private Name name;
        private Phone phone;
        private Email email;
        private Course course;
        
        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setNewId(toCopy.new_id);
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setCourse(toCopy.course);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(new_id, name, phone, email, course);
        }

        public void setNewId(Id new_id) {
            this.new_id = new_id;
        }

        public Optional<Id> getNewId() {
            return Optional.ofNullable(new_id);
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
            return Objects.equals(new_id, otherEditPersonDescriptor.new_id)
                    && Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(course, otherEditPersonDescriptor.course);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("new ID", new_id)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("course", course)
                    .toString();
        }
    }
}
