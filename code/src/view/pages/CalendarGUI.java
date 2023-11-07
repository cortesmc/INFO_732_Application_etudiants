package view.pages;

import model.strategy_pattern.AgendaGUIStrategy;
import model.strategy_pattern.ListPattern;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;


public class CalendarGUI extends InterfaceApp {


    private Calendar calendar;
    private AgendaGUIStrategy panelCreator;
    private JPanel panel;

    public CalendarGUI() {
        super("Agenda");
        try{
            //Initialise calendar
            String filePath = "code/files/calendar.ics"; // Replace with your .ics file path
            FileInputStream fileInputStream = new FileInputStream(filePath);
            CalendarBuilder calendarBuilder = new CalendarBuilder();
            this.calendar = calendarBuilder.build(fileInputStream);
            fileInputStream.close();

            // Create panel for button
            JPanel panel1 = new JPanel();

            //Create first panel
            this.panelCreator = new ListPattern(this.calendar);
            this.panel=panelCreator.buildCalendar();
            this.add(panel,BorderLayout.CENTER);

            //Créer le bouton
            JButton btn = new JButton("Affichage standard");
            panel1.add(btn);
            btn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if (Objects.equals(btn.getText(), "Affichage standard")){
                        createUIStandard();
                        btn.setText("Affichage en liste");
                    } else {
                        createUIlist();
                        btn.setText("Affichage standard");
                    }
                }
            });
            this.add(panel1, BorderLayout.NORTH);
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }

    private void createUIStandard(){
        panel.setVisible(false);
        //this.remove(panel);

    }
    private void createUIlist() {
        panel.setVisible(false);
        panelCreator = new ListPattern(this.calendar);
        panel = panelCreator.buildCalendar();
        this.add(panel,BorderLayout.CENTER);
    }

    /*private void createCalendarView() {
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
        // Replace the URL with your iCalendar URL
        try {
            URL url = new URL("https://ade-usmb-ro.grenet.fr/jsp/custom/modules/plannings/direct_cal.jsp?data=b5cfb898a9c27be94975c12c6eb30e9233bdfae22c1b52e2cd88eb944acf5364c69e3e5921f4a6ebe36e93ea9658a08f,1&resources=2393&projectId=1&calType=ical&lastDate=2042-08-14");
            InputStream in = url.openStream();

            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(in);

            StringBuilder eventDetails = new StringBuilder();
            System.out.println(calendar.getComponentList());

            LocalDate date = LocalDate.of(currentYearMonth.getYear(), currentYearMonth.getMonthValue(), day);
            for (Iterator<net.fortuna.ical4j.model.component.CalendarComponent> i = calendar.getComponents().iterator(); i.hasNext();) {
                Component component = i.next();
                if (component.getName().equals("VEVENT")) {
                    VEvent event = (VEvent) component;

                    // Ensure that the properties are of type Property
                    Property summary = event.getProperty("SUMMARY").orElse(null);
                    Property startDate = event.getProperty("DTSTART").orElse(null);
                    Property endDate = event.getProperty("DTEND").orElse(null);

                    if (summary != null && startDate != null && endDate != null) {
                        System.out.println("Summary: " + summary.getValue());
                        System.out.println("Start Date: " + startDate.getValue());
                        System.out.println("End Date: " + endDate.getValue());
                        System.out.println("-------------");
                    }
                }
            }
            System.out.println("##################");

            eventTextArea.setText(eventDetails.toString());

        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarGUI calendarGUI = new CalendarGUI();
            calendarGUI.setVisible(true);
        });
    }
}