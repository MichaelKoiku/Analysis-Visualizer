package utilitiesLayer;

import java.sql.*;
import javax.swing.*;

public class AuthenticationServer {
    //Here the Singleton Design Pattern is used
    private static AuthenticationServer instance;
    
    
    private AuthenticationServer() {}
    
    
    public static AuthenticationServer getInstance() {
        if(instance == null) {
            instance = new AuthenticationServer();
        }
        return instance;
    }
    /**
     * Connect to the PostgreSQL database and check if the user exists.
     * 
     * @param userTextField
     * @return
     */
    public boolean username(JTextField userTextField) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection db = DriverManager.getConnection("jdbc:sqlite:users.db"); // connect to SQLite database
            PreparedStatement ps = db.prepareStatement("SELECT * FROM users WHERE username = ?"); // SQL user query
            ps.setString(1, userTextField.getText()); // username from the text field
            ResultSet rs = ps.executeQuery(); // execute the query
            if (rs.next()) { // if the user query returns a result
                db.close();
                return true;
            } else { // if the user query does not return a result
                db.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Connect to the PostgreSQL database and check if the email exists.
     * 
     * @param emailTextField
     * @return
     */
    public boolean email(JTextField emailTextField) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection db = DriverManager.getConnection("jdbc:sqlite:users.db"); // connect to SQLite database
            PreparedStatement ps = db.prepareStatement("SELECT * FROM users WHERE email = ?"); // SQL email query
            ps.setString(1, emailTextField.getText()); // email from the text field
            ResultSet rs = ps.executeQuery(); // execute the query
            if (rs.next()) { // if the email query returns a result
                db.close();
                return true;
            } else { // if the email query does not return a result
                db.close();
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * Connect to the SQLite database and register a new user.
     * 
     * @param userTextField
     * @param userPasswordField
     * @param emailTextField
     * @throws SQLException
     */
    public void register(JTextField userTextField, JPasswordField userPasswordField,
            JTextField emailTextField) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection db = DriverManager.getConnection("jdbc:sqlite:users.db"); // connect to SQLite database
            PreparedStatement ps = db.prepareStatement("INSERT INTO users VALUES (?, ?, ?)"); // SQL query
            ps.setString(1, userTextField.getText()); // username from the text field
            ps.setString(2, new String(userPasswordField.getPassword())); // password from the text field
            ps.setString(3, emailTextField.getText()); // email from the text field
            ps.executeUpdate(); // update the database
            JOptionPane.showMessageDialog(null, "Registration successful!", null, JOptionPane.DEFAULT_OPTION); // show a success message dialog
            db.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Connect to the SQLite database and perform queries to login.
     * 
     * @param passwordTextField
     * @param usernameTextField
     * @throws SQLException
     */
    public boolean login(JTextField usernameTextField, JPasswordField passwordTextField) throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection db = DriverManager.getConnection("jdbc:sqlite:users.db"); // connect to SQLite database
            PreparedStatement ps = db.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?"); // SQL query
            ps.setString(1, usernameTextField.getText()); // username from the text field
            ps.setString(2, new String(passwordTextField.getPassword())); // password from the text field
            ResultSet rs = ps.executeQuery(); // execute the query
            if (rs.next()) { // if the query returns a result
                JOptionPane.showMessageDialog(null, "Login successful!", null, JOptionPane.DEFAULT_OPTION); // show a message dialog
                db.close();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Login failed!", null, JOptionPane.WARNING_MESSAGE); // show a warning message dialog
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

}
