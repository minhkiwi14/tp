package seedu.address.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label studentId;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label course;
    @FXML
    private Label attendance;
    @FXML
    private Label participation;
    @FXML
    private Label grade;
    @FXML
    private Label notes;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        studentId.setText(person.getId().id);
        phone.setText(person.getPhone().value);
        email.setText(person.getEmail().value);
        course.setText(person.getCourse().course);
        attendance.setText(person.getAttendance().toString());
        participation.setText(person.getParticipation().toString());
        List<Note> displayNotes = person.getNotes();
        StringBuilder displayedNote = new StringBuilder();
        displayedNote.append("Notes:\n");
        for (Note note : displayNotes) {
            displayedNote.append("* ").append(note.toString()).append("\n");
        }
        notes.setText(displayedNote.toString());
        int gradeValue = person.getGrade().grade;
        if (gradeValue == -1) {
            grade.setText("Grade: N/A");
        } else {
            grade.setText("Grade: " + gradeValue);
        }
    }
}
