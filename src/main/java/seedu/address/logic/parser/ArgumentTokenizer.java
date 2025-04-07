package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Tokenizes arguments string of the form:
 * {@code preamble <prefix>value <prefix>value ...}<br>
 * e.g. {@code some preamble text t/ 11.00 t/12.00 k/ m/ July} where prefixes
 * are {@code t/ k/ m/}.<br>
 * 1. An argument's value can be an empty string e.g. the value of {@code k/} in
 * the above example.<br>
 * 2. Leading and trailing whitespaces of an argument value will be
 * discarded.<br>
 * 3. An argument may be repeated and all its values will be accumulated e.g.
 * the value of {@code t/}
 * in the above example.<br>
 */
public class ArgumentTokenizer {

    /**
     * Tokenizes an arguments string and returns an {@code ArgumentMultimap} object
     * that maps prefixes to their
     * respective argument values. Only the given prefixes will be recognized in the
     * arguments string.
     *
     * @param argsString Arguments string of the form:
     *                   {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to tokenize the arguments string with
     * @return ArgumentMultimap object that maps prefixes to their arguments
     */
    public static ArgumentMultimap tokenize(String argsString, Prefix... prefixes) throws ParseException {
        try {
            List<PrefixPosition> positions = findAllPrefixPositions(argsString, prefixes);
            return extractArguments(argsString, positions);
        } catch (IndexOutOfBoundsException e) {
            return new ArgumentMultimap();
        }
    }

    /**
     * Finds all zero-based prefix positions in the given arguments string.
     *
     * @param argsString Arguments string of the form:
     *                   {@code preamble <prefix>value <prefix>value ...}
     * @param prefixes   Prefixes to find in the arguments string
     * @return List of zero-based prefix positions in the given arguments string
     */
    private static List<PrefixPosition> findAllPrefixPositions(String argsString, Prefix... prefixes) {
        return Arrays.stream(prefixes)
                .flatMap(prefix -> findPrefixPositions(argsString, prefix).stream())
                .collect(Collectors.toList());
    }

    /**
     * Finds all occurrences of a given prefix in the arguments string.
     */
    private static List<PrefixPosition> findPrefixPositions(String argsString, Prefix prefix) {
        List<PrefixPosition> positions = new ArrayList<>();
        int prefixPosition = findPrefixPosition(argsString, prefix.getPrefix(), 0);
        while (prefixPosition != -1) {
            positions.add(new PrefixPosition(prefix, prefixPosition));
            prefixPosition = findPrefixPosition(argsString, prefix.getPrefix(), prefixPosition + 1);
        }
        return positions;
    }

    /**
     * Finds the zero-based position of the prefix in the arguments string,
     * only if the prefix is at the start of the string or preceded by whitespace.
     *
     * @param argsString Arguments string of the form:
     *                   {@code preamble <prefix>value <prefix>value ...}
     * @param prefix     The prefix to find
     * @param fromIndex  The index to start the search from
     * @return Zero-based position of the prefix in the arguments string, or -1 if
     *         not found
     */
    private static int findPrefixPosition(String argsString, String prefix, int fromIndex) {
        String regex = "(?<=^|\\s)" + Pattern.quote(prefix);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(argsString);

        if (matcher.find(fromIndex)) {
            return matcher.start();
        }

        return -1;
    }

    /**
     * Extracts prefixes and their argument values, and returns an
     * {@code ArgumentMultimap} object that maps the
     * extracted prefixes to their respective arguments. Prefixes are extracted
     * based on their zero-based positions in
     * {@code argsString}.
     *
     * @param argsString      Arguments string of the form:
     *                        {@code preamble <prefix>value <prefix>value ...}
     * @param prefixPositions Zero-based positions of all prefixes in
     *                        {@code argsString}
     * @return ArgumentMultimap object that maps prefixes to their arguments
     */
    private static ArgumentMultimap extractArguments(String argsString, List<PrefixPosition> prefixPositions)
            throws ParseException {
        // Sort by start position
        prefixPositions.sort((prefix1, prefix2) -> prefix1.getStartPosition() - prefix2.getStartPosition());

        // Insert a PrefixPosition to represent the preamble
        prefixPositions.add(0, new PrefixPosition(new Prefix(""), 0));

        // Add a dummy PrefixPosition to represent the end of the string
        prefixPositions.add(new PrefixPosition(new Prefix(""), argsString.length()));

        // Map prefixes to their argument values (if any)
        ArgumentMultimap argMultimap = new ArgumentMultimap();
        for (int i = 0; i < prefixPositions.size() - 1; i++) {
            Prefix argPrefix = prefixPositions.get(i).getPrefix();
            String argValue = extractArgumentValue(argsString, prefixPositions.get(i), prefixPositions.get(i + 1));
            argMultimap.put(argPrefix, argValue);
        }

        return argMultimap;
    }

    /**
     * Returns the trimmed value of the argument in the arguments string specified
     * by {@code currentPrefixPosition}.
     * The end position of the value is determined by {@code nextPrefixPosition}.
     */
    private static String extractArgumentValue(String argsString,
                                               PrefixPosition currentPrefixPosition,
                                               PrefixPosition nextPrefixPosition) throws ParseException {
        Prefix prefix = currentPrefixPosition.getPrefix();
        if (prefix.getPrefix().isEmpty()) {
            return argsString.substring(currentPrefixPosition.getStartPosition(),
                    nextPrefixPosition.getStartPosition()).trim();
        }

        int prefixEndPos = currentPrefixPosition.getStartPosition() + prefix.getPrefix().length();
        String token = argsString.substring(prefixEndPos, nextPrefixPosition.getStartPosition());

        int spaceCount = 0;
        while (spaceCount < token.length() && token.charAt(spaceCount) == ' ') {
            spaceCount++;
        }

        if (spaceCount != 1) {
            throw new ParseException("Expected exactly one space after the prefix, "
                    + "followed by an argument: " + prefix.getPrefix());
        }

        return token.substring(spaceCount).trim();
    }

    /**
     * Represents a prefix's position in an arguments string.
     */
    private static class PrefixPosition {
        private final int startPosition;
        private final Prefix prefix;

        PrefixPosition(Prefix prefix, int startPosition) {
            this.prefix = prefix;
            this.startPosition = startPosition;
        }

        int getStartPosition() {
            return startPosition;
        }

        Prefix getPrefix() {
            return prefix;
        }
    }
}
