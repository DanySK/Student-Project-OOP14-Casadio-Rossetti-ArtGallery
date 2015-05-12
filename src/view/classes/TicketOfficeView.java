package view.classes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import view.interfaces.ITicketOfficeView;
import model.classes.Discounts;
import controller.classes.TicketOfficeController;

/**
 * This is the view where the purchase and the payments for tickets can be managed.
 * @author Sofia Rosetti
 *
 */
public class TicketOfficeView extends JFrame implements ITicketOfficeView {

	private static final long serialVersionUID = -6429600782407568259L;
	private static final int FONT_SIZE = 15;
	private static final int FONT_SIZE_TOTAL = 18;
	private static final int GUI_SIZE = 800;
	//private DefaultTableModel tm;
	private JTable table;
	private final JComboBox<String> perc = new JComboBox<String>();
	private final JLabel discount = new JLabel("Sconto: ");
	private final JLabel tickets = new JLabel("Numero biglietti: ");
	private final JLabel total = new JLabel("Totale: ");
	private final JTextField textFieldTickets = new JTextField();
	private final JLabel totalImport = new JLabel();
	private final JButton confirm = new JButton("CONFERMA");
	private final JButton close = new JButton("CHIUDI");
	//private JScrollPane scroll;
	
	private TicketOfficeController ctrl;
	
	
	/**
	 * Constructor.
	 */
	public TicketOfficeView() {
		super("Ticket Office View");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.setSize(GUI_SIZE, GUI_SIZE);  ///
		this.setLocationByPlatform(true);
		final Font font = new Font("Century SchoolBook", Font.BOLD, FONT_SIZE);
		final Font fontTotal = new Font("Century SchoolBook", Font.BOLD, FONT_SIZE_TOTAL);
		this.discount.setFont(font);
		this.tickets.setFont(font);
		this.textFieldTickets.setFont(font);
		this.total.setFont(fontTotal);
		this.totalImport.setFont(fontTotal);
		this.confirm.setFont(font);
		this.perc.setFont(font);
		this.confirm.setIcon(new ImageIcon(this.getClass().getResource("/ok_23x23.png")));
		this.close.setFont(font);	
		this.setHandlers();
		
	}
	
	@Override
	public void attachController(final TicketOfficeController controller) {
		this.ctrl = controller;
	}
	
	@Override
	public void createTab() {
		final DefaultTableModel tm = this.ctrl.uploadExhibits();
		this.table = new JTable(tm);
		this.buildLayout();
	}
	
	/**
	 * This method builds the view's layout using a combination of BorderLayout, GridBagLayout and FlowLayout.
	 */
	private void buildLayout() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
			
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final JScrollPane scroll = new JScrollPane(this.table);
		this.table.setFillsViewportHeight(true);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.textFieldTickets.setColumns(10);
		
		for (final Discounts d: Discounts.values()) {
			this.perc.addItem(d.getDescription());
		}
		
		final GridBagLayout grid = new GridBagLayout();
		final JPanel pEast = new JPanel(grid);
		final GridBagConstraints cnst = new GridBagConstraints();
		cnst.gridheight = 2;
		cnst.gridwidth = 2;
		cnst.weightx = 1;
		cnst.weighty = 1;
		cnst.gridx = 0;
		cnst.gridy = 0;
		pEast.add(this.discount, cnst);
		cnst.gridx += 2;
		pEast.add(this.perc, cnst);
		cnst.gridx = 0;
		cnst.gridy += 2;
		pEast.add(this.tickets, cnst);
		cnst.gridx += 2;
		pEast.add(this.textFieldTickets, cnst);
		cnst.gridx = 0;
		cnst.gridy += 2;
		pEast.add(this.total, cnst);
		cnst.gridx += 2;
		pEast.add(this.totalImport, cnst);
		cnst.gridx = 0;
		cnst.gridy += 2;
		
		final JPanel pSouth = new JPanel(new FlowLayout());
		pSouth.add(this.confirm);
		pSouth.add(this.close);
		
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(pEast, BorderLayout.EAST);
		panel.add(pSouth, BorderLayout.SOUTH);
		
		this.getContentPane().add(panel);
		
		this.setVisible(true);
	}
	
	private void showErrorDialog() {
		JOptionPane.showMessageDialog(this, "Seleziona una riga nella tabella", "Errore", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void updateTotal(final double newTotal) {
		this.totalImport.setText("€ " + newTotal);
	}
	
	private void setHandlers() {
		this.confirm.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					ctrl.commandConfirm((String) table.getModel().getValueAt(table.getSelectedRow(), 0), perc.getSelectedItem().toString(), textFieldTickets.getText(), (double) table.getModel().getValueAt(table.getSelectedRow(), 1));
				} catch (ArrayIndexOutOfBoundsException exc) {
					showErrorDialog();					
				}
			}
		});
		
		this.close.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					ctrl.commandClose();
				} catch (Exception e1) {
					//throw e1;
				}
				setVisible(false);
			}
		});
	}

}
