package model.classes;
import model.interfaces.ITicket;

/**
 * Ticket implementation.
 * @author Sofia Rosetti
 *
 */
public class Ticket implements ITicket {
	
	private final String expName;
	private final int number;
	private final double price;
	private final boolean sold;
	
	
	/**
	 * @param name
	 *            the exposition name
	 * @param number
	 *            the ticket number
	 * @param price
	 *            the ticket price            
	 * @param sold
	 *            if the ticket is already sold            
	 */
	public Ticket(final String name, final int number, final double price, final boolean sold) {
		super();
		this.expName = name;
		this.number = number;
		this.price = price;
		this.sold = sold;
	}
	
	/**
	 * This method returns the progressive number of the ticket. 
	 * 
	 * @return the ticket number
	 */
	public int getNumber() {
		return this.number;
	}
	
	/**
	 * This method returns the name of the exposition. 
	 * 
	 * @return the name of the related exposition
	 */
	public String getExpName() {
		return this.expName;
	}
	
	/**
	 * This method returns the price of the ticket. 
	 * 
	 * @return the price of the ticket
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * This method checks if the ticket is sold.
	 * 
	 * @return true, if the ticket is sold
	 */
	public boolean isSold() {
		return this.sold;
	}

}
