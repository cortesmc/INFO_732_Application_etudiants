import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Iterator;

public class CalendarGUI extends JFrame {

    private YearMonth currentYearMonth;
    private JTextArea eventTextArea;

    public CalendarGUI() {
        currentYearMonth = YearMonth.now();
        eventTextArea = new JTextArea(10, 20);
        eventTextArea.setEditable(false);
        eventTextArea.setWrapStyleWord(true);
        eventTextArea.setLineWrap(true);

        createUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calendar GUI");
        setSize(400, 400);
        setLocationRelativeTo(null);
    }

    private void createUI() {
        setLayout(new BorderLayout());

        createCalendarView();

        JScrollPane eventScrollPane = new JScrollPane(eventTextArea);
        add(eventScrollPane, BorderLayout.SOUTH);
    }

    private void createCalendarView() {
        JPanel calendarPanel = new JPanel(new GridLayout(0, 7));
        add(calendarPanel, BorderLayout.CENTER);

        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (String day : daysOfWeek) {
            calendarPanel.add(new JLabel(day, SwingConstants.CENTER));
        }

        int daysInMonth = currentYearMonth.lengthOfMonth();
        LocalDate firstDayOfMonth = LocalDate.of(currentYearMonth.getYear(), currentYearMonth.getMonthValue(), 1);
        int dayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i < dayOfWeek; i++) {
            calendarPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            int finalDay = day;  // Create a final variable for 'day'
            JButton dayButton = new JButton(Integer.toString(day));
            dayButton.addActionListener(e -> displayEventsForDay(finalDay));  // Use the final variable in the lambda expression
            calendarPanel.add(dayButton);
        }
    }

    private void displayEventsForDay(int day) {
        LocalDate selectedDate = LocalDate.of(currentYearMonth.getYear(), currentYearMonth.getMonthValue(), day);
        StringBuilder eventDetails = new StringBuilder();

        // Replace the URL with your iCalendar URL
        try {
            URL url = new URL("https://ade-usmb-ro.grenet.fr/jsp/custom/modules/plannings/direct_cal.jsp?data=b5cfb898a9c27be94975c12c6eb30e9233bdfae22c1b52e2cd88eb944acf5364c69e3e5921f4a6ebe36e93ea9658a08f,1&resources=2393&projectId=1&calType=ical&lastDate=2042-08-14");
            InputStream in = url.openStream();
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(in);

            for (Iterator<net.fortuna.ical4j.model.component.CalendarComponent> i = calendar.getComponents().iterator(); i.hasNext();) {
                Component component = i.next();
                if (component.getName().equals("VEVENT")) {
                    VEvent event = (VEvent) component;

                    Property startDate = event.getProperty("DTSTART").orElse(null);
                    java.util.Date startDateDate = startDate.getDate();
                    java.util.Calendar calendar = java.util.Calendar.getInstance();
                    calendar.setTime(startDateDate);
                    int year = calendar.get(java.util.Calendar.YEAR);
                    int month = calendar.get(java.util.Calendar.MONTH) + 1; // Month is 0-based
                    int day = calendar.get(java.util.Calendar.DAY_OF_MONTH);
                    LocalDate eventDate = LocalDate.of(year, month, day);
                    LocalDate eventDate = LocalDate.of(startDateValue.getYear(), startDateValue.getMonth(), startDateValue.getDay());

                    if (eventDate.equals(selectedDate)) {
                        Property summary = event.getProperty("SUMMARY").orElse(null);
                        Property endDate = event.getProperty("DTEND").orElse(null);

                        if (summary != null && startDate != null && endDate != null) {
                            eventDetails.append("Summary: ").append(summary.getValue()).append("\n");
                            eventDetails.append("Start Date: ").append(startDate.getValue()).append("\n");
                            eventDetails.append("End Date: ").append(endDate.getValue()).append("\n");
                            eventDetails.append("-------------\n");
                        }
                    }
                }
            }

            eventTextArea.setText(eventDetails.toString());

        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarGUI calendarGUI = new CalendarGUI();
            calendarGUI.setVisible(true);
        });
    }
}
