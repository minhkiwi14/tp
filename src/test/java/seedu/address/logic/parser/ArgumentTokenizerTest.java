package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
    public void tokenize_emptyArgsString_noValues() {
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

        // Verify the last value is returned
        assertEquals(expectedValues[expectedValues.length - 1], argMultimap.getValue(prefix).get());

        // Verify the number of values returned is as expected
        assertEquals(expectedValues.length, argMultimap.getAllValues(prefix).size());

        // Verify all values returned are as expected and in order
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
    public void tokenize_noPrefixes_allTakenAsPreamble() {
        String argsString = "  some random string /option tag with leading and trailing spaces ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString);

        // Same string expected as preamble, but leading/trailing spaces should be trimmed
        assertPreamblePresent(argMultimap, argsString.trim());

    }

    /**
     * Asserts that the preamble and arguments are correctly tokenized.
     */
    @Test
    public void tokenize_oneArgument() {
        // Preamble present
        String argsString = "  Some preamble string /option Argument value ";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashOption);
        assertPreamblePresent(argMultimap, "Some preamble string");
        assertArgumentPresent(argMultimap, slashOption, "Argument value");

        // No preamble
        argsString = " /option   Argument value ";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashOption);
        assertPreambleEmpty(argMultimap);
        assertArgumentPresent(argMultimap, slashOption, "Argument value");

    }

    /**
     * Asserts that the preamble and arguments are correctly tokenized.
     */
    @Test
    public void tokenize_multipleArguments() {
        // Only two arguments are present
        String argsString = " somePreambleString /option value1 /bar value2";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(argsString, slashBar, slashOption);
        assertPreamblePresent(argMultimap, "somePreambleString");
        assertArgumentPresent(argMultimap, slashBar, "value2");
        assertArgumentPresent(argMultimap, slashOption, "value1");

        // All three arguments are present
        argsString = " preamble /option 1 /bar CQ /foo YouFool";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashBar, slashFoo, slashOption);
        assertPreamblePresent(argMultimap, "preamble");
        assertArgumentPresent(argMultimap, slashBar, "CQ");
        assertArgumentPresent(argMultimap, slashFoo, "YouFool");
        assertArgumentPresent(argMultimap, slashOption, "1");

        /* Also covers: Reusing of the tokenizer multiple times */

        // Reuse tokenizer on an empty string to ensure ArgumentMultimap is correctly reset
        // (i.e. no stale values from the previous tokenizing remain)
        argsString = "";
        argMultimap = ArgumentTokenizer.tokenize(argsString, slashOption, slashFoo, slashBar);
        assertPreambleEmpty(argMultimap);
        assertArgumentAbsent(argMultimap, slashOption);

        /* Also covers: testing for prefixes not specified as a prefix */

        // Prefixes not previously given to the tokenizer should not return any values
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
