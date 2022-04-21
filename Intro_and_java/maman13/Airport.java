/**
 * Represents an airport object. Airport takes a city which is its location,
 * and contains up to 200 flights which are either from or to its city.
 * 
 * @author Inbal Elmaleh
 * @version 21.04.2022
*/

public class Airport {

    // --- CLASS VARIABLES AND CONSTANTS

    private Flight[] _flightsSchedule;
    private int _noOfFlights;
    private String _city;

    private final static int _MAX_FLIGHTS = 200;
    private final static int _EMPTY = 0;
    private final static int _ERROR = -1;

    // --- PUBLIC METHODS
    // Constructor

    /**
     * Constructor of Airport object.
     * @param city the city in which the airport is located
     */
    public Airport(String city) {
        setCity(city);
        setNoOfFlights(_EMPTY);
        _flightsSchedule = new Flight[_MAX_FLIGHTS];
    }

    /**
     * Add flight to airport.
     * Flight f will only be added if it is either to or from the city
     * of this airport.
     * @param f flight to add to airport.
     * @return true if f was added, false otherwise (also
     * when airport is full).
     */
    public boolean addFlight(Flight f) {

        if (isFull()) {
            return false;
        }

        boolean isAdded = false;

        // if this airport's city is the origin or dest of the flight
        if (getCity().equals(f.getOrigin()) ||
            getCity().equals(f.getDestination())) {
            
            pushFlightToArray(new Flight(f));
            isAdded = true;
        }

        return isAdded;
    }
    
    /**
     * Remove flight from airport.
     * If found in airport, flight f will be removed from it.
     * @param f flight to remove from airport.
     * @return true if f was removed from airport, false otherwise (also
     * when airport is empty).
     */
    public boolean removeFlight(Flight f) {

        if (isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        // if flight is in array, remove
        for (int i = 0; i < getNoOfFlights(); ++i) {
            if (f.equals(_flightsSchedule[i])) {
                removeFlightFromArray(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }
    
    /**
     * Get the time of the first flight from the city indicated in place argument.
     * @param place the city to find the time of the earliest flight from.
     * @return Time1 object representing the time of the first flight from place.
     *  If airport is empty, null is returned.
     */
    public Time1 firstFlightFromOrigin(String place) {

        if (isEmpty()) {
            return null;
        }
        
        Time1 earliestTime = null;

        for (int i = 0; i < getNoOfFlights(); ++i) {

            Flight currFlight = _flightsSchedule[i];

            // if flight origin equals place
            if (currFlight.getOrigin().equals(place)) {

                /* if current flight is earlier than a flight found previously,
                or if this is the first iteration and earliestTime is still null */
                if ((earliestTime != null &&
                    currFlight.getDeparture().before(earliestTime)) || // Time1 API exposed
                    earliestTime == null) {
                    
                    earliestTime = currFlight.getDeparture();
                }
            }
        }

        return (earliestTime);
    }
    
    /**
     * 
     * @overrides toString in class java.lang.Object.
     * @return string representation of Airport.
     * If airport is empty, null is returned.
     */
    public String toString() {

        if (isEmpty()) {
            return (null);
        }
        
        String str = String.format("The flights for airport %s today are:\n",
                                   getCity()); 
        
        for (int i = 0; i < getNoOfFlights(); ++i) {
            str += (_flightsSchedule[i].toString() + "\n");
        }
        
        return (str);
    }
    
    /**
     * Count the number of full flights in airport.
     * @return the number of full flights in airport.
     * If airport is empty, 0 is returned.
     */
    public int howManyFullFlights() {
        
        if (isEmpty()) {
            return 0;
        }

        int nofFullFlights = 0;

        for (int i = 0; i < getNoOfFlights(); ++i) {
            if (_flightsSchedule[i].getIsFull()) {
                ++nofFullFlights;
            }
        }

        return nofFullFlights;
    }
    
    /**
     * Count the number of flights from aiport to city or vice versa.
     * @param city the city to check for flights to or from.
     * @return the number of flights betweet aiport and city.
     * If airport is empty, 0 is returned.
     */
    public int howManyFlightsBetween(String city) {

        if (isEmpty()) {
            return 0;
        }
        
        int nofFlightsBetween = 0;
        
        /* Checking if city is origin or dest, for each flight.
        Since airport only contains flights to or from _city, 
        no need to check again for that condition. */
        for (int i = 0; i < getNoOfFlights(); ++i) {
            Flight currFlight = _flightsSchedule[i];

            if ((city.equals(currFlight.getDestination())) ||
                (city.equals(currFlight.getOrigin()))) {
                
                    ++nofFlightsBetween;
            }
        }

        return nofFlightsBetween;
    }
    
    /**
     * Find the city which is the most popular destination in all of airport's
     * flights.
     * @return the city which is the most popular destination of all flights
     * currently in airport. If airport is empty, null is returned.
     */
    public String mostPopularDestination() {
        
        // If airport has 0 flights
        if (isEmpty()) {
            return (null);
        }

        // If airport has 1 flight
        if (getNoOfFlights() == 1) {
            return (_flightsSchedule[0].getDestination());
        }

        // If airport has 2 or more flights
        String allDestString = createDestinationsStr();

        // using destinations str to count occurences of each destination
        int currMaxOccurences = 0;
        String currMostPopular = null;

        for (int i = 0; i < getNoOfFlights(); ++i) {

            String currDest = _flightsSchedule[i].getDestination();
            int currOccurencesCount = countOccurencesInStr(allDestString, currDest);

            /* if current destination's count > last documeneted max,
            update current max values */
            if (currOccurencesCount > currMaxOccurences) {
                currMostPopular = currDest;
                currMaxOccurences = currOccurencesCount;
            }
        }

        return currMostPopular;
    }
    
    /**
     * Find the flight with the most expensive ticket price currently in airport.
     * @return the flight with the most expensive ticket price currently
     * in airport. If airport is empty, null is returned.
     */
    public Flight mostExpensiveTicket() {

        if (isEmpty()) {
            return null;
        }   

        Flight mostExpensive = null;
        int MaxPrice = 0;

        for (int i = 0; i < getNoOfFlights(); ++i) {

            Flight currFlight = _flightsSchedule[i];
            int currPrice = currFlight.getPrice();

            /*
            If current flight's ticket price > current max values,
            update max values
            */
            if (currPrice > MaxPrice) {
                mostExpensive = currFlight;
                MaxPrice = currPrice;
            }
        }

        return (new Flight(mostExpensive));
    }
    
    /**
     * Find the longest flight (in duration) currenly in airport
     * @return longest flight currenly in airport.
     * If airport is empty, null is returned.
     */
    public Flight longestFlight() {
        
        if (isEmpty()) {
            return null;
        }

        Flight longestFlight = null;
        int maxDuration = 0;

        for (int i = 0; i < getNoOfFlights(); ++i) {

            Flight currFlight = _flightsSchedule[i];
            int currDuration = currFlight.getFlightDuration();
            
            /*
            If current flight's duration > current max values,
            update max values
            */
            if (currDuration > maxDuration) {
                longestFlight = currFlight;
                maxDuration = currDuration;
            }
        }

        return (new Flight(longestFlight));
    }


    // --- PRIVATE METHODS
    
    // Get airport's city
    private String getCity() {
        return (_city);
    }

    // Set airport's city
    private void setCity(String city) {
        _city = city;
    }
    
    // Get the number of flights in airport
    private int getNoOfFlights() {
        return (_noOfFlights);
    }

    // Set the number of flights in airport
    private void setNoOfFlights(int noOfFlights) {
        _noOfFlights = noOfFlights;
    }

    // Check if airport is empty
    private boolean isEmpty() {
        return (_noOfFlights == _EMPTY);
    }

    // Check if airport is full
    private boolean isFull() {
        return (_noOfFlights == _MAX_FLIGHTS);
    }
    
    // Decrement the flight counter
    private void decFlightCounter() {
        --_noOfFlights;
    }

    // Increment the flight counter
    private void incFlightCounter() {
        ++_noOfFlights;
    }

    // Add a flight to the flights achedule array
    private void pushFlightToArray(Flight f) {
        _flightsSchedule[getNoOfFlights()] = f;
        incFlightCounter();
    }

    /*
    Remove a flight from the flights schedule array.
    Moves the last flight in the array to the position of the removed flight,
    to prevent holes in the array.
    */
    private void removeFlightFromArray(int index) {
        _flightsSchedule[index] = _flightsSchedule[getNoOfFlights() - 1];
        _flightsSchedule[getNoOfFlights() - 1] = null;
        decFlightCounter();
    }

    // Create a string of all destinations in airport, separated by " "
    private String createDestinationsStr() {
        
        String allDestString = _flightsSchedule[0].getDestination();
        
        for (int i = 1; i < getNoOfFlights(); ++i) {
            allDestString += " " + _flightsSchedule[i].getDestination();
        }

        return (allDestString);
    }

    /*
    Counts the number of occurences of string substr in string s.
    Loops over s using java.lang.String.indexOf(substr).
    */
    private int countOccurencesInStr(String s, String substr) {
        int nofOccurences = 0, currIndex = 0;

        // get the start index of first occurence of substr in s
        currIndex = s.indexOf(substr, currIndex);

        // while there is an occurence of substr
        while (currIndex != _ERROR) {
            ++nofOccurences;

            // move forward in s to the next occurence of substr
            currIndex = s.indexOf(substr, currIndex + substr.length());
        }
        
        return nofOccurences;
    }
}