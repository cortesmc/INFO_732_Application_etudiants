package view.pages;

import model.strategy_pattern.AgendaGUIStrategy;
import model.strategy_pattern.ListPattern;
import model.strategy_pattern.monthPattern;
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
            this.panelCreator = new monthPattern(this.calendar);
            this.panel=panelCreator.buildCalendar();
            this.add(panel,BorderLayout.CENTER);

            //Create button to change mode
            JButton btn = new JButton("Affichage en liste");
            panel1.add(btn);
            btn.addActionListener(e -> {
                if (Objects.equals(btn.getText(), "Affichage standard")){
                    createUIStandard();
                    btn.setText("Affichage en liste");
                } else {
                    createUIlist();
                    btn.setText("Affichage standard");
                }
            });



            this.add(panel1, BorderLayout.NORTH);
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }

    private void createUIStandard(){
        panel.setVisible(false);
        panelCreator = new monthPattern(this.calendar);
        panel = panelCreator.buildCalendar();
        this.add(panel,BorderLayout.CENTER);
    }


    private void createUIlist() {
        panel.setVisible(false);
        panelCreator = new ListPattern(this.calendar);
        panel = panelCreator.buildCalendar();
        this.add(panel,BorderLayout.CENTER);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalendarGUI calendarGUI = new CalendarGUI();
            calendarGUI.setVisible(true);
        });
    }
}