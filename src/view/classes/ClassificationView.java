package view.classes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import view.interfaces.IClassificationView;
import controller.classes.ClassificationController;

/**
 * This is the view containing the classification of the most profitable exhibits.
 * @author Sofia Rosetti
 *
 */
public class ClassificationView extends JFrame implements IClassificationView {

	private static final long serialVersionUID = -3100286873694107747L;
	private static final int GUI_SIZE = 800;
	private static final int FONT_SIZE = 18;
	private final JLabel title = new JLabel("Classifica delle esposizioni");
	//private static final String[] PROPS = new String[] { "Esposizione", "Incasso (€)" };
	//private static final Object[][] INIT_DATA = new Object[][] {};
	//private final DefaultTableModel tm = new DefaultTableModel(INIT_DATA, PROPS);
	private JTable table;
	//private final JScrollPane scroll = new JScrollPane(this.table);
	private final JButton close = new JButton("CHIUDI");
	
	private ClassificationController ctrl;
	
	/**
	 * Constructor.
	 */
	public ClassificationView() {
		super("Classification View");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//this.setResizable(false);
		this.setSize(GUI_SIZE, GUI_SIZE);
		this.setLocationByPlatform(true);
		final Font font = new Font("Century SchoolBook", Font.BOLD, FONT_SIZE);
		this.title.setFont(font);
		this.close.setFont(font);
		this.setHandlers();
		//this.buildLayout();
	}
	
	@Override
	public void attachController(final ClassificationController controller) {
		this.ctrl = controller;
	}
	
	@Override
	public void createTab() {
		this.ctrl.createClassificationStructure();
		final DefaultTableModel tm = this.ctrl.uploadClassification();
		this.table = new JTable(tm);
		this.buildLayout();
	}
	
	/**
	 * This method builds the view's layout using BorderLayout.
	 */
	private void buildLayout() {		
		final SpringLayout layout = new SpringLayout();
		final JPanel spring = new JPanel(layout);
		
		final JScrollPane scroll = new JScrollPane(this.table);
		
		layout.putConstraint(SpringLayout.NORTH, this.title, 20, SpringLayout.NORTH, spring);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.title, 0, SpringLayout.HORIZONTAL_CENTER, spring);
		spring.add(this.title);
		
		layout.putConstraint(SpringLayout.NORTH, scroll, 50, SpringLayout.SOUTH, this.title);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, scroll, 0, SpringLayout.HORIZONTAL_CENTER, spring);
		spring.add(scroll);
		
		layout.putConstraint(SpringLayout.NORTH, this.close, 50, SpringLayout.SOUTH, scroll);
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, this.close, 0, SpringLayout.HORIZONTAL_CENTER, spring);
		spring.add(this.close);
		
		this.getContentPane().add(spring);
		//this.pack();
		this.setVisible(true);
	}
	
	private void setHandlers() {
		this.close.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				setVisible(false);
			}
		});
	}

}
