package model.classes;
import model.interfaces.ITicket;

/**
 * Ticket implementation.
 * @author Sofia Rosetti
 *
 */
public class Ticket implements ITicket {
	
	private final String expName;
	private final double price;
	private boolean sold;
	
	
	/**
	 * Constructor.
	 * @param name
	 *            the exposition name
	 * @param newPrice
	 *            the ticket price                      
	 */
	public Ticket(final String name, final double newPrice) {
		super();
		this.expName = name;
		this.price = newPrice;
		this.sold = false;
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
		return "Ticket[exposition name=" + this.expName
				+ ", ticket price=" + this.price + ", is sold=" + this.sold + "]";
	}

}
