package edu.sc.csce747.MeetingPlanner;

import static org.junit.Assert.*;
import org.junit.Test;

public class OrganizationTest {

    @Test
    public void testRoomsExist() {
        Organization org = new Organization();
        assertEquals(5, org.getRooms().size());
    }

    @Test
    public void testEmployeesExist() {
        Organization org = new Organization();
        assertEquals(5, org.getEmployees().size());
    }

    @Test
    public void testGetRoom_valid() throws Exception {
        Organization org = new Organization();
        Room r = org.getRoom("2A01");
        assertEquals("2A01", r.getID());
    }

    @Test(expected = Exception.class)
    public void testGetRoom_invalid() throws Exception {
        Organization org = new Organization();
        org.getRoom("XXX"); // Does not exist
    }
}
