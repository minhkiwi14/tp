package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy =
                new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword in name
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords in name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword in name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords in name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_idContainsKeywords_returnsTrue() {
        // One keyword in ID
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("A123"));
        // Using a valid ID of the form AXXXXXXX[B, J or U]
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567B").build()));

        // Mixed-case keyword in ID
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("a123"));
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567B").build()));
    }

    @Test
    public void test_courseContainsKeywords_returnsTrue() {
        // One keyword in course
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Science"));
        assertTrue(predicate.test(new PersonBuilder().withCourse("Computer Science").build()));

        // Mixed-case keyword in course
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("cOMPuTER"));
        assertTrue(predicate.test(new PersonBuilder().withCourse("Computer Science").build()));
    }

    @Test
    public void test_personDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withId("A1234567B")
                .withCourse("Engineering")
                .build()));

        // Non-matching keyword for name, id and course
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Nonexistent"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice Bob")
                .withId("A1234567B")
                .withCourse("Computer Science")
                .build()));

        // Keywords that match other fields (phone, email) but not name, id, or course.
        // Adjusted id so that it does not contain the substrings from phone or email.
        predicate = new NameContainsKeywordsPredicate(
                Arrays.asList("12345", "alice@email"));
        assertFalse(predicate.test(new PersonBuilder()
                .withName("Alice")
                .withId("A7654321B")
                .withCourse("Computer Science")
                .withPhone("12345")
                .withEmail("alice@email")
                .build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(keywords);
        String expected = new ToStringBuilder(predicate)
                .add("keywords", keywords)
                .toString();
        assertEquals(expected, predicate.toString());
    }
}
