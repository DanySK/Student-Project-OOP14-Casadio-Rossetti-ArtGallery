package model.interfaces;

/**
 * This interface models a ticket.
 * @author Sofia Rosetti
 *
 */
public interface ITicket {
	
	/**
	 * This method returns the progressive number of the ticket. 
	 * 
	 * @return the ticket number
	 */
	int getNumber();
	
	/**
	 * This method returns the name of the exposition. 
	 * 
	 * @return the name of the related exposition
	 */
	String getExpName();
	
	/**
	 * This method returns the price of the ticket. 
	 * 
	 * @return the price of the ticket
	 */
	double getPrice();
	
	/**
	 * This method checks if the ticket is sold.
	 * 
	 * @return true, if the ticket is sold
	 */
	boolean isSold();
	
	/**
	 * This method sets the field "sold" to true.
	 */
	void setSold();

}
