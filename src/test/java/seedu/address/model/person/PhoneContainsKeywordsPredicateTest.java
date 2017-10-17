package seedu.address.model.person;

import org.junit.Test;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PhoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("85355255");
        List<String> secondPredicateKeywordList = Arrays.asList("8535", "5255");

        PhoneContainsKeywordsPredicate firstPredicate = new PhoneContainsKeywordsPredicate(firstPredicateKeywordList);
        PhoneContainsKeywordsPredicate secondPredicate = new PhoneContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy = new EmailContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One email domain
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Collections.singletonList("gmail"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("85355255").build()));

        // Multiple email domains
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("gmail", "yahoo"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("85355255").build()));

        // Mixed-case keywords
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("GmAil", "YAhoo"));
        assertTrue(predicate.test(new PersonBuilder().withPhone("85355255").build()));

    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Zero phone numbers
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withPhone("85355255").build()));

        // phone with 3 digits
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("853"));
        assertFalse(predicate.test(new PersonBuilder().withPhone("85355255").build()));

        // Keywords match email, name and address, but does not match phone
        predicate = new PhoneContainsKeywordsPredicate(Arrays.asList("alice@gmail.com", "Alice", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}