package view.pages;
import view.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifEventGUI extends InterfaceApp {
    private AddEventGUI addEventGUI;
    private DefaultTextField eventTitleField;
    private DefaultTextField eventDescriptionField;
    private DefaultTextField eventLocationField;
    private PrimaryButton modifyButton;
    private JComboBox<String> eventList;

    public ModifEventGUI(AddEventGUI addEventGUI) {
        super("Modifier un évenement");
        this.addEventGUI = addEventGUI;

        eventTitleField = new DefaultTextField();
        eventDescriptionField = new DefaultTextField();
        eventLocationField = new DefaultTextField();
        modifyButton = new PrimaryButton("Modify Event");
        eventList = addEventGUI.eventList;

        modifyButton.addActionListener(e -> modifyEvent());

        // Add components to the frame
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Select Event:"));
        panel.add(eventList);
        panel.add(new JLabel("Event Title:"));
        panel.add(eventTitleField);
        panel.add(new JLabel("Event Description:"));
        panel.add(eventDescriptionField);
        panel.add(new JLabel("Event Location:"));
        panel.add(eventLocationField);
        panel.add(modifyButton);

        add(panel);
    }

    public void modifyEvent() {
        String selectedEvent = (String) eventList.getSelectedItem();

        if (selectedEvent != null) {
            // R�cup�rez les nouvelles valeurs des champs
            String newTitle = eventTitleField.getText();
            String newDescription = eventDescriptionField.getText();
            String newLocation = eventLocationField.getText();

            // Mettez � jour l'�v�nement dans AddEventGUI
            addEventGUI.modifyEvent();

            // Fermez la fen�tre de modification d'�v�nement
            dispose();
        }
    }
}

