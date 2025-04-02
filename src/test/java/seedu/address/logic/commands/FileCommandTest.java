package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FileCommand.FileOperation;

public class FileCommandTest {

    @Test
    public void execute_load_success() {
        FileCommand fileCommand = new FileCommand(FileOperation.LOAD, "data");
        assertEquals(FileOperation.LOAD, fileCommand.getOperation());
        assertEquals("data", fileCommand.getFileName());
    }

    @Test
    public void execute_save_success() {
        FileCommand fileCommand = new FileCommand(FileOperation.SAVE, "data");
        assertEquals(FileOperation.SAVE, fileCommand.getOperation());
        assertEquals("data", fileCommand.getFileName());
    }

    @Test
    public void execute_list_success() {
        FileCommand fileCommand = new FileCommand(FileOperation.LIST, "");
        assertEquals(FileOperation.LIST, fileCommand.getOperation());
        assertEquals("", fileCommand.getFileName());
    }

    @Test
    public void execute_oneFieldSpecified_success() {
        FileCommand fileCommand = new FileCommand(FileOperation.LOAD, "data");
        assertEquals(FileOperation.LOAD, fileCommand.getOperation());
        assertEquals("data", fileCommand.getFileName());
    }

    @Test
    public void equals() {
        // same object -> returns true
        FileCommand fileCommand = new FileCommand(FileOperation.LOAD, "data");
        assertTrue(fileCommand.equals(fileCommand));

        // same values -> returns true
        FileCommand fileCommandCopy = new FileCommand(FileOperation.LOAD, "data");
        assertTrue(fileCommand.equals(fileCommandCopy));

        // different types -> returns false
        assertFalse(fileCommand.equals(1));

        // null -> returns false
        assertFalse(fileCommand.equals(null));

        // different file name -> returns false
        FileCommand fileCommandDiff = new FileCommand(FileOperation.LOAD, "data2");
        assertFalse(fileCommand.equals(fileCommandDiff));

        // different operation -> returns false
        FileCommand fileCommandDiffOp = new FileCommand(FileOperation.SAVE, "data");
        assertFalse(fileCommand.equals(fileCommandDiffOp));
    }

    @Test
    public void toStringMethod() {
        FileCommand fileCommand = new FileCommand(FileOperation.LOAD, "data");
        String expected = FileCommand.class.getCanonicalName() + "{operation=LOAD, arg=data}";
        assertEquals(fileCommand.toString(), expected);
    }
}
