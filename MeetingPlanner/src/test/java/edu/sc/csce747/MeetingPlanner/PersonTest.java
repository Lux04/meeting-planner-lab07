package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class PersonTest {

    @Test
    public void testPersonName() {
        Person p = new Person("Greg");
        assertEquals("Greg", p.getName());
    }

    @Test
    public void testAddMeeting() {
        Person p = new Person("Greg");
        try {
            p.addMeeting(new Meeting(4, 10, 9, 11));
            assertTrue(p.isBusy(4, 10, 9, 11));
        } catch (Exception e) {
            fail("Should not throw");
        }
    }

    @Test(expected = TimeConflictException.class)
    public void testAddMeeting_conflict() throws Exception {
        Person p = new Person("Greg");
        p.addMeeting(new Meeting(4, 10, 9, 11));
        p.addMeeting(new Meeting(4, 10, 10, 12)); // conflict
    }
}
