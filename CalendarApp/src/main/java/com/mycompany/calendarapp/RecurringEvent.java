package com.mycompany.calendarapp;

import java.time.LocalDateTime;  // For date and time
import java.time.LocalDate;  // For end-date handling
import java.time.temporal.ChronoUnit;  // For adding days/weeks/months
import java.util.ArrayList;  // For storing lists
import java.util.List;  // List interface

/**
 * RecurringEvent Class
 * 
 * This class extends MainEvent to support recurring/repeating events.
 * A recurring event happens multiple times at regular intervals.
 * 
 * Purpose:
 * - Represent events that repeat (daily, weekly, or monthly)
 * - Generate all occurrences of a recurring event
 * - Calculate when the next occurrence will happen
 * 
 * Examples:
 * - Daily standup meeting (recurs daily, 20 times)
 * - Weekly team meeting (recurs weekly, 10 times)
 * - Monthly review (recurs monthly, 12 times)
 * 
 * Inheritance:
 * - Extends MainEvent, so it inherits all MainEvent features
 * - Adds recurrence-specific functionality
 */
public class RecurringEvent extends MainEvent {

    // Recurrence-specific fields
    private String recurrenceType;  // Type of recurrence: "DAILY", "WEEKLY", or "MONTHLY"
    private int occurrences;  // How many times the event repeats
    private LocalDate recurrenceEndDate; // Optional end date for recurrence (null if using occurrences)
    private int interval = 1; // Interval between occurrences (1 = every unit, 2 = every 2 units, etc.)

    /**
     * Constructor - Creates a new recurring event
     * 
     * @param eventId Unique identifier
     * @param title Event name
     * @param description Event details
     * @param startDateTime When the first occurrence starts
     * @param endDateTime When the first occurrence ends
     * @param recurrenceType How often it repeats: "DAILY", "WEEKLY", "MONTHLY"
     * @param occurrences How many times total the event repeats
     */
    public RecurringEvent(int eventId, String title, String description,
                          LocalDateTime startDateTime, LocalDateTime endDateTime,
                          String recurrenceType, int occurrences) {
        // Call the parent class (MainEvent) constructor
        super(eventId, title, description, startDateTime, endDateTime);
        this.recurrenceType = recurrenceType.toUpperCase();  // Store in uppercase for consistency
        this.occurrences = occurrences;
        this.recurrenceEndDate = null; // Not using end date in this constructor
        this.interval = 1; // default interval
    }

    /**
     * Constructor that accepts an end date instead of a fixed occurrence count
     */
    public RecurringEvent(int eventId, String title, String description,
                          LocalDateTime startDateTime, LocalDateTime endDateTime,
                          String recurrenceType, LocalDate recurrenceEndDate) {
        super(eventId, title, description, startDateTime, endDateTime);
        this.recurrenceType = recurrenceType.toUpperCase();
        this.occurrences = 0; // Use end date instead
        this.recurrenceEndDate = recurrenceEndDate;
        this.interval = 1; // default interval
    }

    /**
     * Constructor that accepts an interval and occurrences
     */
    public RecurringEvent(int eventId, String title, String description,
                          LocalDateTime startDateTime, LocalDateTime endDateTime,
                          String recurrenceType, int interval, int occurrences) {
        super(eventId, title, description, startDateTime, endDateTime);
        this.recurrenceType = recurrenceType.toUpperCase();
        this.interval = Math.max(1, interval);
        this.occurrences = occurrences;
        this.recurrenceEndDate = null;
    }

    /**
     * Constructor that accepts an interval and an end date
     */
    public RecurringEvent(int eventId, String title, String description,
                          LocalDateTime startDateTime, LocalDateTime endDateTime,
                          String recurrenceType, int interval, LocalDate recurrenceEndDate) {
        super(eventId, title, description, startDateTime, endDateTime);
        this.recurrenceType = recurrenceType.toUpperCase();
        this.interval = Math.max(1, interval);
        this.occurrences = 0;
        this.recurrenceEndDate = recurrenceEndDate;
    }



    // Getter methods
    public String getRecurrenceType() { return recurrenceType; }
    public int getOccurrences() { return occurrences; }
    public LocalDate getRecurrenceEndDate() { return recurrenceEndDate; }
    public int getInterval() { return interval; }

    // Setter methods
    public void setRecurrenceType(String recurrenceType) { 
        this.recurrenceType = recurrenceType.toUpperCase();  // Always store uppercase
    }
    public void setOccurrences(int occurrences) { this.occurrences = occurrences; }
    public void setRecurrenceEndDate(LocalDate recurrenceEndDate) { this.recurrenceEndDate = recurrenceEndDate; }
    public void setInterval(int interval) { this.interval = interval; }

    /**
     * Calculate the next occurrence after a given date/time
     * 
     * This method determines when the event repeats based on the recurrence type:
     * - DAILY: Add 1 day
     * - WEEKLY: Add 7 days (1 week)
     * - MONTHLY: Add 1 month
     * 
     * @param current The current occurrence's date/time
     * @return The date/time of the next occurrence
     */
    public LocalDateTime getNextOccurrence(LocalDateTime current) {
        switch (recurrenceType) {
            case "DAILY": 
                return current.plus(interval, ChronoUnit.DAYS);  // Add interval days
            case "WEEKLY": 
                return current.plus(interval, ChronoUnit.WEEKS);  // Add interval weeks
            case "MONTHLY": 
                return current.plus(interval, ChronoUnit.MONTHS);  // Add interval months
            default: 
                return current;  // If type is unknown, return same time
        }
    }
    
    /**
     * Generate all occurrences of this recurring event
     * 
     * This method creates individual MainEvent objects for each occurrence.
     * Useful for:
     * - Displaying all occurrences in a calendar
     * - Checking for conflicts with other events
     * - Showing expanded event lists
     * 
     * @return List of MainEvent objects, one for each occurrence
     */
    public List<MainEvent> generateOccurrences() {
        List<MainEvent> occurrencesList = new ArrayList<>();  // Create empty list
        
        // Start with the first occurrence's times
        LocalDateTime currentStart = this.getStartDateTime();
        LocalDateTime currentEnd = this.getEndDateTime();
        
        // Calculate how long the event lasts (in minutes)
        long duration = ChronoUnit.MINUTES.between(currentStart, currentEnd);
        
        // If occurrences is set (>0), generate that many occurrences
        if (occurrences > 0) {
            for (int i = 0; i < occurrences; i++) {
                MainEvent occurrence = new MainEvent(
                    this.getEventId(),
                    this.getTitle() + " (Occurrence " + (i + 1) + ")",
                    this.getDescription(),
                    currentStart,
                    currentEnd
                );
                occurrencesList.add(occurrence);
                currentStart = getNextOccurrence(currentStart);
                currentEnd = currentStart.plus(duration, ChronoUnit.MINUTES);
            }
        } else if (recurrenceEndDate != null) {
            // Generate occurrences until the start date exceeds the recurrence end date (inclusive)
            int i = 0;
            while (!currentStart.toLocalDate().isAfter(recurrenceEndDate)) {
                MainEvent occurrence = new MainEvent(
                    this.getEventId(),
                    this.getTitle() + " (Occurrence " + (i + 1) + ")",
                    this.getDescription(),
                    currentStart,
                    currentEnd
                );
                occurrencesList.add(occurrence);
                currentStart = getNextOccurrence(currentStart);
                currentEnd = currentStart.plus(duration, ChronoUnit.MINUTES);
                i++;
                // Safety: avoid infinite loops by breaking after a very large number
                if (i > 10000) break;
            }
        }

        return occurrencesList;  // Return the complete list
    }

    /**
     * toString method - String representation including recurrence info
     * 
     * Calls parent's toString() and adds recurrence details.
     * 
     * @return Formatted string with all event and recurrence information
     */
    @Override
    public String toString() {
        return super.toString() +  // Get parent class's string representation
                ", RecurringEvent{" +
                "recurrenceType='" + recurrenceType + '\'' +
                ", occurrences=" + occurrences +
                ", recurrenceEndDate=" + recurrenceEndDate +
                '}';
    }
}

