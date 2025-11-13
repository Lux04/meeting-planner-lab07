package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalendarTest {

	@Test
	public void testAddMeeting_holiday() {
		Calendar calendar = new Calendar();
		try {
			Meeting midsommar = new Meeting(6, 26, "Midsommar");
			calendar.addMeeting(midsommar);
			assertTrue(calendar.isBusy(6, 26, 0, 23));
		} catch (Exception e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddMeeting_valid() {
		Calendar c = new Calendar();
		try {
			Meeting m = new Meeting(3, 10, 10, 12);
			c.addMeeting(m);
			assertTrue(c.isBusy(3, 10, 10, 12));
		} catch (Exception e) {
			fail("Should not throw: " + e.getMessage());
		}
	}

	@Test(expected = TimeConflictException.class)
	public void testAddMeeting_invalidMonth() throws Exception {
		Calendar c = new Calendar();
		Meeting m = new Meeting(13, 10, 10, 12);
		c.addMeeting(m);
	}

	@Test(expected = TimeConflictException.class)
	public void testAddMeeting_invalidDay() throws Exception {
		Calendar c = new Calendar();
		Meeting m = new Meeting(2, 30, 10, 12);
		c.addMeeting(m);
	}

	@Test(expected = TimeConflictException.class)
	public void testAddMeeting_wrongTimeOrder() throws Exception {
		Calendar c = new Calendar();
		Meeting m = new Meeting(3, 10, 15, 14);
		c.addMeeting(m);
	}

	@Test(expected = TimeConflictException.class)
	public void testAddMeeting_overlap() throws Exception {
		Calendar c = new Calendar();
		// First meeting
		c.addMeeting(new Meeting(5, 5, 10, 12));
		// Overlapping meeting → should throw
		c.addMeeting(new Meeting(5, 5, 11, 13));
	}
}
