package utilitiesLayer;

import javax.swing.JOptionPane;

public class ValidateSelection {

	public static boolean isValid(UserSelectionObject selections) {
		String nullSelections = "";
		if (selections.getSelectedCountry() == null) {
			nullSelections += "Country, ";
		}
		if (selections.getStartYear() == null) {
			nullSelections += "Start Year, ";
		}
		if (selections.getEndYear() == null) {
			nullSelections += "End Year, ";
		}
		if (selections.getSelectedView() == null) {
			nullSelections += "Available View, ";
		}
		if (selections.getSelectedAnalysis() == null) {
			nullSelections += "Analysis Method, ";
		}
		if (nullSelections.length() > 0) {
			nullSelections = nullSelections.substring(0, nullSelections.length() - 2);
			JOptionPane.showMessageDialog(null, "Please select " + nullSelections + "!", "Error Message", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (Integer.parseInt(selections.getStartYear()) > Integer.parseInt(selections.getEndYear())) {
			JOptionPane.showMessageDialog(null, "Please select a valid range of years!", "Error Message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
