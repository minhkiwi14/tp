package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts the filtered person list based on the specified criteria.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons by the specified field.\n"
            + "Parameters: /by FIELD\n"
            + "FIELD can be: name | grade | attendance | participation\n"
            + "Example: sort /by grade";

    public static final String MESSAGE_SUCCESS = "Persons sorted by %s.";

    private final SortDescriptor descriptor;

    /**
     * Creates a SortCommand with the given sort descriptor.
     *
     * @param descriptor The descriptor indicating which field to sort by.
     */
    public SortCommand(SortDescriptor descriptor) {
        requireNonNull(descriptor);
        this.descriptor = descriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            requireNonNull(model);
            Comparator<Person> comparator = descriptor.getComparator();
            if (!descriptor.isSortByName() && !descriptor.isSortByParticipation()) {
                comparator = comparator.reversed();
            }
            model.sortFilteredPersonList(comparator);
            return new CommandResult(String.format(MESSAGE_SUCCESS, descriptor.getSortFieldName()));
        } catch (Exception e) {
            throw new CommandException("Sort failed: " + e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortCommand
                && ((SortCommand) other).descriptor.equals(this.descriptor));
    }

    @Override
    public int hashCode() {
        return descriptor.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getCanonicalName() + "{descriptor=" + descriptor + "}";
    }

    /**
     * Stores which field to sort by and provides the appropriate comparator.
     */
    public static class SortDescriptor {

        private static final Map<String, Integer> ATTENDANCE_PRIORITY;
        private static final Map<String, Integer> PARTICIPATION_PRIORITY;

        static {
            ATTENDANCE_PRIORITY = new HashMap<>();
            ATTENDANCE_PRIORITY.put("PRESENT", 3);
            ATTENDANCE_PRIORITY.put("EXCUSED", 2);
            ATTENDANCE_PRIORITY.put("ABSENT", 1);
            ATTENDANCE_PRIORITY.put("UNMARKED", 0);

            PARTICIPATION_PRIORITY = new HashMap<>();
            PARTICIPATION_PRIORITY.put("EXCELLENT", 0);
            PARTICIPATION_PRIORITY.put("GOOD", 1);
            PARTICIPATION_PRIORITY.put("AVERAGE", 2);
            PARTICIPATION_PRIORITY.put("POOR", 3);
            PARTICIPATION_PRIORITY.put("NONE", 4);
            PARTICIPATION_PRIORITY.put("UNMARKED", 5);
        }

        private boolean sortByName;
        private boolean sortByGrade;
        private boolean sortByAttendance;
        private boolean sortByParticipation;

        public void setSortByName() {
            reset();
            this.sortByName = true;
        }

        public void setSortByGrade() {
            reset();
            this.sortByGrade = true;
        }

        public void setSortByAttendance() {
            reset();
            this.sortByAttendance = true;
        }

        public void setSortByParticipation() {
            reset();
            this.sortByParticipation = true;
        }

        public boolean isSortByName() {
            return sortByName;
        }

        public boolean isSortByParticipation() {
            return sortByParticipation;
        }

        /**
         * Returns a comparator to sort persons based on the active flag.
         *
         * @return A comparator based on the selected sort field.
         */
        public Comparator<Person> getComparator() {
            if (sortByName) {
                return Comparator.comparing((Person p) -> p.getName() != null ? p.getName().toString().toLowerCase()
                        : "", String.CASE_INSENSITIVE_ORDER).thenComparing(
                                p -> p.getName() != null ? p.getName().toString() : "");
            } else if (sortByGrade) {
                return Comparator.comparingInt(p -> p.getGrade() != null ? p.getGrade().grade : Integer.MAX_VALUE);
            } else if (sortByAttendance) {
                return Comparator.comparingInt(p -> {
                    String status = p.getAttendance() != null ? p.getAttendance().getStatus() : "UNMARKED";
                    return ATTENDANCE_PRIORITY.getOrDefault(status, Integer.MAX_VALUE);
                });
            } else if (sortByParticipation) {
                return Comparator.comparingInt(p -> {
                    String status = p.getParticipation() != null ? p.getParticipation().toString() : "UNMARKED";
                    return PARTICIPATION_PRIORITY.getOrDefault(status, Integer.MAX_VALUE);
                });
            } else {
                throw new IllegalStateException("No sort field set.");
            }
        }

        public String getSortFieldName() {
            if (sortByName) {
                return "name";
            }
            if (sortByGrade) {
                return "grade";
            }
            if (sortByAttendance) {
                return "attendance";
            }
            if (sortByParticipation) {
                return "participation";
            }
            return "unknown";
        }

        private void reset() {
            this.sortByName = false;
            this.sortByGrade = false;
            this.sortByAttendance = false;
            this.sortByParticipation = false;
        }

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof SortDescriptor)) {
                return false;
            }
            SortDescriptor o = (SortDescriptor) other;
            return this.sortByName == o.sortByName
                    && this.sortByGrade == o.sortByGrade
                    && this.sortByAttendance == o.sortByAttendance
                    && this.sortByParticipation == o.sortByParticipation;
        }

        @Override
        public int hashCode() {
            return Boolean.hashCode(sortByName)
                    + Boolean.hashCode(sortByGrade)
                    + Boolean.hashCode(sortByAttendance)
                    + Boolean.hashCode(sortByParticipation);
        }

        @Override
        public String toString() {
            return getClass().getCanonicalName()
                    + "{sortByName=" + sortByName
                    + ", sortByGrade=" + sortByGrade
                    + ", sortByAttendance=" + sortByAttendance
                    + ", sortByParticipation=" + sortByParticipation + "}";
        }
    }
}
