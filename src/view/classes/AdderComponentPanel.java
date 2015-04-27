package view.classes;

import java.awt.*;

import javax.swing.*;

/**
 * This class adds the graphical components to a panel with GridBagLayout.
 * @author Elisa Casadio
 *
 */

public class AdderComponentPanel {
	
	/**
	 * Empty constructor.
	 */
	public AdderComponentPanel() {
	}
	
	/**
	 * Adds the component to the panel.
	 * 
	 * @param p
	 * 			the panel where must be added the JComponent.
	 * @param c
	 * 			the component that must be added.
	 * @param x
	 * 			the number of the column.
	 * @param y
	 * 			the number of the row.
	 * @param gwidth
	 * 			the number of the columns occupied by the component.
	 * @param gheight
	 * 			the number of the rows occupied by the component.
	 * @param align
	 * 			the position of the component in the cell.
	 * @param ins
	 * 			the top edge of the component.
	 * @param layout
	 * 			the layout that must be applied the association between the
	 * 			component and the GridBagConstraints.
	 */
	protected void addComponent(final JPanel p, final JComponent c, final int x,
			final int y, final int gwidth, final int gheight, final int align,
			final int ins, final GridBagLayout layout){
		
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1;
		gbc.gridwidth = gwidth;
		gbc.gridheight = gheight;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = align;
		gbc.insets.top = ins;
		layout.setConstraints(c, gbc);
		p.add(c);
	}

}
