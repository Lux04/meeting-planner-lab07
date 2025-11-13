package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class RoomTest {

    @Test
    public void testRoomID() {
        Room r = new Room("2A01");
        assertEquals("2A01", r.getID());
    }

    @Test
    public void testAddMeeting_roomBusy() {
        Room r = new Room("2A01");
        try {
            r.addMeeting(new Meeting(5, 5, 10, 12));
            assertTrue(r.isBusy(5, 5, 10, 12));
        } catch (Exception e) {
            fail("Should not throw exception");
        }
    }

    @Test(expected = TimeConflictException.class)
    public void testAddMeeting_conflict() throws Exception {
        Room r = new Room("2A01");
        r.addMeeting(new Meeting(5, 5, 10, 12));
        r.addMeeting(new Meeting(5, 5, 11, 13)); // conflict
    }
}
