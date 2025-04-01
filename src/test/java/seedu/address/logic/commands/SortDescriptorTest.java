package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand.SortDescriptor;

public class SortDescriptorTest {

    @Test
    public void equals() {
        SortDescriptor descriptorA = new SortDescriptor();
        descriptorA.setSortByName();

        SortDescriptor descriptorSame = new SortDescriptor();
        descriptorSame.setSortByName();

        SortDescriptor descriptorDifferent = new SortDescriptor();
        descriptorDifferent.setSortByGrade();

        assertTrue(descriptorA.equals(descriptorSame));

        assertTrue(descriptorA.equals(descriptorA));

        assertFalse(descriptorA.equals(null));

        assertFalse(descriptorA.equals(5));
        
        assertFalse(descriptorA.equals(descriptorDifferent));
    }

    @Test
    public void toStringMethod() {
        SortDescriptor descriptor = new SortDescriptor();
        descriptor.setSortByAttendance();

        String expected = SortDescriptor.class.getCanonicalName()
                + "{sortByName=false, sortByGrade=false, sortByAttendance=true, sortByParticipation=false}";
        assertEquals(expected, descriptor.toString());
    }
}
