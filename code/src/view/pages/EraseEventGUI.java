package view.pages;

import view.components.*;

import javax.swing.*;
import java.awt.*;

public class EraseEventGUI extends InterfaceApp {
    private JComboBox<String> eventList;
    private PrimaryButton eraseButton;

    public EraseEventGUI() {
        super("Effacer une évènement");

        eventList = new JComboBox<>();
        eraseButton = new PrimaryButton("Erase Event");

        eventList.addItem("Event 1");
        eventList.addItem("Event 2");
        eventList.addItem("Event 3");

        eraseButton.addActionListener(e -> eraseEvent());

        // Add components to the frame
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new DefaultLabel("Select Event to Erase:"));
        panel.add(eventList);
        panel.add(eraseButton);

        add(panel);
    }

    public void eraseEvent() {
        String selectedEvent = (String) eventList.getSelectedItem();

            if (selectedEvent != null) {
            	eventList.removeItem(selectedEvent);
            }
        
    }
}

