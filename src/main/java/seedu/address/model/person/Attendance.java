package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.HashMap;

/**
 * Represents a Person's attendance in BetterCallTA.
 * Guarantees: immutable; is valid as declared in {@link #isValidAttendance(String)}
 */
public class Attendance {

    public static final String statusPresentMessage = "Present for Tutorial";
    public static final String statusAbsentMessage = "Absent for Tutorial";
    public static final String statusExcusedMessage = "Excused with Valid Reason";
    
    public static final String MESSAGE_CONSTRAINTS =
            "Attendance should only be PRESENT, ABSENT or EXCUSED.";
    
    public static final HashMap<String, AttendanceStatus> attendanceMap = new HashMap<>();

    // Initialise the attendanceMap
    static {
        attendanceMap.put("PRESENT", AttendanceStatus.PRESENT);
        attendanceMap.put("ABSENT", AttendanceStatus.ABSENT);
        attendanceMap.put("EXCUSED", AttendanceStatus.EXCUSED);
        attendanceMap.put("UNMARKED", AttendanceStatus.UNMARKED);
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
        this.status = attendanceMap.get(status.toUpperCase());
    }

    /**
     * Returns true if a given string is a valid attendance status.
     */
    public static boolean isValidStatus(String test) {
        return attendanceMap.containsKey(test.toUpperCase());
    }

    @Override
    public String toString() {
        switch (this.status) {
        case PRESENT:
            return statusPresentMessage;
        case ABSENT:
            return statusAbsentMessage;
        case EXCUSED:
            return statusExcusedMessage;
        case UNMARKED:
            return "";
        default:
            assert false : "Unreachable code";
            return null;
        }
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
