import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class AirportTest 
{
    Airport ap1 = new Airport(new String("Tel-Aviv"));
	Airport ap2 = new Airport(new String("Mombasa"));

	@Test
	public void testAddFlightAndPrint()
	{
		String expected = null;
		assertEquals(expected, ap1.toString());

		// creating flights
		Flight flight1 = new Flight(new String("Tel-Aviv"),
									new String("London"),12,0,210,250,100);
		Flight flight2 = new Flight(new String("New York"),
									new String("Tel-Aviv"),10,50,210,250,150);
		Flight flight3 = new Flight(new String("New York"),
									new String("Barcelona"),18,20,750,300,550);
		
		// adding flights
		assertTrue(ap1.addFlight(flight1));
		assertTrue(ap1.addFlight(flight2));
		assertFalse(ap1.addFlight(flight3));
		
		expected = "The flights for airport Tel-Aviv today are:\nFlight from Tel-Aviv to London departs at 12:00. Flight is full.\nFlight from New York to Tel-Aviv departs at 10:50. Flight is full.\n";
		assertEquals(expected, ap1.toString());
		
		// aliasing
		flight1 = null;
		System.gc();
		assertEquals(expected, ap1.toString());
	}

	@Test
	public void testRemoveFlight()
	{        
		Flight flight1 = new Flight(new String("Tel-Aviv"),
									new String("Lisbon"),17,45,340,180,90);
		assertFalse(ap1.removeFlight(flight1));

		assertTrue(ap1.addFlight(flight1));
		assertTrue(ap1.removeFlight(flight1));
	}
    
	@Test
	public void testFirstFlightFromOrigin() 
	{
		Time1 t1 = new Time1(0, 15);
		Time1 t2 = new Time1(14, 55);
		Time1 t3 = new Time1(11, 25);

		Flight flight1 = new Flight(new String("Tel-Aviv"),
									new String("Athens"),t1.getHour(),t1.getMinute(),90,250,30);
		Flight flight2 = new Flight(new String("Athens"),
									new String("Tel-Aviv"),t2.getHour(),t2.getMinute(),110,250,40);
		Flight flight3 = new Flight(new String("Athens"),
									new String("Tel-Aviv"),t3.getHour(),t3.getMinute(),190,180,95);
		ap1.addFlight(flight1);
		ap1.addFlight(flight2);
		
		// cheeck for city that's in schedule once
		assertTrue(t2.equals(ap1.firstFlightFromOrigin("Athens")));
		
		// cheeck for city that's in schedule more than once
		ap1.addFlight(flight3);		
		assertTrue(t3.equals(ap1.firstFlightFromOrigin("Athens")));
		
		// cheeck for city that's not in schedule as origin but as dest
		assertNull(ap1.firstFlightFromOrigin("London"));
		
		// cheeck for city that's not in schedule at all
		assertNull(ap1.firstFlightFromOrigin("Lagos"));
				
		// test for empty schedule
		assertNull(ap2.firstFlightFromOrigin("Mombasa"));
	}

	@Test
	public void testHowManyFullFlights()
	{
		// test for empty schedule
		assertEquals(0, ap2.howManyFullFlights());

		Flight flight1 = new Flight(new String("Tel-Aviv"),
									new String("Athens"),0,15,90,250,30);
		Flight flight2 = new Flight(new String("Athens"),
									new String("Tel-Aviv"),14,55,110,250,40);
		Flight flight3 = new Flight(new String("Athens"),
									new String("Tel-Aviv"),11,25,190,180,95);
		ap1.addFlight(flight1);
		ap1.addFlight(flight2);
		ap1.addFlight(flight3);

		// test for a schedule with flights
		assertEquals(2, ap1.howManyFullFlights());
	}
    
    
	@Test
	public void testHowManyFlightsBetween() 
	{
        // test for empty schedule
		assertEquals(0, ap1.howManyFlightsBetween("Capetown"));
        
		// test with no flights between, without city
		Flight flight1 = new Flight(new String("Tel-Aviv"),
									new String("Athens"),0,15,90,250,30);
		ap1.addFlight(flight1);
		assertEquals(0, ap1.howManyFlightsBetween("Capetown"));
		
		// test with one flight between, flip sides
		assertEquals(1, ap1.howManyFlightsBetween("Athens"));

		Flight flight2 = new Flight(new String("Bologna"),
									new String("Tel-Aviv"),0,15,90,250,30);
		ap1.addFlight(flight2);
		assertEquals(1, ap1.howManyFlightsBetween("Bologna"));

        // test with multiple flights, and with origin and dest appearing separately in other flights as well
		Flight flight3 = new Flight(new String("Tel-Aviv"),
									new String("Bologna"),0,15,90,250,30);
		ap1.addFlight(flight3);

		assertEquals(2, ap1.howManyFlightsBetween("Bologna"));
	}
    
	@Test
	public void testMostPopularDestination()
	{		
		// test for empty schedule
		assertNull(ap1.mostPopularDestination());

        // test for one most popular dest
		Flight flight1 = new Flight(new String("Tel-Aviv"),
									new String("Athens"),0,15,90,250,30);
		Flight flight2 = new Flight(new String("Tel-Aviv"),
									new String("Athens"),8,30,100,150,50);
		Flight flight3 = new Flight(new String("Bologna"),
									new String("Tel-Aviv"),0,15,90,180,30);
		Flight flight4 = new Flight(new String("Tel-Aviv"),
									new String("Bologna"),0,15,90,250,30);
		
		ap1.addFlight(flight1);
		ap1.addFlight(flight2);
		ap1.addFlight(flight3);
		ap1.addFlight(flight4);
		
		assertEquals("Athens", ap1.mostPopularDestination());

		// test for several equally popular dest
		Flight flight5 = new Flight(new String("Tel-Aviv"),
									new String("Bologna"),16,40,310,190,80);	
		Flight flight6 = new Flight(new String("Paris"),
									new String("Tel-Aviv"),14,0,390,160,130);	
		ap1.addFlight(flight5);
		ap1.addFlight(flight6);
		ap1.removeFlight(flight1);
		ap1.removeFlight(flight2);

		assertEquals("Tel-Aviv", ap1.mostPopularDestination());
	}
    
	@Test
	public void testMostExpensiveTicket()
	{
        // test for empty schedule
		assertNull(ap1.mostExpensiveTicket());
	
        // test for one most expensive
		Flight flight1 = new Flight(new String("Tel-Aviv"),
									new String("Athens"),0,15,90,250,30);
		Flight flight2 = new Flight(new String("Athens"),
									new String("Tel-Aviv"),14,55,110,250,40);
		Flight flight3 = new Flight(new String("Athens"),
									new String("Tel-Aviv"),11,25,190,180,95);
		ap1.addFlight(flight1);
		ap1.addFlight(flight2);
		ap1.addFlight(flight3);
		
		assertTrue(flight3.equals(ap1.mostExpensiveTicket()));
		// test for aliasing
		assertTrue(flight3 != ap1.mostExpensiveTicket());

        // test for several most expensive
		ap1.removeFlight(flight3);

		Flight flight4 = new Flight(new String("Los-Angeles"),
									new String("Tel-Aviv"),10,20,920,200,650);
		Flight flight5 = new Flight(new String("Reykjavik"),
									new String("Tel-Aviv"),13,10,790,180,650);
		ap1.addFlight(flight4);
		ap1.addFlight(flight5);

		assertTrue(flight4.equals(ap1.mostExpensiveTicket()));
	}

	@Test
	public void testLongestFlight()
	{
        // test for empty schedule
        assertNull(ap1.longestFlight());
        
		// todo: test for one longest

		Flight flight1 = new Flight(new String("Los-Angeles"),
									new String("Tel-Aviv"),10,20,920,200,650);
		Flight flight2 = new Flight(new String("Reykjavik"),
									new String("Tel-Aviv"),13,10,790,180,650);
		
		ap1.addFlight(flight1);
		ap1.addFlight(flight2);

		assertTrue(flight1.equals(ap1.longestFlight()));
		// test for aliasing
		assertTrue(flight1!= ap1.longestFlight());

        // todo: test for several longest
		Flight flight3 = new Flight(new String("Tel-Aviv"),
									new String("Kathmandu"),15,40,920,150,700);

		ap1.addFlight(flight3);

		assertTrue(flight1.equals(ap1.longestFlight()));
	}
}
