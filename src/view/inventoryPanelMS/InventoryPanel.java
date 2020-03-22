package view.inventoryPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import view.classesForPanels.AddItemOrOrderPanel;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;


public class InventoryPanel extends JPanel {

	private SearchPanel searchInventory;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private GoodsInfoPanel gIPanel;
    
    private JPanel topButtonsPanel;
    private JButton addItemBtn;
    private JButton editItemBtn;
    
    private JFrame addItemFrame;
    private AddItemOrOrderPanel AddItemPanel;
    
    private Cursor cursor;
    
    public InventoryPanel() {
    	
    	columnNames = new String[] {"Goods-ID", "Goods-Name", "Quantity in warehouse"};
        table = new Table(columnNames);
        
        table.fillWithData();
        
        comboboxEl = new String[] {"Goods-ID", "Goods-Name"};
        searchInventory = new SearchPanel(comboboxEl);
        searchField = searchInventory.getSearchField();
        searchButton = searchInventory.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchInventory.getSearchCombo();
        gIPanel = new GoodsInfoPanel();
        
        topButtonsPanel = new JPanel();
        addItemBtn = new JButton("Add item");
        editItemBtn = new JButton("Edit item");
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        addItemBtn.setCursor(cursor);
        editItemBtn.setCursor(cursor);
        
        topButtonsPanel.setPreferredSize(new Dimension(50, 50));
        searchInventory.setPreferredSize(new Dimension(50, 50));
        
        FlowLayout ItemPanelLayout = new FlowLayout();
        topButtonsPanel.setLayout(ItemPanelLayout);
        topButtonsPanel.add(addItemBtn);
        topButtonsPanel.add(editItemBtn);
	    Border innerBorderItem = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderItem = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        topButtonsPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderItem, innerBorderItem));
        
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        
        add(topButtonsPanel);
        add(searchInventory);
        add(table);
        
        table.newInformationFrameIfClicked(gIPanel);
        
        addItemButtonPressed();
        
	}
    
    public void addItemButtonPressed(){
    	addItemBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
            	
            	AddItemPanel = new AddItemOrOrderPanel();
                AddItemPanel.layoutComponentsAddItem();	
            	
        	if (addItemFrame == null) {
	        	   
        		  addItemFrame = new JFrame();
	              
        		  addItemFrame.setLayout(new BorderLayout());

        		  addItemFrame.add(AddItemPanel, BorderLayout.CENTER);
        		  addItemFrame.setVisible(true);
        		  addItemFrame.setDefaultCloseOperation(addItemFrame.DISPOSE_ON_CLOSE);
        		  addItemFrame.setResizable(false);
        		  addItemFrame.pack();
	              
	              Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	              Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
	              Point newLocation = new Point(middle.x - (addItemFrame.getWidth() / 2), 
	                                            middle.y - (addItemFrame.getHeight() / 2));
	              addItemFrame.setLocation(newLocation);
	              
	              addItemFrame.addWindowListener(new java.awt.event.WindowAdapter() {
	  			    @Override
	  			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	  			    	addItemFrame = null;
	  			    }
	  			});
  		    } else {
  		    	addItemFrame.setVisible(true);
  		    }
           }
     });
   }
}
