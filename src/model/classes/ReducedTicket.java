package model.classes;

/**
 * This class models a reduced ticket with a discount percentage.
 * @author Sofia Rosetti
 *
 */
public class ReducedTicket extends Ticket {
	
	private final double percentage;
	
	/**
	 * @param name
	 *            the exposition name
	 * @param number
	 *            the ticket number
	 * @param price
	 *            the ticket price            
	 * @param sold
	 *            if the ticket is already sold  
	 * @param percentage
	 *            the discount percentage                      
	 */
	public ReducedTicket(final String name, final int number, final double price, final boolean sold, final double percentage) {
		super(name, number, price, sold);
		this.percentage = percentage;
	}
	
	/**
	 * This method returns the discount percentage of the ticket. 
	 * 
	 * @return the discount percentage
	 */
	public double getDiscountPercentage() {
		return this.percentage;
	}
	
	/**
	 * This method allows to get the discount import of the ticket.
	 * 
	 * @return the discount import
	 */
	public double getDiscount() {
		return this.getPrice() * this.percentage / 100;
	}
	
	/**
	 * This method allows to get the reduced import of the ticket.
	 * 
	 * @return the reduced import
	 */
	public double getReducedPrice() {
		return this.getPrice() - this.getDiscount();
	} 

}
