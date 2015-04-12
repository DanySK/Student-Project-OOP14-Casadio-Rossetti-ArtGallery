package model.classes;

import java.util.*;

import model.interfaces.ISalesManagement;

/**
 * Sales management implementation.
 * @author Sofia Rosetti
 *
 */

public class SalesManagement implements ISalesManagement {
	
	private final Map<Exhibit, Integer> availability;
	private final Map<Exhibit, Double> income;
	private static final int TICKETS = 150;
	
	/**
	 * Constructor.            
	 */
	public SalesManagement() {
		super();
		this.availability = new HashMap<>();
		this.income = new HashMap<>();
	}
	
	@Override
	public Map<Exhibit, Integer> getAvailability() {
		return this.availability;
	}
	
	@Override
	public Map<Exhibit, Double> getIncome() {
		return this.income;
	}
	
	@Override
	public void purchase(final Exhibit ex, final double percentage, final int number, final double price) {
		double tprice;
		if (percentage == 0) {
			final Ticket ticket = new Ticket(ex.getTitleExhibit(), number, price);
			tprice = ticket.getPrice();
			ticket.setSold();
		} else {
			final ReducedTicket ticket = new ReducedTicket(ex.getTitleExhibit(), number, price, percentage);
			tprice = ticket.getReducedPrice();
			ticket.setSold();
		}
		this.availability.put(ex, this.availability.get(ex) - 1);
		this.income.put(ex, this.income.get(ex) + tprice);
		
	}
	
	@Override
	public int getAvailableTickets(final Exhibit ex) {
		if (this.isExPresent(ex)) {
			return this.availability.get(ex);
		} else {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public boolean isExPresent(final Exhibit ex) {
		return this.availability.containsKey(ex);
	}
	
	@Override
	public void setExposition(final Exhibit ex) {
		this.availability.put(ex, SalesManagement.TICKETS);
	}
	
	@Override
	public String toString() {
		return "Ticket availability: " + this.availability.toString();
	}

}
