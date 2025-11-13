package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class MeetingTest {

    @Test
    public void testCreateMeeting_defaultDayBlock() {
        Meeting m = new Meeting(4, 15);
        assertEquals(4, m.getMonth());
        assertEquals(15, m.getDay());
        assertEquals(0, m.getStartTime());
        assertEquals(23, m.getEndTime());
    }

    @Test
    public void testDescriptionConstructor() {
        Meeting m = new Meeting(5, 20, "Holiday");
        assertEquals("Holiday", m.getDescription());
    }

    @Test
    public void testTimeConstructor() {
        Meeting m = new Meeting(6, 10, 9, 11);
        assertEquals(9, m.getStartTime());
        assertEquals(11, m.getEndTime());
    }
}
