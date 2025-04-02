package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;

/**
 * Represents a Person's attendance in BetterCallTA.
 * Guarantees: immutable; is valid as declared in {@link #isValidAttendance(String)}
 */
public class Attendance {

    public static final String STATUS_PRESENT_MESSAGE = "Present for Tutorial";
    public static final String STATUS_ABSENT_MESSAGE = "Absent for Tutorial";
    public static final String STATUS_EXCUSED_MESSAGE = "Excused with Valid Reason";
    public static final String STATUS_UNMARKED_MESSAGE = "Unmarked";

    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only be PRESENT, ABSENT or EXCUSED.";

    public static final HashMap<String, AttendanceStatus> ATTENDANCE_MAP = new HashMap<>();

    // Initialise the ATTENDANCE_MAP
    static {
        ATTENDANCE_MAP.put("PRESENT", AttendanceStatus.PRESENT);
        ATTENDANCE_MAP.put("ABSENT", AttendanceStatus.ABSENT);
        ATTENDANCE_MAP.put("EXCUSED", AttendanceStatus.EXCUSED);
        ATTENDANCE_MAP.put("UNMARKED", AttendanceStatus.UNMARKED);
    }

    public final AttendanceStatus status;

    /**
     * Constructs a {@code Attendance}.
     * Used when initialising a Person.
     */
    public Attendance() {
        this.status = AttendanceStatus.UNMARKED;
    }

    /**
     * Constructs a {@code Attendance}.
     * Used when updating a Person's attendance.
     *
     * @param status A valid attendance status.
     */
    public Attendance(String status) {
        requireNonNull(status);
        checkArgument(isValidStatus(status), MESSAGE_CONSTRAINTS);
        this.status = ATTENDANCE_MAP.get(status.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid attendance status.
     */
    public static boolean isValidStatus(String test) {
        return ATTENDANCE_MAP.containsKey(test.toUpperCase());
    }

    @Override
    public String toString() {
        switch (this.status) {
        case PRESENT:
            return STATUS_PRESENT_MESSAGE;
        case ABSENT:
            return STATUS_ABSENT_MESSAGE;
        case EXCUSED:
            return STATUS_EXCUSED_MESSAGE;
        case UNMARKED:
            return STATUS_UNMARKED_MESSAGE;
        default:
            assert false : "Unreachable code";
            return null;
        }
    }

    /**
     * Returns the String representation of the attendance status.
     */
    public String getStatus() {
        return status.name();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return this.status == otherAttendance.status;
    }

    @Override
    public int hashCode() {
        return this.status.hashCode();
    }
}

/**
 * Possible statuses of attendance.
 */
enum AttendanceStatus {
    PRESENT, ABSENT, EXCUSED, UNMARKED
}
