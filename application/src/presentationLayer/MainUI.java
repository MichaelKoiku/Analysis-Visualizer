package presentationLayer;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Map;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import executionLayer.ExecutionProxy;
import resultsViewLayer.AnalysisViewsRepository;
import utilitiesLayer.Country;
import utilitiesLayer.CountryCodeRepository;
import utilitiesLayer.CountryDB;
import utilitiesLayer.UserSelectionObject;
import utilitiesLayer.ValidateCountry;
import utilitiesLayer.ValidateSelection;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI extends JFrame {
	private static MainUI instance;
	private CountryCodeRepository repo = CountryCodeRepository.getInstance();
	private AnalysisViewsRepository viewsRepo = AnalysisViewsRepository.getInstance();
	private UserSelectionObject userSelection = new UserSelectionObject();
	private CountryDB countryDB;
	private JComboBox<String> countryComboBox;
	private JComboBox<String> analysisMethods;
	private JComboBox<String> startYearComboBox;
	private JComboBox<String> endYearComboBox;
	private JComboBox<String> viewsList;
	private JComboBox<String> source;
	private JButton addView;
	private JButton removeView;
	private JButton recalculateButton;
	private ValidateCountry blacklist = new ValidateCountry();
	private JPanel north = new JPanel(); // north panel
	private JPanel east = new JPanel();; // east panel
	private JPanel south = new JPanel();; // south panel
	private JPanel west = new JPanel();; // west panel

	/**
	 * The main panel of the application
	 * Design pattern: Singleton
	 * 
	 * @return the main panel of the application
	 */
	public static MainUI getInstance() {
		if (instance == null) {
			instance = new MainUI();
		}
		return instance;
	}

	/**
	 * The constructor of the main panel
	 */
	private MainUI() {
		super("Analysis Visualizer");
		initialize();
		
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		countryComboBox = new JComboBox<String>();

		// retrieve the list of countries from the database
		countryDB = new CountryDB();
		countryComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					if (ValidateCountry.checkBlacklist((String) e.getItem())) {
						countryComboBox.setSelectedIndex(-1);
					} else {
						System.out.println("Selection: " + countryComboBox.getItemAt(countryComboBox.getSelectedIndex()));
						userSelection.setSelectedCountry(repo.getCountryCode(countryComboBox.getItemAt(countryComboBox.getSelectedIndex())));  // set the country selection
						System.out.println("Selected country: " + userSelection.getSelectedCountry()); // Print the selected country

						// enable the year selection combo boxes after a country has been selected
						startYearComboBox.setEnabled(true);
						endYearComboBox.setEnabled(true);

						startYearComboBox.removeAllItems(); // remove all items from the start year combo box
						endYearComboBox.removeAllItems(); // remove all items from the end year combo box

						// retrieve the list of years from the country database and add them to the combo boxes
						Country country = countryDB.getCountry(countryComboBox.getSelectedItem().toString());

						Vector<String> startYears = new Vector<String>();
						Vector<String> endYears = new Vector<String>();
						if (source.getSelectedItem().toString().equals("World Bank")) {
							for (int i = Integer.parseInt(country.getEndYear()); i >= Integer.parseInt(country.getStartYear()); i--) {
								startYears.add(Integer.toString(i));
								endYears.add(Integer.toString(i));
							}
						} else {
							for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= 2020; i--) {
								startYears.add(Integer.toString(i));
								endYears.add(Integer.toString(i));
							}
						}
						startYearComboBox.setModel(new DefaultComboBoxModel<String>(startYears));
						endYearComboBox.setModel(new DefaultComboBoxModel<String>(endYears));
						startYearComboBox.setSelectedIndex(-1); // Set the default value to -1 (no selection)
						endYearComboBox.setSelectedIndex(-1); // Set the default value to -1 (no selection)
					}
				}
			}
		});

		JLabel chooseFromLabel = new JLabel("From"); // Label for the start year combo box
		JLabel chooseToLabel = new JLabel("To"); // Label for the end year combo box

		startYearComboBox = new JComboBox<String>(); // Combo box for the start year
		startYearComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					userSelection.setStartYear((String) e.getItem()); // Set the start year in the user selection object
					System.out.println("Start year: " + userSelection.getStartYear()); // Print the start year
					if (endYearComboBox.getSelectedIndex() != -1) {
						analysisMethods.setEnabled(true); // Enable the analysis methods combo box
					}
				}
			}
		});

		endYearComboBox = new JComboBox<String>(); // Combo box for the end year
		endYearComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					userSelection.setEndYear((String) e.getItem()); // Set the end year in the user selection object
					System.out.println("End year: " + userSelection.getEndYear()); // Print the end year
					if (startYearComboBox.getSelectedIndex() != -1) {
						analysisMethods.setEnabled(true); // Enable the analysis methods combo box
					}
				}
			}
		});

		// Initialize the bottom panel
		recalculateButton = new JButton("Recalculate");
		recalculateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ValidateSelection.isValid(userSelection) == true) { // Validate the user selections
					west.removeAll();
					JPanel charts = new JPanel();
					ExecutionProxy proxy = ExecutionProxy.getInstance();
					proxy.execute(userSelection, charts);
					charts.setLayout(new GridLayout(2, 0)); // Set the layout of the panel to a grid layout with 2 rows and 0 columns
					getContentPane().add(charts, BorderLayout.WEST);
					revalidate();
					setVisible(true);
				} else {
					// TO DO: User selection is not valid
				}
			}
		});

		JLabel viewsLabel = new JLabel("Available Views: "); // Label for the views combo box
		Vector<String> viewsNames = new Vector<String>(); // Vector to store the views names
		viewsNames.add("Pie Chart"); // Add the pie chart view to the vector
		viewsNames.add("Line Chart"); // Add the line chart view to the vector
		viewsNames.add("Bar Chart"); // Add the bar chart view to the vector
		viewsNames.add("Scatter Chart"); // Add the scatter chart view to the vector
		viewsNames.add("Report"); // Add the report view to the vector
		viewsList = new JComboBox<String>(viewsNames); // Combo box to choose a view
		viewsList.setSelectedIndex(-1);

		addView = new JButton("+");
		addView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentlySelectedView = viewsList.getItemAt(viewsList.getSelectedIndex());
				Vector<String> analysisSupportedViews = viewsRepo.getViews(userSelection.getSelectedAnalysis());
				if (analysisSupportedViews.contains(currentlySelectedView)) {
					userSelection.addView(currentlySelectedView); // Set the selected view in the user selection object
					// enable the recalculate button
					recalculateButton.setEnabled(true);
					System.out.println("Selected view: " + userSelection.getSelectedView()); // Print the selected view
					JOptionPane.showMessageDialog(null,
							"This view has been successfully been added. Please choose another view or click \"Recalculate\" ");
				} else {
					JOptionPane.showMessageDialog(null,
							"This view is not supported for the currently selected analysis. Please select a different view");
				}
			}
		});

		removeView = new JButton("-");
		removeView.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String currentlySelectedView = viewsList.getItemAt(viewsList.getSelectedIndex());
				userSelection.removeView(currentlySelectedView);
				JOptionPane.showMessageDialog(null, "This view has been successfully been removed.");
			}
		});

		JLabel chooseAnalysisLabel = new JLabel("        Choose analysis method: ");

		analysisMethods = new JComboBox<String>(); // Combo box to choose an analysis method
		analysisMethods.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					userSelection.setSelectedAnalysis((String) e.getItem()); // Set the selected analysis method in the user selection object
					userSelection.clearViews();
					// enable the views combo box and the add/remove view button 
					viewsList.setEnabled(true);
					addView.setEnabled(true);
					removeView.setEnabled(true);
					System.out.println("Analysis selected: " + userSelection.getSelectedAnalysis()); // Print the selected analysis method
				}
			}
		});

		// Disable all the combo boxes until the user selects the source
		countryComboBox.setEnabled(false);
		startYearComboBox.setEnabled(false);
		endYearComboBox.setEnabled(false);
		viewsList.setEnabled(false);
		addView.setEnabled(false);
		removeView.setEnabled(false);
		analysisMethods.setEnabled(false);
		recalculateButton.setEnabled(false);

		// North panel stores the country label, country, start, and end year combo boxes
		north.add(chooseCountryLabel); // Add the country label to the panel
		north.add(countryComboBox); // Add the country combo box to the panel
		north.add(chooseFromLabel); // Add the start year label to the panel
		north.add(startYearComboBox); // Add the start year combo box to the panel
		north.add(chooseToLabel); // Add the end year label to the panel
		north.add(endYearComboBox); // Add the end year combo box to the panel

		// South panel stores the views combo box, add view button, remove view button, analysis method combo box and recalculate button
		south.add(viewsLabel); // Add the label to the panel
		south.add(viewsList); // Add the views combo box to the panel
		south.add(addView); // Add the add view button to the panel
		south.add(removeView); // Add the remove view button to the panel

		JLabel chooseSource = new JLabel("        Choose source: ");
		south.add(chooseSource);

		Vector<String> sourceNames = new Vector<String>();
		sourceNames.add("World Bank");
		sourceNames.add("Open Covid");
		sourceNames.sort(null);
		source = new JComboBox<String>(sourceNames);
		source.setSelectedIndex(-1);
		source.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					userSelection.setSelectedSource((String) e.getItem()); // Set the selected analysis method in the user selection object
					disableComboBoxes();
					if ((String) e.getItem() == "Open Covid") {
						repo.intializeOCRepo();
						viewsRepo.intializeOCRepo();
						populateCountries();
						populateMethods();
						resetSelection();
					} else {
						repo.intializeWBRepo();
						viewsRepo.intializeWBRepo();
						populateCountries();
						populateMethods();
						resetSelection();
					}
					System.out.println("Source selected: " + userSelection.getSelectedSource()); // Print the selected analysis method
				}
			}
		});

		south.add(source); // Add the source combo box to the panel
		south.add(chooseAnalysisLabel); // Add the label to the panel
		south.add(analysisMethods); // Add the analysis method combo box to the panel
		south.add(recalculateButton); // Add the recalculate button to the panel

		// East Panel
		JPanel east = new JPanel();

		// West Panel
		west.setLayout(new GridLayout(2, 2)); // Set the layout of the panel to a grid layout with 2 rows and 2 columns

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
		setVisible(true);
	}

	public void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1366, 768);
		setLocationRelativeTo(null);
	}
	
	public void resetSelection() {
		countryComboBox.setSelectedIndex(-1);
		startYearComboBox.setSelectedIndex(-1);
		endYearComboBox.setSelectedIndex(-1);
		viewsList.setSelectedIndex(-1);
		analysisMethods.setSelectedIndex(-1);
	}

	public void disableComboBoxes() {
		countryComboBox.setEnabled(true);
		startYearComboBox.setEnabled(false);
		endYearComboBox.setEnabled(false);
		viewsList.setEnabled(false);
		addView.setEnabled(false);
		removeView.setEnabled(false);
		analysisMethods.setEnabled(false);
		recalculateButton.setEnabled(false);
	}

	public void populateCountries() {
		Vector<String> countryNames = new Vector<String>();
		for (Map.Entry<String,String> entry : repo.getRepository().entrySet()) {
			countryNames.add(entry.getKey());
		}
		countryComboBox.setModel(new DefaultComboBoxModel<String>(countryNames));
	}

	public void populateMethods() {
		Vector<String> methodNames = new Vector<String>();
		if (source.getSelectedItem().toString().equals("Open Covid")) {
			methodNames.add("Display Covid Cases");
		} else {
			methodNames.add("Average Forest Area");
			methodNames.add("Average Government Expenditure on Education");
			methodNames.add("CO2 vs GDP");
			methodNames.add("Health care vs Mortality rate");
			methodNames.add("Health expenditure vs Hospital beds");
			methodNames.add("Total population");
			methodNames.add("GDP per capita");
			methodNames.add("Mortality");
		}
		analysisMethods.setModel(new DefaultComboBoxModel<String>(methodNames));
	}

}