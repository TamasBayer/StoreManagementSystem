package view.ordersPanelMS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import view.classesForPanels.AddItemOrOrderPanel;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;
import view.inventoryPanelMS.InventoryPanel;
import view.inventoryPanelMS.InventoryTable;
import view.inventoryPanelMS.SearchInventory;

public class OrdersPanel extends JPanel{

	private SearchPanel searchOrders;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private OrdersInfoPanel infoPanel;
    
    private JPanel topButtonsPanel;
    private JButton addOrderBtn;
    private JButton editOrderBtn;
    private JButton deleteOrderBtn;
    
    private JFrame addOrderFrame;
    private AddItemOrOrderPanel AddOrderPanel;
    
    private Cursor cursor;
    
    private JTable jTable;
    
    private JTextField companyNameField;
    private JTextField orderDatumField;
    
    public OrdersPanel() {
    	
    	columnNames = new String[] {"Order-ID", "Company name", "Order datum"};
        table = new Table(columnNames);
        comboboxEl = new String[] {"Order-ID", "Company name", "Order datum"};
        searchOrders = new SearchPanel(comboboxEl);
        searchField = searchOrders.getSearchField();
        searchButton = searchOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchOrders.getSearchCombo();
        
        infoPanel = new OrdersInfoPanel();
        
        topButtonsPanel = new JPanel();
        addOrderBtn = new JButton("Add order");
        editOrderBtn = new JButton("Edit order");
        deleteOrderBtn = new JButton("Delete order");
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        addOrderBtn.setCursor(cursor);
        editOrderBtn.setCursor(cursor);
        deleteOrderBtn.setCursor(cursor);
        
        topButtonsPanel.setPreferredSize(new Dimension(50, 50));
        searchOrders.setPreferredSize(new Dimension(50, 50));
        
        FlowLayout ItemPanelLayout = new FlowLayout();
        topButtonsPanel.setLayout(ItemPanelLayout);
        topButtonsPanel.add(addOrderBtn);
        topButtonsPanel.add(editOrderBtn);
        topButtonsPanel.add(deleteOrderBtn);
	    Border innerBorderItem = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderItem = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        topButtonsPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderItem, innerBorderItem));
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        
        add(topButtonsPanel);
        add(searchOrders);
        add(table);
        
        table.fillWithData();
        table.newInformationFrameIfClicked(infoPanel);
        
        jTable = table.getTable();
        
        addOrderButtonPressed();
        editOrderButtonPressed();
        deleteButtonPressed();
    }
    
    public void openOrderFrame() {
    	
    	AddOrderPanel = new AddItemOrOrderPanel();
    	AddOrderPanel.layoutComponentsAddOrder();
    	companyNameField = AddOrderPanel.getCompanyNameField();
    	orderDatumField = AddOrderPanel.getOrderDatumField();
    	
	if (addOrderFrame == null) {
    	   
		  addOrderFrame = new JFrame();
          
		  addOrderFrame.setLayout(new BorderLayout());

		  addOrderFrame.add(AddOrderPanel, BorderLayout.CENTER);
		  addOrderFrame.setVisible(true);
		  addOrderFrame.setDefaultCloseOperation(addOrderFrame.DISPOSE_ON_CLOSE);
		  addOrderFrame.setResizable(true);
		  addOrderFrame.pack();
          
          Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
          Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
          Point newLocation = new Point(middle.x - (addOrderFrame.getWidth() / 2), 
                                        middle.y - (addOrderFrame.getHeight() / 2));
          addOrderFrame.setLocation(newLocation);
          
          addOrderFrame.addWindowListener(new java.awt.event.WindowAdapter() {
			    @Override
			    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
			    	addOrderFrame = null;
			    }
			});
	    } else {
	    	addOrderFrame.setVisible(true);
	    }
    }
    
    public void addOrderButtonPressed(){
    	addOrderBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
            	openOrderFrame();
            	
           }
     });
   }
    
    public void editOrderButtonPressed(){
    	editOrderBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
            	
            	
				int row = jTable.getSelectedRow();
            	
            	if(jTable.getSelectionModel().isSelectionEmpty() == false) {
            		openOrderFrame();
                	
            		companyNameField.setText(table.getModel().getValueAt(row, 1).toString());
                	orderDatumField.setText(table.getModel().getValueAt(row, 2).toString());
            	} else {
            		System.out.println("Nothing is selected");
            	}
           }
     });
   }
    
    private void deleteButtonPressed() {
    	deleteOrderBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	
            	int action = JOptionPane.showConfirmDialog(OrdersPanel.this,
                        "Do you really want to delete this Order?",
                        "ComfirmExit", JOptionPane.OK_CANCEL_OPTION);
            	if(action == JOptionPane.OK_OPTION && jTable.getSelectionModel().isSelectionEmpty() == false) {
            		
            		int row = jTable.getSelectedRow();
                    
                    table.getModel().removeRow(jTable.getSelectedRow());
            	}
                
                 }
        });
    }
    
}
