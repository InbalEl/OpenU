/**
 * Time2
 * Represents time - hours:minutes. Values must represent a proper time.
 * @author Inbal Elmaleh
 * @version 20.03.2022
*/

public class Time2 {
    private int _minFromMid;

    private final static int _MIN_VAL = 0;
    private final static int _MAX_HOUR_VAL = 23;
    private final static int _MAX_MINUTES_VAL = 59;
    private final static int _MINUTES_PER_HOUR = 60;
    private final static int _MINIMAL_DOUBLE_DIGIT_VAL = 10;
    private final static int _MINUTES_IN_A_DAY = 1440;

    // Constructors

    /**
     * Constructs a Time2 object. Construct a new time instance with the
     * specified hour and minute.
     * Hour should be between 0-23, otherwise it should be set to 0.
     * Minute should be between 0-59, otherwise they should be set to 0.
     * @param h hour
     * @param m minute
     */
    public Time2(int h, int m) {

        h = (_MIN_VAL <= h && h <= _MAX_HOUR_VAL) ? h : _MIN_VAL;
        m = (_MIN_VAL <= m && m <= _MAX_MINUTES_VAL) ? m : _MIN_VAL;

        _minFromMid = h * _MINUTES_PER_HOUR + m;
    }

    /**
     * Copy constructor for Time2. Constructs a time with the same
     * variables as another time.
     * @param other The time object from which to construct the new time


     */
    public Time2(Time2 other) {
        _minFromMid = other.minFromMidnight();
    }

    // Private constructor for Time1, using a minutesFromMidnight parameter
    private Time2(int minFromMidnight) {
        _minFromMid = (_MIN_VAL <= minFromMidnight &&
                       minFromMidnight <= _MINUTES_IN_A_DAY) ?
                      minFromMidnight :
                      _MIN_VAL;
    }

    // Getters

    /**
     * Returns the hour of the time.
     * @return The hour of the time
     */
    public int getHour() {
        return (minFromMidnight() / _MINUTES_PER_HOUR);
    }

    /**
     * Returns the minute of the time.
     * @return The minute of the time
     */
    public int getMinute() {
        return (minFromMidnight() % _MINUTES_PER_HOUR);
    }

    // Setters

    /**
     * Changes the hour of the time. If an illegal number is received
     * hour will remain unchanged.
     * @param num The new hour
     */
    public void setHour(int num) {
        if (_MIN_VAL <= num && num <= _MAX_HOUR_VAL) {
            setMinuteFromMid((num * _MINUTES_PER_HOUR) + getMinute());
        }
    }

    /**
     * Changes the minute of the time. If an illegal number is received
     * minute will remain unchanged.
     * @param num The new minute
     */
    public void setMinute(int num) {
        if (_MIN_VAL <= num && num <= _MAX_MINUTES_VAL) {
            setMinuteFromMid(minFromMidnight() - getMinute() + num);
        }
    }

    // private setter for _minFromMid
    private void setMinuteFromMid(int num) {
        _minFromMid = num;
    }

    // Other Methods

    /**
     * Returns a string representation of this time(hh:mm).
     * @overrides toString in class java.lang.Object
     * @return String representation of this time(hh:mm).
     */
    public String toString() {
        int h = getHour(), m = getMinute();

        String time = "";
        if (h < _MINIMAL_DOUBLE_DIGIT_VAL) {
            time += "0";
        }

        time += (h + ":");
    
        if (m < _MINIMAL_DOUBLE_DIGIT_VAL) {
            time += "0";
        }

        time += m;

        return (time);
    }

    /**
     * Return the amount of minutes since midnight.
     * @return amount of minutes since midnight.
     */
    public int minFromMidnight() {
        return (_minFromMid);
    }
    
    /**
     * Checks if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return True if the received time is equal to this time
     */
    public boolean equals(Time2 other) {
        return (minFromMidnight() == other.minFromMidnight());
    }
    
    /**
     * Checks if this time is before a received time.
     * @param other The time to check if this time is before
     * @return True if this time is before other time
     */
    public boolean before(Time2 other) {
        return (minFromMidnight() < other.minFromMidnight());
    }
    
    /**
     * Checks if this time is after a received time.
     * @param other The time to check if this time is after
     * @return True if this time is after other time
     */
    public boolean after(Time2 other) {
        return (other.before(this));
    }

    /**
     * Calculates the difference (in minutess) between two times.
     * @param other The time to check the difference with.
     * Assumption: this time is after other time
     * @return int difference in minutes
     */
    public int difference(Time2 other) {
        return (minFromMidnight() - other.minFromMidnight());
    }

    /**
     * Copy current object and add requested minutes to new object.
     * @param num The minutes need to add.
     * @return new update Time2 object.
     */
    public Time2 addMinutes(int num) {
        
        int minutesSum = minFromMidnight() + num;
        
        /*
        If the new minutes total is not negative, then take the result of
        the total % _MINUTES_IN_A_DAY.
        If the total is negative, reduce it from _MINUTES_IN_A_DAY
        */ 
        int newTimeInMinutes = minutesSum >= 0 ?
                               minutesSum % _MINUTES_IN_A_DAY :
                               _MINUTES_IN_A_DAY + (minutesSum % _MINUTES_IN_A_DAY);

        return (new Time2(newTimeInMinutes));
    }
}