package utilitiesLayer;

import javax.swing.*;

/**
 * Field validator class.
 */
public class FieldValidator {
    private AuthenticationServer server = AuthenticationServer.getInstance();
    
    public static void validateFields(JTextField field, JLabel label, String fieldName) {
        if (field.getText().isEmpty()) {
            label.setText(fieldName + " cannot be empty!");
        } else if (!field.getText().isEmpty()) {
            label.setText(fieldName + " username or password!");
        } else {
            label.setText("");
        }
    }

    /**
     * Validate the login fields.
     * 
     * @param userTextField     The username text field.
     * @param userPasswordField The password text field.
     * @param userValidation    The username validation label.
     * @param passValidation    The password validation label.
     */
    public boolean login(JTextField userTextField, JPasswordField userPasswordField,
            JLabel userValidation,
            JLabel passValidation) {
        boolean valid = true;
        if (userTextField.getText().isEmpty()) {
            validateFields(userTextField, userValidation, "Username");
            valid = false;
        } else if (!server.username(userTextField)) {
            validateFields(userTextField, userValidation, "Incorrect");
            valid = false;
        }
        if (userPasswordField.getPassword().length == 0) {
            validateFields(userPasswordField, passValidation, "Password");
            valid = false;
        }
        return valid;
    }

    /**
     * Validate the registration fields.
     * 
     * @param userTextField     The username text field.
     * @param userPasswordField The password text field.
     * @param emailTextField    The email text field.
     * @param userValidation    The username validation label.
     * @param passValidation    The password validation label.
     * @param emailValidation   The email validation label.
     */
    public void register(JTextField userTextField, JPasswordField userPasswordField,
            JTextField emailTextField, JLabel userValidation,
            JLabel passValidation, JLabel emailValidation) {
        if (userTextField.getText().isEmpty()) {
            validateFields(userTextField, userValidation, "Username");
        }
        if (userPasswordField.getPassword().length == 0) {
            validateFields(userPasswordField, passValidation, "Password");
        }
        if (emailTextField.getText().isEmpty()) {
            validateFields(emailTextField, emailValidation, "Email");
        }
    }

}
