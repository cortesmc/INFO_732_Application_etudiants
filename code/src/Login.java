package com.tp.c;

import java.sql.*;
import java.util.Scanner;
public class Login {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Nom d'utilisateur : ");
        String username = scanner.next();
        System.out.print("Mot de passe : ");
        String password = scanner.next();

        if (validateLogin(username, password)) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
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
