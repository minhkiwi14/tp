package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FileCommand.FileOperation;

public class FileCommandTest {

    @Test
    public void execute_load_success() {
        FileCommand fileCommand = new FileCommand(FileOperation.LOAD, "data.json");
        assertEquals(FileOperation.LOAD, fileCommand.getOperation());
        assertEquals("data.json", fileCommand.getFileName());
    }

    @Test
    public void execute_save_success() {
        FileCommand fileCommand = new FileCommand(FileOperation.SAVE, "data.json");
        assertEquals(FileOperation.SAVE, fileCommand.getOperation());
        assertEquals("data.json", fileCommand.getFileName());
    }

    @Test
    public void execute_list_success() {
        FileCommand fileCommand = new FileCommand(FileOperation.LIST, "");
        assertEquals(FileOperation.LIST, fileCommand.getOperation());
        assertEquals("", fileCommand.getFileName());
    }

    @Test
    public void execute_oneFieldSpecified_success() {
        FileCommand fileCommand = new FileCommand(FileOperation.LOAD, "data.json");
        assertEquals(FileOperation.LOAD, fileCommand.getOperation());
        assertEquals("data.json", fileCommand.getFileName());
    }

    @Test
    public void equals() {
        // same object -> returns true
        FileCommand fileCommand = new FileCommand(FileOperation.LOAD, "data.json");
        assertTrue(fileCommand.equals(fileCommand));

        // same values -> returns true
        FileCommand fileCommandCopy = new FileCommand(FileOperation.LOAD, "data.json");
        assertTrue(fileCommand.equals(fileCommandCopy));

        // different types -> returns false
        assertFalse(fileCommand.equals(1));

        // null -> returns false
        assertFalse(fileCommand.equals(null));

        // different file name -> returns false
        FileCommand fileCommandDiff = new FileCommand(FileOperation.LOAD, "data2.json");
        assertFalse(fileCommand.equals(fileCommandDiff));

        // different operation -> returns false
        FileCommand fileCommandDiffOp = new FileCommand(FileOperation.SAVE, "data.json");
        assertFalse(fileCommand.equals(fileCommandDiffOp));
    }

    @Test
    public void toStringMethod() {
        FileCommand fileCommand = new FileCommand(FileOperation.LOAD, "data.json");
        String expected = FileCommand.class.getCanonicalName() + "{operation=LOAD, filePath=data.json}";
        assertTrue(fileCommand.toString().equals(expected));
    }
}
