package presentationLayer;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilitiesLayer.AuthenticationServer;
import utilitiesLayer.EmailValidator;
import utilitiesLayer.FieldValidator;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LoginForm extends JFrame {
    //Authentication Server Instance
    private AuthenticationServer server = AuthenticationServer.getInstance();
    private FieldValidator validateField = new FieldValidator();
    
	// Login Form Variables
	private JPanel contentPane;
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;

	// Registration Form Variables
	private JTextField userTextField;
	private JPasswordField userPasswordField;
	private JTextField emailTextField;

	/**
	 * Create login form and add components
	 */
	public LoginForm() {
		setTitle("Login Form");
		setResizable(false);
		setBounds(100, 100, 620, 425);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create the content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Create the login title label.
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLogin.setBounds(270, 15, 75, 40);
		contentPane.add(lblLogin);

		// Create the username label.
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(155, 70, 65, 14);
		contentPane.add(lblUsername);

		// Create the username validation field.
		JLabel userValidation = new JLabel("");
		userValidation.setBounds(160, 125, 300, 14);
		userValidation.setForeground(new Color(236, 24, 24));
		contentPane.add(userValidation);

		// Create the username text field.
		usernameTextField = new JTextField();
		usernameTextField.addKeyListener(new KeyAdapter() {
			// When the user presses a key, the user validation field is set to empty.
			@Override
			public void keyTyped(KeyEvent e) {
				userValidation.setText("");
			}
		});
		usernameTextField.setBounds(155, 90, 310, 30);
		usernameTextField.setColumns(10);
		contentPane.add(usernameTextField);

		// Create the password label.
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(155, 145, 70, 14);
		contentPane.add(lblPassword);

		// Create the password validation field.
		JLabel passValidation = new JLabel("");
		passValidation.setForeground(new Color(236, 24, 24));
		passValidation.setBounds(160, 200, 300, 14);
		contentPane.add(passValidation);

		// Create the password text field.
		passwordTextField = new JPasswordField();
		passwordTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!passValidation.getText().isEmpty()) {
					passValidation.setText("");
				}
			}
		});
		passwordTextField.setBounds(155, 165, 310, 30);

		contentPane.add(passwordTextField);

		// Create the login button.
		JButton btnLogin = new JButton("CONTINUE");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(Color.DARK_GRAY);
		btnLogin.setBorderPainted(false);
		btnLogin.setFocusPainted(false);
		btnLogin.setBounds(210, 230, 200, 40);
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(new Color(100, 100, 100));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(Color.DARK_GRAY);
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (server.username(usernameTextField) && passwordTextField.getPassword().length > 0) {
					try {
						if (server.login(usernameTextField, passwordTextField)) { // If the login is successful, the login form is disposed and the main form is created.
							dispose();
							MainUI.getInstance();
						} else {
							userValidation.setText("Incorrect username or password!");
						}
					} catch (SQLException exception) {
						exception.printStackTrace();
					}
				} else {
				    validateField.login(usernameTextField, passwordTextField, userValidation, passValidation);
				}
			}
		});
		contentPane.add(btnLogin);

		// Create the register button.
		JLabel registerRedirect = new JLabel("Don't have an account? Register here!");
		registerRedirect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		registerRedirect.setBounds(205, 275, 220, 25);
		registerRedirect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// When the user clicks on the register button, the login form is disposed and the register form is shown.
				dispose();
				RegistrationForm();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel lbl = (JLabel) e.getComponent();
				lbl.setForeground(new Color(255, 127, 80));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel lbl = (JLabel) e.getComponent();
				lbl.setForeground(new Color(0, 0, 0));
			}
		});
		contentPane.add(registerRedirect);

		// Separators for the username and password fields.
		JSeparator userSeparator = new JSeparator();
		userSeparator.setForeground(Color.DARK_GRAY);
		userSeparator.setBackground(Color.DARK_GRAY);
		userSeparator.setBounds(155, 119, 310, 2);
		contentPane.add(userSeparator);

		JSeparator passSeperator = new JSeparator();
		passSeperator.setForeground(Color.DARK_GRAY);
		passSeperator.setBackground(Color.DARK_GRAY);
		passSeperator.setBounds(155, 194, 310, 2);
		contentPane.add(passSeperator);
	}

	/**
	 * Create registration frame and add components.
	 * 
	 * @return void
	 */
	public void RegistrationForm() {
		setResizable(false);
		setBounds(100, 100, 620, 425);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Create the register title label.
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRegister.setBounds(250, 15, 115, 40);
		contentPane.add(lblRegister);

		// Create the username label.
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(155, 70, 65, 14);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblUsername);

		// Create the username validation field.
		JLabel userValidation = new JLabel("");
		userValidation.setForeground(new Color(236, 24, 24));
		userValidation.setBounds(160, 125, 300, 14);
		contentPane.add(userValidation);

		// Create the email validation field.
		userTextField = new JTextField();
		userTextField.setBounds(155, 90, 310, 30);
		userTextField.setColumns(10);
		userTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!userTextField.getText().isEmpty() && !userValidation.getText().isEmpty()) {
					userValidation.setText("");
				}
			}
		});
		contentPane.add(userTextField);

		// Create the email label.
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setBounds(155, 145, 45, 14);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblEmail);

		// Create the email validation field.
		JLabel emailValidation = new JLabel("");
		emailValidation.setForeground(new Color(236, 24, 24));
		emailValidation.setBounds(160, 200, 300, 14);
		contentPane.add(emailValidation);

		// Create the email field.
		emailTextField = new JTextField();
		emailTextField.setBounds(155, 165, 310, 30);
		emailTextField.setColumns(10);
		emailTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!emailTextField.getText().isEmpty() && !emailValidation.getText().isEmpty()) {
					emailValidation.setText("");
				}
			}
		});
		contentPane.add(emailTextField);

		// Create the password label.
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(155, 220, 70, 14);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane.add(lblPassword);

		// Create the password validation field.
		JLabel passValidation = new JLabel("");
		passValidation.setForeground(new Color(236, 24, 24));
		passValidation.setBounds(160, 275, 300, 14);
		contentPane.add(passValidation);

		// Create the password field.
		userPasswordField = new JPasswordField();
		userPasswordField.setBounds(155, 240, 310, 30);
		userPasswordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (userPasswordField.getPassword().length > 0 && !passValidation.getText().isEmpty()) {
					passValidation.setText("");
				}
			}
		});
		contentPane.add(userPasswordField);

		// Separators for the username, email and password fields.
		JSeparator userSeparator = new JSeparator();
		userSeparator.setForeground(Color.DARK_GRAY);
		userSeparator.setBackground(Color.DARK_GRAY);
		userSeparator.setBounds(155, 119, 310, 2);
		contentPane.add(userSeparator);

		JSeparator emailSeparator = new JSeparator();
		emailSeparator.setForeground(Color.DARK_GRAY);
		emailSeparator.setBackground(Color.DARK_GRAY);
		emailSeparator.setBounds(155, 194, 310, 2);
		contentPane.add(emailSeparator);

		JSeparator passSeparator = new JSeparator();
		passSeparator.setForeground(Color.DARK_GRAY);
		passSeparator.setBackground(Color.DARK_GRAY);
		passSeparator.setBounds(155, 269, 310, 2);
		contentPane.add(passSeparator);

		// Create the register button.
		JButton btnRegister = new JButton("CONTINUE");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setBackground(Color.DARK_GRAY);
		btnRegister.setBorderPainted(false);
		btnRegister.setFocusPainted(false);
		btnRegister.setBounds(210, 300, 200, 40);
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!server.username(userTextField)
						&& !server.email(emailTextField)
						&& EmailValidator.isValid(emailTextField.getText())) {
					try {
					    server.register(userTextField, userPasswordField,
								emailTextField);
						dispose();
						launch();
					} catch (SQLException exception) {
						exception.printStackTrace();
					}
				} else {
					// DB query for username.
					if (server.username(userTextField)) {
						userValidation.setText("Username already exists.");
					}
					// DB query for email.
					if (server.email(emailTextField)) {
						emailValidation.setText("Email already exists.");
					}
					// Validate email address.
					if (!EmailValidator.isValid(emailTextField.getText())) {
						emailValidation.setText("Invalid email address.");
					}
					// Check for empty register fields.
					validateField.register(userTextField, userPasswordField, emailTextField, userValidation,
							passValidation, emailValidation);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(new Color(100, 100, 100));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JButton btn = (JButton) e.getComponent();
				btn.setBackground(Color.DARK_GRAY);
			}
		});

		contentPane.add(btnRegister);

		// Redirect label to the login form.
		JLabel loginRedirect = new JLabel("Already have an account? Sign-in here!");
		loginRedirect.setBounds(205, 345, 230, 25);
		loginRedirect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loginRedirect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginForm login = new LoginForm();
				login.setLocationRelativeTo(null);
				login.setVisible(true);
				dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel lbl = (JLabel) e.getComponent();
				lbl.setForeground(new Color(255, 127, 80));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				JLabel lbl = (JLabel) e.getComponent();
				lbl.setForeground(new Color(0, 0, 0));
			}
		});
		contentPane.add(loginRedirect);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Launch the login application.
	 */
	public void launch() {
		/**
		 * Set the look and feel of the application to the system look and feel.
		 */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm loginFrame = new LoginForm();
					loginFrame.setLocationRelativeTo(null);
					loginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
