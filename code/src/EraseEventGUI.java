import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EraseEventGUI extends JFrame {
    private JComboBox<String> eventList;
    private JButton eraseButton;

    public EraseEventGUI() {
        setTitle("Erase Event");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        eventList = new JComboBox<>();
        eraseButton = new JButton("Erase Event");

        eventList.addItem("Event 1");
        eventList.addItem("Event 2");
        eventList.addItem("Event 3");

        eraseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eraseEvent();
            }
        });

        // Add components to the frame
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(new JLabel("Select Event to Erase:"));
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EraseEventGUI eraseEventGUI = new EraseEventGUI();
            eraseEventGUI.setVisible(true);
        });
    }
}

