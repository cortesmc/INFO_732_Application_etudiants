package view.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashboard extends InterfaceApp implements ActionListener {
    private JPanel mainPanel;
    private JButton viewScoresButton;
    private JButton viewExamsButton;
    private JButton calendarButton;

    public StudentDashboard() {
        super("StudentDashboard");
        setTitle("Dashboard - Concert Manager");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        viewScoresButton = new JButton("Voir les scores");
        viewExamsButton = new JButton("Voir les examens à venir");
        calendarButton = new JButton("calendar");

        viewScoresButton.addActionListener(this);
        viewExamsButton.addActionListener(this);
        calendarButton.addActionListener(this);

        mainPanel.add(new JLabel("Tableau de bord de l'élève"));
        mainPanel.add(viewScoresButton);
        mainPanel.add(viewExamsButton);
        mainPanel.add(calendarButton);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();

        if (src == viewScoresButton) {
            JOptionPane.showMessageDialog(this, "Scores : 90, 85, 78, 92, 88");
        } else if (src == viewExamsButton) {
            JOptionPane.showMessageDialog(this, "Examens à venir : Math, Science, History,Physics,Chemistry");
        } else if (src == calendarButton) {

            CalendarGUI calendarGUI = new CalendarGUI();
            calendarGUI.setVisible(true);
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentDashboard().setVisible(true);
        });
    }
}


