//@@author 17navasaw
package seedu.address.model.person.address;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents the block in Person's address.
 * Guarantees: immutable; is valid as declared in {@link #isValidBlock(String)}
 */
public class Block {

    public static final String MESSAGE_BLOCK_CONSTRAINTS =
            "Block can contain alphanumeric characters and spaces, and it should not be blank";
    private static final String BLOCK_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String value;

    public Block(String block) throws IllegalValueException {

        requireNonNull(block);
        String trimmedBlock = block.trim();

        if (!isValidBlock(trimmedBlock)) {
            throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
        }
        this.value = trimmedBlock;
    }

    /**
     * Returns true if a given string is a valid block in an address.
     */
    public static boolean isValidBlock(String test) {
        return test.matches(BLOCK_VALIDATION_REGEX);
    }
}
