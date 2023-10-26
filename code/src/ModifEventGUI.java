import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifEventGUI extends JFrame {
    private AddEventGUI addEventGUI;
    private JTextField eventTitleField;
    private JTextField eventDescriptionField;
    private JTextField eventLocationField;
    private JButton modifyButton;
    private JComboBox<String> eventList;

    public ModifEventGUI(AddEventGUI addEventGUI) {
        this.addEventGUI = addEventGUI;
        setTitle("Modify Event");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        eventTitleField = new JTextField(20);
        eventDescriptionField = new JTextField(20);
        eventLocationField = new JTextField(20);
        modifyButton = new JButton("Modify Event");
        eventList = addEventGUI.eventList;

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modifyEvent();
            }
        });

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
            // Récupérez les nouvelles valeurs des champs
            String newTitle = eventTitleField.getText();
            String newDescription = eventDescriptionField.getText();
            String newLocation = eventLocationField.getText();

            // Mettez à jour l'événement dans AddEventGUI
            addEventGUI.modifyEvent();

            // Fermez la fenêtre de modification d'événement
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddEventGUI addEventGUI = new AddEventGUI();
            ModifEventGUI modifEventGUI = new ModifEventGUI(addEventGUI);
            addEventGUI.setVisible(true);
            modifEventGUI.setVisible(true);
        });
    }
}

