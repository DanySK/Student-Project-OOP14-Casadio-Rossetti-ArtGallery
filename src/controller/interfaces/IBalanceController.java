package controller.interfaces;

import javax.swing.table.DefaultTableModel;

import view.classes.BalanceView;

/**
 * This is the interface for the BalanceController.
 * @author Sofia Rosetti
 *
 */
public interface IBalanceController {
	
	/**
	 * This method adds a new BalanceView to this controller.
	 * @param v
	 * 			the new view
	 */
	void addView(BalanceView v);
	
	/**
	 * This method uploads all the exhibits for which tickets have been sold, 
	 * and the related income, putting these data into a DefaultTableModel.
	 * @return the DefaultTableModel containing the exhibits and the incomes
	 */
	DefaultTableModel uploadTable();

	/**
	 * This method calls the model method which calculates the total income 
	 * deriving from all the uploaded exhibits, at the current date.
	 * @return the total income at the current date
	 */
	double computeProfit();
	
	/**
	 * This method performs the action relative to the pressure of the search button.
	 * @param year
	 * 				the year whose income has to be displayed
	 */
	void commandSearch(final String year);
}
