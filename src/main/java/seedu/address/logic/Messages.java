package seedu.address.logic;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.util.Pair;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_ID = "The person ID provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Id: ")
                .append(person.getId())
                .append("; Name: ")
                .append(person.getName())
                .append("; Phone: ")
                .append(person.getPhone())
                .append("; Email: ")
                .append(person.getEmail())
                .append("; Course: ")
                .append(person.getCourse())
                .append("; Attendance: ")
                .append(person.getAttendance())
                .append("; Participation: ")
                .append(person.getParticipation())
                .append("; Grade: ")
                .append(person.getGrade());
                //.append("; Note: ")
                //.append(person.getNote());

        return builder.toString();
    }

    /**
     * Formats the {@code updatedFields} to be displayed as output for Edit command.
     * 
     * @param oldId original ID to identify person edited.
     */
    public static String editFormat(Id oldId, List<Pair<String, String>> updatedFields) {
        StringBuilder sb = new StringBuilder();
        sb.append("Original Id: " + oldId + ", ");

        for (Pair<String, String> p : updatedFields) {
            sb.append(p.getKey() + ": " + p.getValue() + ", ");
        }
        sb.delete(sb.length() - 2, sb.length()); // remove last comma

        return sb.toString();
    }
}
