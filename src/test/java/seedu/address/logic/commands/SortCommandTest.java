package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class SortCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_sortByName_correctOrder() throws Exception {
        SortCommand.SortDescriptor descriptor = new SortCommand.SortDescriptor();
        descriptor.setSortByName();
        SortCommand command = new SortCommand(descriptor);
        command.execute(model);

        List<Person> actual = model.getFilteredPersonList();
        List<Person> expected = Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE);
        assertEquals(expected, actual);
    }

    @Test
    public void execute_sortByGrade_correctOrder() throws Exception {
        SortCommand.SortDescriptor descriptor = new SortCommand.SortDescriptor();
        descriptor.setSortByGrade();
        SortCommand command = new SortCommand(descriptor);
        command.execute(model);

        List<Person> actual = model.getFilteredPersonList();
        List<Person> expected = Arrays.asList(ALICE, ELLE, FIONA, DANIEL, BENSON, CARL, GEORGE);
        assertEquals(expected, actual);
    }

    @Test
    public void execute_sortByAttendance_correctOrder() throws Exception {
        SortCommand.SortDescriptor descriptor = new SortCommand.SortDescriptor();
        descriptor.setSortByAttendance();
        SortCommand command = new SortCommand(descriptor);
        command.execute(model);

        List<Person> actual = model.getFilteredPersonList();
        List<Person> expected = Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE);
        assertEquals(expected, actual);
    }

    @Test
    public void execute_sortByParticipation_correctOrder() throws Exception {
        SortCommand.SortDescriptor descriptor = new SortCommand.SortDescriptor();
        descriptor.setSortByParticipation();
        SortCommand command = new SortCommand(descriptor);
        command.execute(model);

        List<Person> actual = model.getFilteredPersonList();
        List<Person> expected = Arrays.asList(ALICE, DANIEL, ELLE, BENSON, FIONA, CARL, GEORGE);
        assertEquals(expected, actual);
    }
}
