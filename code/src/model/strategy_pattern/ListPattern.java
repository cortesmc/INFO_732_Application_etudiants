package model.strategy_pattern;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class ListPattern implements AgendaGUIStrategy {

    private final Calendar calendar;

    public ListPattern(Calendar calendar) {
        this.calendar = calendar;
    }

    public JScrollPane getJpanel() {
        JScrollPane scrollPanel = new JScrollPane();
        if (calendar != null) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Calculate the preferred button width
            int preferredButtonWidth = 800; // Set the preferred width as needed

            for (Iterator it = this.calendar.getComponents().iterator(); it.hasNext(); ) {
                var component = (Component) it.next();
                if (component.getName().equals("VEVENT")) {
                    VEvent event = (VEvent) component;

                    // Ensure that the properties are of type Property
                    Property summary = event.getProperty("SUMMARY").orElse(null);
                    Property startDate = event.getProperty("DTSTART").orElse(null);
                    Property endDate = event.getProperty("DTEND").orElse(null);
                    Property location = event.getProperty("LOCATION").orElse(null);


                    if (summary != null && startDate != null && endDate != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'");

                        try {
                            Date startDateValue = dateFormat.parse(startDate.getValue());
                            Date endDateValue = dateFormat.parse(endDate.getValue());
                            SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            String eventText = "Summary: " + summary.getValue() + "\n    Start Date: " + displayFormat.format(startDateValue) + "\n   End Date: " + displayFormat.format(endDateValue) + "\n   Location: " + location.getValue();

                            JButton eventButton = new JButton(eventText);

                            // Set the preferred size for the button
                            eventButton.setPreferredSize(new Dimension(preferredButtonWidth, eventButton.getPreferredSize().height));

                            // Set the maximum size to make it fixed
                            eventButton.setMaximumSize(new Dimension(preferredButtonWidth, eventButton.getPreferredSize().height));

                            eventButton.setHorizontalAlignment(SwingConstants.LEFT);

                            eventButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JOptionPane.showMessageDialog(null, eventText, "Event Details", JOptionPane.INFORMATION_MESSAGE);
                                }
                            });

                            panel.add(eventButton);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            scrollPanel = new JScrollPane(panel);
        }
        return scrollPanel;
    }

    @Override
    public JPanel buildCalendar() {
        JPanel panel = new JPanel();
        panel.add(this.getJpanel()); // Assuming getJpanel() is a method that returns a JPanel
        return panel;
    }


    /*public static void main(String[] args) {
        JFrame frame = new JFrame("iCalendar Viewer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Calendar calendar = null;
        try {
            URL url = new URL("https://ade-usmb-ro.grenet.fr/jsp/custom/modules/plannings/direct_cal.jsp?data=b5cfb898a9c27be94975c12c6eb30e9233bdfae22c1b52e2cd88eb944acf5364c69e3e5921f4a6ebe36e93ea9658a08f,1&resources=2393&projectId=1&calType=ical&lastDate=2042-08-14");
            InputStream in = url.openStream();

            CalendarBuilder builder = new CalendarBuilder();
            calendar = builder.build(in);
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }

        ListPattern panel = new ListPattern(calendar);
        frame.add(panel.getJpanel());

        // Make the JFrame visible
        frame.setVisible(true);
    }*/
}
