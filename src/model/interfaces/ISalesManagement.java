package model.interfaces;

import java.util.Map;
import java.util.NoSuchElementException;

import model.classes.Exhibit;

/**
 * This interface models the act of selling tickets and cashing in the payments.
 * @author Sofia Rosetti
 *
 */

public interface ISalesManagement {
	
	/**
	 * This method returns the map containing the expositions and the related available tickets.
	 * 
	 * @return the map of the expositions and the related available tickets 
	 */
	Map<Exhibit, Integer> getAvailability();
	
	/**
	 * This method returns the map containing the expositions and the related incomes.
	 * 
	 * @return the map of the expositions and the related incomes
	 */
	Map<Exhibit, Double> getIncome();
	
	/**
	 * This method models the purchase of one ticket.
	 * 
	 * @param ex
	 * 			  the exposition
	 * @param percentage
	 * 			  the percentage of discount; 0 if the ticket is a full-price ticket
	 * 
	 * @param number
	 * 			  the progressive number of the ticket
	 * 
	 * @param price
	 * 			  the ticket original price
	 */
	void purchase(Exhibit ex, double percentage, int number, double price);
	
	/**
	 * Given the exposition, this method returns the number of available tickets. 
	 * 
	 * @return the available tickets
	 *  
	 * @param ex
	 *            the exposition
	 * @throws NoSuchElementException
	 * 			  if the given exposition isn't present
	 */
	int getAvailableTickets(Exhibit ex);
	
	/**
	 * Given the exposition, this method returns if it is already present in the map.  
	 * 
	 * @return true, if the exposition is present
	 * 
	 * @param ex
	 *            the exposition 
	 */
	boolean isExPresent(Exhibit ex);
	
	/**
	 * This method adds an exposition to the collection.
	 * @param ex
	 *            the exposition
	 */
	void setExposition(Exhibit ex);

}
