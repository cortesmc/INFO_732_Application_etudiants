package view.pages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ConnexionPage extends InterfaceApp implements ActionListener {
    private JPanel mainPanel;
    protected JTextField loginInput;
    protected JButton connectButton;
    protected JLabel errorLabel;
    protected JButton backButton;
    protected JPasswordField passwordInput;

    public ConnexionPage() {
        super("Concert Manager - Connexion");
        setContentPane(mainPanel);
        backButton.addActionListener(this);
        connectButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object src = event.getSource();

        if(src == connectButton) {
            String login = loginInput.getText().toLowerCase();
            String password = passwordInput.getText().toLowerCase();

            // Validates input
            if (login.isEmpty()) {
                errorLabel.setText("Veuillez renseignez votre login");
                return;
            }
            // Validates input
            if (password.isEmpty()) {
                errorLabel.setText("Veuillez renseignez votre mot de passe");
                return;
            }

            // Check if user exists in db
            // TODO : Pass the user to the new page, handle UnknownUserException, WrongPasswordException
//            if(validateLogin(login, password)) {
                new StudentDashboard();
                dispose();
//            }
        } else if(src == backButton) {
            new ConnexionPage();
            dispose();
        }
    }

    public static boolean validateLogin(String username, String password) {
        String jdbcURL = "jdbc:mysql://localhost:3306/votre_base_de_donnees";
        String dbUser = "votre_utilisateur";
        String dbPassword = "votre_mot_de_passe";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet result = statement.executeQuery()) {
                    return result.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
