package view.classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import view.interfaces.IBalanceView;
import controller.classes.BalanceController;

/**
 * This view shows every exhibit of the current year with the related income.
 * Moreover it is possible to choose a previous year and see it's balance.
 * @author Sofia Rosetti
 *
 */
public class BalanceView extends JFrame implements IBalanceView {

	private static final long serialVersionUID = -1897550428533305715L;
	private static final int GUI_SIZE = 800;
	private static final int FONT_SIZE_TOTAL = 18;
	private static final String[] PROPS = new String[] { "Esposizione", "Incasso (€)" };
	//private final Object[][] tableData = new Object[][]{ {"Cubismo", 20 }, {"Michelangelo", 15} };
	private JTable table;
	//private final JScrollPane scrollPane = new JScrollPane(this.table);
	private final JButton currentTotal = new JButton("Totale: ");
	private final JLabel currentTotalImport = new JLabel("€ ");
	private final JTextField year = new JTextField();
	private final JButton search = new JButton("Cerca incasso precedente");
	private final JLabel specificImport = new JLabel("€ ");
	private final JButton close = new JButton("CHIUDI");
	
	private BalanceController ctrl;
	
	/**
	 * Constructor.
	 */
	public BalanceView() {
		super("Balance View");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//this.setResizable(false);
		this.setSize(GUI_SIZE, GUI_SIZE);
		this.setLocationByPlatform(true);
		//this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		//this.table.setRowSelectionAllowed(false);
		//this.table.setColumnSelectionAllowed(false);
		final Font font = new Font("Century SchoolBook", Font.BOLD, FONT_SIZE_TOTAL);
		this.currentTotal.setFont(font);
		this.currentTotalImport.setFont(font);
		this.search.setFont(font);
		this.specificImport.setFont(font);
		this.year.setColumns(8);
		this.year.setFont(font);
		this.close.setFont(font);
		this.setHandlers();
		
		//this.buildLayout();
		//this.pack();
	}
	
	@Override
	public void attachController(final BalanceController controller) {
		this.ctrl = controller;
	}
	
	@Override
	public void createTab() {
		final DefaultTableModel tm = this.ctrl.uploadTable();
		this.table = new JTable(tm);
		this.buildLayout();
	}
	
	/**
	 * This method builds the view's layout using SpringLayout.
	 */
	private void buildLayout() {	
		final SpringLayout layout = new SpringLayout();
		final JPanel spring = new JPanel(layout);
		
		final JScrollPane scrollPane = new JScrollPane(this.table);
		
		layout.putConstraint(SpringLayout.NORTH, scrollPane, 20, SpringLayout.NORTH, spring);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scrollPane, 0, SpringLayout.HORIZONTAL_CENTER, spring);
		spring.add(scrollPane);
		
		layout.putConstraint(SpringLayout.SOUTH, this.currentTotal, 60, SpringLayout.SOUTH, scrollPane);
		layout.putConstraint(SpringLayout.WEST, this.currentTotal, 250, SpringLayout.WEST, spring);
		spring.add(this.currentTotal);
		
		layout.putConstraint(SpringLayout.SOUTH, this.currentTotalImport, 60, SpringLayout.SOUTH, scrollPane);
		layout.putConstraint(SpringLayout.EAST, this.currentTotalImport, 120, SpringLayout.EAST, this.currentTotal);
		spring.add(this.currentTotalImport);
		
		layout.putConstraint(SpringLayout.SOUTH, this.year, 100, SpringLayout.SOUTH, this.currentTotal);
		layout.putConstraint(SpringLayout.WEST, this.year, 200, SpringLayout.WEST, spring);
		spring.add(this.year);
		
		layout.putConstraint(SpringLayout.SOUTH, this.search, 100, SpringLayout.SOUTH, this.currentTotal);
		layout.putConstraint(SpringLayout.EAST, this.search, 100, SpringLayout.EAST, this.year);
		spring.add(this.search);
		
		layout.putConstraint(SpringLayout.SOUTH, this.specificImport, 100, SpringLayout.SOUTH, this.currentTotal);
		layout.putConstraint(SpringLayout.EAST, this.specificImport, 50, SpringLayout.EAST, this.search);
		spring.add(this.specificImport);
		
		layout.putConstraint(SpringLayout.NORTH, this.close, 100, SpringLayout.NORTH, specificImport);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.close, 0, SpringLayout.HORIZONTAL_CENTER, spring);
		spring.add(this.close);
		
		spring.setBackground(Color.LIGHT_GRAY);
		
		this.getContentPane().add(spring);
		this.setVisible(true);
	}
	
	private void setHandlers() {
		this.currentTotal.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				currentTotalImport.setText("€ " + ctrl.computeProfit());
			}
		});
		
		this.search.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ctrl.commandSearch(year.getText());
			}
		});
		
		this.close.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				setVisible(false);
			}
		});
	}

}
