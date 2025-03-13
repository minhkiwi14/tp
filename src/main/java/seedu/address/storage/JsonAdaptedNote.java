package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Note;

/**
 * Jackson-friendly version of {@link Note}.
 */
public class JsonAdaptedNote {

    private final String message;

    /**
     * Constructs a {@code JsonAdaptedNote} with the given {@code message}.
     */
    @JsonCreator
    public JsonAdaptedNote(String message) {
        this.message = message;
    }

    /**
     * Converts a given {@code Note} into this class for Jackson use.
     */
    public JsonAdaptedNote(Note source) {
        this.message = source.message;
    }

    /**
     * Returns the note message for Jackson serialization.
     */
    @JsonValue
    public String getMessage() {
        return message;
    }

    /**
     * Converts this Jackson-friendly adapted note object into the model's {@code Note} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted note.
     */
    public Note toModelType() throws IllegalValueException {
        if (!Note.isValidNote(message)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(message);
    }
}
