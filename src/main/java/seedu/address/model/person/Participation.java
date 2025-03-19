package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;

/**
 * Represents a Person's participation in BetterCallTA.
 * Guarantees: immutable; is valid as declared in {@link #isValidParticipation(String)}
 */
public class Participation {

    public static final String MESSAGE_CONSTRAINTS =
            "Participation should only be EXCELLENT, GOOD, AVERAGE, POOR or NONE.";

    public static final HashMap<String, ParticipationStatus> PARTICIPATION_MAP = new HashMap<>();

    // Initialise the PARTICIPATION_MAP
    static {
        PARTICIPATION_MAP.put("EXCELLENT", ParticipationStatus.EXCELLENT);
        PARTICIPATION_MAP.put("GOOD", ParticipationStatus.GOOD);
        PARTICIPATION_MAP.put("AVERAGE", ParticipationStatus.AVERAGE);
        PARTICIPATION_MAP.put("POOR", ParticipationStatus.POOR);
        PARTICIPATION_MAP.put("NONE", ParticipationStatus.NONE);
        PARTICIPATION_MAP.put("UNMARKED", ParticipationStatus.UNMARKED);
    }

    public final ParticipationStatus status;

    /**
     * Constructs a {@code Participation}.
     * Used when initialising a Person.
     */
    public Participation() {
        this.status = ParticipationStatus.UNMARKED;
    }

    /**
     * Constructs a {@code Participation}.
     * Used when updating a Person's participation.
     *
     * @param status A valid participation status.
     */
    public Participation(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = PARTICIPATION_MAP.get(status.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid participation status.
     */
    public static boolean isValidStatus(String test) {
        return PARTICIPATION_MAP.containsKey(test.toUpperCase());
    }

    @Override
    public String toString() {
        return this.status.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Participation)) {
            return false;
        }

        Participation otherParticipation = (Participation) other;
        return this.status == otherParticipation.status;
    }

    @Override
    public int hashCode() {
        return this.status.hashCode();
    }
}

/**
 * Possible statuses of participation
 */
enum ParticipationStatus {
    EXCELLENT, GOOD, AVERAGE, POOR, NONE, UNMARKED
}
