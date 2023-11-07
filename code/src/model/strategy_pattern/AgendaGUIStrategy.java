package model.strategy_pattern;

import javax.swing.*;
import net.fortuna.ical4j.model.Calendar;

public interface AgendaGUIStrategy {
    JPanel buildCalendar();
}
