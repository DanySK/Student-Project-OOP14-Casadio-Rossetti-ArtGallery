package model.classes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import model.interfaces.IExhibit;
import model.interfaces.IPurchase;
import model.interfaces.ITicket;

/**
 * Purchase implementation.
 * 
 * @author Sofia Rosetti
 *
 */
public class Purchase implements IPurchase {
	
	private final Calendar date = GregorianCalendar.getInstance();
	private final IExhibit ex;
	private final int number;
	private final ITicket ticket;
	private double total;
	
	/**
	 * Constructor.
	 * @param ex
	 *            the exhibit
	 * @param number
	 *            the number of tickets
	 * @param ticket
	 * 			  the type of the purchased ticket
	 */            
	public Purchase(final IExhibit ex, final int number, final ITicket ticket) {
		this.ex = ex;
		this.number = number;
		this.ticket = ticket;		
	}
	
	@Override
	public Calendar getDate() {
		return this.date;
	}
	
	@Override
	public IExhibit getExhibit() {
		return this.ex;
	}
	
	@Override
	public int getNumber() {
		return this.number;
	}
	
	@Override
	public ITicket getTicket() {
		return this.ticket;
	}
	
	@Override
	public double getTotal() {
		return this.total;
	}
	
	@Override
	public void setTotal() {
		this.total = this.ticket.getPrice() * this.number;
	}
	
	@Override
	public String toString() {
		return "Purchase[date of purchase=" + this.date + ", exhibit =" + this.ex 
				+ ", number of tickets =" + this.number + ", ticket =" + this.ticket + "]";
	}

}
