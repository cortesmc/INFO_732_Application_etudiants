package view.pages;

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
            URL url = new URL("https://ade-usmb-ro.grenet.fr/jsp/custom/modules/plannings/direct_cal.jsp?data=b5cfb898a9c27be94975c12c6eb30e9233bdfae22c1b52e2cd88eb944acf5364c69e3e5921f4a6ebe36e93ea9658a08f,1&resources=2393&projectId=1&calType=ical&lastDate=2042-08-14");
            InputStream in = url.openStream();

            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(in);

            for (Iterator i = calendar.getComponents().iterator(); i.hasNext();) {
                Component component = (Component) i.next();
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

            System.out.println();

        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }
}