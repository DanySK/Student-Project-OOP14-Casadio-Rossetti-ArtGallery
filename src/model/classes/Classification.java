package model.classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import model.interfaces.IClassification;
import model.interfaces.IExhibit;

/**
 * Classification implementation.
 * 
 * @author Sofia Rosetti
 *
 */
public class Classification implements IClassification {

	private Map<IExhibit, Double> map;
	private final List<Entry<IExhibit, Double>> list;
	
	public Classification() {
		this.map = new HashMap<IExhibit, Double>();
		this.list = new ArrayList<Entry<IExhibit, Double>>();
	}
	
	@Override
	public void sortMap(final Map<IExhibit, Double> incomes) {
		this.map = incomes;
		final Set<Entry<IExhibit, Double>> set = this.map.entrySet();
		final Iterator<Entry<IExhibit, Double>> it = set.iterator();
		while (it.hasNext()) {
			this.list.add(it.next());			
		}
		this.list.sort(new EntryComparator());
	}
	
	@Override
	public List<Entry<IExhibit, Double>> getList() {
		return this.list;
	}
	
	/**
	 * This class implements a new Comparator for the Entry<IExhibit, Double>
	 * 
	 * @author Sofia Rosetti
	 *
	 */
	class EntryComparator implements Comparator <Entry<IExhibit, Double>> {
		public int compare(final Entry<IExhibit, Double> e1, final Entry<IExhibit, Double> e2) {
			return e2.getValue().compareTo(e1.getValue());
		}
	}
}
