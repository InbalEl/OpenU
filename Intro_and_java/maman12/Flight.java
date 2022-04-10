/**
 * Represents a flight. A Flight object is represented by the flight's origin,
 * destination,departure time, flight duration, no of passengers,if it is full
 * and the price.
 * @author Inbal Elmaleh
 * @version 06.04.2022
*/

public class Flight {
    private String _origin;
    private String _destination;
    private Time1 _departure;
    private int _flightDuration;
    private int _noOfPassengers;
    private boolean _isFull;
    private int _price;

    private final static int _MAX_CAPACITY = 250;
    private final static int _DEF_VAL = 0;

    // Constructors

    /**
     * Constructor for a Flight object.
     * If the number of passengers exceeds the maximum capacity,
     * the number of passengers will be set to the maximum capacity.
     * If the number of passengers is negative, the number of
     * passengers will be set to zero.
     * If the flight duration is negative the flight duration
     * will be set to zero.
     * If the price is negative the price will be set to zero.
     * @param origin The city the flight leaves from.
     * @param dest The city the flight lands at.
     * @param depHour the departure hour (should be between 0-23).
     * @param depMinute The departure minute (should be between 0-59).
     * @param durTimeMinutes The duration time in minutes
     * (should not be negative).
     * @param noOfPass The number of passengers
     * (should be between 0-maximum capacity).
     * @param price The price (should not be negative).
     */
    public Flight(String origin, String dest, int depHour, int depMinute,
                  int durTimeMinutes, int noOfPass, int price) {

        setOrigin(new String(origin));
        setDestination(new String(dest));
        setDeparture(new Time1(depHour, depMinute));
        setFlightDuration(durTimeMinutes < _DEF_VAL ? _DEF_VAL : durTimeMinutes);
        setPrice(price < _DEF_VAL ? _DEF_VAL : price);

        if (noOfPass > _MAX_CAPACITY) {
            setNoOfPassengers(_MAX_CAPACITY);
        } else if (noOfPass < _DEF_VAL) {
            setNoOfPassengers(_DEF_VAL);
        } else {
            setNoOfPassengers(noOfPass);
        }

        setIsFull();
    }
    
    /**
     * Copy constructor for a Flight object. Construct a Flight object
     * with the same attributes as another Flight object.
     * @param other The Flight object from which to construct the new Flight.
     */
    public Flight(Flight other) {
        setOrigin(other.getOrigin());
        setDestination(other.getDestination());
        setDeparture(other.getDeparture());
        setFlightDuration(other.getFlightDuration());
        setNoOfPassengers(other.getNoOfPassengers());
        setPrice(other.getPrice());
        setIsFull();
    }

    // Getters
    
    /**
     * Returns the flight origin.
     * @return The flight origin.
     */
    public String getOrigin() {
        return (new String(_origin));
    }
    
    /**
     * Returns the flight destination.
     * @return The flight destination.
     */
    public String getDestination() {
        return (new String(_destination));
    }

    /**
     * Returns the flight departure time.
     * @return A copy of the flight departure time.
     */
    public Time1 getDeparture() {
        return (new Time1(_departure));
    }
    
    /**
     * Returns the flight duration time in minutes.
     * @return The flight duration.
     */
    public int getFlightDuration() {
        return (_flightDuration);
    }

    /**
     * Returns the number of passengers on the flight.
     * @return The number of passengers.
     */
    public int getNoOfPassengers() {
        return (_noOfPassengers);
    }

    /**
     * Returns whether the flight is full or not.
     * @return True if the flight is full.
     */
    public boolean getIsFull() {
        return (_isFull);
    }

    /**
     * Returns the price of the flight .
     * @return The price.
     */
    public int getPrice() {
        return (_price);
    }

    /**
     * Returns the arrival time of the flight.
     * @return The arrival time of this flight.
     */
    public Time1 getArrivalTime() {
        return (getDeparture().addMinutes(getFlightDuration()));
    }

    // Setters

    /**
     * Changes the flight's departure time.
     * @param departureTime The flight's new departure time.
     */
    public void setDeparture(Time1 departureTime) {
        _departure = new Time1(departureTime);
    }

    /**
     * Changes the flight's origin.
     * @param origin The flight's new origin.
     */
    public void setOrigin(String origin) {
        _origin = origin;
    }

    /**
     * Changes the flight's destination.
     * @param dest The flight's new destination.
     */
    public void setDestination(String dest) {
        _destination = dest;
    }
    
    /**
     * Changes the flight's duration time.
     * If the parameter is negative the duration time will
     * remain unchanged.
     * @param durTimeMinutes The flight's new duration time.
     */
    public void setFlightDuration(int durTimeMinutes) {
        if (durTimeMinutes >= _DEF_VAL) {
            _flightDuration = durTimeMinutes;
        }
    }
    
    /**
     * Changes the number of passengers.
     * If the parameter is negative or larger than the maximum
     * capacity the number of passengers will remain unchanged.
     * @param noOfPass The new number of passengers.
     */
    public void setNoOfPassengers(int noOfPass) {
        if (_DEF_VAL <= noOfPass && noOfPass <= _MAX_CAPACITY) {
            _noOfPassengers = noOfPass;
            setIsFull();
        }
    }

    /**
     * Changes the flight price.
     * If the parameter is negative the price will remain unchanged.
     * @param price The new price.
     */
    public void setPrice(int price) {
        if (price >= _DEF_VAL) {
            _price = price;
        }
    }

    // set _isfull flag according to current number of passengers
    private void setIsFull() {
        _isFull = (getNoOfPassengers() == _MAX_CAPACITY);
    }

    // Other Methods

    /**
     * Check if the received flight is equal to this flight.
     * Flights are considered equal if the origin, destination
     * and departure times are the same.
     * @param other The flight to be compared with this flight.
     * @return True if the received flight is equal to this flight.
     */
    public boolean equals(Flight other) {
        return ((getOrigin().equals(other.getOrigin())) &&
                (getDestination().equals(other.getDestination())) &&
                (getDeparture().equals(other.getDeparture())));
    }
    
    /**
     * Add passengers to this flight. If the number of passengers
     * exceeds he maximum capacity, no passengers are added and
     * false is returned. If the flight becomes full, the boolean
     * attribute describing whether the flight if full becomes true.
     * @param num The number of passengers to be added to this flight.
     * @return True if the passengers were added to the flight.
     */
    public boolean addPassengers(int num) {
        boolean isAdded = false;

        // if there is space in the flight, and if sun doesn't excede max 
        if (!getIsFull() && (num <= (_MAX_CAPACITY - getNoOfPassengers()))) {
                setNoOfPassengers(getNoOfPassengers() + num);
                setIsFull();
                isAdded = true;
        }

        return (isAdded);
    }
    
    /**
     * Check if this flight is cheaper than another flight.
     * @param other The flight whose price is to be compared with this flight's price.
     * @return True if this flight is cheaper than the received flight.
     */
    public boolean isCheaper(Flight other) {
        return (getPrice() < other.getPrice());
    }
    
    /**
     * Calculate the total price of the flight.
     * @return The total price of the flight.
     */
    public int totalPrice() {
        return (getNoOfPassengers() * getPrice());
    }
    
    /**
     * Check if this flight lands before another flight.
     * Note - the flights may land on different days,
     * the method checks which flight lands first.
     * @param other The flight whose arrival time to be compared with this
     * flight's arrival time.
     * @return True if this flight arrives before the received flight.
     */
    public boolean landsEarlier(Flight other) {
        Time1 thisArrivalTime = getDeparture().addMinutes(getFlightDuration());
        Time1 otherArrivalTime = other.getDeparture().addMinutes(other.getFlightDuration());

        return (thisArrivalTime.before(otherArrivalTime));
    }
    
    /**
     * Return a string representation of this flight (for example:
     * "Flight from London to Paris departs at 09:24.Flight is full.").
     * @overrides toString in class java.lang.Object
     * @return String representation of this flight (for example:
     * "Flight from London to Paris departs at 09:24.Flight is full.").
     */
    public String toString() {
        String flight = "Flight from " + getOrigin() + " to " + getDestination()
         + " departs at " + getDeparture() + ". ";
        flight += getIsFull() ? "Flight is full." : "Flight is not full.";

        return (flight);
    }
}
