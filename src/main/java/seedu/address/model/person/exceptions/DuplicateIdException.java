package seedu.address.model.person.exceptions;

/**
 * Signals that a person with the same ID already exists in the address book.
 */
public class DuplicateIdException extends RuntimeException {
    public DuplicateIdException() {
        super("Person with the same ID already exists in the address book");
    }
}
