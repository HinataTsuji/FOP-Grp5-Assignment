# Calendar Application - Implementation Summary

## ✓ Features Implemented

### 1. Reminders/Notifications (1 mark)
- **Automatic notifications on startup** showing upcoming events
- **Customizable reminder times**: 15 min, 30 min, 1 hour, 2 hours, 1 day, or custom
- **Persistent storage** of reminders in CSV file
- **Reminder management menu** to set, view, and remove reminders
- **Real-time duration display** (e.g., "2 hours", "30 minutes")

**Classes:**
- `Reminder.java` - Stores reminder time in minutes
- `NotificationManager.java` - Checks and displays notifications
- Updated `MainEvent.java` - Added reminder field
- Updated `MainMenu.java` - Added reminder management menu
- Updated `CSVHandler.java` - Saves/loads reminders

### 2. Graphical User Interface - JavaFX (1 mark)
- **Complete JavaFX application** with intuitive GUI
- **Main Menu** with 5 navigation buttons
- **Event Management Panel** - Add/update/delete events
- **Calendar View Panel** - Daily and monthly views
- **Search Panel** - Search by date, date range, or title
- **Reminders Panel** - Full reminder management
- **Dialog-based navigation** with form inputs
- **TableView for data** display with formatted columns
- **Input validation** with error handling

**Key Components:**
- `CalendarAppGUI.java` - Main JavaFX application (700+ lines)
- Uses: Dialog, DatePicker, TableView, TextArea, ComboBox, Spinner
- Responsive UI with clear navigation
- Save/Exit functionality

### 3. Database/Persistence
- All data saved in `events.csv`
- Reminder settings persisted
- Auto-loads on startup
- Clean separation of UI and data layers

## 📂 Project Structure

```
CalendarApp/
├── pom.xml (Updated with JavaFX dependencies)
├── run-gui.bat (Windows launcher)
├── src/main/java/com/mycompany/calendarapp/
│   ├── CalendarAppGUI.java (NEW - JavaFX GUI)
│   ├── Reminder.java (NEW - Reminder model)
│   ├── NotificationManager.java (NEW - Notifications)
│   ├── MainEvent.java (Modified - added reminder)
│   ├── MainMenu.java (Modified - added reminder menu)
│   ├── CSVHandler.java (Modified - reminder persistence)
│   ├── EventManager.java
│   ├── RecurringEvent.java
│   ├── CalendarView.java
│   └── SearchEvent.java
└── FEATURES_IMPLEMENTATION.md (Detailed documentation)
```

## 🚀 Running the Application

### Console Version (Original):
```bash
cd CalendarApp\src\main\java
javac -encoding UTF-8 com/mycompany/calendarapp/*.java
java com.mycompany.calendarapp.MainMenu
```

### GUI Version (JavaFX):
```bash
cd CalendarApp\src\main\java
javac -encoding UTF-8 com/mycompany/calendarapp/*.java
java --add-modules javafx.controls,javafx.fxml com.mycompany.calendarapp.CalendarAppGUI
```

Or use the batch file:
```bash
run-gui.bat
```

## 📝 Features in Action

### Reminders Feature:
1. Start application → Notifications displayed automatically
2. Go to "Manage Reminders" → Set reminder for event
3. Choose preset time or enter custom minutes
4. Reminder saved and persisted in CSV

### GUI Feature:
1. Launch GUI application
2. Click buttons to navigate (no text input needed)
3. Fill dialogs for event details
4. View results in tables or text areas
5. All operations reflected immediately
6. Save on exit

## 🔍 Key Improvements

- **User-friendly** - GUI is more accessible than console
- **Non-intrusive** - Notifications show without blocking
- **Flexible** - Custom reminder times for each event
- **Persistent** - Data saved across sessions
- **Clean code** - Separation of concerns (UI, Logic, Data)
- **Well-documented** - Code comments and feature guide

## 💾 Branch Information

- **Branch:** frontend
- **Commits:** All features committed with detailed messages
- **Ready to merge** with main branch when approved

## 📊 Marks Earned

- **Reminders/Notifications:** 1 mark ✓
- **Graphical User Interface:** 1 mark ✓
- **Total:** 2 marks out of assignment marks

