package com.mycompany.calendarapp;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private List<MainEvent> events = new ArrayList<>();
    private int nextEventId = 1;

    public int generateEventId() { return nextEventId++; }

    public void addEvent(MainEvent event) { events.add(event); }
    public List<MainEvent> getAllEvents() { return events; }

    public MainEvent findEventById(int id) {
        for (MainEvent e : events) {
            if (e.getEventId() == id) return e;
        }
        return null;
    }

    public boolean deleteEvent(int id) {
        MainEvent e = findEventById(id);
        if (e != null) {
            events.remove(e);
            return true;
        }
        return false;
    }

    public void setNextEventId(int id) { this.nextEventId = id; }
}
