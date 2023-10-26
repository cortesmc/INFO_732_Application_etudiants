import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class AddEventGUI extends JFrame {
    private JTextField eventTitleField;
    private JTextField eventDescriptionField;
    private JTextField eventLocationField;
    private JButton createButton;
    private JButton modifyButton;
    private JButton deleteButton;
    JComboBox<String> eventList;
    private ArrayList<String> events;

    public AddEventGUI() {
        events = new ArrayList<>();

        setTitle("Event Management");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        eventTitleField = new JTextField(20);
        eventDescriptionField = new JTextField(20);
        eventLocationField = new JTextField(20);
        createButton = new JButton("Create Event");
        modifyButton = new JButton("Modify Event");
        deleteButton = new JButton("Delete Event");
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
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Event Title:"));
        panel.add(eventTitleField);
        panel.add(new JLabel("Event Description:"));
        panel.add(eventDescriptionField);
        panel.add(new JLabel("Event Location:"));
        panel.add(eventLocationField);
        panel.add(createButton);
        panel.add(new JLabel("Select Event:"));
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddEventGUI addEventGUI = new AddEventGUI();
            addEventGUI.setVisible(true);
        });
    }

	
}


