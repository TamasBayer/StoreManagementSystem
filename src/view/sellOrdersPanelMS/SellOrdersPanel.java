package view.sellOrdersPanelMS;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import view.classesForPanels.AddItemOrOrderPanel;
import view.classesForPanels.SearchPanel;
import view.classesForPanels.Table;
import view.ordersPanelMS.OrdersInfoPanel;
import view.ordersPanelMS.OrdersTable;
import view.ordersPanelMS.SearchOrders;

public class SellOrdersPanel extends JPanel{

	private SearchPanel searchSellOrders;
	private String[] comboboxEl;
	private Table table;
    private String[] columnNames;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox searchCombo;
    private TableRowSorter<DefaultTableModel> rowSorter; 
    private SellOrdersInfoPanel infoPanel;
    
    private JPanel topButtonsPanel;
    private JButton addOrderBtn;
    private JButton editOrderBtn;
    
    private JFrame addOrderFrame;
    private AddItemOrOrderPanel AddOrderPanel;
    
    private Cursor cursor;
    
    public SellOrdersPanel() {
    	
    	columnNames = new String[] {"Sell order-ID", "Company name", "Order datum"};
        table = new Table(columnNames);
        comboboxEl = new String[] {"Sell order-ID", "Company name", "Order datum"};
        searchSellOrders = new SearchPanel(comboboxEl);
        searchField = searchSellOrders.getSearchField();
        searchButton = searchSellOrders.getSearchButton();
        rowSorter = table.getRowSorter();
        searchCombo = searchSellOrders.getSearchCombo();
        
        infoPanel = new SellOrdersInfoPanel();
        
        topButtonsPanel = new JPanel();
        addOrderBtn = new JButton("Add order");
        editOrderBtn = new JButton("Edit order");
        
        cursor = new Cursor(Cursor.HAND_CURSOR);
        addOrderBtn.setCursor(cursor);
        editOrderBtn.setCursor(cursor);
        
        topButtonsPanel.setPreferredSize(new Dimension(50, 50));
        searchSellOrders.setPreferredSize(new Dimension(50, 50));
        
        FlowLayout ItemPanelLayout = new FlowLayout();
        topButtonsPanel.setLayout(ItemPanelLayout);
        topButtonsPanel.add(addOrderBtn);
        topButtonsPanel.add(editOrderBtn);
	    Border innerBorderItem = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorderItem = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        topButtonsPanel.setBorder(BorderFactory.createCompoundBorder(outerBorderItem, innerBorderItem));
          
        Border innerBorder = BorderFactory.createLineBorder(Color.GRAY);
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        setLayout(new BoxLayout (this, BoxLayout.Y_AXIS));
        
        add(topButtonsPanel);
        add(searchSellOrders);
        add(table);
        
        table.fillWithData();
        table.newInformationFrameIfClicked(infoPanel);
        
        addOrderButtonPressed();
    }
    
    public void addOrderButtonPressed(){
    	addOrderBtn.addActionListener(new ActionListener(){

            
            public void actionPerformed(ActionEvent e) {
            	
            	AddOrderPanel = new AddItemOrOrderPanel();
            	AddOrderPanel.layoutComponentsAddSellOrder();
            	
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
     });
   }
}
