/**
 * Time1
 * Represents time - hours:minutes. Coordinates cannot be negative.
 * @author Inbal Elmaleh
 * @version 20.03.2022
*/

public class Time1 {
    private int _hour;
    private int _minute;

    private final static int _MIN_VAL = 0;
    private final static int _MAX_HOUR_VAL = 23;
    private final static int _MAX_MINUTE_VAL = 59;
    private final static int _MINUTES_PER_HOUR = 60;
    private final static int _MINIMAL_DOUBLE_DIGIT_VAL = 10;
    private final static int _MINUTES_IN_A_DAY = 1440;

    // Constructors

    /**
     * Constructs a Time1 object. Construct a new time instance with the
     * specified hour and minute.
     * Hour should be between 0-23, otherwise it should be set to 0.
     * Minute should be between 0-59, otherwise it should be set to 0.
     * @param h the hour of the time
     * @param m the minute of the time
     */
    public Time1(int h, int m) {
        _hour = (_MIN_VAL <= h && h <= _MAX_HOUR_VAL) ? h : _MIN_VAL;
        _minute = (_MIN_VAL <= m && m <= _MAX_MINUTE_VAL) ? m : _MIN_VAL;
    }

    /**
     * Copy constructor for Time1. Construct a time with the same instance
     * variables as another time.
     * @param other The time object from which to construct the new time
     */
    public Time1(Time1 other) {
        setHour(other.getHour());
        setMinute(other.getMinute());
    }
    
    // Private constructor for Time1, using a minutesFromMidnight parameter
    private Time1(int minFromMidnight) {
        this(minFromMidnight / _MINUTES_PER_HOUR,
             minFromMidnight % _MINUTES_PER_HOUR);
    }

    // Getters

    /**
     * Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour() {
        return (_hour);
    }
    
    /**
     * Returns the minute of the time.
     * @return The minute of the time
     */
    public int getMinute() {
        return (_minute);
    }

    // Setters

    /**
     * Changes the hour of the time. If an illegal number is received
     * hour will be unchanged.
     * @param num The new hour
     */
    public void setHour(int num) {
        if ((_MIN_VAL <= num && num <= _MAX_HOUR_VAL)) {
            _hour = num;
        }
    }

    /**
     * Changes the minute of the time. If an illegal number is received
     * minute will be unchanged.
     * @param num The new minute
     */
    public void setMinute(int num) {
        if ((_MIN_VAL <= num && num <= _MAX_MINUTE_VAL)) {
            _minute = num;
        }
    }

    // Other Methods

    /**
     * Return a string representation of this time (hh:mm).
     * @overrides toString in class java.lang.Object
     * @return String representation of this time (hh:mm).
     */
    public String toString() {
        String time = "";
        int h = getHour(), m = getMinute();

        if (h < _MINIMAL_DOUBLE_DIGIT_VAL) {
            time += "0";
        }
        
        time += (h + ":");

        if (m < _MINIMAL_DOUBLE_DIGIT_VAL) {
            time += "0";
        };
        
        time += m;

        return (time);
    }

    /**
     * Return the amount of minutes since midnight.
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight() {
        return (getMinute() + (getHour() * _MINUTES_PER_HOUR));
    }

    /**
     * Check if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return True if the received time is equal to this time
     */
    public boolean equals(Time1 other) {
        return ((getHour() == other.getHour()) &&
                (getMinute() == other.getMinute()));
    }

    /**
     * Check if this time is before a received time.
     * @param other The time to check if this point is before
     * @return True if this time is before other time
     */
    public boolean before(Time1 other) {
        return ((getHour() < other.getHour()) ||
                ((getHour() == other.getHour()) &&
                 (getMinute() < other.getMinute())));
    }
    
    /**
     * Check if this time is after a received time.
     * @param other The time to check if this point is after
     * @return True if this time is after other time
     */
    public boolean after(Time1 other) {
        return (other.before(this));
    }

    /**
     * Calculates the difference (in minutes) between two times.
     * Assumption: this time is after other time.
     * @param other The time to check the difference to
     * @return int difference in minutes
     */
    public int difference(Time1 other) {
        return (this.minFromMidnight() - other.minFromMidnight());
    }

    /**
     * Copy current object and add requested minutes to new object.
     * @param num The minutes need to add.
     * @return new update Time1 object.
     */
    public Time1 addMinutes(int num) {

        int minutesSum = minFromMidnight() + num;

        /*
        If the new minutes total is not negative, then take the result of
        the total % _MINUTES_IN_A_DAY.
        If the total is negative, reduce it from _MINUTES_IN_A_DAY
        */ 
        int newTimeInMinutes = minutesSum >= 0 ?
                               minutesSum % _MINUTES_IN_A_DAY :
                               _MINUTES_IN_A_DAY + (minutesSum % _MINUTES_IN_A_DAY);

        return (new Time1(newTimeInMinutes));
    }
}