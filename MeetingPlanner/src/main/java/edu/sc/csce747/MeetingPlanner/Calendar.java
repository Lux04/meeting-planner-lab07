package edu.sc.csce747.MeetingPlanner;

import java.util.ArrayList;

public class Calendar {
    // Indexed by Month, Day, Meeting list
    private ArrayList<ArrayList<ArrayList<Meeting>>> occupied;

    /**
     * Default constructor, builds a calendar and initializes each day
     * to an empty list.
     */
    public Calendar() {

        occupied = new ArrayList<ArrayList<ArrayList<Meeting>>>();

        // months 0–12 (0 unused, 1–12 valid)
        for (int i = 0; i <= 12; i++) {
            occupied.add(new ArrayList<ArrayList<Meeting>>());
            for (int j = 0; j <= 31; j++) {
                occupied.get(i).add(new ArrayList<Meeting>());
            }
        }

        // invalid days → add dummy meetings
        occupied.get(2).get(29).add(new Meeting(2, 29, "Day does not exist"));
        occupied.get(2).get(30).add(new Meeting(2, 30, "Day does not exist"));
        occupied.get(2).get(31).add(new Meeting(2, 31, "Day does not exist"));
        occupied.get(4).get(31).add(new Meeting(4, 31, "Day does not exist"));
        occupied.get(6).get(31).add(new Meeting(6, 31, "Day does not exist"));
        occupied.get(9).get(31).add(new Meeting(9, 31, "Day does not exist"));
        occupied.get(11).get(31).add(new Meeting(11, 31, "Day does not exist"));
    }

    /**
     * Used to check whether a meeting is scheduled during a particular timeframe.
     */
    public boolean isBusy(int month, int day, int start, int end) throws TimeConflictException {

        checkTimes(month, day, start, end);

        boolean busy = false;

        for (Meeting toCheck : occupied.get(month).get(day)) {

            // skip fake invalid dates only
            if ("Day does not exist".equals(toCheck.getDescription()))
                continue;

            // overlap: start < end2 && end > start2
            if (start < toCheck.getEndTime() && end > toCheck.getStartTime()) {
                busy = true;
                break;
            }
        }
        return busy;
    }

    /**
     * Basic error checking on numbers.
     */
    public static void checkTimes(int mMonth, int mDay, int mStart, int mEnd) throws TimeConflictException {

        if (mMonth < 1 || mMonth > 12)
            throw new TimeConflictException("Month does not exist.");

        if (mDay < 1 || mDay > 31)
            throw new TimeConflictException("Day does not exist.");

        // invalid days by month
        if (mMonth == 2 && mDay > 28)
            throw new TimeConflictException("Invalid date for February.");

        if ((mMonth == 4 || mMonth == 6 || mMonth == 9 || mMonth == 11) && mDay > 30)
            throw new TimeConflictException("Invalid date for this month.");

        // invalid time
        if (mStart < 0 || mStart > 23)
            throw new TimeConflictException("Illegal hour.");

        if (mEnd < 0 || mEnd > 23)
            throw new TimeConflictException("Illegal hour.");

        if (mStart >= mEnd)
            throw new TimeConflictException("Meeting starts before it ends.");
    }

    /**
     * Used to add a meeting to a calendar
     */
    public void addMeeting(Meeting toAdd) throws TimeConflictException {

        int mDay = toAdd.getDay();
        int mMonth = toAdd.getMonth();
        int mStart = toAdd.getStartTime();
        int mEnd = toAdd.getEndTime();

        checkTimes(mMonth, mDay, mStart, mEnd);

        ArrayList<Meeting> thatDay = occupied.get(mMonth).get(mDay);
        boolean booked = false;
        Meeting conflict = null;

        for (Meeting toCheck : thatDay) {

            // skip “fake” invalid-day entries only
            if ("Day does not exist".equals(toCheck.getDescription()))
                continue;

            // overlap
            if (mStart < toCheck.getEndTime() && mEnd > toCheck.getStartTime()) {
                booked = true;
                conflict = toCheck;
                break;
            }
        }

        if (booked) {
            throw new TimeConflictException(
                    "Overlap with another item - " + conflict.getDescription()
                            + " - scheduled from " + conflict.getStartTime()
                            + " and " + conflict.getEndTime());
        }

        occupied.get(mMonth).get(mDay).add(toAdd);
    }

    public void clearSchedule(int month, int day) {
        occupied.get(month).set(day, new ArrayList<Meeting>());
    }

    public String printAgenda(int month) {
        String agenda = "Agenda for " + month + ":\n";
        for (ArrayList<Meeting> toPrint : occupied.get(month)) {
            for (Meeting meeting : toPrint) {
                agenda = agenda + meeting.toString() + "\n";
            }
        }
        return agenda;
    }

    public String printAgenda(int month, int day) {
        String agenda = "Agenda for " + month + "/" + day + ":\n";
        for (Meeting toPrint : occupied.get(month).get(day)) {
            agenda = agenda + toPrint.toString() + "\n";
        }
        return agenda;
    }

    public Meeting getMeeting(int month, int day, int index) {
        return occupied.get(month).get(day).get(index);
    }

    public void removeMeeting(int month, int day, int index) {
        occupied.get(month).get(day).remove(index);
    }
}
