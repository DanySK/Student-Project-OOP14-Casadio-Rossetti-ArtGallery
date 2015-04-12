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
	private boolean sold;
	
	
	/**
	 * Constructor.
	 * @param name
	 *            the exposition name
	 * @param number
	 *            the ticket number
	 * @param price
	 *            the ticket price                      
	 */
	public Ticket(final String name, final int number, final double price) {
		super();
		this.expName = name;
		this.number = number;
		this.price = price;
		this.sold = false;
	}
	
	@Override
	public int getNumber() {
		return this.number;
	}
	
	@Override
	public String getExpName() {
		return this.expName;
	}
	
	@Override
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public boolean isSold() {
		return this.sold;
	}
	
	@Override
	public void setSold() {
		this.sold = true;
	}
	
	@Override
	public String toString() {
		return "Ticket[exposition name=" + this.expName + ", ticket number=" + this.number 
				+ ", ticket price=" + this.price + ", is sold=" + this.sold + "]";
	}

}
