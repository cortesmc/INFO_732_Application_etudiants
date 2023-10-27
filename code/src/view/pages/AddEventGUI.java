package view.pages;
import view.components.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddEventGUI extends InterfaceApp {
    private DefaultTextField eventTitleField;
    private DefaultTextField eventDescriptionField;
    private DefaultTextField eventLocationField;
    private PrimaryButton createButton;
    private PrimaryButton modifyButton;
    private PrimaryButton deleteButton;
    JComboBox<String> eventList;
    private ArrayList<String> events;

    public AddEventGUI() {
        super("Ajout d'évènement");
        events = new ArrayList<>();

        eventTitleField = new DefaultTextField();
        eventDescriptionField = new DefaultTextField();
        eventLocationField = new DefaultTextField();
        createButton = new PrimaryButton("Create Event");
        modifyButton = new PrimaryButton("Modify Event");
        deleteButton = new PrimaryButton("Delete Event");
        eventList = new JComboBox<>();

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEvent();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyEvent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEvent();
            }
        });

        // Add components to the frame
        DefaultPanel panel = new DefaultPanel();
        panel.add(new DefaultLabel("Event Title:"));
        panel.add(eventTitleField);
        panel.add(new DefaultLabel("Event Description:"));
        panel.add(eventDescriptionField);
        panel.add(new DefaultLabel("Event Location:"));
        panel.add(eventLocationField);
        panel.add(createButton);
        panel.add(new DefaultLabel("Select Event:"));
        panel.add(eventList);
        panel.add(modifyButton);
        panel.add(deleteButton);

        add(panel);
    }

    public void createEvent() {
        String title = eventTitleField.getText();
        String description = eventDescriptionField.getText();
        String location = eventLocationField.getText();

        events.add(title); 
        eventList.addItem(title); 
    }

    public void modifyEvent() {
        String selectedEvent = (String) eventList.getSelectedItem();

        if (selectedEvent != null) {
            
            String newTitle = eventTitleField.getText();
            String newDescription = eventDescriptionField.getText();
            String newLocation = eventLocationField.getText();

            
            int selectedIndex = eventList.getSelectedIndex();
            events.set(selectedIndex, newTitle); 
            eventList.removeItemAt(selectedIndex); 
            eventList.insertItemAt(newTitle, selectedIndex); 
        }
    }

    public void deleteEvent() {
        String selectedEvent = (String) eventList.getSelectedItem();
        if (selectedEvent != null) {
            int selectedIndex = eventList.getSelectedIndex();
            events.remove(selectedIndex); 
            eventList.removeItemAt(selectedIndex); 
        }
    }
}


