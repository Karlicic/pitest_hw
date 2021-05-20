package pitest;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ConferenceTest {

    public static double TICKET_PRICE = 8.67;
    public static double AFFILIATE_DISCOUNT = 0.1;
    public static double FACULTY_EMPLOYEE_DISCOUNT = 0.7;
    public Conference conference = new Conference(2);

    @Test
    public void calculatePriceForAffiliate(){
        Person person = new Person("Mike", "Smith", Role.AFFILIATE, 35);
        person.setAge(38);
        person.setSurname("Sam");
        conference.addAttendeeToConference(person);
        double price = conference.calculateTotalPricePaid();
        assertEquals((TICKET_PRICE*(1-AFFILIATE_DISCOUNT)), price, 0);
    }

    @Test
    public void calculatePriceForFacultyEmployee(){
        Person person = new Person("Charlie", "Cumming", Role.FACULTY_EMPLOYEE, 53);
        conference.addAttendeeToConference(person);
        double price = conference.calculateTotalPricePaid();
        assertEquals((TICKET_PRICE*(1-FACULTY_EMPLOYEE_DISCOUNT)), price, 0);
    }

    @Test
    public void calculatePriceForOthers(){
        Person person = new Person("Michel", "Obama", Role.OTHER, 20);
        conference.addAttendeeToConference(person);
        double price = conference.calculateTotalPricePaid();
        assertEquals(TICKET_PRICE, price, 0);
    }

    @Test
    public void calculatePriceForStudent(){
        Person person= new Person("Emma", "Watson", Role.STUDENT, 21);
        conference.addAttendeeToConference(person);
        double price = conference.calculateTotalPricePaid();
        assertEquals(0, price, 0);
    }

    @Test
    public void calculatePriceForOrganizer(){
        Person person= new Person("John", "Richards", Role.ORGANIZER, 30);
        conference.addAttendeeToConference(person);
        double price = conference.calculateTotalPricePaid();
        assertEquals(0, price, 0);
    }

    @Test
    public void checkCanAddAttendee(){
        Person person = new Person("Martha", "Steward", Role.AFFILIATE, 58);
        person.setRole(Role.FACULTY_EMPLOYEE);
        boolean condition = conference.addAttendeeToConference
                (person);

        assertTrue(condition);
    }

    @Test
    public void checkFilledCapacityCanDoubleCapacity(){
        conference.addAttendeeToConference(new Person("John", "Richards", Role.ORGANIZER, 30));
        conference.addAttendeeToConference(new Person("John", "Richards", Role.ORGANIZER, 30));
        assertTrue(conference.addAttendeeToConference(new Person("John", "Richards", Role.ORGANIZER, 30)));
    }

    @Test
    public void checkFilledCapacity(){
        conference.addAttendeeToConference(new Person("John", "Richards", Role.ORGANIZER, 30));
        conference.addAttendeeToConference(new Person("John", "Richards", Role.ORGANIZER, 30));
        conference.addAttendeeToConference(new Person("John", "Richards", Role.ORGANIZER, 30));
        conference.addAttendeeToConference(new Person("John", "Richards", Role.ORGANIZER, 30));
        assertFalse(conference.addAttendeeToConference(new Person("John", "Richards", Role.ORGANIZER, 30)));
    }

    @Test
    public void checkDoubleCapacitySuccess(){
        assertTrue(conference.doubleCapacity());
    }

    @Test
    public void checkDoubleCapacityFailOn10000(){

        List<Person> attendees = conference.getAttendees();
        attendees = null;
        int capacity = conference.getCapacity();
        capacity = 0;
        conference.doubleCapacity();
        assertFalse(conference.doubleCapacity());

    }







}
