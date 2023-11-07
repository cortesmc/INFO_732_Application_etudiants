import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;

public class iCal4jTest {

    public static void main(String[] args) {
        try {
            // Replace the URL with the actual URL of your iCalendar file
            URL url = new URL("https://example.com/path/to/your/calendar.ics");
            InputStream in = url.openStream();

            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(in);

            for (Iterator<Component> i = calendar.getComponents().iterator(); i.hasNext(); ) {
                Component component = i.next();
                if (component.getName().equals("VEVENT")) {
                    VEvent event = (VEvent) component;
                    Property summary = event.getProperty("SUMMARY");
                    Property startDate = event.getProperty("DTSTART");
                    Property endDate = event.getProperty("DTEND");

                    System.out.println("Summary: " + summary.getValue());
                    System.out.println("Start Date: " + startDate.getValue());
                    System.out.println("End Date: " + endDate.getValue());
                    System.out.println("-------------");
                }
            }

        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }
}
