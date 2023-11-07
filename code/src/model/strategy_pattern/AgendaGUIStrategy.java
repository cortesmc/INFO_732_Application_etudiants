package model.strategy_pattern;

import javax.swing.*;
import java.util.Calendar;

public interface AgendaGUIStrategy {
    public JPanel buildCalendar(Calendar calendar); // interface method (does not have a body)

    JPanel buildCalendar(net.fortuna.ical4j.model.Calendar calendar);
}
