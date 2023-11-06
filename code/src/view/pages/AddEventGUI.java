package view.pages;
import view.components.*;

import javax.swing.*;
import java.util.ArrayList;

public class AddEventGUI extends InterfaceApp {
    private final JTextField eventTitleField = new DefaultTextField();
    private final JTextField eventDescriptionField = new DefaultTextField();
    private final JTextField eventLocationField = new DefaultTextField();
    JComboBox<String> eventList;
    private ArrayList<String> events;

    public AddEventGUI() {
        super("Gestion d'évènements");

        // TODO : Get events from db
        events = new ArrayList<>();
        // Filling the events combo box
        eventList = new JComboBox<>(events.toArray(new String[0]));

        JButton createButton = new PrimaryButton("Créer");
        JButton modifyButton = new PrimaryButton("Modifier");
        JButton deleteButton = new SecondaryButton("Supprimer");

        createButton.addActionListener(e -> createEvent());
        modifyButton.addActionListener(e -> modifyEvent());
        deleteButton.addActionListener(e -> deleteEvent());

        // Creation event panel
        JPanel creationPanel = new DefaultPanel(50);
        creationPanel.add(new TitleLabel("Créer un évènement"));
        creationPanel.add(new DefaultLabel("Titre"));
        creationPanel.add(eventTitleField);
        creationPanel.add(new DefaultLabel("Description"));
        creationPanel.add(eventDescriptionField);
        creationPanel.add(new DefaultLabel("Emplacement"));
        creationPanel.add(eventLocationField);
        creationPanel.add(createButton);
        mainPanel.add(creationPanel);

        // Modify/Delete event panel
        DefaultPanel panel = new DefaultPanel(50);
        panel.add(new TitleLabel("Modifier un évènement"));
        panel.add(new DefaultLabel("Sélectionner un évènement"));
        panel.add(eventList);
        panel.add(modifyButton);
        panel.add(deleteButton);
        mainPanel.add(panel);

        // Makes the window scrollable
        endFrameCreation();
    }

    // TODO : Finish this
    public void createEvent() {
        String title = eventTitleField.getText();
        String description = eventDescriptionField.getText();
        String location = eventLocationField.getText();

        events.add(title); 
        eventList.addItem(title); 
    }

    // TODO : Finish this
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


