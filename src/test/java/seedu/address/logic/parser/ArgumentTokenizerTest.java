package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains tests for {@code ArgumentTokenizer}.
 */
public class ArgumentTokenizerTest {

    // Prefixes used in the tests below.
    private static final Prefix slashOption = new Prefix("/option");
    private static final Prefix slashBar = new Prefix("/bar");
    private static final Prefix slashFoo = new Prefix("/foo");

    /**
     * Prefixes used in the tests below.
     */
    @Test
    public void tokenize_emptyArgsString_noValues() throws ParseException {
        String argsString = "  ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashOption);

        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, slashOption);
    }

    private void assertPreamblePresent(ArgumentMultimap argMultimap, String expectedPreamble) {
        assertEquals(expectedPreamble, argMultimap.getPreamble());
    }

    private void assertPreambleEmpty(ArgumentMultimap argMultimap) {
        assertTrue(argMultimap.getPreamble().isEmpty());
    }

    /**
     * Asserts all the arguments in {@code argMultimap} with {@code prefix} match the {@code expectedValues}
     * and only the last value is returned upon calling {@code ArgumentMultimap#getValue(Prefix)}.
     */
    private void assertArgumentPresent(ArgumentMultimap argMultimap, Prefix prefix, String... expectedValues) {
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefix).get());
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefix).size());
        for (int i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], argMultimap.getAllValues(prefix).get(i));
        }
    }

    private void assertArgumentAbsent(ArgumentMultimap argMultimap, Prefix prefix) {
        assertFalse(argMultimap.getValue(prefix).isPresent());
    }

    /**
     * Asserts that all prefixes in {@code argMultimap} are absent.
     */
    @Test
    public void tokenize_noPrefixes_allTakenAsPreamble() throws ParseException {
        String argsString = "  some random string /option tag with leading and trailing spaces ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString);
        assertPreamblePresent(argMultimap, argsString.trim());
    }

    /**
     * Asserts that the preamble and arguments are correctly tokenized.
     */
    @Test
    public void tokenize_oneArgument() throws ParseException {
        String argsString = "  Some preamble string /option Argument value ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashOption);
        assertPreamblePresent(argMultimap, "Some preamble string");
        assertArgumentPresent(argMultimap, slashOption, "Argument value");

        argsString = " /option Argument value ";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashOption);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, slashOption, "Argument value");
    }

    /**
     * Asserts that the preamble and arguments are correctly tokenized.
     */
    @Test
    public void tokenize_multipleArguments() throws ParseException {
        String argsString = " somePreambleString /option value1 /bar value2";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashBar, slashOption);
        assertPreamblePresent(argMultimap, "somePreambleString");
        assertArgumentPresent(argMultimap, slashBar, "value2");
        assertArgumentPresent(argMultimap, slashOption, "value1");

        argsString = " preamble /option 1 /bar CQ /foo YouFool";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashBar, slashFoo, slashOption);
        assertPreamblePresent(argMultimap, "preamble");
        assertArgumentPresent(argMultimap, slashBar, "CQ");
        assertArgumentPresent(argMultimap, slashFoo, "YouFool");
        assertArgumentPresent(argMultimap, slashOption, "1");

        argsString = "";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashOption, slashFoo, slashBar);
        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, slashOption);

        argsString = " /unknown /lol /kek";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashOption, slashFoo, slashBar);
        assertArgumentAbsent(argMultimap, slashOption);
    }

    @Test
    public void equalsMethod() {
        Prefix aaa = new Prefix("aaa");

        assertEquals(aaa, aaa);
        assertEquals(aaa, new Prefix("aaa"));
        assertNotEquals(aaa, "aaa");
        assertNotEquals(aaa, new Prefix("aab"));
    }
}
