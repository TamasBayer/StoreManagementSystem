package view.inventoryPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import view.MainFrame;
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
    private JButton deleteItemBtn;
    
    private JFrame itemFrame;
    private AddItemOrOrderPanel itemPanel;
    
    private Cursor cursor;
    
    private JTable jTable;
    
    private JTextField itemID;
    private JTextField itemName;
    
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
        deleteItemBtn = new JButton("Delete Item");
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        addItemBtn.setCursor(cursor);
        editItemBtn.setCursor(cursor);
        deleteItemBtn.setCursor(cursor);
        
        topButtonsPanel.setPreferredSize(new Dimension(50, 50));
        searchInventory.setPreferredSize(new Dimension(50, 50));
        
        FlowLayout ItemPanelLayout = new FlowLayout();
        topButtonsPanel.setLayout(ItemPanelLayout);
        topButtonsPanel.add(addItemBtn);
        topButtonsPanel.add(editItemBtn);
        topButtonsPanel.add(deleteItemBtn);
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
        
        editButtonPressed();
        
        deleteButtonPressed();
        
        
        jTable = table.getTable();
        
        
        
	}
    
    private void openItemFrame() {
    	
    	
    	itemPanel = new AddItemOrOrderPanel();
        itemPanel.layoutComponentsAddItem();	
        itemID = itemPanel.getNewItemIDField();
        itemName = itemPanel.getNewItemNameField();
        
	if (itemFrame == null) {
    	   
		  itemFrame = new JFrame();
          
		  itemFrame.setLayout(new BorderLayout());

		  itemFrame.add(itemPanel, BorderLayout.CENTER);
		  itemFrame.setVisible(true);
		  itemFrame.setDefaultCloseOperation(itemFrame.DISPOSE_ON_CLOSE);
		  itemFrame.setResizable(false);
		  itemFrame.pack();
          
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
          Point newLocation = new Point(middle.x - (itemFrame.getWidth() / 2), 
                                        middle.y - (itemFrame.getHeight() / 2));
          itemFrame.setLocation(newLocation);
          
          itemFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	itemFrame = null;
			    }
			});
	    } else {
	    	itemFrame.setVisible(true);
	    }
    }
    
    public void addItemButtonPressed(){
    	addItemBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
            	
            	openItemFrame();
           }
     });
   }
    
    private void editButtonPressed() {
    	editItemBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	int row = jTable.getSelectedRow();
            	
            	if(jTable.getSelectionModel().isSelectionEmpty() == false) {
            		openItemFrame();
                	
                	itemID.setText(table.getModel().getValueAt(row, 0).toString());
                	itemName.setText(table.getModel().getValueAt(row, 1).toString());
            	} else {
            		System.out.println("Nothing is selected");
            	}
            	
                 }
        });
    }
   
    private void deleteButtonPressed() {
    	deleteItemBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	
            	int action = JOptionPane.showConfirmDialog(InventoryPanel.this,
                        "Do you really want to delete this Item?",
                        "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
            	if(action == JOptionPane.OK_OPTION && jTable.getSelectionModel().isSelectionEmpty() == false) {
            		
            		int row = jTable.getSelectedRow();
                    
                    table.getModel().removeRow(jTable.getSelectedRow());
            	}
                
                
                 }
        });
    } 
    
}
